package com.ruoyi.Transportcompany.mapper;

import java.util.List;
import com.ruoyi.Transportcompany.domain.Transportcompany;

/**
 * TransportcompanyMapper接口
 * 
 * @author zx
 * @date 2024-11-27
 */
public interface TransportcompanyMapper 
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
     * 删除Transportcompany
     * 
     * @param companyId Transportcompany主键
     * @return 结果
     */
    public int deleteTransportcompanyByCompanyId(Long companyId);

    /**
     * 批量删除Transportcompany
     * 
     * @param companyIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTransportcompanyByCompanyIds(Long[] companyIds);
}
