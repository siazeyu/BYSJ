package com.boot.credit.mapper;

import java.util.List;
import com.boot.credit.domain.SysCreditType;

/**
 * 加分项Mapper接口
 * 
 * @author boot
 * @date 2023-03-28
 */
public interface SysCreditTypeMapper 
{
    /**
     * 查询加分项
     * 
     * @param typeId 加分项主键
     * @return 加分项
     */
    public SysCreditType selectSysCreditTypeByTypeId(Long typeId);

    /**
     * 查询加分项列表
     * 
     * @param sysCreditType 加分项
     * @return 加分项集合
     */
    public List<SysCreditType> selectSysCreditTypeList(SysCreditType sysCreditType);

    /**
     * 新增加分项
     * 
     * @param sysCreditType 加分项
     * @return 结果
     */
    public int insertSysCreditType(SysCreditType sysCreditType);

    /**
     * 修改加分项
     * 
     * @param sysCreditType 加分项
     * @return 结果
     */
    public int updateSysCreditType(SysCreditType sysCreditType);

    /**
     * 删除加分项
     * 
     * @param typeId 加分项主键
     * @return 结果
     */
    public int deleteSysCreditTypeByTypeId(Long typeId);

    /**
     * 批量删除加分项
     * 
     * @param typeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCreditTypeByTypeIds(Long[] typeIds);
}
