package com.boot.credit.service;

import java.util.List;
import com.boot.credit.domain.SysCreditRoute;
import com.boot.credit.domain.SysCreditRouteItem;

/**
 * 申请路线Service接口
 * 
 * @author boot
 * @date 2023-04-25
 */
public interface ISysCreditRouteService 
{
    /**
     * 查询申请路线
     * 
     * @param routeId 申请路线主键
     * @return 申请路线
     */
    public SysCreditRoute selectSysCreditRouteByRouteId(Long routeId);

    /**
     * 查询用户审批的路线
     * @param username 用户主键
     * @return 申请路线
     */
    public List<SysCreditRoute> selectSysCreditRoute(String username);


    /**
     * 查询申请路线列表
     * 
     * @param sysCreditRoute 申请路线
     * @return 申请路线集合
     */
    public List<SysCreditRoute> selectSysCreditRouteList(SysCreditRoute sysCreditRoute);

    /**
     * 新增申请路线
     * 
     * @param sysCreditRoute 申请路线
     * @return 结果
     */
    public int insertSysCreditRoute(SysCreditRoute sysCreditRoute);

    /**
     * 修改申请路线
     * 
     * @param sysCreditRoute 申请路线
     * @return 结果
     */
    public int updateSysCreditRoute(SysCreditRoute sysCreditRoute);

    /**
     * 批量删除申请路线
     * 
     * @param routeIds 需要删除的申请路线主键集合
     * @return 结果
     */
    public int deleteSysCreditRouteByRouteIds(Long[] routeIds);

    /**
     * 删除申请路线信息
     * 
     * @param routeId 申请路线主键
     * @return 结果
     */
    public int deleteSysCreditRouteByRouteId(Long routeId);
}
