package com.boot.credit.domain;

import com.boot.common.annotation.Excel;
import com.boot.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 申请路线子项对象 sys_credit_route_item
 * 
 * @author Siaze
 * @date 2023-04-25
 */
public class SysCreditRouteItem extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 路线ID */
    private Long routeId;

    /** 下阶段id */
    private Long nextId;

    /** 部门id */
    @Excel(name = "部门id")
    private String deptId;

    /** 部门名称 */
    @Excel(name = "部门名称")
    private String deptName;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }

    public Long getRouteId() {
        return routeId;
    }

    public void setRouteId(Long routeId) {
        this.routeId = routeId;
    }

    public void setNextId(Long nextId)
    {
        this.nextId = nextId;
    }

    public Long getNextId() 
    {
        return nextId;
    }
    public void setDeptId(String deptId) 
    {
        this.deptId = deptId;
    }

    public String getDeptId() 
    {
        return deptId;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("nextId", getNextId())
            .append("deptId", getDeptId())
            .append("deptName", getDeptName())
            .toString();
    }
}
