package com.ruoyi.Productcategory.controller;

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
import com.ruoyi.Productcategory.domain.Productcategory;
import com.ruoyi.Productcategory.service.IProductcategoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * ProductcategoryController
 * 
 * @author zx
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/Productcategory/productcategory")
public class ProductcategoryController extends BaseController
{
    @Autowired
    private IProductcategoryService productcategoryService;

    /**
     * 查询Productcategory列表
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:list')")
    @GetMapping("/list")
    public TableDataInfo list(Productcategory productcategory)
    {
        startPage();
        List<Productcategory> list = productcategoryService.selectProductcategoryList(productcategory);
        return getDataTable(list);
    }

    /**
     * 导出Productcategory列表
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:export')")
    @Log(title = "Productcategory", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Productcategory productcategory)
    {
        List<Productcategory> list = productcategoryService.selectProductcategoryList(productcategory);
        ExcelUtil<Productcategory> util = new ExcelUtil<Productcategory>(Productcategory.class);
        util.exportExcel(response, list, "Productcategory数据");
    }

    /**
     * 获取Productcategory详细信息
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:query')")
    @GetMapping(value = "/{categoryId}")
    public AjaxResult getInfo(@PathVariable("categoryId") Long categoryId)
    {
        return success(productcategoryService.selectProductcategoryByCategoryId(categoryId));
    }

    /**
     * 新增Productcategory
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:add')")
    @Log(title = "Productcategory", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Productcategory productcategory)
    {
        return toAjax(productcategoryService.insertProductcategory(productcategory));
    }

    /**
     * 修改Productcategory
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:edit')")
    @Log(title = "Productcategory", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Productcategory productcategory)
    {
        return toAjax(productcategoryService.updateProductcategory(productcategory));
    }

    /**
     * 删除Productcategory
     */
    @PreAuthorize("@ss.hasPermi('Productcategory:productcategory:remove')")
    @Log(title = "Productcategory", businessType = BusinessType.DELETE)
	@DeleteMapping("/{categoryIds}")
    public AjaxResult remove(@PathVariable Long[] categoryIds)
    {
        return toAjax(productcategoryService.deleteProductcategoryByCategoryIds(categoryIds));
    }
}
