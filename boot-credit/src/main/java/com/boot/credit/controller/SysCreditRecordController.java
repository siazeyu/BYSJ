package com.boot.credit.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.boot.common.annotation.Log;
import com.boot.common.core.controller.BaseController;
import com.boot.common.core.domain.AjaxResult;
import com.boot.common.enums.BusinessType;
import com.boot.credit.domain.SysCreditRecord;
import com.boot.credit.service.ISysCreditRecordService;
import com.boot.common.utils.poi.ExcelUtil;
import com.boot.common.core.page.TableDataInfo;

/**
 * 审批记录Controller
 * 
 * @author Siaze
 * @date 2023-04-26
 */
@RestController
@RequestMapping("/credit/record")
public class SysCreditRecordController extends BaseController
{
    @Autowired
    private ISysCreditRecordService sysCreditRecordService;

    /**
     * 查询审批记录列表
     */
    @PreAuthorize("@ss.hasPermi('credit:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCreditRecord sysCreditRecord)
    {
        startPage();
        List<SysCreditRecord> list = sysCreditRecordService.selectSysCreditRecordList(sysCreditRecord);
        return getDataTable(list);
    }

    /**
     * 导出审批记录列表
     */
    @PreAuthorize("@ss.hasPermi('credit:record:export')")
    @Log(title = "审批记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCreditRecord sysCreditRecord)
    {
        List<SysCreditRecord> list = sysCreditRecordService.selectSysCreditRecordList(sysCreditRecord);
        ExcelUtil<SysCreditRecord> util = new ExcelUtil<SysCreditRecord>(SysCreditRecord.class);
        util.exportExcel(response, list, "审批记录数据");
    }

    /**
     * 获取审批记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('credit:record:query')")
    @GetMapping(value = "/{recordId}")
    public AjaxResult getInfo(@PathVariable("recordId") Long recordId)
    {
        return success(sysCreditRecordService.selectSysCreditRecordByRecordId(recordId));
    }

    /**
     * 新增审批记录
     */
    @PreAuthorize("@ss.hasPermi('credit:record:add')")
    @Log(title = "审批记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCreditRecord sysCreditRecord)
    {
        return toAjax(sysCreditRecordService.insertSysCreditRecord(sysCreditRecord));
    }

    /**
     * 修改审批记录
     */
    @PreAuthorize("@ss.hasPermi('credit:record:edit')")
    @Log(title = "审批记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCreditRecord sysCreditRecord)
    {
        return toAjax(sysCreditRecordService.updateSysCreditRecord(sysCreditRecord));
    }

    /**
     * 删除审批记录
     */
    @PreAuthorize("@ss.hasPermi('credit:record:remove')")
    @Log(title = "审批记录", businessType = BusinessType.DELETE)
	@DeleteMapping("/{recordIds}")
    public AjaxResult remove(@PathVariable Long[] recordIds)
    {
        return toAjax(sysCreditRecordService.deleteSysCreditRecordByRecordIds(recordIds));
    }
}
