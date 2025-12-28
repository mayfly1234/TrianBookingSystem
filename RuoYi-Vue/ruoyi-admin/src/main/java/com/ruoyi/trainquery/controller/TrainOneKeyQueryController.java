package com.ruoyi.trainquery.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.trainquery.domain.vo.TrainOneKeyVO;
import com.ruoyi.trainquery.service.ITrainOneKeyQueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

/**
 * 列车一站式查询控制器（完全独立，无增删改）
 */
@RestController
@RequestMapping("/trainquery/onekey")
public class TrainOneKeyQueryController extends BaseController {

    @Autowired
    private ITrainOneKeyQueryService trainOneKeyQueryService;

    /**
     * 一站式查询车次
     */
    @GetMapping("/query")
    public AjaxResult query(
            @RequestParam(required = false) String startStation,
            @RequestParam(required = false) String endStation,
            @RequestParam(required = false) String departDate) {
        List<TrainOneKeyVO> list = trainOneKeyQueryService.queryTrain(startStation, endStation, departDate);
        return AjaxResult.success(list);
    }

    /**
     * 查询所有站点（下拉框）
     */
    @GetMapping("/stations")
    public AjaxResult getAllStations() {
        List<String> list = trainOneKeyQueryService.getAllStations();
        return AjaxResult.success(list);
    }
}