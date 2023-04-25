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
import com.boot.credit.domain.SysCreditRouteItem;
import com.boot.credit.service.ISysCreditRouteItemService;
import com.boot.common.utils.poi.ExcelUtil;

/**
 * 申请路线子项Controller
 * 
 * @author Siaze
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/credit/item")
public class SysCreditRouteItemController extends BaseController
{
    @Autowired
    private ISysCreditRouteItemService sysCreditRouteItemService;

    /**
     * 查询申请路线子项列表
     */
    @PreAuthorize("@ss.hasPermi('credit:item:list')")
    @GetMapping("/list")
    public AjaxResult list(SysCreditRouteItem sysCreditRouteItem)
    {
        List<SysCreditRouteItem> list = sysCreditRouteItemService.selectSysCreditRouteItemList(sysCreditRouteItem);
        return success(list);
    }

    /**
     * 导出申请路线子项列表
     */
    @PreAuthorize("@ss.hasPermi('credit:item:export')")
    @Log(title = "申请路线子项", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCreditRouteItem sysCreditRouteItem)
    {
        List<SysCreditRouteItem> list = sysCreditRouteItemService.selectSysCreditRouteItemList(sysCreditRouteItem);
        ExcelUtil<SysCreditRouteItem> util = new ExcelUtil<SysCreditRouteItem>(SysCreditRouteItem.class);
        util.exportExcel(response, list, "申请路线子项数据");
    }

    /**
     * 获取申请路线子项详细信息
     */
    @PreAuthorize("@ss.hasPermi('credit:item:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(sysCreditRouteItemService.selectSysCreditRouteItemById(id));
    }

    /**
     * 新增申请路线子项
     */
    @PreAuthorize("@ss.hasPermi('credit:item:add')")
    @Log(title = "申请路线子项", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCreditRouteItem sysCreditRouteItem)
    {
        return toAjax(sysCreditRouteItemService.insertSysCreditRouteItem(sysCreditRouteItem));
    }

    /**
     * 修改申请路线子项
     */
    @PreAuthorize("@ss.hasPermi('credit:item:edit')")
    @Log(title = "申请路线子项", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCreditRouteItem sysCreditRouteItem)
    {
        return toAjax(sysCreditRouteItemService.updateSysCreditRouteItem(sysCreditRouteItem));
    }

    /**
     * 删除申请路线子项
     */
    @PreAuthorize("@ss.hasPermi('credit:item:remove')")
    @Log(title = "申请路线子项", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(sysCreditRouteItemService.deleteSysCreditRouteItemByIds(ids));
    }
}
