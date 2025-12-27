package com.ruoyi.message.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.message.mapper.MessageCheckMapper;
import com.ruoyi.message.domain.MessageCheck;
import com.ruoyi.message.service.IMessageCheckService;

/**
 * 查看公告Service业务层处理
 *
 * @author 罗佳淦
 * @date 2025-12-27
 */
@Service
public class MessageCheckServiceImpl implements IMessageCheckService
{
    @Autowired
    private MessageCheckMapper messageCheckMapper;

    /**
     * 查询查看公告列表
     */
    @Override
    public List<MessageCheck> selectMessageCheckList(MessageCheck messageCheck)
    {
        return messageCheckMapper.selectMessageCheckList(messageCheck);
    }

    /**
     * 通过infoId查询查看公告
     */
    @Override
    public MessageCheck selectMessageCheckById(String infoId)
    {
        return messageCheckMapper.selectMessageCheckById(infoId);
    }
}