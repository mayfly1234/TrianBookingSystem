package com.ruoyi.train.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 列车基础信息对象 train_basic
 * 
 * @author 罗佳淦
 * @date 2025-12-27
 */
public class TrainBasic extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long basicId;

    /** 列车号 */
    @Excel(name = "列车号")
    private String trainNo;

    /** 列车类型 */
    @Excel(name = "列车类型")
    private String trainType;

    /** 所属铁路局 */
    @Excel(name = "所属铁路局")
    private String railwayBureau;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    public void setBasicId(Long basicId) 
    {
        this.basicId = basicId;
    }

    public Long getBasicId() 
    {
        return basicId;
    }

    public void setTrainNo(String trainNo) 
    {
        this.trainNo = trainNo;
    }

    public String getTrainNo() 
    {
        return trainNo;
    }

    public void setTrainType(String trainType) 
    {
        this.trainType = trainType;
    }

    public String getTrainType() 
    {
        return trainType;
    }

    public void setRailwayBureau(String railwayBureau) 
    {
        this.railwayBureau = railwayBureau;
    }

    public String getRailwayBureau() 
    {
        return railwayBureau;
    }

    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("basicId", getBasicId())
            .append("trainNo", getTrainNo())
            .append("trainType", getTrainType())
            .append("railwayBureau", getRailwayBureau())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
