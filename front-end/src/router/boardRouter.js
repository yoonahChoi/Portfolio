export default [
  {
    path: '/board',
    component: () => import('@/views/board/BoardPage.vue'),
    childrens: [

    ]
  },
  {
    path: '/board/write',
    component: () => import('@/views/board/BoardWrite.vue')
  }
]
