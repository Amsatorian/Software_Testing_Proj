package com.ruoyi.Transportcompany.service;

import java.util.List;
import com.ruoyi.Transportcompany.domain.Transportcompany;

/**
 * TransportcompanyService接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface ITransportcompanyService 
{
    /**
     * 查询Transportcompany
     * 
     * @param companyId Transportcompany主键
     * @return Transportcompany
     */
    public Transportcompany selectTransportcompanyByCompanyId(Long companyId);

    /**
     * 查询Transportcompany列表
     * 
     * @param transportcompany Transportcompany
     * @return Transportcompany集合
     */
    public List<Transportcompany> selectTransportcompanyList(Transportcompany transportcompany);

    /**
     * 新增Transportcompany
     * 
     * @param transportcompany Transportcompany
     * @return 结果
     */
    public int insertTransportcompany(Transportcompany transportcompany);

    /**
     * 修改Transportcompany
     * 
     * @param transportcompany Transportcompany
     * @return 结果
     */
    public int updateTransportcompany(Transportcompany transportcompany);

    /**
     * 批量删除Transportcompany
     * 
     * @param companyIds 需要删除的Transportcompany主键集合
     * @return 结果
     */
    public int deleteTransportcompanyByCompanyIds(Long[] companyIds);

    /**
     * 删除Transportcompany信息
     * 
     * @param companyId Transportcompany主键
     * @return 结果
     */
    public int deleteTransportcompanyByCompanyId(Long companyId);
}
