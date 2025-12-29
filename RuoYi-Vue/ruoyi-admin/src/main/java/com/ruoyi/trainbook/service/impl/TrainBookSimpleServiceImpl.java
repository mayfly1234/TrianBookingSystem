package com.ruoyi.trainbook.service.impl;

import com.ruoyi.trainbook.domain.TrainBook;
import com.ruoyi.trainbook.domain.vo.TrainBookSubmitVO;
import com.ruoyi.trainbook.mapper.TrainBookMapper;
import com.ruoyi.trainbook.service.ITrainBookSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * 火车订票Service实现类
 *
 * @author ruoyi
 */
@Service
public class TrainBookSimpleServiceImpl implements ITrainBookSimpleService {

    @Autowired
    private TrainBookMapper trainBookMapper;

    @Override
    public Map<String, Object> submitBook(TrainBookSubmitVO submitVO, Long userId) {
        // 1. 生成唯一订单号
        String orderNo = "TRAIN_" + System.currentTimeMillis() + UUID.randomUUID().toString().substring(0, 6);

        // 2. VO转数据库实体
        TrainBook trainBook = new TrainBook();
        trainBook.setOrderNo(orderNo);
        trainBook.setUserId(userId);
        trainBook.setScheduleId(submitVO.getScheduleId());
        trainBook.setTrainNo(submitVO.getTrainNo());
        trainBook.setStartStation(submitVO.getStartStation());
        trainBook.setEndStation(submitVO.getEndStation());
        trainBook.setDepartDate(submitVO.getDepartDate());
        trainBook.setPassengerName(submitVO.getPassengerName());
        trainBook.setIdCard(submitVO.getIdCard());
        trainBook.setPhone(submitVO.getPhone());
        trainBook.setCarriageNo(submitVO.getCarriageNo());
        trainBook.setSeatNo(submitVO.getSeatNo());
        trainBook.setSeatType(submitVO.getSeatType());
        trainBook.setTicketPrice(submitVO.getTicketPrice());

        // 3. 插入数据库
        int result = trainBookMapper.insertTrainBook(trainBook);
        if (result <= 0) {
            throw new RuntimeException("订票记录插入数据库失败");
        }

        // 4. 构建返回结果
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("orderNo", orderNo);
        resultMap.put("trainNo", submitVO.getTrainNo());
        resultMap.put("scheduleId", submitVO.getScheduleId());
        resultMap.put("passengerName", submitVO.getPassengerName());
        resultMap.put("ticketPrice", submitVO.getTicketPrice());

        return resultMap;
    }
}