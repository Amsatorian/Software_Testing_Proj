package com.ruoyi.supplier.service;

import java.util.List;
import com.ruoyi.supplier.domain.Supplier;

/**
 * supplierService接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface ISupplierService 
{
    /**
     * 查询supplier
     * 
     * @param supplierId supplier主键
     * @return supplier
     */
    public Supplier selectSupplierBySupplierId(Long supplierId);

    /**
     * 查询supplier列表
     * 
     * @param supplier supplier
     * @return supplier集合
     */
    public List<Supplier> selectSupplierList(Supplier supplier);

    /**
     * 新增supplier
     * 
     * @param supplier supplier
     * @return 结果
     */
    public int insertSupplier(Supplier supplier);

    /**
     * 修改supplier
     * 
     * @param supplier supplier
     * @return 结果
     */
    public int updateSupplier(Supplier supplier);

    /**
     * 批量删除supplier
     * 
     * @param supplierIds 需要删除的supplier主键集合
     * @return 结果
     */
    public int deleteSupplierBySupplierIds(Long[] supplierIds);

    /**
     * 删除supplier信息
     * 
     * @param supplierId supplier主键
     * @return 结果
     */
    public int deleteSupplierBySupplierId(Long supplierId);
}
