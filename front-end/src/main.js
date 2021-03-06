import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueMq from 'vue-mq'

Vue.use(VueMq, {
  breakpoints: {
    mobile: 426,
    tablet: 769,
    laptop: 1024
  }
})

Vue.config.productionTip = false

new Vue({
  router,
  render: h => h(App)
}).$mount('#app')
