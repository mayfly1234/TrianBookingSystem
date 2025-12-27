package com.ruoyi.message.service;

import java.util.List;
import com.ruoyi.message.domain.MessageCheck;

/**
 * 查看公告Service接口
 *
 * @author 罗佳淦
 * @date 2025-12-27
 */
public interface IMessageCheckService
{
    /**
     * 查询查看公告列表
     *
     * @param messageCheck 查看公告对象
     * @return 查看公告集合
     */
    public List<MessageCheck> selectMessageCheckList(MessageCheck messageCheck);

    /**
     * 通过infoId查询查看公告
     *
     * @param infoId 信息编号
     * @return 查看公告对象信息
     */
    public MessageCheck selectMessageCheckById(String infoId);
}