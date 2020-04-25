import axios from 'axios'

const DOMAIN = 'http://localhost:8080/portfolio'

const request = (method, url, data) => {
  return axios({
    method,
    url: DOMAIN + url,
    data
  }).then(result => result.data)
    .catch(result => {
      console.log(result)
    })
}

export const board = {
  fetchList (cid, start) {
    if (cid === 0) return request('get', `/board/?start=${start}`)
    return request('get', `/board/?cid=${cid}&start=${start}`)
  },
  post (data) {
    return request('post', '/board/', data)
  },
  fetch (id) {
    return request('get', `/board/${id}`)
  }
}
