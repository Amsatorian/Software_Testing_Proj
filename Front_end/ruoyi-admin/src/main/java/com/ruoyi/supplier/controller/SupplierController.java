package com.ruoyi.supplier.controller;

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
import com.ruoyi.supplier.domain.Supplier;
import com.ruoyi.supplier.service.ISupplierService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * supplierController
 * 
 * @author zx
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/supplier/supplier")
public class SupplierController extends BaseController
{
    @Autowired
    private ISupplierService supplierService;

    /**
     * 查询supplier列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:list')")
    @GetMapping("/list")
    public TableDataInfo list(Supplier supplier)
    {
        startPage();
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        return getDataTable(list);
    }

    /**
     * 导出supplier列表
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:export')")
    @Log(title = "supplier", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Supplier supplier)
    {
        List<Supplier> list = supplierService.selectSupplierList(supplier);
        ExcelUtil<Supplier> util = new ExcelUtil<Supplier>(Supplier.class);
        util.exportExcel(response, list, "supplier数据");
    }

    /**
     * 获取supplier详细信息
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:query')")
    @GetMapping(value = "/{supplierId}")
    public AjaxResult getInfo(@PathVariable("supplierId") Long supplierId)
    {
        return success(supplierService.selectSupplierBySupplierId(supplierId));
    }

    /**
     * 新增supplier
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:add')")
    @Log(title = "supplier", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.insertSupplier(supplier));
    }

    /**
     * 修改supplier
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:edit')")
    @Log(title = "supplier", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Supplier supplier)
    {
        return toAjax(supplierService.updateSupplier(supplier));
    }

    /**
     * 删除supplier
     */
    @PreAuthorize("@ss.hasPermi('supplier:supplier:remove')")
    @Log(title = "supplier", businessType = BusinessType.DELETE)
	@DeleteMapping("/{supplierIds}")
    public AjaxResult remove(@PathVariable Long[] supplierIds)
    {
        return toAjax(supplierService.deleteSupplierBySupplierIds(supplierIds));
    }
}
