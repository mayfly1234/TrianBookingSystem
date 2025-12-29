package com.ruoyi.order.controller;

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
import com.ruoyi.order.domain.TrainBookchack;
import com.ruoyi.order.service.ITrainBookchackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 火车订票Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-29
 */
@RestController
@RequestMapping("/order/order")
public class TrainBookchackController extends BaseController
{
    @Autowired
    private ITrainBookchackService trainBookchackService;

    /**
     * 查询火车订票列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainBookchack trainBookchack)
    {
        startPage();
        List<TrainBookchack> list = trainBookchackService.selectTrainBookchackList(trainBookchack);
        return getDataTable(list);
    }

    /**
     * 导出火车订票列表
     */
    @PreAuthorize("@ss.hasPermi('order:order:export')")
    @Log(title = "火车订票", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainBookchack trainBookchack)
    {
        List<TrainBookchack> list = trainBookchackService.selectTrainBookchackList(trainBookchack);
        ExcelUtil<TrainBookchack> util = new ExcelUtil<TrainBookchack>(TrainBookchack.class);
        util.exportExcel(response, list, "火车订票数据");
    }

    /**
     * 获取火车订票详细信息
     */
    @PreAuthorize("@ss.hasPermi('order:order:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(trainBookchackService.selectTrainBookchackById(id));
    }

    /**
     * 新增火车订票
     */
    @PreAuthorize("@ss.hasPermi('order:order:add')")
    @Log(title = "火车订票", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainBookchack trainBookchack)
    {
        return toAjax(trainBookchackService.insertTrainBookchack(trainBookchack));
    }

    /**
     * 修改火车订票
     */
    @PreAuthorize("@ss.hasPermi('order:order:edit')")
    @Log(title = "火车订票", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainBookchack trainBookchack)
    {
        return toAjax(trainBookchackService.updateTrainBookchack(trainBookchack));
    }

    /**
     * 删除火车订票
     */
    @PreAuthorize("@ss.hasPermi('order:order:remove')")
    @Log(title = "火车订票", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(trainBookchackService.deleteTrainBookchackByIds(ids));
    }
}
