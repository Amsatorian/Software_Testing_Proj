package com.ruoyi.Employee.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * Employee对象 employee
 * 
 * @author zx
 * @date 2024-11-27
 */
public class Employee extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 员工ID */
    private Long employeeId;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String name;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contactNumber;

    /** 电子邮箱 */
    @Excel(name = "电子邮箱")
    private String email;

    /** 职位 */
    @Excel(name = "职位")
    private String position;

    /** 入职日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "入职日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hireDate;

    /** 状态（active/disabled） */
    @Excel(name = "状态", readConverterExp = "a=ctive/disabled")
    private String status;

    public void setEmployeeId(Long employeeId) 
    {
        this.employeeId = employeeId;
    }

    public Long getEmployeeId() 
    {
        return employeeId;
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
    public void setEmail(String email) 
    {
        this.email = email;
    }

    public String getEmail() 
    {
        return email;
    }
    public void setPosition(String position) 
    {
        this.position = position;
    }

    public String getPosition() 
    {
        return position;
    }
    public void setHireDate(Date hireDate) 
    {
        this.hireDate = hireDate;
    }

    public Date getHireDate() 
    {
        return hireDate;
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
            .append("employeeId", getEmployeeId())
            .append("name", getName())
            .append("contactNumber", getContactNumber())
            .append("email", getEmail())
            .append("position", getPosition())
            .append("hireDate", getHireDate())
            .append("status", getStatus())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
