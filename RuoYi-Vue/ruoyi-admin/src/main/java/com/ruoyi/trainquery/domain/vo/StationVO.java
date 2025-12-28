package com.ruoyi.trainquery.domain.vo;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 经停站点VO
 */
public class StationVO {
    private Integer stationOrder;  // 站点顺序
    private String stationName;    // 站点名
    private String arriveTime;     // 到站时间
    private String departTime;     // 发车时间
    private String stopDuration;   // 停靠时长
    private Integer mileage;       // 累计里程

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("stationOrder", stationOrder)
                .append("stationName", stationName)
                .append("arriveTime", arriveTime)
                .append("departTime", departTime)
                .append("stopDuration", stopDuration)
                .append("mileage", mileage)
                .toString();
    }

    // get/set
    public Integer getStationOrder() { return stationOrder; }
    public void setStationOrder(Integer stationOrder) { this.stationOrder = stationOrder; }
    public String getStationName() { return stationName; }
    public void setStationName(String stationName) { this.stationName = stationName; }
    public String getArriveTime() { return arriveTime; }
    public void setArriveTime(String arriveTime) { this.arriveTime = arriveTime; }
    public String getDepartTime() { return departTime; }
    public void setDepartTime(String departTime) { this.departTime = departTime; }
    public String getStopDuration() { return stopDuration; }
    public void setStopDuration(String stopDuration) { this.stopDuration = stopDuration; }
    public Integer getMileage() { return mileage; }
    public void setMileage(Integer mileage) { this.mileage = mileage; }
}