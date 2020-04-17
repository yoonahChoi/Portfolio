<template>
  <div class="content-wrap">
    <div class="board-wrap">
      <div class="board-top" :class="$mq">
        <div class="category-wrap">
          <ul class="category-list" :class="$mq">
            <li v-for="(cate, index) in category" :key="index" @click="selectedCategory(index)" :class="{selected: index === cid}">{{ cate }}</li>
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
      <board-table></board-table>
      <div class="board-bottom">
        <div class="board-addition">
          <a class="write-btn" href="/board/write">글쓰기</a>
        </div>
        <div class="board-page">
          <!-- <a class="page-pre" href="#">&lt;</a> -->
          <ul class="page-list">
            <li><a href="#" class="selected">1</a></li>
          </ul>
          <!-- <a class="page-next" href="#">&gt;</a> -->
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import Table from '@/components/Table'
export default {
  name: 'BoardPage',
  components: {
    'board-table': Table
  },
  data () {
    return {
      cid: 0,
      category: ['전체', '질문', '정보', '잡담', '자료']
    }
  },
  methods: {
    selectedCategory: function (index) {
      this.cid = index
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
    box-shadow: 3px 3px 5px 1px rgba(0, 0, 0, 0.1);
    border-radius: 5px;
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
  border-radius: 5px;
  margin: 0 5px;
}
.page-pre {
  float: left;
  width: 10px;
  @include page-btn;
}
.page-next {
  @extend .page-pre;
}
.page-list {
  float: left;
  li {
    display: inline-block;

  }
  a {
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
