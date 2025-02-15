package com.ruoyi.commission.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.commission.domain.City;
import com.ruoyi.commission.service.ICityService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 地区Controller
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
@RestController
@RequestMapping("/commission/city")
public class CityController extends BaseController
{
    @Autowired
    private ICityService cityService;

    /**
     * 查询地区列表
     */
    @PreAuthorize("@ss.hasPermi('commission:city:list')")
    @GetMapping("/list")
    public AjaxResult list(City city)
    {
        List<City> list = cityService.selectCityList(city);
        return success(list);
    }

    /**
     * 导出地区列表
     */
    @PreAuthorize("@ss.hasPermi('commission:city:export')")
    @Log(title = "地区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, City city)
    {
        List<City> list = cityService.selectCityList(city);
        ExcelUtil<City> util = new ExcelUtil<City>(City.class);
        util.exportExcel(response, list, "地区数据");
    }

    /**
     * 获取地区详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:city:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(cityService.selectCityById(id));
    }

    /**
     * 新增地区
     */
    @PreAuthorize("@ss.hasPermi('commission:city:add')")
    @Log(title = "地区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody City city)
    {
        return toAjax(cityService.insertCity(city));
    }

    /**
     * 修改地区
     */
    @PreAuthorize("@ss.hasPermi('commission:city:edit')")
    @Log(title = "地区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody City city)
    {
        return toAjax(cityService.updateCity(city));
    }

    /**
     * 删除地区
     */
    @PreAuthorize("@ss.hasPermi('commission:city:remove')")
    @Log(title = "地区", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(cityService.deleteCityByIds(ids));
    }
}
