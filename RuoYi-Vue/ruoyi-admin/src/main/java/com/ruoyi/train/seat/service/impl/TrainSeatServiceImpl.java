package com.ruoyi.train.seat.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.train.seat.mapper.TrainSeatMapper;
import com.ruoyi.train.seat.domain.TrainSeat;
import com.ruoyi.train.seat.service.ITrainSeatService;

/**
 * 座位信息Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class TrainSeatServiceImpl implements ITrainSeatService 
{
    @Autowired
    private TrainSeatMapper trainSeatMapper;

    /**
     * 查询座位信息
     * 
     * @param seatId 座位信息主键
     * @return 座位信息
     */
    @Override
    public TrainSeat selectTrainSeatBySeatId(Long seatId)
    {
        return trainSeatMapper.selectTrainSeatBySeatId(seatId);
    }

    /**
     * 查询座位信息列表
     * 
     * @param trainSeat 座位信息
     * @return 座位信息
     */
    @Override
    public List<TrainSeat> selectTrainSeatList(TrainSeat trainSeat)
    {
        return trainSeatMapper.selectTrainSeatList(trainSeat);
    }

    /**
     * 新增座位信息
     * 
     * @param trainSeat 座位信息
     * @return 结果
     */
    @Override
    public int insertTrainSeat(TrainSeat trainSeat)
    {
        trainSeat.setCreateTime(DateUtils.getNowDate());
        return trainSeatMapper.insertTrainSeat(trainSeat);
    }

    /**
     * 修改座位信息
     * 
     * @param trainSeat 座位信息
     * @return 结果
     */
    @Override
    public int updateTrainSeat(TrainSeat trainSeat)
    {
        trainSeat.setUpdateTime(DateUtils.getNowDate());
        return trainSeatMapper.updateTrainSeat(trainSeat);
    }

    /**
     * 批量删除座位信息
     * 
     * @param seatIds 需要删除的座位信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainSeatBySeatIds(Long[] seatIds)
    {
        return trainSeatMapper.deleteTrainSeatBySeatIds(seatIds);
    }

    /**
     * 删除座位信息信息
     * 
     * @param seatId 座位信息主键
     * @return 结果
     */
    @Override
    public int deleteTrainSeatBySeatId(Long seatId)
    {
        return trainSeatMapper.deleteTrainSeatBySeatId(seatId);
    }
}
