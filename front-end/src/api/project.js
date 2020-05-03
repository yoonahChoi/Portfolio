import { request } from './board'

export const project = {
  fetchList () {
    return request('get', '/project/')
  }
}
