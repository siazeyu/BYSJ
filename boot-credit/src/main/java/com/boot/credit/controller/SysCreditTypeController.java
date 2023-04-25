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
import com.boot.credit.domain.SysCreditType;
import com.boot.credit.service.ISysCreditTypeService;
import com.boot.common.utils.poi.ExcelUtil;

/**
 * 加分项Controller
 * 
 * @author boot
 * @date 2023-03-28
 */
@RestController
@RequestMapping("/credit/type")
public class SysCreditTypeController extends BaseController
{
    @Autowired
    private ISysCreditTypeService sysCreditTypeService;

    /**
     * 查询加分项列表
     */
    @PreAuthorize("@ss.hasPermi('credit:type:list')")
    @GetMapping("/list")
    public AjaxResult list(SysCreditType sysCreditType)
    {
        List<SysCreditType> list = sysCreditTypeService.selectSysCreditTypeList(sysCreditType);
        return success(list);
    }

    /**
     * 导出加分项列表
     */
    @PreAuthorize("@ss.hasPermi('credit:type:export')")
    @Log(title = "加分项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCreditType sysCreditType)
    {
        List<SysCreditType> list = sysCreditTypeService.selectSysCreditTypeList(sysCreditType);
        ExcelUtil<SysCreditType> util = new ExcelUtil<SysCreditType>(SysCreditType.class);
        util.exportExcel(response, list, "加分项数据");
    }

    /**
     * 获取加分项详细信息
     */
    @PreAuthorize("@ss.hasPermi('credit:type:query')")
    @GetMapping(value = "/{typeId}")
    public AjaxResult getInfo(@PathVariable("typeId") Long typeId)
    {
        return success(sysCreditTypeService.selectSysCreditTypeByTypeId(typeId));
    }

    /**
     * 新增加分项
     */
    @PreAuthorize("@ss.hasPermi('credit:type:add')")
    @Log(title = "加分项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCreditType sysCreditType)
    {
        return toAjax(sysCreditTypeService.insertSysCreditType(sysCreditType));
    }

    /**
     * 修改加分项
     */
    @PreAuthorize("@ss.hasPermi('credit:type:edit')")
    @Log(title = "加分项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCreditType sysCreditType)
    {
        return toAjax(sysCreditTypeService.updateSysCreditType(sysCreditType));
    }

    /**
     * 删除加分项
     */
    @PreAuthorize("@ss.hasPermi('credit:type:remove')")
    @Log(title = "加分项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{typeIds}")
    public AjaxResult remove(@PathVariable Long[] typeIds)
    {
        return toAjax(sysCreditTypeService.deleteSysCreditTypeByTypeIds(typeIds));
    }
}
