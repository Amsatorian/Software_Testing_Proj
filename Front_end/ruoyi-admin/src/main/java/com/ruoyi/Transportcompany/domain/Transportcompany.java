package com.ruoyi.Transportcompany.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Transportcompany对象 transportcompany
 * 
 * @author zx
 * @date 2024-11-27
 */
public class Transportcompany extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 运输公司ID */
    private Long companyId;

    /** 运输公司名称 */
    @Excel(name = "运输公司名称")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactNumber;

    /** 地址 */
    @Excel(name = "地址")
    private String address;

    /** 状态（active/disabled） */
    @Excel(name = "状态", readConverterExp = "a=ctive/disabled")
    private String status;

    public void setCompanyId(Long companyId) 
    {
        this.companyId = companyId;
    }

    public Long getCompanyId() 
    {
        return companyId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setContactNumber(String contactNumber) 
    {
        this.contactNumber = contactNumber;
    }

    public String getContactNumber() 
    {
        return contactNumber;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
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
            .append("companyId", getCompanyId())
            .append("name", getName())
            .append("contactNumber", getContactNumber())
            .append("address", getAddress())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
