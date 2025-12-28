import request from '@/utils/request'

// 查询车次经停站点列表
export function listStation(query) {
  return request({
    url: '/station/station/list',
    method: 'get',
    params: query
  })
}

// 查询车次经停站点详细
export function getStation(stationId) {
  return request({
    url: '/station/station/' + stationId,
    method: 'get'
  })
}

// 新增车次经停站点
export function addStation(data) {
  return request({
    url: '/station/station',
    method: 'post',
    data: data
  })
}

// 修改车次经停站点
export function updateStation(data) {
  return request({
    url: '/station/station',
    method: 'put',
    data: data
  })
}

// 删除车次经停站点
export function delStation(stationId) {
  return request({
    url: '/station/station/' + stationId,
    method: 'delete'
  })
}
