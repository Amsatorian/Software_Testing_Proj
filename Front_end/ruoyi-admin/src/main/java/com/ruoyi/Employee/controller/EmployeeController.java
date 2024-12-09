package com.ruoyi.Employee.controller;

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
import com.ruoyi.Employee.domain.Employee;
import com.ruoyi.Employee.service.IEmployeeService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * EmployeeController
 * 
 * @author zx
 * @date 2024-11-27
 */
@RestController
@RequestMapping("/Employee/employee")
public class EmployeeController extends BaseController
{
    @Autowired
    private IEmployeeService employeeService;

    /**
     * 查询Employee列表
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:list')")
    @GetMapping("/list")
    public TableDataInfo list(Employee employee)
    {
        startPage();
        List<Employee> list = employeeService.selectEmployeeList(employee);
        return getDataTable(list);
    }

    /**
     * 导出Employee列表
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:export')")
    @Log(title = "Employee", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, Employee employee)
    {
        List<Employee> list = employeeService.selectEmployeeList(employee);
        ExcelUtil<Employee> util = new ExcelUtil<Employee>(Employee.class);
        util.exportExcel(response, list, "Employee数据");
    }

    /**
     * 获取Employee详细信息
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:query')")
    @GetMapping(value = "/{employeeId}")
    public AjaxResult getInfo(@PathVariable("employeeId") Long employeeId)
    {
        return success(employeeService.selectEmployeeByEmployeeId(employeeId));
    }

    /**
     * 新增Employee
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:add')")
    @Log(title = "Employee", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Employee employee)
    {
        return toAjax(employeeService.insertEmployee(employee));
    }

    /**
     * 修改Employee
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:edit')")
    @Log(title = "Employee", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Employee employee)
    {
        return toAjax(employeeService.updateEmployee(employee));
    }

    /**
     * 删除Employee
     */
    @PreAuthorize("@ss.hasPermi('Employee:employee:remove')")
    @Log(title = "Employee", businessType = BusinessType.DELETE)
	@DeleteMapping("/{employeeIds}")
    public AjaxResult remove(@PathVariable Long[] employeeIds)
    {
        return toAjax(employeeService.deleteEmployeeByEmployeeIds(employeeIds));
    }
}
