<template>
  <div id="app">
    <div class="header">
      <div class="content-wrap">
        <div class="logo-wrap">
          <h1 class="logo">Yoon's</h1>
        </div>
        <a href="#" class="menu"></a>
        <nav class="nav">
          <ul class="nav-list">
            <li><router-link class="nav-link active" to="/">홈</router-link></li>
            <li><router-link class="nav-link" to="/project">프로젝트</router-link></li>
            <li><router-link class="nav-link" to="/board">게시판</router-link></li>
            <li><router-link class="nav-link" to="/planner">일정관리</router-link></li>
          </ul>
        </nav>
        <div class="login-wrap">
          <a href="#" class="login-link" >로그인</a>
        </div>
      </div>
    </div>
    <router-view></router-view>
    <div class="footer">
      <p class="copyright">Copyrightⓒ2020 All rights reserved. YoonAh Choi</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'App'
}
</script>

<style src="./assets/css/main.css"/>
<style>
.header {
    height: 60px;
    overflow: hidden;
    background-color: #0f4c81;
  }
  .logo-wrap, nav, .login-wrap {
    height: 100%;
    line-height: 60px;
  }
  .logo-wrap {
    text-align: center;
    width: 90px;
    margin: 0 auto;
  }
  .logo {
    font-size: 23px;
    color: #fff;
  }
  .menu{
    position: absolute;
    top: 17.5px;
    left:17.5px;
    width: 35px;
    height: 25px;
    color: transparent;
    background: linear-gradient(#fff 50%, transparent 50%);
    background-size: 100% 10px;
  }
  .nav, .login-wrap{
    display: none;
  }
  .active {
    color: #fff;
    font-weight: bold;
  }
  .footer {
    height: 50px;
    background-color: #dadfe9;
  }
  .copyright {
    color: #0f4c81;
    font-size: 13px;
    text-align: center;
    line-height: 50px;
  }
  @media screen and (min-width: 768px) {
    .menu {
      display: none;
    }
    .nav, .login-wrap{
      display: block;
    }
    .logo-wrap, nav {
      float: left;
    }
    .logo-wrap {
      padding: 0 30px;
    }
    .login-wrap {
      float: right;
    }
    .nav-list li {
      color: #90a0bf;
      display: inline-block;
    }
    .nav-list li:hover, .login-link{
      color: #fff;
    }
    .nav-link, .login-link {
      padding: 0 30px;
    }
  }
</style>
