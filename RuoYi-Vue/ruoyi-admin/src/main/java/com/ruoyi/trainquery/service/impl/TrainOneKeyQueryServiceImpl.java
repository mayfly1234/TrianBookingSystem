package com.ruoyi.trainquery.service.impl;

import com.ruoyi.trainquery.mapper.TrainOneKeyQueryMapper;
import com.ruoyi.trainquery.domain.vo.SeatVO;
import com.ruoyi.trainquery.domain.vo.StationVO;
import com.ruoyi.trainquery.domain.vo.TrainOneKeyVO;
import com.ruoyi.trainquery.service.ITrainOneKeyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 列车一站式查询服务实现
 */
@Service
public class TrainOneKeyQueryServiceImpl implements ITrainOneKeyQueryService {

    @Autowired
    private TrainOneKeyQueryMapper trainOneKeyQueryMapper;

    @Override
    public List<TrainOneKeyVO> queryTrain(String startStation, String endStation, String departDate) {
        // 1. 查车次基础信息（XML已查询schedule_id，自动映射到VO的scheduleId，但兜底赋值更稳妥）
        List<TrainOneKeyVO> voList = trainOneKeyQueryMapper.selectTrainByCondition(startStation, endStation, departDate);
        // 2. 补充座位和站点
        for (TrainOneKeyVO vo : voList) {
            Long scheduleId = trainOneKeyQueryMapper.selectScheduleIdByTrainNoAndDate(vo.getTrainNo(), departDate);
            if (scheduleId != null) {
                // ========== 新增：将scheduleId赋值到VO中（核心！确保前端能拿到） ==========
                vo.setScheduleId(scheduleId);

                List<SeatVO> seatList = trainOneKeyQueryMapper.selectSeatByScheduleId(scheduleId);
                vo.setSeatList(seatList);
                List<StationVO> stationList = trainOneKeyQueryMapper.selectStationByScheduleId(scheduleId);
                vo.setStationList(stationList);
            }
        }
        return voList;
    }

    @Override
    public List<String> getAllStations() {
        return trainOneKeyQueryMapper.selectAllStations();
    }
}