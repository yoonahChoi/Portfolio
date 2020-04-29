<template>
  <div class="content-wrap">
    <div class="form-wrap" :class="$mq">
      <form @submit.prevent="postContent">
        <select name="categoryId" class="category-select" :class="$mq" v-model="selected">
          <option value="">카테고리</option>
          <option value="1">질문</option>
          <option value="2">정보</option>
          <option value="3">잡담</option>
          <option value="4">자료</option>
        </select>
        <input v-model="writer" type="text" class="writer-input" placeholder="닉네임" :class="$mq" maxlength="15" required>
        <input v-model="password" type="text" class="password-input" placeholder="비밀번호" :class="$mq" maxlength="15" required>
        <input v-model="title" class="title-input" type="text" placeholder="제목을 입력해 주세요." maxlength="40" required>
        <vue-editor id="editor" v-model="content"></vue-editor>
        <input type="file" class="file-input" name="file" v-on:change="handleFileUpload($event)">
        <div class="btn-wrap">
          <a href="/board" class="cancel-btn">취소</a>
          <button type="submit" class="submit-btn" :class="$mq">등록</button>
          <!-- <button @click.prevent="saveContent" class="temporary-btn" :class="$mq">임시저장</button> -->
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import { VueEditor } from 'vue2-editor'
import { board } from '@/api/board'

export default {
  name: 'BoardWrite',
  components: {
    VueEditor
  },
  data () {
    return {
      selected: '',
      writer: '',
      password: '',
      title: '',
      content: '',
      file: ''
    }
  },
  created () {
    const params = this.$route.params
    this.writer = params.writer
    this.title = params.title
    this.content = params.content
    this.file = params.file

    if (!params) console.log('등록')
    else console.log('수정')
  },
  methods: {
    saveContent () {
      alert('임시 저장 됐습니다.')
    },
    postContent () {
      if (this.formValidate()) return

      const form = new FormData()
      form.append('categoryId', this.selected)
      form.append('writer', this.writer)
      form.append('password', this.password)
      form.append('title', this.title)
      form.append('content', this.content)

      if (this.file !== '') {
        form.append('file', this.file)
      }

      board.post(form)
        .then(() => {
          alert('게시글 등록 성공')
          this.$router.push('/board')
        }).catch(err => {
          alert('게시글 등록 실패')
          console.log(err)
        })
    },
    formValidate () {
      if (!this.content.length) {
        alert('내용은 필수 항목입니다.')
        return true
      }
      if (!this.selected) {
        alert('카테고리를 골라주세요')
        return true
      }
    },
    handleFileUpload (e) {
      this.file = e.target.files[0]
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';

@mixin write-page-btn {
  padding: 8px;
  border: 1px solid $white-blue;
  color: $classic-blue;
  background-color: #fff;
  font-size: 13px;
}

.form-wrap {
  &.laptop {
    width: 1000px;
  }
  border: 1px solid $white-blue;
  padding: 40px 20px;
  margin: 80px auto;
  box-sizing: border-box;
}
.category-select {
  height: 30px;
  width: 80px;
  &.mobile {
    display: block;
  }
}
.writer-input {
  position: relative;
  top: -2px;
  height: 26px;
  line-height: 26px;
  padding: 1px 5px;
  font-size: 13px;
  &.tablet {
    width: 150px;
  }
  &.mobile {
    width: 110px;
  }
}
.category-select, .writer-input, .title-input{
  border: 1px solid $white-blue;
    margin: 0 10px 10px 0;
}
.password-input {
  @extend .writer-input
}
.title-input {
  display: block;
  width: 80%;
  height: 30px;
  padding: 1px 5px;
}
.file-input {
  margin-top: 20px;
}
.btn-wrap {
  overflow: hidden;
  margin-top: 30px;
}
.cancel-btn {
  float: left;
  box-sizing: border-box;
  text-align: center;
  height: 35px;
  width: 60px;
  line-height: 17px;
  @include write-page-btn()
}
.submit-btn {
  float: right;
  margin-left: 15px;
  width: 80px;
  cursor: pointer;
  @include write-page-btn();

  &.mobile {
    margin-left: 5px;
  }
}
.temporary-btn {
  @extend .submit-btn
}
.warning {
  margin-top: 20px;
  color: red;
}
</style>
