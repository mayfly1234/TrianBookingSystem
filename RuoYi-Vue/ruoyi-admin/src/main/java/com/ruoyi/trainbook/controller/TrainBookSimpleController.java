package com.ruoyi.trainbook.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.trainbook.domain.vo.TrainBookSubmitVO;
import com.ruoyi.trainbook.service.ITrainBookSimpleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Map;

/**
 * 火车订票控制器（简化版，适配课程设计）
 *
 * @author ruoyi
 */
@RestController
@RequestMapping("/trainbook/simple")
public class TrainBookSimpleController extends BaseController {

    @Autowired
    private ITrainBookSimpleService trainBookSimpleService;

    /**
     * 提交订票信息
     */
    @Log(title = "火车订票", businessType = BusinessType.INSERT)
    @PostMapping("/submit")
    public AjaxResult submitBook(@Valid @RequestBody TrainBookSubmitVO submitVO) {
        try {
            // 获取登录用户ID（若依内置方法，测试可固定为1）
            Long userId = getUserId();
            // 测试环境替换：Long userId = 1L;

            // 调用Service处理订票
            Map<String, Object> result = trainBookSimpleService.submitBook(submitVO, userId);

            return AjaxResult.success("订票成功", result);
        } catch (Exception e) {
            return AjaxResult.error("订票失败：" + e.getMessage());
        }
    }
}