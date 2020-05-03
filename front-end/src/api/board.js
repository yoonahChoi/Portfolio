import axios from 'axios'

const DOMAIN = 'http://localhost:8080/portfolio'

export const request = (method, url, data) => {
  return axios({
    method,
    url: DOMAIN + url,
    data,
    withCredentials: true
  }).then(result => result)
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
  },
  file (id) {
    return axios({
      method: 'get',
      url: `http://localhost:8080/portfolio/board/download?id=${id}`,
      responseType: 'arraybuffer'
    }).then(res => {
      const blob = new Blob([res.data])
      const url = window.URL.createObjectURL(blob)
      const link = document.createElement('a')
      const contentDisposition = res.headers['content-disposition']

      let fileName = 'unknown'
      if (contentDisposition) {
        const [fileNameMatch] = contentDisposition.split(';').filter(str => str.includes('filename'))
        if (fileNameMatch) {
          [, fileName] = fileNameMatch.split('=')
        }
        fileName = fileName.replace(/^"(.*)"$/, '$1')
      }

      link.href = url
      link.setAttribute('download', `${fileName}`)
      link.style.cssText = 'display:none'
      document.body.appendChild(link)
      link.click()
      link.remove()
    })
  },
  search (keyword, type, start) {
    return request('get', `/board/search/${keyword}?type=${type}&start=${start}`)
  }
}
