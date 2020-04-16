import Vue from 'vue'
import VueRouter from 'vue-router'
import HomePage from '@/views/HomePage.vue'
import PageNotFound from '@/views/PageNotFound'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: HomePage
  },
  {
    path: '/project',
    name: 'Project',
    component: () => import('@/views/ProjectPage.vue')
  },
  {
    path: '/board',
    name: 'Board',
    component: () => import('@/views/BoardPage.vue')
  },
  {
    path: '/planner',
    name: 'Planner',
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
