import request from '@/utils/request'

// 查询公告信息列表
export function listInfo(query) {
  return request({
    url: '/info/info/list',
    method: 'get',
    params: query
  })
}

// 查询公告信息详细
export function getInfo(infoId) {
  return request({
    url: '/info/info/' + infoId,
    method: 'get'
  })
}

// 新增公告信息
export function addInfo(data) {
  return request({
    url: '/info/info',
    method: 'post',
    data: data
  })
}

// 修改公告信息
export function updateInfo(data) {
  return request({
    url: '/info/info',
    method: 'put',
    data: data
  })
}

// 删除公告信息
export function delInfo(infoId) {
  return request({
    url: '/info/info/' + infoId,
    method: 'delete'
  })
}
