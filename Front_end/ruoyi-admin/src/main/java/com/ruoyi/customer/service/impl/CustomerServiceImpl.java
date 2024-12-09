package com.ruoyi.customer.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.customer.mapper.CustomerMapper;
import com.ruoyi.customer.domain.Customer;
import com.ruoyi.customer.service.ICustomerService;

/**
 * customerService业务层处理
 * 
 * @author zx
 * @date 2024-11-27
 */
@Service
public class CustomerServiceImpl implements ICustomerService 
{
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 查询customer
     *
     * @param customerId customer主键
     * @return customer
     */
    @Override
    public Customer selectCustomerByCustomerId(Long customerId)
    {
        return customerMapper.selectCustomerByCustomerId(customerId);
    }

    /**
     * 查询customer列表
     * 
     * @param customer customer
     * @return customer
     */
    @Override
    public List<Customer> selectCustomerList(Customer customer)
    {
        return customerMapper.selectCustomerList(customer);
    }

    /**
     * 新增customer
     * 
     * @param customer customer
     * @return 结果
     */
    @Override
    public int insertCustomer(Customer customer)
    {
        customer.setCreateTime(DateUtils.getNowDate());
        return customerMapper.insertCustomer(customer);
    }

    /**
     * 修改customer
     * 
     * @param customer customer
     * @return 结果
     */
    @Override
    public int updateCustomer(Customer customer)
    {
        customer.setUpdateTime(DateUtils.getNowDate());
        return customerMapper.updateCustomer(customer);
    }

    /**
     * 批量删除customer
     * 
     * @param customerIds 需要删除的customer主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCustomerIds(Long[] customerIds)
    {
        return customerMapper.deleteCustomerByCustomerIds(customerIds);
    }

    /**
     * 删除customer信息
     * 
     * @param customerId customer主键
     * @return 结果
     */
    @Override
    public int deleteCustomerByCustomerId(Long customerId)
    {
        return customerMapper.deleteCustomerByCustomerId(customerId);
    }
}
