import { mount } from '@vue/test-utils'
import App from '@/App.vue'

describe('App.vue', () => {
  let wrapper

  beforeEach(() => {
    wrapper = mount(App, {
      stubs: ['router-link', 'router-view']
    })
  })

  it('네비게이션 바를 출력', () => {
    const home = wrapper.findAll('.nav-link').at(0)
    const project = wrapper.findAll('.nav-link').at(1)
    const board = wrapper.findAll('.nav-link').at(2)
    const planner = wrapper.findAll('.nav-link').at(3)

    expect(home.text()).toBe('홈')
    expect(project.text()).toBe('프로젝트')
    expect(board.text()).toBe('게시판')
    expect(planner.text()).toBe('일정관리')
  })

  it('로그인 버튼 출력', () => {
    const login = wrapper.find('.login-link')
    expect(login.text()).toBe('로그인')
  })
})
