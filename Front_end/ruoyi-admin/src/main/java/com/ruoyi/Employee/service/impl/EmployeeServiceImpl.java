package com.ruoyi.Employee.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Employee.mapper.EmployeeMapper;
import com.ruoyi.Employee.domain.Employee;
import com.ruoyi.Employee.service.IEmployeeService;

/**
 * EmployeeService业务层处理
 * 
 * @author zx
 * @date 2024-11-27
 */
@Service
public class EmployeeServiceImpl implements IEmployeeService 
{
    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 查询Employee
     * 
     * @param employeeId Employee主键
     * @return Employee
     */
    @Override
    public Employee selectEmployeeByEmployeeId(Long employeeId)
    {
        return employeeMapper.selectEmployeeByEmployeeId(employeeId);
    }

    /**
     * 查询Employee列表
     * 
     * @param employee Employee
     * @return Employee
     */
    @Override
    public List<Employee> selectEmployeeList(Employee employee)
    {
        return employeeMapper.selectEmployeeList(employee);
    }

    /**
     * 新增Employee
     * 
     * @param employee Employee
     * @return 结果
     */
    @Override
    public int insertEmployee(Employee employee)
    {
        employee.setCreateTime(DateUtils.getNowDate());
        return employeeMapper.insertEmployee(employee);
    }

    /**
     * 修改Employee
     * 
     * @param employee Employee
     * @return 结果
     */
    @Override
    public int updateEmployee(Employee employee)
    {
        employee.setUpdateTime(DateUtils.getNowDate());
        return employeeMapper.updateEmployee(employee);
    }

    /**
     * 批量删除Employee
     * 
     * @param employeeIds 需要删除的Employee主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeByEmployeeIds(Long[] employeeIds)
    {
        return employeeMapper.deleteEmployeeByEmployeeIds(employeeIds);
    }

    /**
     * 删除Employee信息
     * 
     * @param employeeId Employee主键
     * @return 结果
     */
    @Override
    public int deleteEmployeeByEmployeeId(Long employeeId)
    {
        return employeeMapper.deleteEmployeeByEmployeeId(employeeId);
    }
}
