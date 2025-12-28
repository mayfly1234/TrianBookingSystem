import request from '@/utils/request'

// 查询车厢信息列表
export function listCarriage(query) {
  return request({
    url: '/carriage/carriage/list',
    method: 'get',
    params: query
  })
}

// 查询车厢信息详细
export function getCarriage(carriageId) {
  return request({
    url: '/carriage/carriage/' + carriageId,
    method: 'get'
  })
}

// 新增车厢信息
export function addCarriage(data) {
  return request({
    url: '/carriage/carriage',
    method: 'post',
    data: data
  })
}

// 修改车厢信息
export function updateCarriage(data) {
  return request({
    url: '/carriage/carriage',
    method: 'put',
    data: data
  })
}

// 删除车厢信息
export function delCarriage(carriageId) {
  return request({
    url: '/carriage/carriage/' + carriageId,
    method: 'delete'
  })
}
