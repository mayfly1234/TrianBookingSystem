package com.ruoyi.trainquery.domain.vo;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 列车一站式查询返回VO
 */
public class TrainOneKeyVO {
    // 新增：车次唯一标识ID（核心，前端需要的字段）
    private Long scheduleId;        // 车次ID（对应数据库的schedule_id）

    // 列车核心信息（原有字段，完全保留）
    private String trainNo;        // 列车号（如G123）
    private String trainType;      // 列车类型（高铁/动车/普快）
    private String startStation;   // 出发站
    private String endStation;     // 到达站
    private String departDate;     // 发车日期（yyyy-MM-dd）
    private String startTime;      // 发车时间（HH:mm:ss）
    private String arriveTime;     // 到达时间（HH:mm:ss）
    private String totalDuration;  // 总时长

    // 座位信息列表（原有字段）
    private List<SeatVO> seatList;
    // 经停站点列表（原有字段）
    private List<StationVO> stationList;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("scheduleId", scheduleId) // 新增：toString中加入车次ID
                .append("trainNo", trainNo)
                .append("trainType", trainType)
                .append("startStation", startStation)
                .append("endStation", endStation)
                .append("departDate", departDate)
                .append("startTime", startTime)
                .append("arriveTime", arriveTime)
                .append("totalDuration", totalDuration)
                .append("seatList", seatList)
                .append("stationList", stationList)
                .toString();
    }

    // 新增：scheduleId的getter/setter（必须加，否则JSON序列化会丢失该字段）
    public Long getScheduleId() {
        return scheduleId;
    }
    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    // 原有字段的getter/setter（完全保留，无任何改动）
    public String getTrainNo() { return trainNo; }
    public void setTrainNo(String trainNo) { this.trainNo = trainNo; }
    public String getTrainType() { return trainType; }
    public void setTrainType(String trainType) { this.trainType = trainType; }
    public String getStartStation() { return startStation; }
    public void setStartStation(String startStation) { this.startStation = startStation; }
    public String getEndStation() { return endStation; }
    public void setEndStation(String endStation) { this.endStation = endStation; }
    public String getDepartDate() { return departDate; }
    public void setDepartDate(String departDate) { this.departDate = departDate; }
    public String getStartTime() { return startTime; }
    public void setStartTime(String startTime) { this.startTime = startTime; }
    public String getArriveTime() { return arriveTime; }
    public void setArriveTime(String arriveTime) { this.arriveTime = arriveTime; }
    public String getTotalDuration() { return totalDuration; }
    public void setTotalDuration(String totalDuration) { this.totalDuration = totalDuration; }
    public List<SeatVO> getSeatList() { return seatList; }
    public void setSeatList(List<SeatVO> seatList) { this.seatList = seatList; }
    public List<StationVO> getStationList() { return stationList; }
    public void setStationList(List<StationVO> stationList) { this.stationList = stationList; }
}