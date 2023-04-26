package com.boot.credit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditRouteItemMapper;
import com.boot.credit.domain.SysCreditRouteItem;
import com.boot.credit.service.ISysCreditRouteItemService;

/**
 * 申请路线子项Service业务层处理
 * 
 * @author Siaze
 * @date 2023-04-25
 */
@Service
public class SysCreditRouteItemServiceImpl implements ISysCreditRouteItemService 
{
    @Autowired
    private SysCreditRouteItemMapper sysCreditRouteItemMapper;

    /**
     * 查询申请路线子项
     * 
     * @param id 申请路线子项主键
     * @return 申请路线子项
     */
    @Override
    public SysCreditRouteItem selectSysCreditRouteItemById(Long id)
    {
        return sysCreditRouteItemMapper.selectSysCreditRouteItemById(id);
    }

    /**
     * 查询申请路线子项列表
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 申请路线子项
     */
    @Override
    public List<SysCreditRouteItem> selectSysCreditRouteItemList(SysCreditRouteItem sysCreditRouteItem)
    {
        return sysCreditRouteItemMapper.selectSysCreditRouteItemList(sysCreditRouteItem);
    }

    @Override
    public List<SysCreditRouteItem> selectSysCreditRouteItemListByDeptId(Long deptId) {
        return sysCreditRouteItemMapper.selectSysCreditRouteItemListByDeptId(deptId);
    }

    /**
     * 新增申请路线子项
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 结果
     */
    @Override
    public int insertSysCreditRouteItem(SysCreditRouteItem sysCreditRouteItem)
    {
        return sysCreditRouteItemMapper.insertSysCreditRouteItem(sysCreditRouteItem);
    }

    /**
     * 修改申请路线子项
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 结果
     */
    @Override
    public int updateSysCreditRouteItem(SysCreditRouteItem sysCreditRouteItem)
    {
        return sysCreditRouteItemMapper.updateSysCreditRouteItem(sysCreditRouteItem);
    }

    /**
     * 批量删除申请路线子项
     * 
     * @param ids 需要删除的申请路线子项主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRouteItemByIds(Long[] ids)
    {
        return sysCreditRouteItemMapper.deleteSysCreditRouteItemByIds(ids);
    }

    /**
     * 删除申请路线子项信息
     * 
     * @param id 申请路线子项主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRouteItemById(Long id)
    {
        return sysCreditRouteItemMapper.deleteSysCreditRouteItemById(id);
    }

    @Override
    public boolean itemIsFinish(Long id) {
        SysCreditRouteItem routeItem = sysCreditRouteItemMapper.selectSysCreditRouteItemById(id);
        return routeItem.getParentId() != null && routeItem.getNextId() != 0;
    }
}
