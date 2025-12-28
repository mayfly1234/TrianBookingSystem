package com.ruoyi.train.controller;

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
import com.ruoyi.train.domain.TrainBasic;
import com.ruoyi.train.service.ITrainBasicService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 列车基础信息Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/basic/basic")
public class TrainBasicController extends BaseController
{
    @Autowired
    private ITrainBasicService trainBasicService;

    /**
     * 查询列车基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainBasic trainBasic)
    {
        startPage();
        List<TrainBasic> list = trainBasicService.selectTrainBasicList(trainBasic);
        return getDataTable(list);
    }

    /**
     * 导出列车基础信息列表
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:export')")
    @Log(title = "列车基础信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainBasic trainBasic)
    {
        List<TrainBasic> list = trainBasicService.selectTrainBasicList(trainBasic);
        ExcelUtil<TrainBasic> util = new ExcelUtil<TrainBasic>(TrainBasic.class);
        util.exportExcel(response, list, "列车基础信息数据");
    }

    /**
     * 获取列车基础信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:query')")
    @GetMapping(value = "/{basicId}")
    public AjaxResult getInfo(@PathVariable("basicId") Long basicId)
    {
        return success(trainBasicService.selectTrainBasicByBasicId(basicId));
    }

    /**
     * 新增列车基础信息
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:add')")
    @Log(title = "列车基础信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainBasic trainBasic)
    {
        return toAjax(trainBasicService.insertTrainBasic(trainBasic));
    }

    /**
     * 修改列车基础信息
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:edit')")
    @Log(title = "列车基础信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainBasic trainBasic)
    {
        return toAjax(trainBasicService.updateTrainBasic(trainBasic));
    }

    /**
     * 删除列车基础信息
     */
    @PreAuthorize("@ss.hasPermi('basic:basic:remove')")
    @Log(title = "列车基础信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{basicIds}")
    public AjaxResult remove(@PathVariable Long[] basicIds)
    {
        return toAjax(trainBasicService.deleteTrainBasicByBasicIds(basicIds));
    }


}
