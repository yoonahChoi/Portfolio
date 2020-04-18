<template>
  <div id="app">
    <div class="header">
      <div class="content-wrap">
        <div class="logo-wrap" :class="$mq">
          <h1 class="logo"><router-link to="/">Yoon's</router-link></h1>
        </div>
        <a href="#" class="menu" @click="activeMenuBtn" :class="[{active: isActive},$mq]">
          <span></span>
          <span></span>
          <span></span>
        </a>
        <div class="side-bar" :class="[{open: isOpen},$mq]">
          <nav class="nav" :class="$mq">
            <ul class="nav-list" :class="$mq">
              <li><router-link class="nav-link" to="/project">프로젝트</router-link></li>
              <li><router-link class="nav-link" to="/board">게시판</router-link></li>
              <li><router-link class="nav-link" to="/planner">일정관리</router-link></li>
            </ul>
          </nav>
          <div class="login-wrap" :class="$mq">
            <a href="#" class="login-link" >로그인</a>
          </div>
        </div>
      </div>
    </div>
    <router-view></router-view>
    <footer class="footer">
      <p class="copyright">Copyrightⓒ2020 All rights reserved. YoonAh Choi</p>
    </footer>
  </div>
</template>

<script>
export default {
  name: 'App',
  data () {
    return {
      isOpen: false,
      isActive: false
    }
  },
  methods: {
    activeMenuBtn () {
      this.isActive = !this.isActive
      this.activeSideBar()
    },
    activeSideBar () {
      this.isOpen = !this.isOpen
    }
  }
}
</script>

<style lang="scss">
@import '@/assets/scss/main.scss';
@import '@/assets/scss/_variables.scss';

.header {
  background-color: $classic-blue;
  height: 60px;
}
.footer {
    height: 50px;
    background-color: $white-blue;
  }
.copyright {
  color: $classic-blue;
  font-size: 13px;
  text-align: center;
  line-height: 50px;
}
.logo-wrap {
  text-align: center;
  width: 90px;

  &.tablet, &.mobile{
    margin: 0 auto;
  }
  &.laptop {
    float: left;
    padding: 0 30px;
  }
}
.logo {
  font-size: 23px;
  color: #fff;
}
.logo-wrap, .side-bar, .login-wrap {
  line-height: 60px;
}
.side-bar {
  margin-left: -150px;
  z-index: 999;
  &.mobile, &.tablet {
  transition: all .4s;
  transform: translate(-150px,0);
  -webkit-transform: translate(-150px,0);
  width: 150px;
  text-align: center;
  background-color: #456795;
  position: absolute;
    a {
      color: #fff;
    }
  }
  &.open.mobile, &.open.tablet {
    transition: all .4s;
    transform: translate(150px,0);
    -webkit-transform: translate(150px,0);
  }
  &.laptop {
    display: block !important;
    height: 60px;
    .nav-link {
      color: #90a0bf
    }
  }
}
.nav.laptop {
  float: left;
}
.login-wrap.laptop {
  float: right;
}
.nav-list {
  &.laptop li {
    color: #90a0bf;
    display: inline-block;
  }
  &.laptop a:hover{
    color: #fff;
  }
}
.nav-link, .login-link {
  height: 60px;
  padding: 0 30px;
}
.login-link {
  color: #fff;
}
.router-link-active {
  color: #fff !important;
  font-weight: bold;
}
.menu, .menu span {
  display: inline-block;
  transition: all .4s;
  box-sizing: border-box;
}
.menu {
  position: absolute;
  top: 18px;
  left: 18px;
  width: 35px;
  height: 26px;

  &.laptop {
    display: none;
  }
  span {
    position: absolute;
    width: 100%;
    height: 4px;
    background-color: #fff;
    border-radius: 5px;

    &:nth-last-of-type(1) {
      top: 0;
    }
    &:nth-last-of-type(2) {
      top: 11px;
    }
    &:nth-last-of-type(3) {
      bottom: 0;
    }
  }
}
.menu.active span {
  &:nth-last-of-type(1) {
    transform : translateY(11px) rotate(-45deg);
    -webkit-transform : translateY(11px) rotate(-45deg);
  }
  &:nth-last-of-type(2) {
    opacity : 0;
  }
  &:nth-last-of-type(3) {
    transform : translateY(-11px) rotate(45deg);
    -webkit-transform : translateY(-11px) rotate(45deg);
  }
}
</style>
