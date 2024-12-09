package com.ruoyi.customer.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.service.ICustomerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
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
 * CustomerController的单元测试类，使用JUnit4进行测试
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class CustomerControllerTest {

    // 注入Controller和模拟的Service
    @InjectMocks
    private CustomerController customerController;

    @Mock
    private ICustomerService customerService;

    // MockMvc对象，用于模拟HTTP请求
    private MockMvc mockMvc;

    // 在每个测试方法之前执行，初始化MockMvc对象
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    /**
     * 测试查询客户列表接口
     * @throws Exception
     */
    @Test
    public void testListCustomers() throws Exception {
        // 模拟Service层返回的数据
        when(customerService.selectCustomerList(any(Customer.class))).thenReturn(List.of(new Customer(), new Customer()));

        // 模拟GET请求访问 /customer/customer/list
        mockMvc.perform(get("/customer/customer/list"))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试获取客户详细信息接口
     * @throws Exception
     */
    @Test
    public void testGetCustomerInfo() throws Exception {
        // 模拟一个Customer对象
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        when(customerService.selectCustomerByCustomerId(1L)).thenReturn(customer);

        // 模拟GET请求访问 /customer/customer/{customerId}
        mockMvc.perform(get("/customer/customer/{customerId}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.data.customerId").value(1L));  // 期望返回的客户ID为1
    }

    /**
     * 测试新增客户接口
     * @throws Exception
     */
    @Test
    public void testAddCustomer() throws Exception {
        // 模拟一个Customer对象
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        when(customerService.insertCustomer(any(Customer.class))).thenReturn(1);  // 假设返回1表示插入成功

        // 模拟POST请求访问 /customer/customer
        mockMvc.perform(post("/customer/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": 1, \"name\": \"Test Customer\"}"))  // 传入JSON格式的客户数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试修改客户信息接口
     * @throws Exception
     */
    @Test
    public void testEditCustomer() throws Exception {
        // 模拟一个Customer对象
        Customer customer = new Customer();
        customer.setCustomerId(1L);
        when(customerService.updateCustomer(any(Customer.class))).thenReturn(1);  // 假设返回1表示更新成功

        // 模拟PUT请求访问 /customer/customer
        mockMvc.perform(put("/customer/customer")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": 1, \"name\": \"Updated Customer\"}"))  // 传入JSON格式的更新数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试删除客户接口
     * @throws Exception
     */
    @Test
    public void testDeleteCustomer() throws Exception {
        // 模拟Service层删除操作
        when(customerService.deleteCustomerByCustomerIds(any(Long[].class))).thenReturn(1);  // 假设返回1表示删除成功

        // 模拟DELETE请求访问 /customer/customer/{customerIds}
        mockMvc.perform(delete("/customer/customer/{customerIds}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试导出客户数据接口
     * @throws Exception
     */
    @Test
    public void testExportCustomer() throws Exception {
        // 模拟查询返回多个客户对象
        List<Customer> customers = List.of(new Customer(), new Customer());
        when(customerService.selectCustomerList(any(Customer.class))).thenReturn(customers);

        // 模拟POST请求访问导出接口 /customer/customer/export
        mockMvc.perform(post("/customer/customer/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"customerId\": 1}"))  // 传入参数
                .andExpect(status().isOk());  // 期望返回状态为200
    }
}
