package com.ruoyi.train.station.service;

import java.util.List;
import com.ruoyi.train.station.domain.TrainScheduleStation;

/**
 * 车次经停站点Service接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface ITrainScheduleStationService 
{
    /**
     * 查询车次经停站点
     * 
     * @param stationId 车次经停站点主键
     * @return 车次经停站点
     */
    public TrainScheduleStation selectTrainScheduleStationByStationId(Long stationId);

    /**
     * 查询车次经停站点列表
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 车次经停站点集合
     */
    public List<TrainScheduleStation> selectTrainScheduleStationList(TrainScheduleStation trainScheduleStation);

    /**
     * 新增车次经停站点
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 结果
     */
    public int insertTrainScheduleStation(TrainScheduleStation trainScheduleStation);

    /**
     * 修改车次经停站点
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 结果
     */
    public int updateTrainScheduleStation(TrainScheduleStation trainScheduleStation);

    /**
     * 批量删除车次经停站点
     * 
     * @param stationIds 需要删除的车次经停站点主键集合
     * @return 结果
     */
    public int deleteTrainScheduleStationByStationIds(Long[] stationIds);

    /**
     * 删除车次经停站点信息
     * 
     * @param stationId 车次经停站点主键
     * @return 结果
     */
    public int deleteTrainScheduleStationByStationId(Long stationId);
}
