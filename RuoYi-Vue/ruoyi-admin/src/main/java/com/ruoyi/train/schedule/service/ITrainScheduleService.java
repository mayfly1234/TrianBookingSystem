package com.ruoyi.train.schedule.service;

import java.util.List;
import com.ruoyi.train.schedule.domain.TrainSchedule;

/**
 * 车次信息Service接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface ITrainScheduleService 
{
    /**
     * 查询车次信息
     * 
     * @param scheduleId 车次信息主键
     * @return 车次信息
     */
    public TrainSchedule selectTrainScheduleByScheduleId(Long scheduleId);

    /**
     * 查询车次信息列表
     * 
     * @param trainSchedule 车次信息
     * @return 车次信息集合
     */
    public List<TrainSchedule> selectTrainScheduleList(TrainSchedule trainSchedule);

    /**
     * 新增车次信息
     * 
     * @param trainSchedule 车次信息
     * @return 结果
     */
    public int insertTrainSchedule(TrainSchedule trainSchedule);

    /**
     * 修改车次信息
     * 
     * @param trainSchedule 车次信息
     * @return 结果
     */
    public int updateTrainSchedule(TrainSchedule trainSchedule);

    /**
     * 批量删除车次信息
     * 
     * @param scheduleIds 需要删除的车次信息主键集合
     * @return 结果
     */
    public int deleteTrainScheduleByScheduleIds(Long[] scheduleIds);

    /**
     * 删除车次信息信息
     * 
     * @param scheduleId 车次信息主键
     * @return 结果
     */
    public int deleteTrainScheduleByScheduleId(Long scheduleId);
}
