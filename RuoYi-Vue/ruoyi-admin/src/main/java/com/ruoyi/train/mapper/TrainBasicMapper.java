package com.ruoyi.train.mapper;

import java.util.List;
import com.ruoyi.train.domain.TrainBasic;

/**
 * 列车基础信息Mapper接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface TrainBasicMapper 
{
    /**
     * 查询列车基础信息
     * 
     * @param basicId 列车基础信息主键
     * @return 列车基础信息
     */
    public TrainBasic selectTrainBasicByBasicId(Long basicId);

    /**
     * 查询列车基础信息列表
     * 
     * @param trainBasic 列车基础信息
     * @return 列车基础信息集合
     */
    public List<TrainBasic> selectTrainBasicList(TrainBasic trainBasic);

    /**
     * 新增列车基础信息
     * 
     * @param trainBasic 列车基础信息
     * @return 结果
     */
    public int insertTrainBasic(TrainBasic trainBasic);

    /**
     * 修改列车基础信息
     * 
     * @param trainBasic 列车基础信息
     * @return 结果
     */
    public int updateTrainBasic(TrainBasic trainBasic);

    /**
     * 删除列车基础信息
     * 
     * @param basicId 列车基础信息主键
     * @return 结果
     */
    public int deleteTrainBasicByBasicId(Long basicId);

    /**
     * 批量删除列车基础信息
     * 
     * @param basicIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainBasicByBasicIds(Long[] basicIds);
}
