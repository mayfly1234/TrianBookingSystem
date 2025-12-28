package com.ruoyi.trainbook.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 订票订单详情（单张车票）
 * 车票状态：0-未使用 1-已使用 2-已退票
 *
 * @author ruoyi
 */
public class TrainOrderDetail extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 详情ID */
    private Long id;

    /** 订单ID */
    @Excel(name = "订单ID")
    private Long orderId;

    /** 订单编号（冗余） */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 乘客ID */
    @Excel(name = "乘客ID")
    private Long passengerId;

    /** 车厢号 */
    @Excel(name = "车厢号")
    private String carriageNo;

    /** 座位号 */
    @Excel(name = "座位号")
    private String seatNo;

    /** 座位类型 */
    @Excel(name = "座位类型")
    private String seatType;

    /** 单张票价 */
    @Excel(name = "单张票价")
    private BigDecimal ticketPrice;

    /** 车票状态 */
    @Excel(name = "车票状态", readConverterExp = "0=未使用,1=已使用,2=已退票")
    private String ticketStatus;

    /** 乘客姓名（冗余） */
    @Excel(name = "乘客姓名")
    private String passengerName;

    /** 身份证号（冗余） */
    @Excel(name = "身份证号")
    private String idCard;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getOrderId()
    {
        return orderId;
    }

    public void setOrderId(Long orderId)
    {
        this.orderId = orderId;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public Long getPassengerId()
    {
        return passengerId;
    }

    public void setPassengerId(Long passengerId)
    {
        this.passengerId = passengerId;
    }

    public String getCarriageNo()
    {
        return carriageNo;
    }

    public void setCarriageNo(String carriageNo)
    {
        this.carriageNo = carriageNo;
    }

    public String getSeatNo()
    {
        return seatNo;
    }

    public void setSeatNo(String seatNo)
    {
        this.seatNo = seatNo;
    }

    public String getSeatType()
    {
        return seatType;
    }

    public void setSeatType(String seatType)
    {
        this.seatType = seatType;
    }

    public BigDecimal getTicketPrice()
    {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice)
    {
        this.ticketPrice = ticketPrice;
    }

    public String getTicketStatus()
    {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus)
    {
        this.ticketStatus = ticketStatus;
    }

    public String getPassengerName()
    {
        return passengerName;
    }

    public void setPassengerName(String passengerName)
    {
        this.passengerName = passengerName;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("orderId", getOrderId())
                .append("orderNo", getOrderNo())
                .append("passengerId", getPassengerId())
                .append("carriageNo", getCarriageNo())
                .append("seatNo", getSeatNo())
                .append("seatType", getSeatType())
                .append("ticketPrice", getTicketPrice())
                .append("ticketStatus", getTicketStatus())
                .append("passengerName", getPassengerName())
                .append("idCard", getIdCard())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}