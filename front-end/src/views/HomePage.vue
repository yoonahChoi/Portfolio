<template>
  <div class="content-wrap">
    <h2 class="home-title">&lt; Project &gt;</h2>
    <section class="project">
      <div class="card">
          <div class="image-wrap" :class="$mq">
          </div>
          <div class="describe-wrap" :class="$mq"></div>
      </div>
      <div class="card">
          <div class="image-wrap" :class="$mq">
          </div>
          <div class="describe-wrap" :class="$mq"></div>
      </div>
    </section>
  </div>
</template>

<script>
export default {
  name: 'HomePage'
}
</script>

<style lang="scss">
@import '@/assets/scss/_variables.scss';

.home-title {
  color: $classic-blue;
  font-size: 35px;
  text-align: center;
  margin: 50px 0;
}
.project {
  max-width: 1000px;
  margin: 0 auto;
  padding: 20px 100px;
  box-sizing: border-box;
}
.card {
  height: 250px;
  border: 1px solid $white-blue;
  border-radius: 5px;
  margin-bottom: 40px;
  box-shadow: 1px 1px 5px 1px rgba(0, 0, 0, 0.1);

  &:hover {
    box-shadow: 1px 1px 5px 1px rgba(0, 0, 0, 0.3);
  }
}
.image-wrap {
  &.laptop {
    display: inline-block;
    height: 100%;
    width: 35%;
  }
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;
  background-color: #eee;
}
.describe-wrap {
  &.laptop {
    display: inline-block;
    height: 100%;
    width: 65%;
  }
}
</style>
