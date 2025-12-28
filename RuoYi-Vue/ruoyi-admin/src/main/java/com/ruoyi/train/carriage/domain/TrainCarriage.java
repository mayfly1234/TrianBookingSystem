package com.ruoyi.train.carriage.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车厢信息对象 train_carriage
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class TrainCarriage extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long carriageId;

    /** 车次ID */
    @Excel(name = "车次ID")
    private Long scheduleId;

    /** 车厢号 */
    @Excel(name = "车厢号")
    private String carriageNo;

    /** 车厢类型 */
    @Excel(name = "车厢类型")
    private String carriageType;

    /** 总座位数 */
    @Excel(name = "总座位数")
    private Long totalSeats;

    /** 可用座位数 */
    @Excel(name = "可用座位数")
    private Long availableSeats;

    public void setCarriageId(Long carriageId) 
    {
        this.carriageId = carriageId;
    }

    public Long getCarriageId() 
    {
        return carriageId;
    }

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setCarriageNo(String carriageNo) 
    {
        this.carriageNo = carriageNo;
    }

    public String getCarriageNo() 
    {
        return carriageNo;
    }

    public void setCarriageType(String carriageType) 
    {
        this.carriageType = carriageType;
    }

    public String getCarriageType() 
    {
        return carriageType;
    }

    public void setTotalSeats(Long totalSeats) 
    {
        this.totalSeats = totalSeats;
    }

    public Long getTotalSeats() 
    {
        return totalSeats;
    }

    public void setAvailableSeats(Long availableSeats) 
    {
        this.availableSeats = availableSeats;
    }

    public Long getAvailableSeats() 
    {
        return availableSeats;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("carriageId", getCarriageId())
            .append("scheduleId", getScheduleId())
            .append("carriageNo", getCarriageNo())
            .append("carriageType", getCarriageType())
            .append("totalSeats", getTotalSeats())
            .append("availableSeats", getAvailableSeats())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
