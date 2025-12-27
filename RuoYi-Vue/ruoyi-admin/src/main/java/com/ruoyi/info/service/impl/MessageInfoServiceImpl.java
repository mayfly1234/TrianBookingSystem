package com.ruoyi.info.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.info.mapper.MessageInfoMapper;
import com.ruoyi.info.domain.MessageInfo;
import com.ruoyi.info.service.IMessageInfoService;

/**
 * 公告信息Service业务层处理
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class MessageInfoServiceImpl implements IMessageInfoService 
{
    @Autowired
    private MessageInfoMapper messageInfoMapper;

    /**
     * 查询公告信息
     * 
     * @param infoId 公告信息主键
     * @return 公告信息
     */
    @Override
    public MessageInfo selectMessageInfoByInfoId(String infoId)
    {
        return messageInfoMapper.selectMessageInfoByInfoId(infoId);
    }

    /**
     * 查询公告信息列表
     * 
     * @param messageInfo 公告信息
     * @return 公告信息
     */
    @Override
    public List<MessageInfo> selectMessageInfoList(MessageInfo messageInfo)
    {
        return messageInfoMapper.selectMessageInfoList(messageInfo);
    }

    /**
     * 新增公告信息
     * 
     * @param messageInfo 公告信息
     * @return 结果
     */
    @Override
    public int insertMessageInfo(MessageInfo messageInfo)
    {
        return messageInfoMapper.insertMessageInfo(messageInfo);
    }

    /**
     * 修改公告信息
     * 
     * @param messageInfo 公告信息
     * @return 结果
     */
    @Override
    public int updateMessageInfo(MessageInfo messageInfo)
    {
        return messageInfoMapper.updateMessageInfo(messageInfo);
    }

    /**
     * 批量删除公告信息
     * 
     * @param infoIds 需要删除的公告信息主键
     * @return 结果
     */
    @Override
    public int deleteMessageInfoByInfoIds(String[] infoIds)
    {
        return messageInfoMapper.deleteMessageInfoByInfoIds(infoIds);
    }

    /**
     * 删除公告信息信息
     * 
     * @param infoId 公告信息主键
     * @return 结果
     */
    @Override
    public int deleteMessageInfoByInfoId(String infoId)
    {
        return messageInfoMapper.deleteMessageInfoByInfoId(infoId);
    }
}
