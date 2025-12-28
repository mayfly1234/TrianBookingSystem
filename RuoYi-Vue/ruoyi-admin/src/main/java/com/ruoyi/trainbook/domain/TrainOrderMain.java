package com.ruoyi.trainbook.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订票订单主表
 * 订单状态：0-待支付 1-已支付 2-已取消 3-已退票 4-已完成
 *
 * @author ruoyi
 */
public class TrainOrderMain extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单ID */
    private Long id;

    /** 订单编号 */
    @Excel(name = "订单编号")
    private String orderNo;

    /** 用户ID */
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

    /** 订单总金额 */
    @Excel(name = "订单总金额")
    private BigDecimal totalAmount;

    /** 订单状态 */
    @Excel(name = "订单状态", readConverterExp = "0=待支付,1=已支付,2=已取消,3=已退票,4=已完成")
    private String orderStatus;

    /** 支付时间 */
    @Excel(name = "支付时间")
    private Date payTime;

    /** 订单过期时间 */
    @Excel(name = "过期时间")
    private Date expireTime;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getOrderNo()
    {
        return orderNo;
    }

    public void setOrderNo(String orderNo)
    {
        this.orderNo = orderNo;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public String getTrainNo()
    {
        return trainNo;
    }

    public void setTrainNo(String trainNo)
    {
        this.trainNo = trainNo;
    }

    public String getStartStation()
    {
        return startStation;
    }

    public void setStartStation(String startStation)
    {
        this.startStation = startStation;
    }

    public String getEndStation()
    {
        return endStation;
    }

    public void setEndStation(String endStation)
    {
        this.endStation = endStation;
    }

    public Date getDepartDate()
    {
        return departDate;
    }

    public void setDepartDate(Date departDate)
    {
        this.departDate = departDate;
    }

    public BigDecimal getTotalAmount()
    {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount)
    {
        this.totalAmount = totalAmount;
    }

    public String getOrderStatus()
    {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus)
    {
        this.orderStatus = orderStatus;
    }

    public Date getPayTime()
    {
        return payTime;
    }

    public void setPayTime(Date payTime)
    {
        this.payTime = payTime;
    }

    public Date getExpireTime()
    {
        return expireTime;
    }

    public void setExpireTime(Date expireTime)
    {
        this.expireTime = expireTime;
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
                .append("totalAmount", getTotalAmount())
                .append("orderStatus", getOrderStatus())
                .append("payTime", getPayTime())
                .append("expireTime", getExpireTime())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}