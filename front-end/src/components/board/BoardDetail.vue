<template>
  <div class="content-wrap">
    <div class="detail-wrap" :class="$mq">
      <div class="title-wrap">
        <p class="detail-category">{{ detail.category }}</p>
        <p class="detail-title">{{ detail.title }}</p>
      </div>
      <div class="info-wrap">
        <p class="detail-writer">{{ detail.writer }}</p>
        <p class="detail-date">{{ detail.reg_date }}</p>
        <p class="detail-hits">조회수 {{ detail.hits }}</p>
      </div>
      <div class="post-wrap">
        <p class="detail-content">{{ detail.content }}</p>
        <div class="recommend-wrap">
          <div class="likes">
            <p class="number">{{ detail.likes }}</p>
            <p class="comment">추천</p>
          </div>
          <div class="dislikes">
            <p class="number">{{ detail.dislikes }}</p>
            <p class="comment">비추</p>
          </div>
        </div>
      </div>
      <div class="comment-wrap">
      </div>
      <div class="detail-bottom">
        <a class="write-btn" href="/board/write">글쓰기</a>
        <a class="delete-btn" href="/board/delete">삭제</a>
        <a class="modify-btn" href="/board/modify">수정</a>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'DetailPage',
  props: ['detail']
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';
.detail-wrap {
  border-top: 2px solid $white-blue;
  border-bottom: 2px solid $white-blue;
  padding: 20px;
  margin: 80px auto;
  &.laptop {
    width: 960px;
  }
}
.title-wrap {
  border-top: 1px solid $white-blue;
  border-bottom: 1px solid $white-blue;
  padding: 20px;
}
.detail-category {
  float: left;
  color: #6c83aa;
  margin-right: 20px;
}
.detail-title {
  font-weight: bold;
  font-size: 16px;
}
.info-wrap {
  background-color: #eee;
  padding: 10px 20px;
  overflow: hidden;
  font-size: 14px;
}
.detail-writer {
  padding-right: 10px;
  margin-right: 10px;
  border-right: 1px solid $white-blue;
}
.detail-writer, .detail-date {
  float: left;
}
.detail-hits {
  float: right;
}
.detail-content {
  padding: 40px 20px;
}
.post-wrap {
  border-bottom: 1px solid $white-blue;
}
.recommend-wrap {
  width: 180px;
  margin: 30px auto;
  text-align: center;
}
.likes, .dislikes {
  width: 40px;
  height: 40px;
  line-height: 20px;
  border-radius: 50%;
  border: 2px solid #eee;
  padding: 10px;
  display: inline-block;
  cursor: pointer;

  &:hover {
    border-color: $classic-blue;
  }
  .comment {
    font-size: 13px;
    font-weight: bold;
  }
}
.likes {
  margin-right: 20px;

  .number {
    color: green;
  }
}
.dislikes {
  .number {
    color: maroon;
  }
}
.detail-bottom {
  margin-top: 20px;
  overflow: hidden;
}
.modify-btn, .delete-btn {
  float: right;
  margin-right: 10px;
  border: 1px solid #eee;
  font-size: 12px;
  padding: 10px;
  color: $classic-blue;
}
</style>
