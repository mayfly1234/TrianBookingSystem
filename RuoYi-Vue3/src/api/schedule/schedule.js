import request from '@/utils/request'

// 查询车次信息列表
export function listSchedule(query) {
  return request({
    url: '/schedule/schedule/list',
    method: 'get',
    params: query
  })
}

// 查询车次信息详细
export function getSchedule(scheduleId) {
  return request({
    url: '/schedule/schedule/' + scheduleId,
    method: 'get'
  })
}

// 新增车次信息
export function addSchedule(data) {
  return request({
    url: '/schedule/schedule',
    method: 'post',
    data: data
  })
}

// 修改车次信息
export function updateSchedule(data) {
  return request({
    url: '/schedule/schedule',
    method: 'put',
    data: data
  })
}

// 删除车次信息
export function delSchedule(scheduleId) {
  return request({
    url: '/schedule/schedule/' + scheduleId,
    method: 'delete'
  })
}
