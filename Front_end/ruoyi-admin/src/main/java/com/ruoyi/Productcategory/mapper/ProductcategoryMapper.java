package com.ruoyi.Productcategory.mapper;

import java.util.List;
import com.ruoyi.Productcategory.domain.Productcategory;

/**
 * ProductcategoryMapper接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface ProductcategoryMapper 
{
    /**
     * 查询Productcategory
     * 
     * @param categoryId Productcategory主键
     * @return Productcategory
     */
    public Productcategory selectProductcategoryByCategoryId(Long categoryId);

    /**
     * 查询Productcategory列表
     * 
     * @param productcategory Productcategory
     * @return Productcategory集合
     */
    public List<Productcategory> selectProductcategoryList(Productcategory productcategory);

    /**
     * 新增Productcategory
     * 
     * @param productcategory Productcategory
     * @return 结果
     */
    public int insertProductcategory(Productcategory productcategory);

    /**
     * 修改Productcategory
     * 
     * @param productcategory Productcategory
     * @return 结果
     */
    public int updateProductcategory(Productcategory productcategory);

    /**
     * 删除Productcategory
     * 
     * @param categoryId Productcategory主键
     * @return 结果
     */
    public int deleteProductcategoryByCategoryId(Long categoryId);

    /**
     * 批量删除Productcategory
     * 
     * @param categoryIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteProductcategoryByCategoryIds(Long[] categoryIds);
}
