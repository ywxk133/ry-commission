package com.ruoyi.commission.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 地区对象 city
 * 
 * @author ruoyi
 * @date 2025-02-14
 */
public class City extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 省市区名称 */
    @Excel(name = "省市区名称")
    private String name;

    /** 简称 */
    @Excel(name = "简称")
    private String shortname;

    /** 级别:0,中国；1，省分；2，市；3，区、县 */
    @Excel(name = "级别:0,中国；1，省分；2，市；3，区、县")
    private Long leveltype;

    /** 城市代码 */
    @Excel(name = "城市代码")
    private String citycode;

    /** 邮编 */
    @Excel(name = "邮编")
    private String zipcode;

    /** 经度 */
    @Excel(name = "经度")
    private String lng;

    /** 纬度 */
    @Excel(name = "纬度")
    private String lat;

    /** 拼音 */
    @Excel(name = "拼音")
    private String pinyin;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String status;
    /** 父菜单ID */
    private Long parentId;

    @Override
    public Long getParentId() {
        return parentId;
    }

    @Override
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setShortname(String shortname) 
    {
        this.shortname = shortname;
    }

    public String getShortname() 
    {
        return shortname;
    }
    public void setLeveltype(Long leveltype) 
    {
        this.leveltype = leveltype;
    }

    public Long getLeveltype() 
    {
        return leveltype;
    }
    public void setCitycode(String citycode) 
    {
        this.citycode = citycode;
    }

    public String getCitycode() 
    {
        return citycode;
    }
    public void setZipcode(String zipcode) 
    {
        this.zipcode = zipcode;
    }

    public String getZipcode() 
    {
        return zipcode;
    }
    public void setLng(String lng) 
    {
        this.lng = lng;
    }

    public String getLng() 
    {
        return lng;
    }
    public void setLat(String lat) 
    {
        this.lat = lat;
    }

    public String getLat() 
    {
        return lat;
    }
    public void setPinyin(String pinyin) 
    {
        this.pinyin = pinyin;
    }

    public String getPinyin() 
    {
        return pinyin;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("parentid", getParentId())
            .append("shortname", getShortname())
            .append("leveltype", getLeveltype())
            .append("citycode", getCitycode())
            .append("zipcode", getZipcode())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("pinyin", getPinyin())
            .append("status", getStatus())
            .toString();
    }
}
