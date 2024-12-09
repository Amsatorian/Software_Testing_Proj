package com.ruoyi.customer.service;

import java.util.List;
import com.ruoyi.customer.domain.Customer;

/**
 * customerService接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface ICustomerService 
{
    /**
     * 查询customer
     *
     * @param customerId customer主键
     * @return customer
     */
    public Customer selectCustomerByCustomerId(Long customerId);

    /**
     * 查询customer列表
     * 
     * @param customer customer
     * @return customer集合
     */
    public List<Customer> selectCustomerList(Customer customer);

    /**
     * 新增customer
     * 
     * @param customer customer
     * @return 结果
     */
    public int insertCustomer(Customer customer);

    /**
     * 修改customer
     * 
     * @param customer customer
     * @return 结果
     */
    public int updateCustomer(Customer customer);

    /**
     * 批量删除customer
     * 
     * @param customerIds 需要删除的customer主键集合
     * @return 结果
     */
    public int deleteCustomerByCustomerIds(Long[] customerIds);

    /**
     * 删除customer信息
     * 
     * @param customerId customer主键
     * @return 结果
     */
    public int deleteCustomerByCustomerId(Long customerId);
}
