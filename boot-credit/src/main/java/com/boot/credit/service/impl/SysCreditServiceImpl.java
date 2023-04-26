package com.boot.credit.service.impl;

import java.util.List;

import com.boot.credit.domain.SysCreditRouteItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditMapper;
import com.boot.credit.domain.SysCredit;
import com.boot.credit.service.ISysCreditService;

/**
 * 学分申请Service业务层处理
 * 
 * @author boot
 * @date 2023-03-25
 */
@Service
public class SysCreditServiceImpl implements ISysCreditService 
{
    @Autowired
    private SysCreditMapper sysCreditMapper;

    /**
     * 查询学分申请
     * 
     * @param id 学分申请主键
     * @return 学分申请
     */
    @Override
    public SysCredit selectSysCreditById(Long id)
    {
        return sysCreditMapper.selectSysCreditById(id);
    }

    /**
     * 查询学分申请列表
     * 
     * @param sysCredit 学分申请
     * @return 学分申请
     */
    @Override
    public List<SysCredit> selectSysCreditList(SysCredit sysCredit)
    {
        return sysCreditMapper.selectSysCreditList(sysCredit);
    }

    @Override
    public List<SysCredit> selectSysCreditListByItems(List<Long> itemIds) {
        return sysCreditMapper.selectSysCreditListByItems(itemIds);
    }

    /**
     * 新增学分申请
     * 
     * @param sysCredit 学分申请
     * @return 结果
     */
    @Override
    public int insertSysCredit(SysCredit sysCredit)
    {
        return sysCreditMapper.insertSysCredit(sysCredit);
    }

    /**
     * 修改学分申请
     * 
     * @param sysCredit 学分申请
     * @return 结果
     */
    @Override
    public int updateSysCredit(SysCredit sysCredit)
    {
        return sysCreditMapper.updateSysCredit(sysCredit);
    }

    /**
     * 批量删除学分申请
     * 
     * @param ids 需要删除的学分申请主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditByIds(Long[] ids)
    {
        return sysCreditMapper.deleteSysCreditByIds(ids);
    }

    /**
     * 删除学分申请信息
     * 
     * @param id 学分申请主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditById(Long id)
    {
        return sysCreditMapper.deleteSysCreditById(id);
    }
}
