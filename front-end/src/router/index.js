import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import PageNotFound from '@/views/PageNotFound'
import board from '@/router/boardRouter'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/project',
    component: () => import('@/views/ProjectPage.vue')
  },
  ...board,
  {
    path: '/planner',
    component: () => import('@/views/PlannerPage.vue')
  },
  {
    path: '*',
    name: '404',
    component: PageNotFound
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
