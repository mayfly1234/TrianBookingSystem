package com.ruoyi.trainbook.service;

import com.ruoyi.trainbook.domain.vo.TrainBookSubmitVO;

import java.util.Map;

/**
 * 火车订票Service接口
 *
 * @author ruoyi
 */
public interface ITrainBookSimpleService {
    /**
     * 提交订票信息
     *
     * @param submitVO 前端提交参数
     * @param userId   登录用户ID
     * @return 订单信息（含订单号）
     */
    Map<String, Object> submitBook(TrainBookSubmitVO submitVO, Long userId);
}