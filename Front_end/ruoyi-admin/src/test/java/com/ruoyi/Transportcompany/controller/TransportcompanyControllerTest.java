package com.ruoyi.Transportcompany.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.Transportcompany.domain.Transportcompany;
import com.ruoyi.Transportcompany.service.ITransportcompanyService;
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
 * TransportcompanyController的单元测试类，使用JUnit4进行测试
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class TransportcompanyControllerTest {

    // 注入Controller和模拟的Service
    @InjectMocks
    private TransportcompanyController transportcompanyController;

    @Mock
    private ITransportcompanyService transportcompanyService;

    // MockMvc对象，用于模拟HTTP请求
    private MockMvc mockMvc;

    // 在每个测试方法之前执行，初始化MockMvc对象
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(transportcompanyController).build();
    }

    /**
     * 测试查询Transportcompany列表接口
     * @throws Exception
     */
    @Test
    public void testListTransportcompany() throws Exception {
        // 模拟Service层返回的数据
        when(transportcompanyService.selectTransportcompanyList(any(Transportcompany.class)))
                .thenReturn(List.of(new Transportcompany(), new Transportcompany()));

        // 模拟GET请求访问 /Transportcompany/transportcompany/list
        mockMvc.perform(get("/Transportcompany/transportcompany/list"))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试获取Transportcompany详细信息接口
     * @throws Exception
     */
    @Test
    public void testGetTransportcompanyInfo() throws Exception {
        // 模拟一个Transportcompany对象
        Transportcompany transportcompany = new Transportcompany();
        transportcompany.setCompanyId(1L);
        when(transportcompanyService.selectTransportcompanyByCompanyId(1L)).thenReturn(transportcompany);

        // 模拟GET请求访问 /Transportcompany/transportcompany/{companyId}
        mockMvc.perform(get("/Transportcompany/transportcompany/{companyId}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.data.companyId").value(1L));  // 期望返回的companyId为1
    }

    /**
     * 测试新增Transportcompany接口
     * @throws Exception
     */
    @Test
    public void testAddTransportcompany() throws Exception {
        Transportcompany transportcompany = new Transportcompany();
        transportcompany.setCompanyId(1L);
        when(transportcompanyService.insertTransportcompany(any(Transportcompany.class))).thenReturn(1);  // 假设返回1表示插入成功

        // 模拟POST请求访问 /Transportcompany/transportcompany
        mockMvc.perform(post("/Transportcompany/transportcompany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyId\": 1, \"companyName\": \"Test TransportCompany\"}"))  // 传入JSON格式的Transportcompany数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试修改Transportcompany接口
     * @throws Exception
     */
    @Test
    public void testEditTransportcompany() throws Exception {
        Transportcompany transportcompany = new Transportcompany();
        transportcompany.setCompanyId(1L);
        when(transportcompanyService.updateTransportcompany(any(Transportcompany.class))).thenReturn(1);  // 假设返回1表示更新成功

        // 模拟PUT请求访问 /Transportcompany/transportcompany
        mockMvc.perform(put("/Transportcompany/transportcompany")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyId\": 1, \"companyName\": \"Updated TransportCompany\"}"))  // 传入JSON格式的更新数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试删除Transportcompany接口
     * @throws Exception
     */
    @Test
    public void testDeleteTransportcompany() throws Exception {
        when(transportcompanyService.deleteTransportcompanyByCompanyIds(any(Long[].class))).thenReturn(1);  // 假设返回1表示删除成功

        // 模拟DELETE请求访问 /Transportcompany/transportcompany/{companyIds}
        mockMvc.perform(delete("/Transportcompany/transportcompany/{companyIds}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试导出Transportcompany数据接口
     * @throws Exception
     */
    @Test
    public void testExportTransportcompany() throws Exception {
        List<Transportcompany> transportcompanies = List.of(new Transportcompany(), new Transportcompany());
        when(transportcompanyService.selectTransportcompanyList(any(Transportcompany.class))).thenReturn(transportcompanies);

        // 模拟POST请求访问导出接口 /Transportcompany/transportcompany/export
        mockMvc.perform(post("/Transportcompany/transportcompany/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"companyId\": 1}"))  // 传入参数
                .andExpect(status().isOk());  // 期望返回状态为200
    }
}
