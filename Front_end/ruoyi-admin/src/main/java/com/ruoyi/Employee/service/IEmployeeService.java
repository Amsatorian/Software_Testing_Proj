package com.ruoyi.Employee.service;

import java.util.List;
import com.ruoyi.Employee.domain.Employee;

/**
 * EmployeeService接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface IEmployeeService 
{
    /**
     * 查询Employee
     * 
     * @param employeeId Employee主键
     * @return Employee
     */
    public Employee selectEmployeeByEmployeeId(Long employeeId);

    /**
     * 查询Employee列表
     * 
     * @param employee Employee
     * @return Employee集合
     */
    public List<Employee> selectEmployeeList(Employee employee);

    /**
     * 新增Employee
     * 
     * @param employee Employee
     * @return 结果
     */
    public int insertEmployee(Employee employee);

    /**
     * 修改Employee
     * 
     * @param employee Employee
     * @return 结果
     */
    public int updateEmployee(Employee employee);

    /**
     * 批量删除Employee
     * 
     * @param employeeIds 需要删除的Employee主键集合
     * @return 结果
     */
    public int deleteEmployeeByEmployeeIds(Long[] employeeIds);

    /**
     * 删除Employee信息
     * 
     * @param employeeId Employee主键
     * @return 结果
     */
    public int deleteEmployeeByEmployeeId(Long employeeId);
}
