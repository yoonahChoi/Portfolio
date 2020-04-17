export default [
  {
    path: '/board',
    name: 'Board',
    component: () => import('@/views/board/Board.vue')
  },
  {
    path: '/board/write',
    name: 'BoardWrite',
    component: () => import('@/views/board/BoardWrite.vue')
  }
]
