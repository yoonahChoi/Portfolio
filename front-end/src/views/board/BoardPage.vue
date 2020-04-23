<template>
  <div class="content-wrap">
    <div class="board-wrap">
      <div class="board-top" :class="$mq">
        <div class="category-wrap">
          <ul class="category-list" :class="$mq">
            <li v-for="(cate, index) in category" :key="index" @click="selectCategory(index)" :class="{selected: index === currentCategory}">{{ cate.name }}</li>
          </ul>
        </div>
        <div class="search-wrap" :class="$mq">
          <form @submit.prevent="" action="">
            <select class="search-select" name="search_target" id="">
              <option value="title">제목</option>
              <option value="title_content">제목+내용</option>
              <option value="content">내용</option>
            </select>
            <input class="search-input" type="text" :class="$mq">
            <button class="search-btn" type="submit">검색</button>
          </form>
        </div>
      </div>
      <board-table :list="boardList"></board-table>
      <div class="board-bottom">
        <div class="board-addition">
          <a class="write-btn" href="/board/write">글쓰기</a>
        </div>
        <div class="board-page">
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
import Table from '@/components/Table'
import { board } from '@/api/board'
import paging from '@/utils/Paging'
export default {
  name: 'BoardPage',
  components: {
    'board-table': Table
  },
  computed: {
    pageNext () {
      return this.pageStartList.length >= 2 && this.category[this.currentCategory].pageBlock < this.pageStartList.length - 1
    }
  },
  data () {
    return {
      boardList: [],
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
      pageStartList: [],
      pageList: [],
      selectedPage: 0,
      pageSize: 5
    }
  },
  created () {
    this.fetchData(0)
  },
  methods: {
    selectCategory (index) {
      this.selectedPage = 0
      this.category[index].pageBlock = 0
      this.currentCategory = index
      this.fetchData(index)
    },
    getList (index, start) {
      this.selectedPage = index
      this.fetchData(this.currentCategory, start)
    },
    fetchData (index, start = 0) {
      board.fetch(index, start)
        .then(data => {
          this.pageStartList = paging(data.pageStartList, this.pageSize)
          this.boardList = data.list
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
      this.fetchData(this.currentCategory, this.pageList[0])
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
</style>
