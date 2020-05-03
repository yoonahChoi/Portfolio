<template>
  <div class="content-wrap">
    <h2 class="home-title">&lt; Project &gt;</h2>
    <section class="project">
      <div v-for="(project, index) in projectList" :key="index" class="card">
          <a :href="project.link" class="image-wrap" :class="$mq">
            <img :src=" 'http://localhost:8080/portfolio/img/' + project.image">
          </a>
          <div class="describe-wrap" :class="$mq">
            <div class="card-type">{{ project.type }}</div>
            <div class="card-link"><a :href="project.link">Git hub</a></div>
            <div class="card-title"><h4>{{ project.title }}</h4></div>
            <div class="card-time">{{ project.start_date }} ~ {{ project.end_date }}</div>
            <div class="card-content">{{ project.content }}</div>
            <div class="card-tag-wrap" :class="$mq">
              <span v-for="(tag, index) in tags[index]" :key="index" class="tag">{{ tag }}</span>
            </div>
          </div>
      </div>
    </section>
  </div>
</template>

<script>
import { project } from '@/api/project'
export default {
  name: 'HomePage',
  data () {
    return {
      projectList: [],
      tags: []
    }
  },
  created () {
    this.fetchList()
  },
  methods: {
    fetchList () {
      project.fetchList()
        .then(res => {
          this.projectList = res.data
          this.getTagList()
        })
    },
    getTagList () {
      this.projectList.forEach(e => {
        this.tags.push(e.tags.split('#').sort().filter(n => n !== ''))
      })
    }
  }
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
  padding: 20px;
  box-sizing: border-box;
}
.card {
  height: 250px;
  border: 1px solid $white-blue;
  border-radius: 5px;
  margin-bottom: 40px;
  box-shadow: 1px 1px 5px 1px rgba(0, 0, 0, 0.1);
  overflow: hidden;

  &:hover {
    box-shadow: 1px 1px 5px 1px rgba(0, 0, 0, 0.3);
  }
}
.image-wrap {
  &.laptop {
    height: inherit;
    width: 35%;
    float: left;
  }
  &.tablet {
    display: none;
  }
  border-top-left-radius: 5px;
  border-bottom-left-radius: 5px;

  &.mobile {
    width: 100%;
    height: 100%;
  }

  & img {
    width: 100%;
    height: 100%;
  }
}
.describe-wrap {
  position: relative;
  &.laptop {
    float: left;
    height: inherit;
    width: 65%;
  }

  &.mobile {
    display: none;
  }
}
.card-type {
  text-align: center;
  font-size: 18px;
  font-weight: bold;
  color: $classic-blue;
  line-height: 35px;
  position: absolute;
  top: 10px;
  right: 15px;
}
.card-title {
  position: absolute;
  top: 20px;
  left: 25px;
  font-size: 28px;
}
.card-time {
  position: absolute;
  font-size: 18px;
  top: 65px;
  left: 25px;
}
.card-content {
  position: absolute;
  font-size: 20px;
  top: 90px;
  width: 90%;
  left: 25px;
  line-height: 150%;
  height: 80px;
  overflow: auto;
}
.card-tag-wrap {
  position: absolute;
  font-size: 16px;
  bottom: 15px;
  width: 90%;
  height: 50px;
  left: 25px;
  line-height: 150%;
  overflow: auto;

  &.tablet {
    top: 180px;
  }
}
.tag {
  float: left;
  font-size: 15px;
  padding: 0 10px;
  border-radius: 20px;
  background-color: $classic-blue;
  color: #fff;
  margin: 0 10px 10px 0;
}
.card-link {
  position: absolute;
  font-size: 15px;
  top: 50px;
  right: 15px;
  border: 1px solid $white-blue;
  border-radius: 20px;
  padding: 5px 10px;

  & a:visited {
    color: black;
  }

  &:hover {
    border-color: $classic-blue;
    a {
      color: $classic-blue;
    }
  }
}
</style>
