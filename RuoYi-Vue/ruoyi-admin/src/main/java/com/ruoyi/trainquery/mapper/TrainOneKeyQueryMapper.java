package com.ruoyi.trainquery.mapper;

import com.ruoyi.trainquery.domain.vo.SeatVO;
import com.ruoyi.trainquery.domain.vo.StationVO;
import com.ruoyi.trainquery.domain.vo.TrainOneKeyVO;
import org.apache.ibatis.annotations.Param; // 新增导入这个注解
import java.util.List;

public interface TrainOneKeyQueryMapper {
    /**
     * 按条件查询车次（添加@Param注解，匹配XML中的参数名）
     */
    List<TrainOneKeyVO> selectTrainByCondition(
            @Param("startStation") String startStation,  // 对应XML中的#{startStation}
            @Param("endStation") String endStation,      // 对应XML中的#{endStation}
            @Param("departDate") String departDate       // 对应XML中的#{departDate}
    );

    /**
     * 查询座位信息（添加@Param）
     */
    List<SeatVO> selectSeatByScheduleId(@Param("scheduleId") Long scheduleId);

    /**
     * 查询经停站点（添加@Param）
     */
    List<StationVO> selectStationByScheduleId(@Param("scheduleId") Long scheduleId);

    /**
     * 查scheduleId（添加@Param）
     */
    Long selectScheduleIdByTrainNoAndDate(
            @Param("trainNo") String trainNo,
            @Param("departDate") String departDate
    );

    /**
     * 查询所有站点（无参数，无需改）
     */
    List<String> selectAllStations();
}