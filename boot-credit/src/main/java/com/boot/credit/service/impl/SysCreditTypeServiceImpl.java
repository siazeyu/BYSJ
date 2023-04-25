package com.boot.credit.service.impl;

import java.util.List;
import com.boot.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditTypeMapper;
import com.boot.credit.domain.SysCreditType;
import com.boot.credit.service.ISysCreditTypeService;

/**
 * 加分项Service业务层处理
 * 
 * @author boot
 * @date 2023-03-28
 */
@Service
public class SysCreditTypeServiceImpl implements ISysCreditTypeService 
{
    @Autowired
    private SysCreditTypeMapper sysCreditTypeMapper;

    /**
     * 查询加分项
     * 
     * @param typeId 加分项主键
     * @return 加分项
     */
    @Override
    public SysCreditType selectSysCreditTypeByTypeId(Long typeId)
    {
        return sysCreditTypeMapper.selectSysCreditTypeByTypeId(typeId);
    }

    /**
     * 查询加分项列表
     * 
     * @param sysCreditType 加分项
     * @return 加分项
     */
    @Override
    public List<SysCreditType> selectSysCreditTypeList(SysCreditType sysCreditType)
    {
        return sysCreditTypeMapper.selectSysCreditTypeList(sysCreditType);
    }

    /**
     * 新增加分项
     * 
     * @param sysCreditType 加分项
     * @return 结果
     */
    @Override
    public int insertSysCreditType(SysCreditType sysCreditType)
    {
        sysCreditType.setCreateTime(DateUtils.getNowDate());
        return sysCreditTypeMapper.insertSysCreditType(sysCreditType);
    }

    /**
     * 修改加分项
     * 
     * @param sysCreditType 加分项
     * @return 结果
     */
    @Override
    public int updateSysCreditType(SysCreditType sysCreditType)
    {
        sysCreditType.setUpdateTime(DateUtils.getNowDate());
        return sysCreditTypeMapper.updateSysCreditType(sysCreditType);
    }

    /**
     * 批量删除加分项
     * 
     * @param typeIds 需要删除的加分项主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditTypeByTypeIds(Long[] typeIds)
    {
        return sysCreditTypeMapper.deleteSysCreditTypeByTypeIds(typeIds);
    }

    /**
     * 删除加分项信息
     * 
     * @param typeId 加分项主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditTypeByTypeId(Long typeId)
    {
        return sysCreditTypeMapper.deleteSysCreditTypeByTypeId(typeId);
    }
}
