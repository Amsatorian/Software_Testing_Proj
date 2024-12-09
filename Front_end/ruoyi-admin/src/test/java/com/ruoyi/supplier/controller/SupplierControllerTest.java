package com.ruoyi.supplier.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.supplier.domain.Supplier;
import com.ruoyi.supplier.service.ISupplierService;
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
 * SupplierController的单元测试类，使用JUnit4进行测试
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class SupplierControllerTest {

    // 注入Controller和模拟的Service
    @InjectMocks
    private SupplierController supplierController;

    @Mock
    private ISupplierService supplierService;

    // MockMvc对象，用于模拟HTTP请求
    private MockMvc mockMvc;

    // 在每个测试方法之前执行，初始化MockMvc对象
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(supplierController).build();
    }

    /**
     * 测试查询供应商列表接口
     * @throws Exception
     */
    @Test
    public void testListSuppliers() throws Exception {
        // 模拟Service层返回的数据
        when(supplierService.selectSupplierList(any(Supplier.class)))
                .thenReturn(List.of(new Supplier(), new Supplier()));

        // 模拟GET请求访问 /supplier/supplier/list
        mockMvc.perform(get("/supplier/supplier/list"))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试获取供应商详细信息接口
     * @throws Exception
     */
    @Test
    public void testGetSupplierInfo() throws Exception {
        // 模拟一个Supplier对象
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(supplierService.selectSupplierBySupplierId(1L)).thenReturn(supplier);

        // 模拟GET请求访问 /supplier/supplier/{supplierId}
        mockMvc.perform(get("/supplier/supplier/{supplierId}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.data.supplierId").value(1L));  // 期望返回的供应商ID为1
    }

    /**
     * 测试新增供应商接口
     * @throws Exception
     */
    @Test
    public void testAddSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(supplierService.insertSupplier(any(Supplier.class))).thenReturn(1);  // 假设返回1表示插入成功

        // 模拟POST请求访问 /supplier/supplier
        mockMvc.perform(post("/supplier/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"supplierId\": 1, \"supplierName\": \"Test Supplier\"}"))  // 传入JSON格式的供应商数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试修改供应商信息接口
     * @throws Exception
     */
    @Test
    public void testEditSupplier() throws Exception {
        Supplier supplier = new Supplier();
        supplier.setSupplierId(1L);
        when(supplierService.updateSupplier(any(Supplier.class))).thenReturn(1);  // 假设返回1表示更新成功

        // 模拟PUT请求访问 /supplier/supplier
        mockMvc.perform(put("/supplier/supplier")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"supplierId\": 1, \"supplierName\": \"Updated Supplier\"}"))  // 传入JSON格式的更新数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试删除供应商接口
     * @throws Exception
     */
    @Test
    public void testDeleteSupplier() throws Exception {
        when(supplierService.deleteSupplierBySupplierIds(any(Long[].class))).thenReturn(1);  // 假设返回1表示删除成功

        // 模拟DELETE请求访问 /supplier/supplier/{supplierIds}
        mockMvc.perform(delete("/supplier/supplier/{supplierIds}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试导出供应商数据接口
     * @throws Exception
     */
    @Test
    public void testExportSupplier() throws Exception {
        List<Supplier> suppliers = List.of(new Supplier(), new Supplier());
        when(supplierService.selectSupplierList(any(Supplier.class))).thenReturn(suppliers);

        // 模拟POST请求访问导出接口 /supplier/supplier/export
        mockMvc.perform(post("/supplier/supplier/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"supplierId\": 1}"))  // 传入参数
                .andExpect(status().isOk());  // 期望返回状态为200
    }
}
