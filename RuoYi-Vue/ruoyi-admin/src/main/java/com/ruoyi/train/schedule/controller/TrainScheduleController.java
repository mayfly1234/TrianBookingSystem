package com.ruoyi.train.schedule.controller;

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
import com.ruoyi.train.schedule.domain.TrainSchedule;
import com.ruoyi.train.schedule.service.ITrainScheduleService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车次信息Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/schedule/schedule")
public class TrainScheduleController extends BaseController
{
    @Autowired
    private ITrainScheduleService trainScheduleService;

    /**
     * 查询车次信息列表
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainSchedule trainSchedule)
    {
        startPage();
        List<TrainSchedule> list = trainScheduleService.selectTrainScheduleList(trainSchedule);
        return getDataTable(list);
    }

    /**
     * 导出车次信息列表
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:export')")
    @Log(title = "车次信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainSchedule trainSchedule)
    {
        List<TrainSchedule> list = trainScheduleService.selectTrainScheduleList(trainSchedule);
        ExcelUtil<TrainSchedule> util = new ExcelUtil<TrainSchedule>(TrainSchedule.class);
        util.exportExcel(response, list, "车次信息数据");
    }

    /**
     * 获取车次信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:query')")
    @GetMapping(value = "/{scheduleId}")
    public AjaxResult getInfo(@PathVariable("scheduleId") Long scheduleId)
    {
        return success(trainScheduleService.selectTrainScheduleByScheduleId(scheduleId));
    }

    /**
     * 新增车次信息
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:add')")
    @Log(title = "车次信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainSchedule trainSchedule)
    {
        return toAjax(trainScheduleService.insertTrainSchedule(trainSchedule));
    }

    /**
     * 修改车次信息
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:edit')")
    @Log(title = "车次信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainSchedule trainSchedule)
    {
        return toAjax(trainScheduleService.updateTrainSchedule(trainSchedule));
    }

    /**
     * 删除车次信息
     */
    @PreAuthorize("@ss.hasPermi('schedule:schedule:remove')")
    @Log(title = "车次信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{scheduleIds}")
    public AjaxResult remove(@PathVariable Long[] scheduleIds)
    {
        return toAjax(trainScheduleService.deleteTrainScheduleByScheduleIds(scheduleIds));
    }
}
