package com.boot.credit.service.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.boot.common.core.domain.entity.SysDept;
import com.boot.common.core.domain.entity.SysUser;
import com.boot.common.utils.DateUtils;
import com.boot.common.utils.StringUtils;
import com.boot.credit.domain.DoubleLinkedNode;
import com.boot.credit.domain.SysCreditRouteItem;
import com.boot.credit.service.ISysCreditRouteItemService;
import com.boot.system.service.ISysDeptService;
import com.boot.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditRouteMapper;
import com.boot.credit.domain.SysCreditRoute;
import com.boot.credit.service.ISysCreditRouteService;
import org.springframework.transaction.annotation.Transactional;

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

    @Autowired
    private ISysDeptService iSysDeptService;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询申请路线
     * 
     * @param routeId 申请路线主键
     * @return 申请路线
     */
    @Override
    public SysCreditRoute selectSysCreditRouteByRouteId(Long routeId)
    {
        SysCreditRouteItem routeItem = new SysCreditRouteItem();
        routeItem.setRouteId(routeId);
        SysCreditRoute sysCreditRoute = sysCreditRouteMapper.selectSysCreditRouteByRouteId(routeId);
        sysCreditRoute.setData(sysCreditRouteItemService.selectSysCreditRouteItemList(routeItem));
        return sysCreditRoute;
    }

    @Override
    public List<SysCreditRoute> selectSysCreditRoute(String username) {
        List<Long> ids = new ArrayList<>();
        SysUser sysUser = userService.selectUserByUserName(username);
        List<SysCreditRouteItem> sysCreditRouteItems = sysCreditRouteItemService.selectSysCreditRouteItemList(null);
        for (SysCreditRouteItem sysCreditRouteItem : sysCreditRouteItems) {
            List<String> strings = StringUtils.str2List(sysCreditRouteItem.getDeptId(), ",", true, true);
            if (ids.contains(sysCreditRouteItem.getRouteId())){
                continue;
            }
            for (String string : strings) {
                if (string.equals(sysUser.getDeptId().toString())){
                    ids.add(sysCreditRouteItem.getRouteId());
                }
            }
        }
        Long[] idArray = new Long[ids.size()];
        ids.toArray(idArray);
        List<SysCreditRoute> res = sysCreditRouteMapper.selectSysCreditRouteByRouteId(idArray);
        return res;
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
    @Transactional
    @Override
    public int insertSysCreditRoute(SysCreditRoute sysCreditRoute)
    {

        sysCreditRoute.setCreateTime(DateUtils.getNowDate());
        int res = sysCreditRouteMapper.insertSysCreditRoute(sysCreditRoute);
        DoubleLinkedNode<SysDept> item = sortItem(sysCreditRoute.getData());
        DoubleLinkedNode<SysDept> parent = item.getParent();

        SysCreditRouteItem last = null;
        while (parent != null){
            List<SysDept> data = parent.getData();
            SysCreditRouteItem insert = new SysCreditRouteItem();
            insert.setRouteId(sysCreditRoute.getRouteId());
            if (last != null){
               insert.setParentId(last.getId());
            }
            insert.setDeptId(StringUtils.list2String(data.stream().map(SysDept::getDeptId).collect(Collectors.toList())));
            insert.setDeptName(StringUtils.list2String(data.stream().map(SysDept::getDeptName).collect(Collectors.toList())));
            sysCreditRouteItemService.insertSysCreditRouteItem(insert);
            if (last != null){
                last.setNextId(insert.getId());
                sysCreditRouteItemService.updateSysCreditRouteItem(last);
            }
            last = insert;
            parent = parent.getNext();
        }
        return res;
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

    /**
     * 阶段排序
     * @param items
     * @return 排序好的阶段
     */
    private DoubleLinkedNode<SysDept> sortItem(List<SysCreditRouteItem> items){
        List<Long> collect = items.stream().map(item -> Long.parseLong(item.getDeptId())).collect(Collectors.toList());
        ArrayList<SysDept> list = new ArrayList<>();
        for (Long id : collect) {
            SysDept sysDept = iSysDeptService.selectDeptById(id);
            list.add(sysDept);
        }

        Collections.sort(list, Comparator.comparingInt(a -> a.getAncestors().split(",").length));
        DoubleLinkedNode result = new DoubleLinkedNode<SysDept>();

        for (int i = 0; i < list.size(); i++) {
            SysDept sysDept = list.get(i);
            if (i == 0 || list.get(i - 1).getAncestors().split(",").length == sysDept.getAncestors().split(",").length){
                result.addData(sysDept);
            }else {
                DoubleLinkedNode next = new DoubleLinkedNode();
                next.addData(sysDept);
                next.setLast(result);
                result.setNext(next);
                result = next;
            }
        }
        return result;
    }


}
