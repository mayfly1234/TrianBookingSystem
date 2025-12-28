package com.ruoyi.train.seat.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 座位信息对象 train_seat
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class TrainSeat extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long seatId;

    /** 车厢ID */
    @Excel(name = "车厢ID")
    private Long carriageId;

    /** 座位编号 */
    @Excel(name = "座位编号")
    private String seatNo;

    /** 座位类型 */
    @Excel(name = "座位类型")
    private String seatType;

    /** 座位状态 */
    @Excel(name = "座位状态")
    private String seatStatus;

    public void setSeatId(Long seatId) 
    {
        this.seatId = seatId;
    }

    public Long getSeatId() 
    {
        return seatId;
    }

    public void setCarriageId(Long carriageId) 
    {
        this.carriageId = carriageId;
    }

    public Long getCarriageId() 
    {
        return carriageId;
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

    public void setSeatStatus(String seatStatus) 
    {
        this.seatStatus = seatStatus;
    }

    public String getSeatStatus() 
    {
        return seatStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("seatId", getSeatId())
            .append("carriageId", getCarriageId())
            .append("seatNo", getSeatNo())
            .append("seatType", getSeatType())
            .append("seatStatus", getSeatStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
