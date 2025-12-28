package com.ruoyi.train.station.mapper;

import java.util.List;
import com.ruoyi.train.station.domain.TrainScheduleStation;

/**
 * 车次经停站点Mapper接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface TrainScheduleStationMapper 
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
     * 删除车次经停站点
     * 
     * @param stationId 车次经停站点主键
     * @return 结果
     */
    public int deleteTrainScheduleStationByStationId(Long stationId);

    /**
     * 批量删除车次经停站点
     * 
     * @param stationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainScheduleStationByStationIds(Long[] stationIds);
}
