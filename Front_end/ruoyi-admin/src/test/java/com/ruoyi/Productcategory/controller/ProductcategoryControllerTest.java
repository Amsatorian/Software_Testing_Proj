package com.ruoyi.Productcategory.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.Productcategory.domain.Productcategory;
import com.ruoyi.Productcategory.service.IProductcategoryService;
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
 * ProductcategoryController的单元测试类，使用JUnit4进行测试
 */
@RunWith(org.mockito.junit.MockitoJUnitRunner.class)
public class ProductcategoryControllerTest {

    // 注入Controller和模拟的Service
    @InjectMocks
    private ProductcategoryController productcategoryController;

    @Mock
    private IProductcategoryService productcategoryService;

    // MockMvc对象，用于模拟HTTP请求
    private MockMvc mockMvc;

    // 在每个测试方法之前执行，初始化MockMvc对象
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productcategoryController).build();
    }

    /**
     * 测试查询产品分类列表接口
     * @throws Exception
     */
    @Test
    public void testListProductcategories() throws Exception {
        // 模拟Service层返回的数据
        when(productcategoryService.selectProductcategoryList(any(Productcategory.class)))
                .thenReturn(List.of(new Productcategory(), new Productcategory()));

        // 模拟GET请求访问 /Productcategory/productcategory/list
        mockMvc.perform(get("/Productcategory/productcategory/list"))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试获取产品分类详细信息接口
     * @throws Exception
     */
    @Test
    public void testGetProductcategoryInfo() throws Exception {
        // 模拟一个Productcategory对象
        Productcategory productcategory = new Productcategory();
        productcategory.setCategoryId(1L);
        when(productcategoryService.selectProductcategoryByCategoryId(1L)).thenReturn(productcategory);

        // 模拟GET请求访问 /Productcategory/productcategory/{categoryId}
        mockMvc.perform(get("/Productcategory/productcategory/{categoryId}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.data.categoryId").value(1L));  // 期望返回的产品分类ID为1
    }

    /**
     * 测试新增产品分类接口
     * @throws Exception
     */
    @Test
    public void testAddProductcategory() throws Exception {
        Productcategory productcategory = new Productcategory();
        productcategory.setCategoryId(1L);
        when(productcategoryService.insertProductcategory(any(Productcategory.class))).thenReturn(1);  // 假设返回1表示插入成功

        // 模拟POST请求访问 /Productcategory/productcategory
        mockMvc.perform(post("/Productcategory/productcategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\": 1, \"categoryName\": \"Test Category\"}"))  // 传入JSON格式的产品分类数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试修改产品分类信息接口
     * @throws Exception
     */
    @Test
    public void testEditProductcategory() throws Exception {
        Productcategory productcategory = new Productcategory();
        productcategory.setCategoryId(1L);
        when(productcategoryService.updateProductcategory(any(Productcategory.class))).thenReturn(1);  // 假设返回1表示更新成功

        // 模拟PUT请求访问 /Productcategory/productcategory
        mockMvc.perform(put("/Productcategory/productcategory")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\": 1, \"categoryName\": \"Updated Category\"}"))  // 传入JSON格式的更新数据
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试删除产品分类接口
     * @throws Exception
     */
    @Test
    public void testDeleteProductcategory() throws Exception {
        when(productcategoryService.deleteProductcategoryByCategoryIds(any(Long[].class))).thenReturn(1);  // 假设返回1表示删除成功

        // 模拟DELETE请求访问 /Productcategory/productcategory/{categoryIds}
        mockMvc.perform(delete("/Productcategory/productcategory/{categoryIds}", 1L))
                .andExpect(status().isOk())  // 期望返回状态为200
                .andExpect(jsonPath("$.code").value(200));  // 假设返回的JSON中code字段为200表示成功
    }

    /**
     * 测试导出产品分类数据接口
     * @throws Exception
     */
    @Test
    public void testExportProductcategory() throws Exception {
        List<Productcategory> productcategories = List.of(new Productcategory(), new Productcategory());
        when(productcategoryService.selectProductcategoryList(any(Productcategory.class))).thenReturn(productcategories);

        // 模拟POST请求访问导出接口 /Productcategory/productcategory/export
        mockMvc.perform(post("/Productcategory/productcategory/export")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"categoryId\": 1}"))  // 传入参数
                .andExpect(status().isOk());  // 期望返回状态为200
    }
}
