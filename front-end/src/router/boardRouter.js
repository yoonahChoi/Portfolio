export default [
  {
    path: '/board',
    component: () => import('@/views/board/BoardPage.vue')
  },
  {
    path: '/board/write',
    component: () => import('@/views/board/BoardWrite.vue')
  },
  {
    path: '/board/modify',
    name: 'BoardModify',
    component: () => import('@/views/board/BoardWrite.vue')
  }
]
