import axios from 'axios'

const DOMAIN = 'http://localhost:8080/portfolio'

const request = (method, url, data) => {
  return axios({
    method,
    url: DOMAIN + url,
    data,
    withCredentials: true
  }).then(result => result.data)
    .catch(err => {
      if (err.response.status === 304) alert('하루에 한번만 가능합니다')
      else if (err.response.status === 403) alert('권한이 없습니다')
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
  },
  recommend (id, type) {
    if (type === 'like') return request('get', `/board/like?bid=${id}`)
    else if (type === 'dislike') return request('get', `/board/dislike?bid=${id}`)
  },
  delete (bid, password) {
    return request('post', `/board/${bid}`, password)
  }
}
