package com.ruoyi.trainquery.service;

import com.ruoyi.trainquery.domain.vo.TrainOneKeyVO;
import java.util.List;

/**
 * 列车一站式查询服务接口
 */
public interface ITrainOneKeyQueryService {
    /**
     * 查询车次列表
     */
    List<TrainOneKeyVO> queryTrain(String startStation, String endStation, String departDate);

    /**
     * 查询所有站点
     */
    List<String> getAllStations();
}