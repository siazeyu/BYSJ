package com.boot.credit.service;

import java.util.List;
import com.boot.credit.domain.SysCreditRouteItem;

/**
 * 申请路线子项Service接口
 * 
 * @author Siaze
 * @date 2023-04-25
 */
public interface ISysCreditRouteItemService 
{
    /**
     * 查询申请路线子项
     * 
     * @param id 申请路线子项主键
     * @return 申请路线子项
     */
    public SysCreditRouteItem selectSysCreditRouteItemById(Long id);

    /**
     * 查询申请路线子项列表
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 申请路线子项集合
     */
    public List<SysCreditRouteItem> selectSysCreditRouteItemList(SysCreditRouteItem sysCreditRouteItem);

    /**
     * 通过部门Id查询路线子项列表
     *
     * @param deptId 部门id
     * @return 申请路线子项集合
     */
    public List<SysCreditRouteItem> selectSysCreditRouteItemListByDeptId(Long deptId);

    /**
     * 新增申请路线子项
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 结果
     */
    public int insertSysCreditRouteItem(SysCreditRouteItem sysCreditRouteItem);

    /**
     * 修改申请路线子项
     * 
     * @param sysCreditRouteItem 申请路线子项
     * @return 结果
     */
    public int updateSysCreditRouteItem(SysCreditRouteItem sysCreditRouteItem);

    /**
     * 批量删除申请路线子项
     * 
     * @param ids 需要删除的申请路线子项主键集合
     * @return 结果
     */
    public int deleteSysCreditRouteItemByIds(Long[] ids);

    /**
     * 删除申请路线子项信息
     * 
     * @param id 申请路线子项主键
     * @return 结果
     */
    public int deleteSysCreditRouteItemById(Long id);

    /**
     * 检查子项是否结束
     * @return
     */
    public boolean itemIsFinish(Long id);
}
