package com.boot.credit.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.boot.credit.mapper.SysCreditRecordMapper;
import com.boot.credit.domain.SysCreditRecord;
import com.boot.credit.service.ISysCreditRecordService;

/**
 * 审批记录Service业务层处理
 * 
 * @author Siaze
 * @date 2023-04-26
 */
@Service
public class SysCreditRecordServiceImpl implements ISysCreditRecordService 
{
    @Autowired
    private SysCreditRecordMapper sysCreditRecordMapper;

    /**
     * 查询审批记录
     * 
     * @param recordId 审批记录主键
     * @return 审批记录
     */
    @Override
    public SysCreditRecord selectSysCreditRecordByRecordId(Long recordId)
    {
        return sysCreditRecordMapper.selectSysCreditRecordByRecordId(recordId);
    }

    /**
     * 查询审批记录列表
     * 
     * @param sysCreditRecord 审批记录
     * @return 审批记录
     */
    @Override
    public List<SysCreditRecord> selectSysCreditRecordList(SysCreditRecord sysCreditRecord)
    {
        return sysCreditRecordMapper.selectSysCreditRecordList(sysCreditRecord);
    }

    /**
     * 新增审批记录
     * 
     * @param sysCreditRecord 审批记录
     * @return 结果
     */
    @Override
    public int insertSysCreditRecord(SysCreditRecord sysCreditRecord)
    {
        return sysCreditRecordMapper.insertSysCreditRecord(sysCreditRecord);
    }

    /**
     * 修改审批记录
     * 
     * @param sysCreditRecord 审批记录
     * @return 结果
     */
    @Override
    public int updateSysCreditRecord(SysCreditRecord sysCreditRecord)
    {
        return sysCreditRecordMapper.updateSysCreditRecord(sysCreditRecord);
    }

    /**
     * 批量删除审批记录
     * 
     * @param recordIds 需要删除的审批记录主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRecordByRecordIds(Long[] recordIds)
    {
        return sysCreditRecordMapper.deleteSysCreditRecordByRecordIds(recordIds);
    }

    /**
     * 删除审批记录信息
     * 
     * @param recordId 审批记录主键
     * @return 结果
     */
    @Override
    public int deleteSysCreditRecordByRecordId(Long recordId)
    {
        return sysCreditRecordMapper.deleteSysCreditRecordByRecordId(recordId);
    }
}
