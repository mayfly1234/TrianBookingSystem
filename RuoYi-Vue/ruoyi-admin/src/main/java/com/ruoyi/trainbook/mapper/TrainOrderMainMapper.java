package com.ruoyi.trainbook.mapper;

import com.ruoyi.trainbook.domain.TrainOrderMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订票订单主表Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface TrainOrderMainMapper
{
    /**
     * 查询订单信息
     *
     * @param id 订单主键
     * @return 订单信息
     */
    public TrainOrderMain selectTrainOrderMainById(Long id);

    /**
     * 查询订单列表
     *
     * @param trainOrderMain 订单信息
     * @return 订单集合
     */
    public List<TrainOrderMain> selectTrainOrderMainList(TrainOrderMain trainOrderMain);

    /**
     * 新增订单信息
     *
     * @param trainOrderMain 订单信息
     * @return 结果
     */
    public int insertTrainOrderMain(TrainOrderMain trainOrderMain);

    /**
     * 修改订单信息
     *
     * @param trainOrderMain 订单信息
     * @return 结果
     */
    public int updateTrainOrderMain(TrainOrderMain trainOrderMain);

    /**
     * 删除订单信息
     *
     * @param id 订单主键
     * @return 结果
     */
    public int deleteTrainOrderMainById(Long id);

    /**
     * 批量删除订单信息
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainOrderMainByIds(Long[] ids);
}