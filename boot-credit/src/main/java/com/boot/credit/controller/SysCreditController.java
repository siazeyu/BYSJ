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
import com.boot.credit.domain.SysCredit;
import com.boot.credit.service.ISysCreditService;
import com.boot.common.utils.poi.ExcelUtil;
import com.boot.common.core.page.TableDataInfo;

/**
 * 学分申请Controller
 * 
 * @author boot
 * @date 2023-03-25
 */
@RestController
@RequestMapping("/credit/credit")
public class SysCreditController extends BaseController
{
    @Autowired
    private ISysCreditService sysCreditService;

    /**
     * 查询学分申请列表
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCredit sysCredit)
    {
        startPage();
        List<SysCredit> list = sysCreditService.selectSysCreditList(sysCredit);
        return getDataTable(list);
    }

    /**
     * 导出学分申请列表
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:export')")
    @Log(title = "学分申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCredit sysCredit)
    {
        List<SysCredit> list = sysCreditService.selectSysCreditList(sysCredit);
        ExcelUtil<SysCredit> util = new ExcelUtil<SysCredit>(SysCredit.class);
        util.exportExcel(response, list, "学分申请数据");
    }

    /**
     * 获取学分申请详细信息
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCreditService.selectSysCreditById(id));
    }

    /**
     * 新增学分申请
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:add')")
    @Log(title = "学分申请", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCredit sysCredit)
    {
        return toAjax(sysCreditService.insertSysCredit(sysCredit));
    }

    /**
     * 修改学分申请
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:edit')")
    @Log(title = "学分申请", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCredit sysCredit)
    {
        return toAjax(sysCreditService.updateSysCredit(sysCredit));
    }

    /**
     * 删除学分申请
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:remove')")
    @Log(title = "学分申请", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCreditService.deleteSysCreditByIds(ids));
    }
}
