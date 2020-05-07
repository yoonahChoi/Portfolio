<template>
  <div class="content-wrap">
    <div class="form-wrap">
      <form class="login-form">
        <p class="login-title">Login</p>
        <span class="form-label">아이디</span>
        <input type="text" placeholder="아이디" required>
        <span class="form-label">비밀번호</span>
        <input type="text" placeholder="비밀번호" required>
        <button type="submit">로그인</button>
      </form>
      <div class="join-wrap">
        <p>아직 회원이 아니신가요?</p>
        <button class="join-btn" @click="showModal = true">회원가입</button>
        <join-modal v-if="showModal">
          <template slot="header">
            <h3>회원가입</h3>
          </template>
          <template slot="body">
            <form>
              <span class="form-label">아이디</span>
              <input type="text" placeholder="아이디" required>
              <span class="form-label">비밀번호</span>
              <input type="text" placeholder="비밀번호" required>
              <span class="form-label">비밀번호 재확인</span>
              <input type="text" placeholder="비밀번호 재확인" required>
              <span class="form-label">닉네임</span>
              <input type="text" placeholder="닉네임" required>
              <span class="form-label">이메일</span>
              <input type="text" placeholder="이메일" required>
              <button type="submit">가입하기</button>
            </form>
          </template>
          <template slot="footer">
            <button class="modal-default-button cancle-btn" @click="showModal = false">취소</button>
          </template>
        </join-modal>
      </div>
    </div>
  </div>
</template>

<script>
import Modal from '@/components/Modal'

export default {
  data () {
    return {
      showModal: false
    }
  },
  components: {
    'join-modal': Modal
  },
  methods: {
  }
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';

@mixin login-wrap {
  padding: 30px 50px;
  box-sizing: border-box;
  position: absolute;
  top: 50px;
  width: 400px;
  height: 300px;
}
@mixin login-btn {
  background-color: $classic-blue;
  color: #fff;
  height: 40px;
  border: none;
}
@mixin input-style {
  width: 100%;
  height: 35px;
  border: 1px solid $white-blue;
  padding: 5px 10px;
  margin-bottom: 20px;
  box-sizing: border-box;
}
.form-wrap {
  position: relative;
  padding: 0;
  margin: 100px auto;
  width: 800px;
  height: 400px;
}
.form-label {
  display: block;
  color: $classic-blue;
  font-weight: bold;
  margin-bottom: 5px;
}
.login-title {
  font-size: 35px;
  color: $classic-blue;
  text-align: center;
  margin-bottom: 10px;
}
.login-form {
  @include login-wrap();
  border-right: 1px solid #eee;

  input {
    @include input-style();
  }

  button {
    width: 100%;
    height: 35px;
    border: 1px solid $white-blue;
    @include login-btn();
  }
}
.join-wrap {
  @include login-wrap();
  right: 0;

  p {
    font-size: 25px;
    color: $classic-blue;
    text-align: center;
    margin: 50px 0;
  }
  .join-btn {
    @include login-btn();
    display: block;
    width: 50%;
    margin: 0 auto;
  }
  input {
    @include input-style();
  }
  form button {
    width: 100%;
    height: 35px;
    border: 1px solid $white-blue;
    @include login-btn();
  }
}
.cancle-btn {
  float: left;
  margin: 0;
}
</style>
