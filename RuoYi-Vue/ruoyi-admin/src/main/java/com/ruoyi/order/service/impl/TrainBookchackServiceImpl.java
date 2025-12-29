package com.ruoyi.order.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.order.mapper.TrainBookchackMapper;
import com.ruoyi.order.domain.TrainBookchack;
import com.ruoyi.order.service.ITrainBookchackService;

/**
 * 火车订票Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-29
 */
@Service
public class TrainBookchackServiceImpl implements ITrainBookchackService 
{
    @Autowired
    private TrainBookchackMapper trainBookchackMapper;

    /**
     * 查询火车订票
     * 
     * @param id 火车订票主键
     * @return 火车订票
     */
    @Override
    public TrainBookchack selectTrainBookchackById(Long id)
    {
        return trainBookchackMapper.selectTrainBookchackById(id);
    }

    /**
     * 查询火车订票列表
     * 
     * @param trainBookchack 火车订票
     * @return 火车订票
     */
    @Override
    public List<TrainBookchack> selectTrainBookchackList(TrainBookchack trainBookchack)
    {
        return trainBookchackMapper.selectTrainBookchackList(trainBookchack);
    }

    /**
     * 新增火车订票
     * 
     * @param trainBookchack 火车订票
     * @return 结果
     */
    @Override
    public int insertTrainBookchack(TrainBookchack trainBookchack)
    {
        trainBookchack.setCreateTime(DateUtils.getNowDate());
        return trainBookchackMapper.insertTrainBookchack(trainBookchack);
    }

    /**
     * 修改火车订票
     * 
     * @param trainBookchack 火车订票
     * @return 结果
     */
    @Override
    public int updateTrainBookchack(TrainBookchack trainBookchack)
    {
        return trainBookchackMapper.updateTrainBookchack(trainBookchack);
    }

    /**
     * 批量删除火车订票
     * 
     * @param ids 需要删除的火车订票主键
     * @return 结果
     */
    @Override
    public int deleteTrainBookchackByIds(Long[] ids)
    {
        return trainBookchackMapper.deleteTrainBookchackByIds(ids);
    }

    /**
     * 删除火车订票信息
     * 
     * @param id 火车订票主键
     * @return 结果
     */
    @Override
    public int deleteTrainBookchackById(Long id)
    {
        return trainBookchackMapper.deleteTrainBookchackById(id);
    }
}
