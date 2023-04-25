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
import com.boot.credit.domain.SysCreditRoute;
import com.boot.credit.service.ISysCreditRouteService;
import com.boot.common.utils.poi.ExcelUtil;
import com.boot.common.core.page.TableDataInfo;

/**
 * 申请路线Controller
 * 
 * @author boot
 * @date 2023-04-25
 */
@RestController
@RequestMapping("/credit/route")
public class SysCreditRouteController extends BaseController
{
    @Autowired
    private ISysCreditRouteService sysCreditRouteService;

    /**
     * 查询申请路线列表
     */
    @PreAuthorize("@ss.hasPermi('credit:route:list')")
    @GetMapping("/list")
    public TableDataInfo list(SysCreditRoute sysCreditRoute)
    {
        startPage();
        List<SysCreditRoute> list = sysCreditRouteService.selectSysCreditRouteList(sysCreditRoute);
        return getDataTable(list);
    }

    /**
     * 导出申请路线列表
     */
    @PreAuthorize("@ss.hasPermi('credit:route:export')")
    @Log(title = "申请路线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, SysCreditRoute sysCreditRoute)
    {
        List<SysCreditRoute> list = sysCreditRouteService.selectSysCreditRouteList(sysCreditRoute);
        ExcelUtil<SysCreditRoute> util = new ExcelUtil<SysCreditRoute>(SysCreditRoute.class);
        util.exportExcel(response, list, "申请路线数据");
    }

    /**
     * 获取申请路线详细信息
     */
    @PreAuthorize("@ss.hasPermi('credit:route:query')")
    @GetMapping(value = "/{routeId}")
    public AjaxResult getInfo(@PathVariable("routeId") Long routeId)
    {
        return success(sysCreditRouteService.selectSysCreditRouteByRouteId(routeId));
    }

    /**
     * 新增申请路线
     */
    @PreAuthorize("@ss.hasPermi('credit:route:add')")
    @Log(title = "申请路线", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody SysCreditRoute sysCreditRoute)
    {
        return toAjax(sysCreditRouteService.insertSysCreditRoute(sysCreditRoute));
    }

    /**
     * 修改申请路线
     */
    @PreAuthorize("@ss.hasPermi('credit:route:edit')")
    @Log(title = "申请路线", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody SysCreditRoute sysCreditRoute)
    {
        return toAjax(sysCreditRouteService.updateSysCreditRoute(sysCreditRoute));
    }

    /**
     * 删除申请路线
     */
    @PreAuthorize("@ss.hasPermi('credit:route:remove')")
    @Log(title = "申请路线", businessType = BusinessType.DELETE)
	@DeleteMapping("/{routeIds}")
    public AjaxResult remove(@PathVariable Long[] routeIds)
    {
        return toAjax(sysCreditRouteService.deleteSysCreditRouteByRouteIds(routeIds));
    }
}
