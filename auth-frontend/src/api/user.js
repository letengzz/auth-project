import request from '@/utils/request'

export function login(data) {
  return request({
    url: '/api/system/index/login',
    method: 'post',
    data
  })
}

export function getInfo(token) {
  return request({
    url: '/api/system/index/info',
    method: 'get',
    params: { token }
  })
}

export function logout() {
  return request({
    url: '/api/system/index/logout',
    method: 'post'
  })
}
