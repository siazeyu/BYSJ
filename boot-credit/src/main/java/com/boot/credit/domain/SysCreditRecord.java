package com.boot.credit.domain;

import java.math.BigDecimal;
import java.util.Date;

import com.boot.common.core.domain.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.boot.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 审批记录对象 sys_credit_record
 * 
 * @author Siaze
 * @date 2023-04-26
 */
public class SysCreditRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long recordId;

    /** 学分申请ID */
    @Excel(name = "学分申请ID")
    private Long applyId;

    /** 申请类型 */
    @Excel(name = "申请类型")
    private Long creditTypeId;

    /** 申请项目名称 */
    @Excel(name = "申请项目名称")
    private String creditTypeName;

    /** 学分 */
    private BigDecimal credit;

    /** 申请者 */
    @Excel(name = "申请者")
    private String applicants;

    /** 审批人 */
    @Excel(name = "审批人")
    private String operator;

    /** 审核结果 */
    @Excel(name = "审核结果")
    private Long result;

    /** 操作时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "操作时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 客观学分 */
    private BigDecimal objectiveCredit;

    /** 备注 */
    private String remarks;

    public void setRecordId(Long recordId) 
    {
        this.recordId = recordId;
    }

    public Long getRecordId() 
    {
        return recordId;
    }
    public void setApplyId(Long applyId) 
    {
        this.applyId = applyId;
    }

    public Long getApplyId() 
    {
        return applyId;
    }
    public void setCreditTypeId(Long creditTypeId) 
    {
        this.creditTypeId = creditTypeId;
    }

    public Long getCreditTypeId() 
    {
        return creditTypeId;
    }
    public void setCreditTypeName(String creditTypeName) 
    {
        this.creditTypeName = creditTypeName;
    }

    public String getCreditTypeName() 
    {
        return creditTypeName;
    }
    public void setCredit(BigDecimal credit)
    {
        this.credit = credit;
    }

    public BigDecimal getCredit()
    {
        return credit;
    }
    public void setApplicants(String applicants) 
    {
        this.applicants = applicants;
    }

    public String getApplicants() 
    {
        return applicants;
    }
    public void setOperator(String operator) 
    {
        this.operator = operator;
    }

    public String getOperator() 
    {
        return operator;
    }
    public void setResult(Long result)
    {
        this.result = result;
    }

    public Long getResult()
    {
        return result;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
    }
    public void setObjectiveCredit(BigDecimal objectiveCredit) 
    {
        this.objectiveCredit = objectiveCredit;
    }

    public BigDecimal getObjectiveCredit() 
    {
        return objectiveCredit;
    }
    public void setRemarks(String remarks) 
    {
        this.remarks = remarks;
    }

    public String getRemarks() 
    {
        return remarks;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("recordId", getRecordId())
            .append("applyId", getApplyId())
            .append("creditTypeId", getCreditTypeId())
            .append("creditTypeName", getCreditTypeName())
            .append("credit", getCredit())
            .append("applicants", getApplicants())
            .append("operator", getOperator())
            .append("result", getResult())
            .append("createDate", getCreateDate())
            .append("objectiveCredit", getObjectiveCredit())
            .append("remarks", getRemarks())
            .toString();
    }
}
