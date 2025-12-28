package com.ruoyi.train.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.mapper.TrainBasicMapper;
import com.ruoyi.train.domain.TrainBasic;
import com.ruoyi.train.service.ITrainBasicService;

/**
 * 列车基础信息Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class TrainBasicServiceImpl implements ITrainBasicService 
{
    @Autowired
    private TrainBasicMapper trainBasicMapper;

    /**
     * 查询列车基础信息
     * 
     * @param basicId 列车基础信息主键
     * @return 列车基础信息
     */
    @Override
    public TrainBasic selectTrainBasicByBasicId(Long basicId)
    {
        return trainBasicMapper.selectTrainBasicByBasicId(basicId);
    }

    /**
     * 查询列车基础信息列表
     * 
     * @param trainBasic 列车基础信息
     * @return 列车基础信息
     */
    @Override
    public List<TrainBasic> selectTrainBasicList(TrainBasic trainBasic)
    {
        return trainBasicMapper.selectTrainBasicList(trainBasic);
    }

    /**
     * 新增列车基础信息
     * 
     * @param trainBasic 列车基础信息
     * @return 结果
     */
    @Override
    public int insertTrainBasic(TrainBasic trainBasic)
    {
        trainBasic.setCreateTime(DateUtils.getNowDate());
        return trainBasicMapper.insertTrainBasic(trainBasic);
    }

    /**
     * 修改列车基础信息
     * 
     * @param trainBasic 列车基础信息
     * @return 结果
     */
    @Override
    public int updateTrainBasic(TrainBasic trainBasic)
    {
        trainBasic.setUpdateTime(DateUtils.getNowDate());
        return trainBasicMapper.updateTrainBasic(trainBasic);
    }

    /**
     * 批量删除列车基础信息
     * 
     * @param basicIds 需要删除的列车基础信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainBasicByBasicIds(Long[] basicIds)
    {
        return trainBasicMapper.deleteTrainBasicByBasicIds(basicIds);
    }

    /**
     * 删除列车基础信息信息
     * 
     * @param basicId 列车基础信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainBasicByBasicId(Long basicId)
    {
        return trainBasicMapper.deleteTrainBasicByBasicId(basicId);
    }
}
