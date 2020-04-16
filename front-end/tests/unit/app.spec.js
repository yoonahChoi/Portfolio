import { mount } from '@vue/test-utils'
import App from '@/App.vue'

describe('App.vue', () => {
  let wrapper

  beforeEach(() => {
    wrapper = mount(App, {
      stubs: ['router-link', 'router-view']
    })
  })

  it('사이드바 작동', () => {
    const spyMenuBtn = jest.spyOn(wrapper.vm, 'activeMenuBtn')
    const spySideBar = jest.spyOn(wrapper.vm, 'activeSideBar')
    const menu = wrapper.find('.menu')
    const sideBar = wrapper.find('.side-bar')
    menu.trigger('click')
    expect(spyMenuBtn).toHaveBeenCalled()
    expect(spySideBar).toHaveBeenCalled()
    expect(menu.classes()).toContain('.active')
    expect(sideBar.hasStyle('display', 'block')).toBe(true)
  })
})
