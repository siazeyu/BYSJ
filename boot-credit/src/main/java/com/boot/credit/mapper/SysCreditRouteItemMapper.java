package com.boot.credit.mapper;

import java.util.List;
import com.boot.credit.domain.SysCreditRouteItem;

/**
 * 申请路线子项Mapper接口
 * 
 * @author Siaze
 * @date 2023-04-25
 */
public interface SysCreditRouteItemMapper 
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
     * 删除申请路线子项
     * 
     * @param id 申请路线子项主键
     * @return 结果
     */
    public int deleteSysCreditRouteItemById(Long id);

    /**
     * 批量删除申请路线子项
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCreditRouteItemByIds(Long[] ids);
}
