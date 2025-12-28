package com.ruoyi.train.carriage.controller;

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
import com.ruoyi.train.carriage.domain.TrainCarriage;
import com.ruoyi.train.carriage.service.ITrainCarriageService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 车厢信息Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/carriage/carriage")
public class TrainCarriageController extends BaseController
{
    @Autowired
    private ITrainCarriageService trainCarriageService;

    /**
     * 查询车厢信息列表
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainCarriage trainCarriage)
    {
        startPage();
        List<TrainCarriage> list = trainCarriageService.selectTrainCarriageList(trainCarriage);
        return getDataTable(list);
    }

    /**
     * 导出车厢信息列表
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:export')")
    @Log(title = "车厢信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainCarriage trainCarriage)
    {
        List<TrainCarriage> list = trainCarriageService.selectTrainCarriageList(trainCarriage);
        ExcelUtil<TrainCarriage> util = new ExcelUtil<TrainCarriage>(TrainCarriage.class);
        util.exportExcel(response, list, "车厢信息数据");
    }

    /**
     * 获取车厢信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:query')")
    @GetMapping(value = "/{carriageId}")
    public AjaxResult getInfo(@PathVariable("carriageId") Long carriageId)
    {
        return success(trainCarriageService.selectTrainCarriageByCarriageId(carriageId));
    }

    /**
     * 新增车厢信息
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:add')")
    @Log(title = "车厢信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainCarriage trainCarriage)
    {
        return toAjax(trainCarriageService.insertTrainCarriage(trainCarriage));
    }

    /**
     * 修改车厢信息
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:edit')")
    @Log(title = "车厢信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainCarriage trainCarriage)
    {
        return toAjax(trainCarriageService.updateTrainCarriage(trainCarriage));
    }

    /**
     * 删除车厢信息
     */
    @PreAuthorize("@ss.hasPermi('carriage:carriage:remove')")
    @Log(title = "车厢信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{carriageIds}")
    public AjaxResult remove(@PathVariable Long[] carriageIds)
    {
        return toAjax(trainCarriageService.deleteTrainCarriageByCarriageIds(carriageIds));
    }
}
