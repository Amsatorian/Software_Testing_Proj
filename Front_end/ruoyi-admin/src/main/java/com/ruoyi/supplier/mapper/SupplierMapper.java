package com.ruoyi.supplier.mapper;

import java.util.List;
import com.ruoyi.supplier.domain.Supplier;

/**
 * supplierMapper接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface SupplierMapper 
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
     * 删除supplier
     * 
     * @param supplierId supplier主键
     * @return 结果
     */
    public int deleteSupplierBySupplierId(Long supplierId);

    /**
     * 批量删除supplier
     * 
     * @param supplierIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteSupplierBySupplierIds(Long[] supplierIds);
}
