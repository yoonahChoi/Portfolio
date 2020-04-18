import axios from 'axios'

const DOMAIN = 'http://localhost:3000'

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
  fetch () {
    return request('get', '/board')
  }
}
