package com.ruoyi.commission.service;

import java.util.List;
import com.ruoyi.commission.domain.City;

/**
 * 地区Service接口
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
public interface ICityService 
{
    /**
     * 查询地区
     * 
     * @param id 地区主键
     * @return 地区
     */
    public City selectCityById(Long id);

    /**
     * 查询地区列表
     * 
     * @param city 地区
     * @return 地区集合
     */
    public List<City> selectCityList(City city);

    /**
     * 新增地区
     * 
     * @param city 地区
     * @return 结果
     */
    public int insertCity(City city);

    /**
     * 修改地区
     * 
     * @param city 地区
     * @return 结果
     */
    public int updateCity(City city);

    /**
     * 批量删除地区
     * 
     * @param ids 需要删除的地区主键集合
     * @return 结果
     */
    public int deleteCityByIds(Long[] ids);

    /**
     * 删除地区信息
     * 
     * @param id 地区主键
     * @return 结果
     */
    public int deleteCityById(Long id);
}
