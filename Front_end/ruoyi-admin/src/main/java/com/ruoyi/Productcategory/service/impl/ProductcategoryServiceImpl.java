package com.ruoyi.Productcategory.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Productcategory.mapper.ProductcategoryMapper;
import com.ruoyi.Productcategory.domain.Productcategory;
import com.ruoyi.Productcategory.service.IProductcategoryService;

/**
 * ProductcategoryService业务层处理
 * 
 * @author zx
 * @date 2024-11-27
 */
@Service
public class ProductcategoryServiceImpl implements IProductcategoryService 
{
    @Autowired
    private ProductcategoryMapper productcategoryMapper;

    /**
     * 查询Productcategory
     * 
     * @param categoryId Productcategory主键
     * @return Productcategory
     */
    @Override
    public Productcategory selectProductcategoryByCategoryId(Long categoryId)
    {
        return productcategoryMapper.selectProductcategoryByCategoryId(categoryId);
    }

    /**
     * 查询Productcategory列表
     * 
     * @param productcategory Productcategory
     * @return Productcategory
     */
    @Override
    public List<Productcategory> selectProductcategoryList(Productcategory productcategory)
    {
        return productcategoryMapper.selectProductcategoryList(productcategory);
    }

    /**
     * 新增Productcategory
     * 
     * @param productcategory Productcategory
     * @return 结果
     */
    @Override
    public int insertProductcategory(Productcategory productcategory)
    {
        productcategory.setCreateTime(DateUtils.getNowDate());
        return productcategoryMapper.insertProductcategory(productcategory);
    }

    /**
     * 修改Productcategory
     * 
     * @param productcategory Productcategory
     * @return 结果
     */
    @Override
    public int updateProductcategory(Productcategory productcategory)
    {
        productcategory.setUpdateTime(DateUtils.getNowDate());
        return productcategoryMapper.updateProductcategory(productcategory);
    }

    /**
     * 批量删除Productcategory
     * 
     * @param categoryIds 需要删除的Productcategory主键
     * @return 结果
     */
    @Override
    public int deleteProductcategoryByCategoryIds(Long[] categoryIds)
    {
        return productcategoryMapper.deleteProductcategoryByCategoryIds(categoryIds);
    }

    /**
     * 删除Productcategory信息
     * 
     * @param categoryId Productcategory主键
     * @return 结果
     */
    @Override
    public int deleteProductcategoryByCategoryId(Long categoryId)
    {
        return productcategoryMapper.deleteProductcategoryByCategoryId(categoryId);
    }
}
