package com.boot.credit.service;

import java.util.List;
import com.boot.credit.domain.SysCredit;
import com.boot.credit.domain.SysCreditRouteItem;

/**
 * 学分申请Service接口
 * 
 * @author boot
 * @date 2023-03-25
 */
public interface ISysCreditService 
{
    /**
     * 查询学分申请
     * 
     * @param id 学分申请主键
     * @return 学分申请
     */
    public SysCredit selectSysCreditById(Long id);

    /**
     * 查询学分申请列表
     * 
     * @param sysCredit 学分申请
     * @return 学分申请集合
     */
    public List<SysCredit> selectSysCreditList(SysCredit sysCredit);

    /**
     * 查询学分申请列表
     *
     * @param itemIds 学分申请
     * @return 学分申请集合
     */
    public List<SysCredit> selectSysCreditListByItems(List<Long> itemIds);

    /**
     * 新增学分申请
     * 
     * @param sysCredit 学分申请
     * @return 结果
     */
    public int insertSysCredit(SysCredit sysCredit);

    /**
     * 修改学分申请
     * 
     * @param sysCredit 学分申请
     * @return 结果
     */
    public int updateSysCredit(SysCredit sysCredit);

    /**
     * 批量删除学分申请
     * 
     * @param ids 需要删除的学分申请主键集合
     * @return 结果
     */
    public int deleteSysCreditByIds(Long[] ids);

    /**
     * 删除学分申请信息
     * 
     * @param id 学分申请主键
     * @return 结果
     */
    public int deleteSysCreditById(Long id);
}
