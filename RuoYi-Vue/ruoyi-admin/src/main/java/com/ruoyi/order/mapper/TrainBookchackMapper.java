package com.ruoyi.order.mapper;

import java.util.List;
import com.ruoyi.order.domain.TrainBookchack;

/**
 * 火车订票Mapper接口
 * 
 * @author 罗佳淦
 * @date 2025-12-29
 */
public interface TrainBookchackMapper 
{
    /**
     * 查询火车订票
     * 
     * @param id 火车订票主键
     * @return 火车订票
     */
    public TrainBookchack selectTrainBookchackById(Long id);

    /**
     * 查询火车订票列表
     * 
     * @param trainBookchack 火车订票
     * @return 火车订票集合
     */
    public List<TrainBookchack> selectTrainBookchackList(TrainBookchack trainBookchack);

    /**
     * 新增火车订票
     * 
     * @param trainBookchack 火车订票
     * @return 结果
     */
    public int insertTrainBookchack(TrainBookchack trainBookchack);

    /**
     * 修改火车订票
     * 
     * @param trainBookchack 火车订票
     * @return 结果
     */
    public int updateTrainBookchack(TrainBookchack trainBookchack);

    /**
     * 删除火车订票
     * 
     * @param id 火车订票主键
     * @return 结果
     */
    public int deleteTrainBookchackById(Long id);

    /**
     * 批量删除火车订票
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainBookchackByIds(Long[] ids);
}
