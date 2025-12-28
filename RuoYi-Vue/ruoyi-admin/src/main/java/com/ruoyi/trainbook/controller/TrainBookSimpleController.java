package com.ruoyi.trainbook.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.trainbook.service.ITrainBookSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 极简订票控制器
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/trainbook/simple")
public class TrainBookSimpleController extends BaseController
{
    @Autowired
    private ITrainBookSimpleService trainBookSimpleService;

    /**
     * 提交订票信息
     */
    @PreAuthorize("@ss.hasPermi('train:book:submit')")
    @Log(title = "火车订票", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    public AjaxResult submitBook(@RequestBody Map<String, Object> submitData)
    {
        try
        {
            // 获取当前登录用户ID（若依内置方法）
            Long userId = getUserId();
            Map<String, Object> result = trainBookSimpleService.submitBook(submitData, userId);
            return AjaxResult.success("订票成功", result);
        }
        catch (Exception e)
        {
            return AjaxResult.error("订票失败：" + e.getMessage());
        }
    }
}