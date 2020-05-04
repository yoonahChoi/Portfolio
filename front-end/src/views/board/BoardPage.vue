<template>
  <div class="content-wrap">
    <div class="board-wrap">
      <board-detail v-if="boardId > 0" :detail="detail" @recommend="addRecommend" @delboard="deleteBoard" @download="download"></board-detail>
      <div class="board-top" :class="$mq">
        <div class="category-wrap">
          <ul class="category-list" :class="$mq">
            <li v-for="(cate, index) in category" :key="index" @click="selectCategory(index)" :class="{selected: index === currentCategory}">{{ cate.name }}</li>
          </ul>
        </div>
        <div class="search-wrap" :class="$mq">
          <form @submit.prevent="search">
            <select class="search-select" name="search_target" v-model="selected">
              <option value="title">제목</option>
              <option value="title_content">제목+내용</option>
              <option value="content">내용</option>
              <option value="writer">작성자</option>
            </select>
            <input v-model="keyword" class="search-input" type="text" :class="$mq">
            <button class="search-btn" type="submit">검색</button>
          </form>
        </div>
      </div>
      <board-table :list="boardList" @child="getBoardId" @scroll="scrollToTop"></board-table>
      <div class="board-bottom">
          <div class="board-addition">
            <div v-if="searchMore" class="search-more" @click="fetchSearchList">계속 검색</div>
            <router-link class="write-btn" to="/board/write">글쓰기</router-link>
          </div>
          <div v-if="pageStartList.length" class="board-page">
            <span v-show="category[currentCategory].pageBlock > 0" class="page-pre" href="#" @click="changePageList('pre')">&lt;</span>
            <ul class="page-list">
              <li v-for="(item, index) in pageList" :key="index" @click="getList(index, item)">
                <span :class="{selected: selectedPage === index}">{{ (item/10) + 1 }}</span>
              </li>
            </ul>
            <span v-show="pageNext" class="page-next" href="#" @click="changePageList('next')">&gt;</span>
          </div>
      </div>
    </div>
  </div>
</template>

<script>
import { board } from '@/api/board'
import paging from '@/utils/Paging'
import Table from '@/components/board/Table'
import BoardDetail from '@/components/board/BoardDetail'
export default {
  name: 'BoardPage',
  components: {
    'board-table': Table,
    'board-detail': BoardDetail
  },
  computed: {
    pageNext () {
      return this.pageStartList.length >= 2 && this.category[this.currentCategory].pageBlock < this.pageStartList.length - 1
    }
  },
  data () {
    return {
      boardList: [],
      boardId: 0,
      category: [
        {
          id: 0,
          name: '전체',
          pageBlock: 0
        }, {
          id: 1,
          name: '질문',
          pageBlock: 0
        }, {
          id: 2,
          name: '정보',
          pageBlock: 0
        }, {
          id: 3,
          name: '잡담',
          pageBlock: 0
        }, {
          id: 4,
          name: '자료',
          pageBlock: 0
        }],
      currentCategory: 0,
      detail: '',
      pageStartList: [],
      pageList: [],
      pageSize: 5,
      selectedPage: 0,
      searchMore: false,
      searchStart: 0,
      searchStartList: [],
      selected: 'title',
      keyword: ''
    }
  },
  created () {
    this.fetchList(0)
  },
  methods: {
    selectCategory (index) {
      this.selectedPage = 0
      this.category[index].pageBlock = 0
      this.currentCategory = index
      this.searchMore = false
      this.keyword = ''
      this.fetchList(index)
    },
    getList (index, start) {
      this.selectedPage = index
      this.fetchList(this.currentCategory, start)
    },
    fetchList (index, start = 0) {
      board.fetchList(index, start)
        .then(res => {
          this.pageStartList = paging(res.data.pageStartList, this.pageSize)
          this.boardList = res.data.list
          this.changePageBlock(this.category[this.currentCategory].pageBlock)
        })
    },
    changePageBlock (index) {
      this.pageList = this.pageStartList[index]
    },
    changePageList (direction) {
      if (direction === 'pre') {
        this.changePageBlock(--this.category[this.currentCategory].pageBlock)
      } else if (direction === 'next') {
        this.changePageBlock(++this.category[this.currentCategory].pageBlock)
      }
      this.selectedPage = 0
      this.fetchList(this.currentCategory, this.pageList[0])
    },
    getBoardId (id) {
      this.fetchDetail(id)
      this.fetchList(this.currentCategory)
    },
    fetchDetail (bid) {
      this.boardId = bid
      board.fetch(bid)
        .then(res => {
          this.detail = res.data
        })
    },
    addRecommend (id, type) {
      board.recommend(id, type)
        .then(() => {
          this.fetchDetail(id)
        })
    },
    deleteBoard (bid, password) {
      const form = new FormData()
      form.append('password', password)
      board.delete(bid, form)
        .then(res => {
          if (res.status === 200) alert('삭제 완료')
          this.boardId = 0
          this.fetchList(this.currentCategory)
        })
    },
    download (id) {
      board.file(id)
    },
    search () {
      this.searchStart = 0
      this.searchMore = true
      this.fetchSearchList()
    },
    fetchSearchList () {
      board.search(this.keyword, this.selected, this.searchStart)
        .then(res => {
          this.boardList = res.data.list
          this.pageStartList = []

          if (res.data.pageStartList.length > this.searchStart) {
            this.searchStart++
          } else {
            this.searchMore = false
          }
        })
    },
    scrollToTop () {
      window.scrollTo(0, 0)
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';

.board-wrap {
  max-width: 1000px;
  margin: 80px auto;
}
.board-top {
  overflow: hidden;
  &.laptop {
    height: 60px;
  }
  &.tablet, &.mobile {
    height: 92px;
  }
}
.selected {
  background-color: $classic-blue;
  border-color: $classic-blue;
  color: #fff !important;
}
.category-list {
  &.laptop {
    float: left;
    margin: 14px 0;
  }
  &.tablet, &.mobile {
    margin-top: 10px;
  }
  li {
    display: inline-block;
    border: 1px solid $white-blue;
    box-shadow: 1px 1px 5px 1px rgba(0, 0, 0, 0.1);
    padding: 7px 10px;
    color: $classic-blue;
    margin: 0 5px;

    &:hover {
      @extend .selected
    }
  }
}
.search-wrap {
  &.laptop {
    float: right;
    margin-top: 15px;
  }
  &.tablet, &.mobile {
    margin: 10px 5px;
  }
}
@mixin search-gap {
  margin-right: 10px;
  border: 1px solid $white-blue;
}
.search-select {
  height: 30px;
  padding: 5px;
  @include search-gap;
}
.search-input {
  position: relative;
  top: -3px;
  height: 26px;
  line-height: 26px;
  padding: 1px 5px;
  @include search-gap;

  &.mobile {
    width: 130px;
  }
}
.search-btn {
  position: relative;
  top: -1px;
  background-color: #fff;
  color: $classic-blue;
  padding: 5px 10px;
  @include search-gap;
}
.board-bottom {
  margin: 20px 0;
  text-align: center;
  overflow: hidden;
}
.board-page {
  display: inline-block;
  overflow: hidden;
}
@mixin page-btn {
  border: 1px solid $white-blue;
  padding: 6px 14px;
  margin: 0 5px;

  &:hover {
    cursor: pointer;
  }
}
.page-pre {
  float: left;
  width: 10px;
  @include page-btn;
  &:hover {
    background-color: $classic-blue;
    color: #fff;
  }
}
.page-next {
  @extend .page-pre;
}
.page-list {
  float: left;
  li {
    display: inline-block;

  }
  span {
    display: block;
    color: black;
    padding: 10px 14px;
    @include page-btn;
  }
}
.board-addition {
  overflow: hidden;
}
.write-btn {
  float: right;
  border: 1px solid #eee;
  font-size: 12px;
  padding: 10px;
  color: $classic-blue;
}
.search-more {
  margin-bottom: 20px;
  border: 1px solid #eee;
  font-size: 12px;
  padding: 10px;
  color: $classic-blue;
  cursor: pointer;

  &:hover {
    border-color: $classic-blue;
  }
}
</style>
