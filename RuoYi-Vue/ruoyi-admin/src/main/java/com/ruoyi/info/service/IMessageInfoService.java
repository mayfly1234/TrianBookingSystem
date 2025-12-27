package com.ruoyi.info.service;

import java.util.List;
import com.ruoyi.info.domain.MessageInfo;

/**
 * 公告信息Service接口
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface IMessageInfoService 
{
    /**
     * 查询公告信息
     * 
     * @param infoId 公告信息主键
     * @return 公告信息
     */
    public MessageInfo selectMessageInfoByInfoId(String infoId);

    /**
     * 查询公告信息列表
     * 
     * @param messageInfo 公告信息
     * @return 公告信息集合
     */
    public List<MessageInfo> selectMessageInfoList(MessageInfo messageInfo);

    /**
     * 新增公告信息
     * 
     * @param messageInfo 公告信息
     * @return 结果
     */
    public int insertMessageInfo(MessageInfo messageInfo);

    /**
     * 修改公告信息
     * 
     * @param messageInfo 公告信息
     * @return 结果
     */
    public int updateMessageInfo(MessageInfo messageInfo);

    /**
     * 批量删除公告信息
     * 
     * @param infoIds 需要删除的公告信息主键集合
     * @return 结果
     */
    public int deleteMessageInfoByInfoIds(String[] infoIds);

    /**
     * 删除公告信息信息
     * 
     * @param infoId 公告信息主键
     * @return 结果
     */
    public int deleteMessageInfoByInfoId(String infoId);
}
