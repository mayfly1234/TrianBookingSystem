import request from '@/utils/request'

// 查询车次
export function queryTrain(params) {
  return request({
    url: '/trainquery/onekey/query',
    method: 'get',
    params
  })
}

// 查询所有站点
export function getAllStations() {
  return request({
    url: '/trainquery/onekey/stations',
    method: 'get'
  })
}