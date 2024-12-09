package com.ruoyi.Transportcompany.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.Transportcompany.mapper.TransportcompanyMapper;
import com.ruoyi.Transportcompany.domain.Transportcompany;
import com.ruoyi.Transportcompany.service.ITransportcompanyService;

/**
 * TransportcompanyService业务层处理
 * 
 * @author zx
 * @date 2024-11-27
 */
@Service
public class TransportcompanyServiceImpl implements ITransportcompanyService 
{
    @Autowired
    private TransportcompanyMapper transportcompanyMapper;

    /**
     * 查询Transportcompany
     * 
     * @param companyId Transportcompany主键
     * @return Transportcompany
     */
    @Override
    public Transportcompany selectTransportcompanyByCompanyId(Long companyId)
    {
        return transportcompanyMapper.selectTransportcompanyByCompanyId(companyId);
    }

    /**
     * 查询Transportcompany列表
     * 
     * @param transportcompany Transportcompany
     * @return Transportcompany
     */
    @Override
    public List<Transportcompany> selectTransportcompanyList(Transportcompany transportcompany)
    {
        return transportcompanyMapper.selectTransportcompanyList(transportcompany);
    }

    /**
     * 新增Transportcompany
     * 
     * @param transportcompany Transportcompany
     * @return 结果
     */
    @Override
    public int insertTransportcompany(Transportcompany transportcompany)
    {
        transportcompany.setCreateTime(DateUtils.getNowDate());
        return transportcompanyMapper.insertTransportcompany(transportcompany);
    }

    /**
     * 修改Transportcompany
     * 
     * @param transportcompany Transportcompany
     * @return 结果
     */
    @Override
    public int updateTransportcompany(Transportcompany transportcompany)
    {
        transportcompany.setUpdateTime(DateUtils.getNowDate());
        return transportcompanyMapper.updateTransportcompany(transportcompany);
    }

    /**
     * 批量删除Transportcompany
     * 
     * @param companyIds 需要删除的Transportcompany主键
     * @return 结果
     */
    @Override
    public int deleteTransportcompanyByCompanyIds(Long[] companyIds)
    {
        return transportcompanyMapper.deleteTransportcompanyByCompanyIds(companyIds);
    }

    /**
     * 删除Transportcompany信息
     * 
     * @param companyId Transportcompany主键
     * @return 结果
     */
    @Override
    public int deleteTransportcompanyByCompanyId(Long companyId)
    {
        return transportcompanyMapper.deleteTransportcompanyByCompanyId(companyId);
    }
}
