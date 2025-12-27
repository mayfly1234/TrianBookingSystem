package com.ruoyi.message.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 查看公告对象 message_info（和发布公告共用同一张表）
 *
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class MessageCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 信息编号（主键） */
    private String infoId; // 和你的MessageInfo保持一致，用String类型

    /** 信息内容 */
    @Excel(name = "信息内容")
    private String infoContent;

    /** 发布时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "发布时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date publishTime;

    // 手动写get/set方法（和你的MessageInfo风格一致）
    public void setInfoId(String infoId)
    {
        this.infoId = infoId;
    }

    public String getInfoId()
    {
        return infoId;
    }

    public void setInfoContent(String infoContent)
    {
        this.infoContent = infoContent;
    }

    public String getInfoContent()
    {
        return infoContent;
    }

    public void setPublishTime(Date publishTime)
    {
        this.publishTime = publishTime;
    }

    public Date getPublishTime()
    {
        return publishTime;
    }

    // 重写toString方法（和你的MessageInfo风格一致）
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("infoId", getInfoId())
                .append("infoContent", getInfoContent())
                .append("publishTime", getPublishTime())
                .toString();
    }
}
