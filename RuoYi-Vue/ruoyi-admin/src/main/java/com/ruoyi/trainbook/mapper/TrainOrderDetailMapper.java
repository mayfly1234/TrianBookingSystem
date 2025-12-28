package com.ruoyi.trainbook.mapper;

import com.ruoyi.trainbook.domain.TrainOrderDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 订票订单详情Mapper接口
 *
 * @author ruoyi
 */
@Mapper
public interface TrainOrderDetailMapper
{
    /**
     * 查询订单详情
     *
     * @param id 详情主键
     * @return 订单详情
     */
    public TrainOrderDetail selectTrainOrderDetailById(Long id);

    /**
     * 查询订单详情列表
     *
     * @param trainOrderDetail 订单详情
     * @return 订单详情集合
     */
    public List<TrainOrderDetail> selectTrainOrderDetailList(TrainOrderDetail trainOrderDetail);

    /**
     * 新增订单详情
     *
     * @param trainOrderDetail 订单详情
     * @return 结果
     */
    public int insertTrainOrderDetail(TrainOrderDetail trainOrderDetail);

    /**
     * 修改订单详情
     *
     * @param trainOrderDetail 订单详情
     * @return 结果
     */
    public int updateTrainOrderDetail(TrainOrderDetail trainOrderDetail);

    /**
     * 删除订单详情
     *
     * @param id 详情主键
     * @return 结果
     */
    public int deleteTrainOrderDetailById(Long id);

    /**
     * 批量删除订单详情
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainOrderDetailByIds(Long[] ids);
}