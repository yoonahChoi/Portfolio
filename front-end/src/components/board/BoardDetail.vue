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
        <p v-html="detail.content" class="detail-content"></p>
        <div class="recommend-wrap">
          <div class="likes" @click="addRecommend(detail.board_id, 'like')">
            <p class="number">{{ detail.likes }}</p>
            <p class="comment">추천</p>
          </div>
          <div class="dislikes" @click="addRecommend(detail.board_id, 'dislike')">
            <p class="number">{{ detail.dislikes }}</p>
            <p class="comment">비추</p>
          </div>
        </div>
      </div>
      <div class="file-wrap">
        <p class="file-title">첨부파일</p>
        <p class="download" @click="downloadFile">{{ detail.filename }}</p>
      </div>
      <div class="comment-wrap">
      </div>
      <div class="detail-bottom">
        <router-link class="write-btn" to="/board/write">글쓰기</router-link>
        <div>
          <button class="delete-btn" @click="showModal = true">삭제</button>
          <delete-modal v-if="showModal">
            <template slot="header">
              <h3>게시글 삭제</h3>
            </template>
            <template slot="body">
              <span>비밀번호 </span>
              <input class="pwd-input" type="text" v-model="password">
            </template>
            <template slot="footer">
              <button class="modal-default-button" style="color:maroon;" @click="deleteBoard(detail.board_id)">삭제</button>
              <button class="modal-default-button" @click="showModal = false">취소</button>
            </template>
          </delete-modal>
        </div>
        <router-link class="modify-btn" :to="{ name: 'BoardModify', params: detail}">수정</router-link>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from '@/components/Modal'
export default {
  name: 'DetailPage',
  props: ['detail'],
  data () {
    return {
      showModal: false,
      password: ''
    }
  },
  components: {
    'delete-modal': Modal
  },
  methods: {
    addRecommend (id, type) {
      this.$emit('recommend', id, type)
    },
    deleteBoard (id) {
      const pwd = this.password
      if (!pwd) {
        alert('비밀번호를 입력하세요')
      } else {
        this.$emit('delboard', id, pwd)
      }
    },
    downloadFile () {
      this.$emit('download', this.detail.board_id)
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';
.detail-wrap {
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

  .comment {
    font-size: 13px;
    font-weight: bold;
  }
}
.likes {
  margin-right: 20px;

  .number {
    color: $classic-blue;
  }

  &:hover {
    border-color: $classic-blue;
  }
}
.dislikes {
  .number {
    color: maroon;
  }

  &:hover {
    border-color: maroon;
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
  color: $classic-blue !important;
  font-weight: normal;
}
.delete-btn {
  height: 34px;
  line-height: 13px;
  background-color: #fff;
}
.pwd-input {
  width: 70%;
  margin-left: 10px;
  border: 1px solid $white-blue;
  padding: 4px;
  line-height: 15px;
}
.file-wrap {
  background-color: #eee;
  padding: 10px 15px;
  font-size: 14px;

  & p {
    display: inline-block;
  }
}
.file-title {
  padding-right: 10px;
  margin-right: 10px;
  border-right: 1px solid $white-blue;
}
.download {
  font-size: 16px;

  &:hover {
    color: $classic-blue;
    text-decoration: underline;
    cursor: pointer;
  }
}
</style>
