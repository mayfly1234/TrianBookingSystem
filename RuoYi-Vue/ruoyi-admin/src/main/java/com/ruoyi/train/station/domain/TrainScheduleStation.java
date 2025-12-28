package com.ruoyi.train.station.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车次经停站点对象 train_schedule_station
 *
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class TrainScheduleStation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private Long stationId;

    /** 车次ID */
    @Excel(name = "车次ID")
    private Long scheduleId;

    /** 站点名称 */
    @Excel(name = "站点名称")
    private String stationName;

    /** 站点顺序 */
    @Excel(name = "站点顺序")
    private Long stationOrder;

    /** 到站时间 */
    @Excel(name = "到站时间")
    private String arriveTime;

    /** 发车时间 */
    @Excel(name = "发车时间")
    private String departTime;

    /** 站台号 */
    @Excel(name = "站台号")
    private String platformNo;

    /** 停靠时长 */
    @Excel(name = "停靠时长")
    private String stopDuration;

    /** 累计里程 */
    @Excel(name = "累计里程")
    private Long cumulativeMileage;

    public void setStationId(Long stationId)
    {
        this.stationId = stationId;
    }

    public Long getStationId()
    {
        return stationId;
    }

    public void setScheduleId(Long scheduleId)
    {
        this.scheduleId = scheduleId;
    }

    public Long getScheduleId()
    {
        return scheduleId;
    }

    public void setStationName(String stationName)
    {
        this.stationName = stationName;
    }

    public String getStationName()
    {
        return stationName;
    }

    public void setStationOrder(Long stationOrder)
    {
        this.stationOrder = stationOrder;
    }

    public Long getStationOrder()
    {
        return stationOrder;
    }

    public void setArriveTime(String arriveTime)
    {
        this.arriveTime = arriveTime;
    }

    public String getArriveTime()
    {
        return arriveTime;
    }

    public void setDepartTime(String departTime)
    {
        this.departTime = departTime;
    }

    public String getDepartTime()
    {
        return departTime;
    }

    public void setPlatformNo(String platformNo)
    {
        this.platformNo = platformNo;
    }

    public String getPlatformNo()
    {
        return platformNo;
    }

    public void setStopDuration(String stopDuration)
    {
        this.stopDuration = stopDuration;
    }

    public String getStopDuration()
    {
        return stopDuration;
    }

    public void setCumulativeMileage(Long cumulativeMileage)
    {
        this.cumulativeMileage = cumulativeMileage;
    }

    public Long getCumulativeMileage()
    {
        return cumulativeMileage;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationId", getStationId())
            .append("scheduleId", getScheduleId())
            .append("stationName", getStationName())
            .append("stationOrder", getStationOrder())
            .append("arriveTime", getArriveTime())
            .append("departTime", getDepartTime())
            .append("platformNo", getPlatformNo())
            .append("stopDuration", getStopDuration())
            .append("cumulativeMileage", getCumulativeMileage())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }


}


