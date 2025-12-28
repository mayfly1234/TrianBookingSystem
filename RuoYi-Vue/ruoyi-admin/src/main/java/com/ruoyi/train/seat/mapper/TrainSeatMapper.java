package com.ruoyi.train.seat.mapper;

import java.util.List;
import com.ruoyi.train.seat.domain.TrainSeat;

/**
 * 座位信息Mapper接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface TrainSeatMapper 
{
    /**
     * 查询座位信息
     * 
     * @param seatId 座位信息主键
     * @return 座位信息
     */
    public TrainSeat selectTrainSeatBySeatId(Long seatId);

    /**
     * 查询座位信息列表
     * 
     * @param trainSeat 座位信息
     * @return 座位信息集合
     */
    public List<TrainSeat> selectTrainSeatList(TrainSeat trainSeat);

    /**
     * 新增座位信息
     * 
     * @param trainSeat 座位信息
     * @return 结果
     */
    public int insertTrainSeat(TrainSeat trainSeat);

    /**
     * 修改座位信息
     * 
     * @param trainSeat 座位信息
     * @return 结果
     */
    public int updateTrainSeat(TrainSeat trainSeat);

    /**
     * 删除座位信息
     * 
     * @param seatId 座位信息主键
     * @return 结果
     */
    public int deleteTrainSeatBySeatId(Long seatId);

    /**
     * 批量删除座位信息
     * 
     * @param seatIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTrainSeatBySeatIds(Long[] seatIds);
}
