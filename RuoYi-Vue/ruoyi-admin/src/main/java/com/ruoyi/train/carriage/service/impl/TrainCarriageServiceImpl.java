package com.ruoyi.train.carriage.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.carriage.mapper.TrainCarriageMapper;
import com.ruoyi.train.carriage.domain.TrainCarriage;
import com.ruoyi.train.carriage.service.ITrainCarriageService;

/**
 * 车厢信息Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class TrainCarriageServiceImpl implements ITrainCarriageService 
{
    @Autowired
    private TrainCarriageMapper trainCarriageMapper;

    /**
     * 查询车厢信息
     * 
     * @param carriageId 车厢信息主键
     * @return 车厢信息
     */
    @Override
    public TrainCarriage selectTrainCarriageByCarriageId(Long carriageId)
    {
        return trainCarriageMapper.selectTrainCarriageByCarriageId(carriageId);
    }

    /**
     * 查询车厢信息列表
     * 
     * @param trainCarriage 车厢信息
     * @return 车厢信息
     */
    @Override
    public List<TrainCarriage> selectTrainCarriageList(TrainCarriage trainCarriage)
    {
        return trainCarriageMapper.selectTrainCarriageList(trainCarriage);
    }

    /**
     * 新增车厢信息
     * 
     * @param trainCarriage 车厢信息
     * @return 结果
     */
    @Override
    public int insertTrainCarriage(TrainCarriage trainCarriage)
    {
        trainCarriage.setCreateTime(DateUtils.getNowDate());
        return trainCarriageMapper.insertTrainCarriage(trainCarriage);
    }

    /**
     * 修改车厢信息
     * 
     * @param trainCarriage 车厢信息
     * @return 结果
     */
    @Override
    public int updateTrainCarriage(TrainCarriage trainCarriage)
    {
        trainCarriage.setUpdateTime(DateUtils.getNowDate());
        return trainCarriageMapper.updateTrainCarriage(trainCarriage);
    }

    /**
     * 批量删除车厢信息
     * 
     * @param carriageIds 需要删除的车厢信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainCarriageByCarriageIds(Long[] carriageIds)
    {
        return trainCarriageMapper.deleteTrainCarriageByCarriageIds(carriageIds);
    }

    /**
     * 删除车厢信息信息
     * 
     * @param carriageId 车厢信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainCarriageByCarriageId(Long carriageId)
    {
        return trainCarriageMapper.deleteTrainCarriageByCarriageId(carriageId);
    }
}
