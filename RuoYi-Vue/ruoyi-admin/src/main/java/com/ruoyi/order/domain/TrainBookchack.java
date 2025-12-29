package com.ruoyi.order.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 火车订票对象 train_book
 * 
 * @author 罗佳淦
 * @date 2025-12-29
 */
public class TrainBookchack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 订单号 */
    @Excel(name = "订单号")
    private String orderNo;

    /** 车次号 */
    @Excel(name = "车次号")
    private String trainNo;

    /** 时刻表ID */
    @Excel(name = "时刻表ID")
    private Long scheduleId;

    /** 出发站 */
    @Excel(name = "出发站")
    private String startStation;

    /** 到达站 */
    @Excel(name = "到达站")
    private String endStation;

    /** 发车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发车日期", width = 30, dateFormat = "yyyy-MM-dd")
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

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public void setOrderNo(String orderNo) 
    {
        this.orderNo = orderNo;
    }

    public String getOrderNo() 
    {
        return orderNo;
    }

    public void setTrainNo(String trainNo) 
    {
        this.trainNo = trainNo;
    }

    public String getTrainNo() 
    {
        return trainNo;
    }

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setStartStation(String startStation) 
    {
        this.startStation = startStation;
    }

    public String getStartStation() 
    {
        return startStation;
    }

    public void setEndStation(String endStation) 
    {
        this.endStation = endStation;
    }

    public String getEndStation() 
    {
        return endStation;
    }

    public void setDepartDate(Date departDate) 
    {
        this.departDate = departDate;
    }

    public Date getDepartDate() 
    {
        return departDate;
    }

    public void setPassengerName(String passengerName) 
    {
        this.passengerName = passengerName;
    }

    public String getPassengerName() 
    {
        return passengerName;
    }

    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }

    public void setPhone(String phone) 
    {
        this.phone = phone;
    }

    public String getPhone() 
    {
        return phone;
    }

    public void setCarriageNo(String carriageNo) 
    {
        this.carriageNo = carriageNo;
    }

    public String getCarriageNo() 
    {
        return carriageNo;
    }

    public void setSeatNo(String seatNo) 
    {
        this.seatNo = seatNo;
    }

    public String getSeatNo() 
    {
        return seatNo;
    }

    public void setSeatType(String seatType) 
    {
        this.seatType = seatType;
    }

    public String getSeatType() 
    {
        return seatType;
    }

    public void setTicketPrice(BigDecimal ticketPrice) 
    {
        this.ticketPrice = ticketPrice;
    }

    public BigDecimal getTicketPrice() 
    {
        return ticketPrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("orderNo", getOrderNo())
            .append("trainNo", getTrainNo())
            .append("scheduleId", getScheduleId())
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
            .append("createTime", getCreateTime())
            .toString();
    }
}
