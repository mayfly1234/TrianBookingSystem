package com.ruoyi.info.controller;

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
import com.ruoyi.info.domain.MessageInfo;
import com.ruoyi.info.service.IMessageInfoService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公告信息Controller
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@RestController
@RequestMapping("/info/info")
public class MessageInfoController extends BaseController
{
    @Autowired
    private IMessageInfoService messageInfoService;

    /**
     * 查询公告信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:list')")
    @GetMapping("/list")
    public TableDataInfo list(MessageInfo messageInfo)
    {
        startPage();
        List<MessageInfo> list = messageInfoService.selectMessageInfoList(messageInfo);
        return getDataTable(list);
    }

    /**
     * 导出公告信息列表
     */
    @PreAuthorize("@ss.hasPermi('info:info:export')")
    @Log(title = "公告信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, MessageInfo messageInfo)
    {
        List<MessageInfo> list = messageInfoService.selectMessageInfoList(messageInfo);
        ExcelUtil<MessageInfo> util = new ExcelUtil<MessageInfo>(MessageInfo.class);
        util.exportExcel(response, list, "公告信息数据");
    }

    /**
     * 获取公告信息详细信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:query')")
    @GetMapping(value = "/{infoId}")
    public AjaxResult getInfo(@PathVariable("infoId") String infoId)
    {
        return success(messageInfoService.selectMessageInfoByInfoId(infoId));
    }

    /**
     * 新增公告信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:add')")
    @Log(title = "公告信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody MessageInfo messageInfo)
    {
        return toAjax(messageInfoService.insertMessageInfo(messageInfo));
    }

    /**
     * 修改公告信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:edit')")
    @Log(title = "公告信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody MessageInfo messageInfo)
    {
        return toAjax(messageInfoService.updateMessageInfo(messageInfo));
    }

    /**
     * 删除公告信息
     */
    @PreAuthorize("@ss.hasPermi('info:info:remove')")
    @Log(title = "公告信息", businessType = BusinessType.DELETE)
	@DeleteMapping("/{infoIds}")
    public AjaxResult remove(@PathVariable String[] infoIds)
    {
        return toAjax(messageInfoService.deleteMessageInfoByInfoIds(infoIds));
    }
}
