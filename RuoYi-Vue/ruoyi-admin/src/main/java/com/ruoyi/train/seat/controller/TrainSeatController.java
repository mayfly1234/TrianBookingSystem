package com.ruoyi.train.seat.controller;

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
import com.ruoyi.train.seat.domain.TrainSeat;
import com.ruoyi.train.seat.service.ITrainSeatService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 座位信息Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/seat/seat")
public class TrainSeatController extends BaseController
{
    @Autowired
    private ITrainSeatService trainSeatService;

    /**
     * 查询座位信息列表
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:list')")
    @GetMapping("/list")
    public TableDataInfo list(TrainSeat trainSeat)
    {
        startPage();
        List<TrainSeat> list = trainSeatService.selectTrainSeatList(trainSeat);
        return getDataTable(list);
    }

    /**
     * 导出座位信息列表
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:export')")
    @Log(title = "座位信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, TrainSeat trainSeat)
    {
        List<TrainSeat> list = trainSeatService.selectTrainSeatList(trainSeat);
        ExcelUtil<TrainSeat> util = new ExcelUtil<TrainSeat>(TrainSeat.class);
        util.exportExcel(response, list, "座位信息数据");
    }

    /**
     * 获取座位信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:query')")
    @GetMapping(value = "/{seatId}")
    public AjaxResult getInfo(@PathVariable("seatId") Long seatId)
    {
        return success(trainSeatService.selectTrainSeatBySeatId(seatId));
    }

    /**
     * 新增座位信息
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:add')")
    @Log(title = "座位信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody TrainSeat trainSeat)
    {
        return toAjax(trainSeatService.insertTrainSeat(trainSeat));
    }

    /**
     * 修改座位信息
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:edit')")
    @Log(title = "座位信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody TrainSeat trainSeat)
    {
        return toAjax(trainSeatService.updateTrainSeat(trainSeat));
    }

    /**
     * 删除座位信息
     */
    @PreAuthorize("@ss.hasPermi('seat:seat:remove')")
    @Log(title = "座位信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{seatIds}")
    public AjaxResult remove(@PathVariable Long[] seatIds)
    {
        return toAjax(trainSeatService.deleteTrainSeatBySeatIds(seatIds));
    }
}
