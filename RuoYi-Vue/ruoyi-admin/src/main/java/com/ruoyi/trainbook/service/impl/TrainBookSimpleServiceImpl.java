package com.ruoyi.trainbook.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.trainbook.domain.Passenger;
import com.ruoyi.trainbook.domain.TrainOrderDetail;
import com.ruoyi.trainbook.domain.TrainOrderMain;
import com.ruoyi.trainbook.mapper.PassengerMapper;
import com.ruoyi.trainbook.mapper.TrainOrderDetailMapper;
import com.ruoyi.trainbook.mapper.TrainOrderMainMapper;
import com.ruoyi.trainbook.service.ITrainBookSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 极简订票服务实现
 *
 * @author ruoyi
 */
@Service
public class TrainBookSimpleServiceImpl implements ITrainBookSimpleService
{
    @Autowired
    private PassengerMapper passengerMapper;

    @Autowired
    private TrainOrderMainMapper orderMainMapper;

    @Autowired
    private TrainOrderDetailMapper orderDetailMapper;

    /**
     * 生成唯一订单编号（使用日期+随机数，无UUID依赖）
     */
    private String generateOrderNo()
    {
        String dateStr = DateUtils.dateTimeNow("yyyyMMddHHmmss");
        Random random = new Random();
        int randomNum = random.nextInt(900000) + 100000; // 6位随机数
        return dateStr + randomNum;
    }

    /**
     * 提交订票（事务保证）
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> submitBook(Map<String, Object> submitData, Long userId)
    {
        Map<String, Object> result = new HashMap<String, Object>();

        // ========== 1. 处理乘客信息（去重入库） ==========
        Map<String, Object> passengerMap = (Map<String, Object>) submitData.get("passenger");
        String idCard = passengerMap.get("idCard").toString();
        Passenger passenger = passengerMapper.selectPassengerByIdCard(idCard);
        Long passengerId;

        if (passenger != null)
        {
            // 乘客已存在，复用ID
            passengerId = passenger.getId();
        }
        else
        {
            // 乘客不存在，新增
            passenger = new Passenger();
            passenger.setUserId(userId);
            passenger.setPassengerName(passengerMap.get("passengerName").toString());
            passenger.setIdCard(idCard);
            passenger.setPhone(passengerMap.get("phone").toString());
            passenger.setCreateTime(DateUtils.getNowDate());
            passenger.setUpdateTime(DateUtils.getNowDate());
            passengerMapper.insertPassenger(passenger);
            passengerId = passenger.getId();
        }

        // ========== 2. 处理订单主表 ==========
        Map<String, Object> orderMap = (Map<String, Object>) submitData.get("order");
        String orderNo = generateOrderNo();

        TrainOrderMain orderMain = new TrainOrderMain();
        orderMain.setUserId(userId);
        orderMain.setOrderNo(orderNo);
        orderMain.setScheduleId(Long.parseLong(orderMap.get("scheduleId").toString()));
        orderMain.setTrainNo(orderMap.get("trainNo").toString());
        orderMain.setStartStation(orderMap.get("startStation").toString());
        orderMain.setEndStation(orderMap.get("endStation").toString());
        // 转换日期（前端传的是字符串，转Date）
        orderMain.setDepartDate(DateUtils.parseDate(orderMap.get("departDate").toString()));
        // 总金额=单票价
        BigDecimal ticketPrice = new BigDecimal(orderMap.get("ticketPrice").toString());
        orderMain.setTotalAmount(ticketPrice);
        orderMain.setOrderStatus("1"); // 直接标记为已支付
        // 过期时间：当前时间+15分钟
        Date expireTime = new Date(System.currentTimeMillis() + 15 * 60 * 1000);
        orderMain.setExpireTime(expireTime);
        orderMain.setCreateTime(DateUtils.getNowDate());
        orderMain.setUpdateTime(DateUtils.getNowDate());
        orderMainMapper.insertTrainOrderMain(orderMain);

        // ========== 3. 处理订单详情 ==========
        TrainOrderDetail orderDetail = new TrainOrderDetail();
        orderDetail.setOrderId(orderMain.getId());
        orderDetail.setOrderNo(orderNo);
        orderDetail.setPassengerId(passengerId);
        orderDetail.setCarriageNo(orderMap.get("carriageNo").toString());
        orderDetail.setSeatNo(orderMap.get("seatNo").toString());
        orderDetail.setSeatType(orderMap.get("seatType").toString());
        orderDetail.setTicketPrice(ticketPrice);
        orderDetail.setTicketStatus("0"); // 未使用
        orderDetail.setPassengerName(passengerMap.get("passengerName").toString());
        orderDetail.setIdCard(idCard);
        orderDetail.setCreateTime(DateUtils.getNowDate());
        orderDetail.setUpdateTime(DateUtils.getNowDate());
        orderDetailMapper.insertTrainOrderDetail(orderDetail);

        // 返回订单号
        result.put("orderNo", orderNo);
        return result;
    }
}