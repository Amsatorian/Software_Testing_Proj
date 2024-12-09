package com.ruoyi.Employee.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.Employee.domain.Employee;
import com.ruoyi.Employee.service.IEmployeeService;
import com.ruoyi.common.core.page.TableDataInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * EmployeeController的单元测试类，使用JUnit4进行测试
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class EmployeeControllerTest {

    // 注入Controller和模拟的Service
    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private IEmployeeService employeeService;

    // MockMvc对象，用于模拟HTTP请求
    private MockMvc mockMvc;

    // 在每个测试方法之前执行，初始化MockMvc对象
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    /**
     * 测试查询员工列表接口
     * @throws Exception
     */
    @Test
    public void testListEmployees() throws Exception {
        // 模拟Service层返回的数据
        when(employeeService.selectEmployeeList(any(Employee.class))).thenReturn(List.of(new Employee(), new Employee()));

        // 模拟GET请求访问 /Employee/employee/list
        mockMvc.perform(get("/Employee/employee/list"))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试获取员工详细信息接口
     * @throws Exception
     */
    @Test
    public void testGetEmployeeInfo() throws Exception {
        // 模拟一个Employee对象
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        when(employeeService.selectEmployeeByEmployeeId(1L)).thenReturn(employee);

        // 模拟GET请求访问 /Employee/employee/{employeeId}
        mockMvc.perform(get("/Employee/employee/{employeeId}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.data.employeeId").value(1L));  // 期望返回的员工ID为1
    }

    /**
     * 测试新增员工接口
     * @throws Exception
     */
    @Test
    public void testAddEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        when(employeeService.insertEmployee(any(Employee.class))).thenReturn(1);  // 假设返回1表示插入成功

        // 模拟POST请求访问 /Employee/employee
        mockMvc.perform(post("/Employee/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeId\": 1, \"name\": \"Test Employee\"}"))  // 传入JSON格式的员工数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试修改员工信息接口
     * @throws Exception
     */
    @Test
    public void testEditEmployee() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        when(employeeService.updateEmployee(any(Employee.class))).thenReturn(1);  // 假设返回1表示更新成功

        // 模拟PUT请求访问 /Employee/employee
        mockMvc.perform(put("/Employee/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeId\": 1, \"name\": \"Updated Employee\"}"))  // 传入JSON格式的更新数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试删除员工接口
     * @throws Exception
     */
    @Test
    public void testDeleteEmployee() throws Exception {
        when(employeeService.deleteEmployeeByEmployeeIds(any(Long[].class))).thenReturn(1);  // 假设返回1表示删除成功

        // 模拟DELETE请求访问 /Employee/employee/{employeeIds}
        mockMvc.perform(delete("/Employee/employee/{employeeIds}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试导出员工数据接口
     * @throws Exception
     */
    @Test
    public void testExportEmployee() throws Exception {
        List<Employee> employees = List.of(new Employee(), new Employee());
        when(employeeService.selectEmployeeList(any(Employee.class))).thenReturn(employees);

        // 模拟POST请求访问导出接口 /Employee/employee/export
        mockMvc.perform(post("/Employee/employee/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeId\": 1}"))  // 传入参数
                .andExpect(status().isOk());  // 期望返回状态为200
    }
}
