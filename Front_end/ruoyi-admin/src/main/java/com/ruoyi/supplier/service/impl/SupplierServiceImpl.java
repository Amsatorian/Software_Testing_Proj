package com.ruoyi.supplier.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.supplier.mapper.SupplierMapper;
import com.ruoyi.supplier.domain.Supplier;
import com.ruoyi.supplier.service.ISupplierService;

/**
 * supplierService业务层处理
 * 
 * @author zx
 * @date 2024-11-27
 */
@Service
public class SupplierServiceImpl implements ISupplierService 
{
    @Autowired
    private SupplierMapper supplierMapper;

    /**
     * 查询supplier
     * 
     * @param supplierId supplier主键
     * @return supplier
     */
    @Override
    public Supplier selectSupplierBySupplierId(Long supplierId)
    {
        return supplierMapper.selectSupplierBySupplierId(supplierId);
    }

    /**
     * 查询supplier列表
     * 
     * @param supplier supplier
     * @return supplier
     */
    @Override
    public List<Supplier> selectSupplierList(Supplier supplier)
    {
        return supplierMapper.selectSupplierList(supplier);
    }

    /**
     * 新增supplier
     * 
     * @param supplier supplier
     * @return 结果
     */
    @Override
    public int insertSupplier(Supplier supplier)
    {
        supplier.setCreateTime(DateUtils.getNowDate());
        return supplierMapper.insertSupplier(supplier);
    }

    /**
     * 修改supplier
     * 
     * @param supplier supplier
     * @return 结果
     */
    @Override
    public int updateSupplier(Supplier supplier)
    {
        supplier.setUpdateTime(DateUtils.getNowDate());
        return supplierMapper.updateSupplier(supplier);
    }

    /**
     * 批量删除supplier
     * 
     * @param supplierIds 需要删除的supplier主键
     * @return 结果
     */
    @Override
    public int deleteSupplierBySupplierIds(Long[] supplierIds)
    {
        return supplierMapper.deleteSupplierBySupplierIds(supplierIds);
    }

    /**
     * 删除supplier信息
     * 
     * @param supplierId supplier主键
     * @return 结果
     */
    @Override
    public int deleteSupplierBySupplierId(Long supplierId)
    {
        return supplierMapper.deleteSupplierBySupplierId(supplierId);
    }
}
