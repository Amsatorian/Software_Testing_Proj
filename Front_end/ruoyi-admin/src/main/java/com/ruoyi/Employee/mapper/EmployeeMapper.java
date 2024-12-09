package com.ruoyi.Employee.mapper;

import java.util.List;
import com.ruoyi.Employee.domain.Employee;

/**
 * EmployeeMapper接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface EmployeeMapper 
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
     * 删除Employee
     * 
     * @param employeeId Employee主键
     * @return 结果
     */
    public int deleteEmployeeByEmployeeId(Long employeeId);

    /**
     * 批量删除Employee
     * 
     * @param employeeIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteEmployeeByEmployeeIds(Long[] employeeIds);
}
