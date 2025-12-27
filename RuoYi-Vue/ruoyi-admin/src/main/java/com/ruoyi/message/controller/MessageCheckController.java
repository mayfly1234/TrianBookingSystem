package com.ruoyi.message.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// 替换：导入若依的匿名访问注解（核心）
import com.ruoyi.common.annotation.Anonymous;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.message.domain.MessageCheck;
import com.ruoyi.message.service.IMessageCheckService;

/**
 * 查看公告Controller
 */
@RestController
@RequestMapping("/message/check")
@Anonymous // 核心：替换为@Anonymous，放行整个Controller（跳过Token+权限）
public class MessageCheckController extends BaseController
{
    @Autowired
    private IMessageCheckService messageCheckService;

    /**
     * 查询查看公告列表
     */
    @GetMapping("/list")
    // 注释掉权限注解（彻底放行）
    // @PreAuthorize("@ss.hasPermi('message:check:list')")
    public TableDataInfo list(MessageCheck messageCheck)
    {
        startPage(); // 若依分页工具，必须保留
        List<MessageCheck> list = messageCheckService.selectMessageCheckList(messageCheck);
        return getDataTable(list); // 返回标准分页格式
    }

    /**
     * 根据infoId查询查看公告
     */
    @GetMapping(value = "/{infoId}")
    // 注释掉权限注解
    // @PreAuthorize("@ss.hasPermi('message:check:query')")
    public AjaxResult getInfo(@PathVariable("infoId") String infoId)
    {
        return success(messageCheckService.selectMessageCheckById(infoId));
    }
}
