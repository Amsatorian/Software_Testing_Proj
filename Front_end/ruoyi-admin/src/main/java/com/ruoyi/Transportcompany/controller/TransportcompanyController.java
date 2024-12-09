package com.ruoyi.Transportcompany.controller;

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
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.Transportcompany.domain.Transportcompany;
import com.ruoyi.Transportcompany.service.ITransportcompanyService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * TransportcompanyController
 * 
 * @author zx
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/Transportcompany/transportcompany")
public class TransportcompanyController extends BaseController
{
    @Autowired
    private ITransportcompanyService transportcompanyService;

    /**
     * 查询Transportcompany列表
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:list')")
    @GetMapping("/list")
    public TableDataInfo list(Transportcompany transportcompany)
    {
        startPage();
        List<Transportcompany> list = transportcompanyService.selectTransportcompanyList(transportcompany);
        return getDataTable(list);
    }

    /**
     * 导出Transportcompany列表
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:export')")
    @Log(title = "Transportcompany", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Transportcompany transportcompany)
    {
        List<Transportcompany> list = transportcompanyService.selectTransportcompanyList(transportcompany);
        ExcelUtil<Transportcompany> util = new ExcelUtil<Transportcompany>(Transportcompany.class);
        util.exportExcel(response, list, "Transportcompany数据");
    }

    /**
     * 获取Transportcompany详细信息
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:query')")
    @GetMapping(value = "/{companyId}")
    public AjaxResult getInfo(@PathVariable("companyId") Long companyId)
    {
        return success(transportcompanyService.selectTransportcompanyByCompanyId(companyId));
    }

    /**
     * 新增Transportcompany
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:add')")
    @Log(title = "Transportcompany", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Transportcompany transportcompany)
    {
        return toAjax(transportcompanyService.insertTransportcompany(transportcompany));
    }

    /**
     * 修改Transportcompany
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:edit')")
    @Log(title = "Transportcompany", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Transportcompany transportcompany)
    {
        return toAjax(transportcompanyService.updateTransportcompany(transportcompany));
    }

    /**
     * 删除Transportcompany
     */
    @PreAuthorize("@ss.hasPermi('Transportcompany:transportcompany:remove')")
    @Log(title = "Transportcompany", businessType = BusinessType.DELETE)
	@DeleteMapping("/{companyIds}")
    public AjaxResult remove(@PathVariable Long[] companyIds)
    {
        return toAjax(transportcompanyService.deleteTransportcompanyByCompanyIds(companyIds));
    }
}
