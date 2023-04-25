package com.boot.credit.domain;

import com.boot.common.annotation.Excel;
import com.boot.common.core.domain.BaseEntity;
import com.boot.common.core.domain.TreeEntity;
import com.boot.common.core.domain.entity.SysDept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 申请路线对象 sys_credit_route
 * 
 * @author boot
 * @date 2023-04-25
 */
public class SysCreditRoute extends TreeEntity<SysCreditRoute>
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long routeId;

    /** 开始id */
    private Long startId;

    /** 申请路线名称 */
    @Excel(name = "申请路线名称")
    private String routeName;

    /** 路线信息 */
    @Excel(name = "路线信息")
    private String routes;

    /** 状态 */
    @Excel(name = "状态")
    private String status;


    /** 路线信息 */
    private List<SysCreditRouteItem> data;

    public List<SysCreditRouteItem> getData() {
        return data;
    }

    public void setData(List<SysCreditRouteItem> data) {
        this.data = data;
    }

    public void setRouteId(Long routeId)
    {
        this.routeId = routeId;
    }

    public Long getRouteId() 
    {
        return routeId;
    }
    public void setStartId(Long startId) 
    {
        this.startId = startId;
    }

    public Long getStartId() 
    {
        return startId;
    }
    public void setRouteName(String routeName) 
    {
        this.routeName = routeName;
    }

    public String getRouteName() 
    {
        return routeName;
    }
    public void setRoutes(String routes)
    {
        this.routes = routes;
    }

    public String getRoutes()
    {
        return routes;
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
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("routeId", getRouteId())
            .append("startId", getStartId())
            .append("routeName", getRouteName())
            .append("routes", getRoutes())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
