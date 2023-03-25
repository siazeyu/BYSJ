package com.boot.credit.mapper;

import java.util.List;
import com.boot.credit.domain.SysCredit;

/**
 * 学分申请Mapper接口
 * 
 * @author boot
 * @date 2023-03-25
 */
public interface SysCreditMapper 
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
     * 删除学分申请
     * 
     * @param id 学分申请主键
     * @return 结果
     */
    public int deleteSysCreditById(Long id);

    /**
     * 批量删除学分申请
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSysCreditByIds(Long[] ids);
}
