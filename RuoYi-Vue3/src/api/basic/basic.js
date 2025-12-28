import request from '@/utils/request'

// 查询列车基础信息列表
export function listBasic(query) {
  return request({
    url: '/basic/basic/list',
    method: 'get',
    params: query
  })
}

// 查询列车基础信息详细
export function getBasic(basicId) {
  return request({
    url: '/basic/basic/' + basicId,
    method: 'get'
  })
}

// 新增列车基础信息
export function addBasic(data) {
  return request({
    url: '/basic/basic',
    method: 'post',
    data: data
  })
}

// 修改列车基础信息
export function updateBasic(data) {
  return request({
    url: '/basic/basic',
    method: 'put',
    data: data
  })
}

// 删除列车基础信息
export function delBasic(basicId) {
  return request({
    url: '/basic/basic/' + basicId,
    method: 'delete'
  })
}
