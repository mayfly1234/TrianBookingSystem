package com.ruoyi.trainbook.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 火车订票实体（单表存储，适配课程设计场景）
 *
 * @author ruoyi
 */
public class TrainBook extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 用户ID（若依系统用户ID） */
    @Excel(name = "用户ID")
    private Long userId;

    /** 车次ID */
    @Excel(name = "车次ID")
    private Long scheduleId;

    /** 车次号 */
    @Excel(name = "车次号")
    private String trainNo;

    /** 出发站 */
    @Excel(name = "出发站")
    private String startStation;

    /** 到达站 */
    @Excel(name = "到达站")
    private String endStation;

    /** 发车日期 */
    @Excel(name = "发车日期")
    private Date departDate;

    /** 乘客姓名 */
    @Excel(name = "乘客姓名")
    private String passengerName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    /** 车厢号 */
    @Excel(name = "车厢号")
    private String carriageNo;

    /** 座位号 */
    @Excel(name = "座位号")
    private String seatNo;

    /** 座位类型 */
    @Excel(name = "座位类型")
    private String seatType;

    /** 票价 */
    @Excel(name = "票价")
    private BigDecimal ticketPrice;

    // ========== Getter/Setter ==========
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getEndStation() {
        return endStation;
    }

    public void setEndStation(String endStation) {
        this.endStation = endStation;
    }

    public Date getDepartDate() {
        return departDate;
    }

    public void setDepartDate(Date departDate) {
        this.departDate = departDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCarriageNo() {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo) {
        this.carriageNo = carriageNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderNo", getOrderNo())
                .append("userId", getUserId())
                .append("scheduleId", getScheduleId())
                .append("trainNo", getTrainNo())
                .append("startStation", getStartStation())
                .append("endStation", getEndStation())
                .append("departDate", getDepartDate())
                .append("passengerName", getPassengerName())
                .append("idCard", getIdCard())
                .append("phone", getPhone())
                .append("carriageNo", getCarriageNo())
                .append("seatNo", getSeatNo())
                .append("seatType", getSeatType())
                .append("ticketPrice", getTicketPrice())
                .toString();
    }
}