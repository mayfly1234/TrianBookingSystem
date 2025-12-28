package com.ruoyi.train.station.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.train.station.domain.TrainScheduleStation;
import com.ruoyi.train.station.service.ITrainScheduleStationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车次经停站点Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/station/station")
public class TrainScheduleStationController extends BaseController
{
    @Autowired
    private ITrainScheduleStationService trainScheduleStationService;

    /**
     * 查询车次经停站点列表
     */
    @PreAuthorize("@ss.hasPermi('station:station:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainScheduleStation trainScheduleStation)
    {
        startPage();
        List<TrainScheduleStation> list = trainScheduleStationService.selectTrainScheduleStationList(trainScheduleStation);
        return getDataTable(list);
    }

    /**
     * 导出车次经停站点列表
     */
    @PreAuthorize("@ss.hasPermi('station:station:export')")
    @Log(title = "车次经停站点", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainScheduleStation trainScheduleStation)
    {
        List<TrainScheduleStation> list = trainScheduleStationService.selectTrainScheduleStationList(trainScheduleStation);
        ExcelUtil<TrainScheduleStation> util = new ExcelUtil<TrainScheduleStation>(TrainScheduleStation.class);
        util.exportExcel(response, list, "车次经停站点数据");
    }

    /**
     * 获取车次经停站点详细信息
     */
    @PreAuthorize("@ss.hasPermi('station:station:query')")
    @GetMapping(value = "/{stationId}")
    public AjaxResult getInfo(@PathVariable("stationId") Long stationId)
    {
        return success(trainScheduleStationService.selectTrainScheduleStationByStationId(stationId));
    }

    /**
     * 新增车次经停站点
     */
    @PreAuthorize("@ss.hasPermi('station:station:add')")
    @Log(title = "车次经停站点", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainScheduleStation trainScheduleStation)
    {
        return toAjax(trainScheduleStationService.insertTrainScheduleStation(trainScheduleStation));
    }

    /**
     * 修改车次经停站点
     */
    @PreAuthorize("@ss.hasPermi('station:station:edit')")
    @Log(title = "车次经停站点", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainScheduleStation trainScheduleStation)
    {
        return toAjax(trainScheduleStationService.updateTrainScheduleStation(trainScheduleStation));
    }

    /**
     * 删除车次经停站点
     */
    @PreAuthorize("@ss.hasPermi('station:station:remove')")
    @Log(title = "车次经停站点", businessType = BusinessType.DELETE)
	@DeleteMapping("/{stationIds}")
    public AjaxResult remove(@PathVariable Long[] stationIds)
    {
        return toAjax(trainScheduleStationService.deleteTrainScheduleStationByStationIds(stationIds));
    }


}
