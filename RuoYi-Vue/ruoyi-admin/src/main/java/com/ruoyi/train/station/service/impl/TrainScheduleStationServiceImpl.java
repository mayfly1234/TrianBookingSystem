package com.ruoyi.train.station.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.station.mapper.TrainScheduleStationMapper;
import com.ruoyi.train.station.domain.TrainScheduleStation;
import com.ruoyi.train.station.service.ITrainScheduleStationService;

/**
 * 车次经停站点Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class TrainScheduleStationServiceImpl implements ITrainScheduleStationService 
{
    @Autowired
    private TrainScheduleStationMapper trainScheduleStationMapper;

    /**
     * 查询车次经停站点
     * 
     * @param stationId 车次经停站点主键
     * @return 车次经停站点
     */
    @Override
    public TrainScheduleStation selectTrainScheduleStationByStationId(Long stationId)
    {
        return trainScheduleStationMapper.selectTrainScheduleStationByStationId(stationId);
    }

    /**
     * 查询车次经停站点列表
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 车次经停站点
     */
    @Override
    public List<TrainScheduleStation> selectTrainScheduleStationList(TrainScheduleStation trainScheduleStation)
    {
        return trainScheduleStationMapper.selectTrainScheduleStationList(trainScheduleStation);
    }

    /**
     * 新增车次经停站点
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 结果
     */
    @Override
    public int insertTrainScheduleStation(TrainScheduleStation trainScheduleStation)
    {
        trainScheduleStation.setCreateTime(DateUtils.getNowDate());
        return trainScheduleStationMapper.insertTrainScheduleStation(trainScheduleStation);
    }

    /**
     * 修改车次经停站点
     * 
     * @param trainScheduleStation 车次经停站点
     * @return 结果
     */
    @Override
    public int updateTrainScheduleStation(TrainScheduleStation trainScheduleStation)
    {
        trainScheduleStation.setUpdateTime(DateUtils.getNowDate());
        return trainScheduleStationMapper.updateTrainScheduleStation(trainScheduleStation);
    }

    /**
     * 批量删除车次经停站点
     * 
     * @param stationIds 需要删除的车次经停站点主键
     * @return 结果
     */
    @Override
    public int deleteTrainScheduleStationByStationIds(Long[] stationIds)
    {
        return trainScheduleStationMapper.deleteTrainScheduleStationByStationIds(stationIds);
    }

    /**
     * 删除车次经停站点信息
     * 
     * @param stationId 车次经停站点主键
     * @return 结果
     */
    @Override
    public int deleteTrainScheduleStationByStationId(Long stationId)
    {
        return trainScheduleStationMapper.deleteTrainScheduleStationByStationId(stationId);
    }
}
