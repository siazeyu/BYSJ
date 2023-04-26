package com.boot.credit.controller;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;

import com.boot.common.core.domain.model.LoginUser;
import com.boot.common.exception.ServiceException;
import com.boot.common.utils.DateUtils;
import com.boot.credit.domain.*;
import com.boot.credit.service.*;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
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

    @Autowired
    private ISysCreditTypeService sysCreditTypeService;

    @Autowired
    private ISysCreditRecordService sysCreditRecordService;

    @Autowired
    private ISysCreditRouteService sysCreditRouteService;

    @Autowired
    private ISysCreditRouteItemService sysCreditRouteItemService;



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
     * 查询学分申请列表
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:list')")
    @GetMapping("/getApproval")
    public TableDataInfo getApproval()
    {
        List<SysCreditRouteItem> items = sysCreditRouteItemService.selectSysCreditRouteItemListByDeptId(getLoginUser().getDeptId());
        List<Long> collect = items.stream().map(SysCreditRouteItem::getId).collect(Collectors.toList());
        startPage();
        List<SysCredit> list = sysCreditService.selectSysCreditListByItems(collect);
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
        sysCredit.setCreditDate(new Date());
        LoginUser loginUser = getLoginUser();
        sysCredit.setUserId(loginUser.getUserId());
        sysCredit.setUsername(loginUser.getUsername());
        sysCredit.setStatue(CreditStatue.APPLYING);
        SysCreditType sysCreditType = sysCreditTypeService.selectSysCreditTypeByTypeId(sysCredit.getCreditType());
        sysCredit.setCredit(sysCreditType.getPoint());
        Long deptId = getLoginUser().getDeptId();

        // 获取第一个审批阶段
        List<SysCreditRouteItem> items = sysCreditRouteItemService.selectSysCreditRouteItemListByDeptId(deptId);
        for (SysCreditRouteItem item : items) {
            if (item.getNextId() != null && item.getNextId() != 0){
                sysCredit.setItemId(item.getId());
                break;
            }
        }
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


    /**
     * 审批学分申请
     */
    @PreAuthorize("@ss.hasPermi('credit:credit:approval')")
    @Log(title = "学分申请审批", businessType = BusinessType.UPDATE)
    @Transactional
    @PutMapping("/approval")
    public AjaxResult approval(@RequestBody SysCreditRecord sysCreditRecord)
    {
        SysCredit sysCredit = sysCreditService.selectSysCreditById(sysCreditRecord.getApplyId());
        SysCreditType sysCreditType = sysCreditTypeService.selectSysCreditTypeByTypeId(sysCredit.getCreditType());
        sysCreditRecord.setCredit(sysCredit.getCredit());
        sysCreditRecord.setCreditTypeId(sysCreditType.getTypeId());
        sysCreditRecord.setCreditTypeName(sysCreditType.getTypeName());
        sysCreditRecord.setOperator(getUsername());
        sysCreditRecord.setApplicants(sysCredit.getUsername());
        sysCreditRecord.setCreateTime(DateUtils.getNowDate());

        SysCreditRouteItem routeItem = sysCreditRouteItemService.selectSysCreditRouteItemById(sysCredit.getItemId());
        // 判断审批是否通过
        if (sysCreditRecord.getResult() == null || sysCreditRecord.getResult() != CreditStatue.SUCCESS){
            sysCredit.setStatue(CreditStatue.FAIL);
            sysCredit.setFinalDate(DateUtils.getNowDate());
            sysCreditRecord.setResult(CreditStatue.FAIL);
            // 判断审批是否结束
        }else if (routeItem.getParentId() != null && routeItem.getParentId() == 0){
            //通过审批，且完成全部审批
            sysCredit.setStatue(CreditStatue.SUCCESS);
            sysCredit.setFinalDate(DateUtils.getNowDate());
            sysCreditRecord.setResult(CreditStatue.SUCCESS);
        }else {
            // 通过，进入下阶段审批
            sysCredit.setItemId(routeItem.getParentId());
            sysCreditRecord.setResult(CreditStatue.SUCCESS);
        }
        sysCreditService.updateSysCredit(sysCredit);
        return toAjax(sysCreditRecordService.insertSysCreditRecord(sysCreditRecord));
    }


}
