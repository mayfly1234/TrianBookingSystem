import request from '@/utils/request'

// 查询座位信息列表
export function listSeat(query) {
  return request({
    url: '/seat/seat/list',
    method: 'get',
    params: query
  })
}

// 查询座位信息详细
export function getSeat(seatId) {
  return request({
    url: '/seat/seat/' + seatId,
    method: 'get'
  })
}

// 新增座位信息
export function addSeat(data) {
  return request({
    url: '/seat/seat',
    method: 'post',
    data: data
  })
}

// 修改座位信息
export function updateSeat(data) {
  return request({
    url: '/seat/seat',
    method: 'put',
    data: data
  })
}

// 删除座位信息
export function delSeat(seatId) {
  return request({
    url: '/seat/seat/' + seatId,
    method: 'delete'
  })
}
