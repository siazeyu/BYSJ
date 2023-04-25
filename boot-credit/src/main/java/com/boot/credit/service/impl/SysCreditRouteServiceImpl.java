package com.boot.credit.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.boot.common.utils.DateUtils;
import com.boot.credit.domain.SysCreditRouteItem;
import com.boot.credit.service.ISysCreditRouteItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditRouteMapper;
import com.boot.credit.domain.SysCreditRoute;
import com.boot.credit.service.ISysCreditRouteService;

/**
 * 申请路线Service业务层处理
 * 
 * @author boot
 * @date 2023-04-25
 */
@Service
public class SysCreditRouteServiceImpl implements ISysCreditRouteService 
{
    @Autowired
    private SysCreditRouteMapper sysCreditRouteMapper;

    @Autowired
    private ISysCreditRouteItemService sysCreditRouteItemService;

//    @Autowired
//    private

    /**
     * 查询申请路线
     * 
     * @param routeId 申请路线主键
     * @return 申请路线
     */
    @Override
    public SysCreditRoute selectSysCreditRouteByRouteId(Long routeId)
    {
        return sysCreditRouteMapper.selectSysCreditRouteByRouteId(routeId);
    }

    /**
     * 查询申请路线列表
     * 
     * @param sysCreditRoute 申请路线
     * @return 申请路线
     */
    @Override
    public List<SysCreditRoute> selectSysCreditRouteList(SysCreditRoute sysCreditRoute)
    {
        return sysCreditRouteMapper.selectSysCreditRouteList(sysCreditRoute);
    }

    /**
     * 新增申请路线
     * 
     * @param sysCreditRoute 申请路线
     * @return 结果
     */
    @Override
    public int insertSysCreditRoute(SysCreditRoute sysCreditRoute)
    {

        sysCreditRoute.setCreateTime(DateUtils.getNowDate());
        return sysCreditRouteMapper.insertSysCreditRoute(sysCreditRoute);
    }

    /**
     * 修改申请路线
     * 
     * @param sysCreditRoute 申请路线
     * @return 结果
     */
    @Override
    public int updateSysCreditRoute(SysCreditRoute sysCreditRoute)
    {
        sysCreditRoute.setUpdateTime(DateUtils.getNowDate());
        return sysCreditRouteMapper.updateSysCreditRoute(sysCreditRoute);
    }

    /**
     * 批量删除申请路线
     * 
     * @param routeIds 需要删除的申请路线主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRouteByRouteIds(Long[] routeIds)
    {
        return sysCreditRouteMapper.deleteSysCreditRouteByRouteIds(routeIds);
    }

    /**
     * 删除申请路线信息
     * 
     * @param routeId 申请路线主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRouteByRouteId(Long routeId)
    {
        return sysCreditRouteMapper.deleteSysCreditRouteByRouteId(routeId);
    }

    private SysCreditRouteItem formatItem(List<SysCreditRouteItem> items){
        List<Long> collect = items.stream().map(item -> item.getDeptId()).collect(Collectors.toList());

        return null;
    }
}
