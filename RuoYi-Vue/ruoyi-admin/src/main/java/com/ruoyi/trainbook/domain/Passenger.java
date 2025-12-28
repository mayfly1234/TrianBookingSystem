package com.ruoyi.trainbook.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 乘客信息实体
 *
 * @author ruoyi
 */
public class Passenger extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 乘客ID */
    private Long id;

    /** 关联系统用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 乘客姓名 */
    @Excel(name = "乘客姓名")
    private String passengerName;

    /** 身份证号 */
    @Excel(name = "身份证号")
    private String idCard;

    /** 手机号 */
    @Excel(name = "手机号")
    private String phone;

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassengerName()
    {
        return passengerName;
    }

    public void setPassengerName(String passengerName)
    {
        this.passengerName = passengerName;
    }

    public String getIdCard()
    {
        return idCard;
    }

    public void setIdCard(String idCard)
    {
        this.idCard = idCard;
    }

    public String getPhone()
    {
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("userId", getUserId())
                .append("passengerName", getPassengerName())
                .append("idCard", getIdCard())
                .append("phone", getPhone())
                .append("createTime", getCreateTime())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}