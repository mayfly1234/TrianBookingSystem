package com.ruoyi.trainbook.service;

import java.util.Map;

/**
 * 极简订票服务接口
 *
 * @author ruoyi
 */
public interface ITrainBookSimpleService
{
    /**
     * 提交订票信息（乘客+订单入库）
     *
     * @param submitData 提交数据
     * @param userId 当前登录用户ID
     * @return 订单信息（含订单号）
     */
    public Map<String, Object> submitBook(Map<String, Object> submitData, Long userId);
}