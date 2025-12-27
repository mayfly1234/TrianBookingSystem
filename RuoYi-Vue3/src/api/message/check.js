import request from '@/utils/request'

/**
 * 查看公告相关API
 */
// 分页查询公告列表（支持模糊查询）
export function listMessageCheck(query) {
  return request({
    url: '/message/check/list',
    method: 'get',
    params: query
  })
}

// 根据ID查询公告详情
export function getMessageCheck(infoId) {
  return request({
    url: '/message/check/' + infoId,
    method: 'get'
  })
}