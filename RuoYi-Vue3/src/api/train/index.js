import request from '@/utils/request'

/**
 * 车次查询接口（一站式查询）
 * @param {Object} params - 查询参数
 * @param {String} params.startStation - 出发站
 * @param {String} params.endStation - 到达站
 * @param {String} params.departDate - 发车日期（格式：YYYY-MM-DD）
 * @returns {Promise}
 */
export function queryTrainList(params) {
  console.log('【车次查询】请求参数：', params) // 调试日志
  return request({
    url: '/trainquery/onekey/query',
    method: 'get',
    params: params,
    // 强制序列化参数，避免日期/空值问题
    paramsSerializer: function (params) {
      return new URLSearchParams(params).toString()
    }
  })
}

/**
 * 提交订票信息
 * @param {Object} data - 订票数据
 * @returns {Promise}
 */
export function submitBookInfo(data) {
  console.log('【提交订票】请求数据：', data) // 调试日志
  return request({
    url: '/trainbook/simple/submit',
    method: 'post',
    data: data
  })
}