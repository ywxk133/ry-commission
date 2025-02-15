package com.ruoyi.commission.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CityMapper;
import com.ruoyi.commission.domain.City;
import com.ruoyi.commission.service.ICityService;

/**
 * 地区Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
@Service
public class CityServiceImpl implements ICityService 
{
    @Autowired
    private CityMapper cityMapper;

    /**
     * 查询地区
     * 
     * @param id 地区主键
     * @return 地区
     */
    @Override
    public City selectCityById(Long id)
    {
        return cityMapper.selectCityById(id);
    }

    /**
     * 查询地区列表
     * 
     * @param city 地区
     * @return 地区
     */
    @Override
    public List<City> selectCityList(City city)
    {
        return cityMapper.selectCityList(city);
    }

    /**
     * 新增地区
     * 
     * @param city 地区
     * @return 结果
     */
    @Override
    public int insertCity(City city)
    {
        return cityMapper.insertCity(city);
    }

    /**
     * 修改地区
     * 
     * @param city 地区
     * @return 结果
     */
    @Override
    public int updateCity(City city)
    {
        return cityMapper.updateCity(city);
    }

    /**
     * 批量删除地区
     * 
     * @param ids 需要删除的地区主键
     * @return 结果
     */
    @Override
    public int deleteCityByIds(Long[] ids)
    {
        return cityMapper.deleteCityByIds(ids);
    }

    /**
     * 删除地区信息
     * 
     * @param id 地区主键
     * @return 结果
     */
    @Override
    public int deleteCityById(Long id)
    {
        return cityMapper.deleteCityById(id);
    }
}
