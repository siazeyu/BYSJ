package com.boot.credit.service;

import java.util.List;
import com.boot.credit.domain.SysCreditRecord;

/**
 * 审批记录Service接口
 * 
 * @author Siaze
 * @date 2023-04-26
 */
public interface ISysCreditRecordService 
{
    /**
     * 查询审批记录
     * 
     * @param recordId 审批记录主键
     * @return 审批记录
     */
    public SysCreditRecord selectSysCreditRecordByRecordId(Long recordId);

    /**
     * 查询审批记录列表
     * 
     * @param sysCreditRecord 审批记录
     * @return 审批记录集合
     */
    public List<SysCreditRecord> selectSysCreditRecordList(SysCreditRecord sysCreditRecord);

    /**
     * 新增审批记录
     * 
     * @param sysCreditRecord 审批记录
     * @return 结果
     */
    public int insertSysCreditRecord(SysCreditRecord sysCreditRecord);

    /**
     * 修改审批记录
     * 
     * @param sysCreditRecord 审批记录
     * @return 结果
     */
    public int updateSysCreditRecord(SysCreditRecord sysCreditRecord);

    /**
     * 批量删除审批记录
     * 
     * @param recordIds 需要删除的审批记录主键集合
     * @return 结果
     */
    public int deleteSysCreditRecordByRecordIds(Long[] recordIds);

    /**
     * 删除审批记录信息
     * 
     * @param recordId 审批记录主键
     * @return 结果
     */
    public int deleteSysCreditRecordByRecordId(Long recordId);
}
