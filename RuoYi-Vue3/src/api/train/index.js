import request from '@/utils/request'
// 新增：引入消息提示（若依前端内置）
import { ElMessage } from 'element-plus'

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
    paramsSerializer: function (params) {
      return new URLSearchParams(params).toString()
    }
  }).catch(err => {
    // 统一捕获查询失败异常
    ElMessage.error('车次查询失败：' + (err.msg || '网络异常'))
    // 抛出异常，让调用方可以继续处理
    return Promise.reject(err)
  })
}

/**
 * 提交订票信息
 * @param {Object} data - 订票数据
 * @returns {Promise}
 */
export function submitBookInfo(data) {
  console.log('【提交订票】请求数据：', data) // 调试日志
  // 新增：数据格式校验（前端提前拦截错误，减少后端请求）
  const requiredFields = ['scheduleId', 'trainNo', 'startStation', 'endStation', 'departDate', 'passengerName', 'idCard', 'phone', 'carriageNo', 'seatNo', 'seatType', 'ticketPrice']
  const missingFields = requiredFields.filter(field => !data[field])
  if (missingFields.length > 0) {
    ElMessage.error(`订票失败：缺少必填字段 - ${missingFields.join(', ')}`)
    return Promise.reject(new Error(`缺少必填字段：${missingFields.join(', ')}`))
  }

  return request({
    url: '/trainbook/simple/submit',
    method: 'post',
    data: data
  }).catch(err => {
    // 统一捕获订票失败异常（优先显示后端返回的错误信息）
    ElMessage.error('订票失败：' + (err.msg || err.message || '网络异常'))
    return Promise.reject(err)
  })
}