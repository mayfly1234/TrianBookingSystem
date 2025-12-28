package com.ruoyi.train.schedule.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.schedule.mapper.TrainScheduleMapper;
import com.ruoyi.train.schedule.domain.TrainSchedule;
import com.ruoyi.train.schedule.service.ITrainScheduleService;

/**
 * 车次信息Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class TrainScheduleServiceImpl implements ITrainScheduleService 
{
    @Autowired
    private TrainScheduleMapper trainScheduleMapper;

    /**
     * 查询车次信息
     * 
     * @param scheduleId 车次信息主键
     * @return 车次信息
     */
    @Override
    public TrainSchedule selectTrainScheduleByScheduleId(Long scheduleId)
    {
        return trainScheduleMapper.selectTrainScheduleByScheduleId(scheduleId);
    }

    /**
     * 查询车次信息列表
     * 
     * @param trainSchedule 车次信息
     * @return 车次信息
     */
    @Override
    public List<TrainSchedule> selectTrainScheduleList(TrainSchedule trainSchedule)
    {
        return trainScheduleMapper.selectTrainScheduleList(trainSchedule);
    }

    /**
     * 新增车次信息
     * 
     * @param trainSchedule 车次信息
     * @return 结果
     */
    @Override
    public int insertTrainSchedule(TrainSchedule trainSchedule)
    {
        trainSchedule.setCreateTime(DateUtils.getNowDate());
        return trainScheduleMapper.insertTrainSchedule(trainSchedule);
    }

    /**
     * 修改车次信息
     * 
     * @param trainSchedule 车次信息
     * @return 结果
     */
    @Override
    public int updateTrainSchedule(TrainSchedule trainSchedule)
    {
        trainSchedule.setUpdateTime(DateUtils.getNowDate());
        return trainScheduleMapper.updateTrainSchedule(trainSchedule);
    }

    /**
     * 批量删除车次信息
     * 
     * @param scheduleIds 需要删除的车次信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainScheduleByScheduleIds(Long[] scheduleIds)
    {
        return trainScheduleMapper.deleteTrainScheduleByScheduleIds(scheduleIds);
    }

    /**
     * 删除车次信息信息
     * 
     * @param scheduleId 车次信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainScheduleByScheduleId(Long scheduleId)
    {
        return trainScheduleMapper.deleteTrainScheduleByScheduleId(scheduleId);
    }
}
