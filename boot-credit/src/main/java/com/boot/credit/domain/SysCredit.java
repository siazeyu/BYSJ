package com.boot.credit.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.boot.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.boot.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 学分申请对象 sys_credit
 * 
 * @author boot
 * @date 2023-03-25
 */
public class SysCredit extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    @Excel(name = "用户学号")
    private String username;

    /** 学分类型 */
    @Excel(name = "学分类型")
    private Long creditType;

    /** 学分值 */
    @Excel(name = "学分值")
    private BigDecimal credit;

    /** 申请附件 */
    @Excel(name = "申请附件")
    private String requestFile;

    /** 状态 */
    @Excel(name = "状态")
    private Long statue;

    /** 申请时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creditDate;

    /** 完成时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "完成时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date finalDate;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setCreditType(Long creditType) 
    {
        this.creditType = creditType;
    }

    public Long getCreditType() 
    {
        return creditType;
    }
    public void setCredit(BigDecimal credit)
    {
        this.credit = credit;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String userName) {
        this.username = userName;
    }

    public BigDecimal getCredit()
    {
        return credit;
    }
    public void setRequestFile(String requestFile) 
    {
        this.requestFile = requestFile;
    }

    public String getRequestFile() 
    {
        return requestFile;
    }
    public void setStatue(Long statue) 
    {
        this.statue = statue;
    }

    public Long getStatue() 
    {
        return statue;
    }
    public void setCreditDate(Date creditDate) 
    {
        this.creditDate = creditDate;
    }

    public Date getCreditDate() 
    {
        return creditDate;
    }
    public void setFinalDate(Date finalDate) 
    {
        this.finalDate = finalDate;
    }

    public Date getFinalDate() 
    {
        return finalDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("userId", getUserId())
            .append("creditType", getCreditType())
            .append("credit", getCredit())
            .append("requestFile", getRequestFile())
            .append("statue", getStatue())
            .append("creditDate", getCreditDate())
            .append("finalDate", getFinalDate())
            .toString();
    }
}
