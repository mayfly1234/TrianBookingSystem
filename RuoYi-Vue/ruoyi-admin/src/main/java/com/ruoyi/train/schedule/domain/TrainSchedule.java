package com.ruoyi.train.schedule.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车次信息对象 train_schedule
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class TrainSchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long scheduleId;

    /** 列车号 */
    @Excel(name = "列车号")
    private String trainNo;

    /** 发车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发车日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date departDate;

    /** 始发站 */
    @Excel(name = "始发站")
    private String startStation;

    /** 终点站 */
    @Excel(name = "终点站")
    private String endStation;

    /** 总时长 */
    @Excel(name = "总时长")
    private String totalDuration;

    /** 车次状态 */
    @Excel(name = "车次状态")
    private String status;

    public void setScheduleId(Long scheduleId) 
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId() 
    {
        return scheduleId;
    }

    public void setTrainNo(String trainNo) 
    {
        this.trainNo = trainNo;
    }

    public String getTrainNo() 
    {
        return trainNo;
    }

    public void setDepartDate(Date departDate) 
    {
        this.departDate = departDate;
    }

    public Date getDepartDate() 
    {
        return departDate;
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

    public void setTotalDuration(String totalDuration) 
    {
        this.totalDuration = totalDuration;
    }

    public String getTotalDuration() 
    {
        return totalDuration;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("scheduleId", getScheduleId())
            .append("trainNo", getTrainNo())
            .append("departDate", getDepartDate())
            .append("startStation", getStartStation())
            .append("endStation", getEndStation())
            .append("totalDuration", getTotalDuration())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
