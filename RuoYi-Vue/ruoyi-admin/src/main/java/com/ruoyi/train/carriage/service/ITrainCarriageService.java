package com.ruoyi.train.carriage.service;

import java.util.List;
import com.ruoyi.train.carriage.domain.TrainCarriage;

/**
 * 车厢信息Service接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface ITrainCarriageService 
{
    /**
     * 查询车厢信息
     * 
     * @param carriageId 车厢信息主键
     * @return 车厢信息
     */
    public TrainCarriage selectTrainCarriageByCarriageId(Long carriageId);

    /**
     * 查询车厢信息列表
     * 
     * @param trainCarriage 车厢信息
     * @return 车厢信息集合
     */
    public List<TrainCarriage> selectTrainCarriageList(TrainCarriage trainCarriage);

    /**
     * 新增车厢信息
     * 
     * @param trainCarriage 车厢信息
     * @return 结果
     */
    public int insertTrainCarriage(TrainCarriage trainCarriage);

    /**
     * 修改车厢信息
     * 
     * @param trainCarriage 车厢信息
     * @return 结果
     */
    public int updateTrainCarriage(TrainCarriage trainCarriage);

    /**
     * 批量删除车厢信息
     * 
     * @param carriageIds 需要删除的车厢信息主键集合
     * @return 结果
     */
    public int deleteTrainCarriageByCarriageIds(Long[] carriageIds);

    /**
     * 删除车厢信息信息
     * 
     * @param carriageId 车厢信息主键
     * @return 结果
     */
    public int deleteTrainCarriageByCarriageId(Long carriageId);
}
