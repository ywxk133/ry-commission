package com.ruoyi.commission.mapper;

import java.util.List;

import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.domain.UserAgent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户代理管理Mapper接口
 * 
 * @author xing
 * @date 2025-02-14
 */
@Mapper
public interface UserAgentMapper 
{
    /**
     * 查询用户代理管理
     * 
     * @param userId 用户代理管理主键
     * @return 用户代理管理
     */
    public UserAgent selectUserAgentByUserId(Long userId);

    /**
     * 查询用户代理管理列表
     * 
     * @param userAgent 用户代理管理
     * @return 用户代理管理集合
     */
    public List<UserAgent> selectUserAgentList(UserAgent userAgent);

    /**
     * 新增用户代理管理
     * 
     * @param userAgent 用户代理管理
     * @return 结果
     */
    public int insertUserAgent(UserAgent userAgent);

    /**
     * 修改用户代理管理
     * 
     * @param userAgent 用户代理管理
     * @return 结果
     */
    public int updateUserAgent(UserAgent userAgent);

    /**
     * 删除用户代理管理
     * 
     * @param userId 用户代理管理主键
     * @return 结果
     */
    public int deleteUserAgentByUserId(Long userId);

    /**
     * 批量删除用户代理管理
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserAgentByUserIds(Long[] userIds);

    List<CommissionUser> getCityLevelUsers(@Param("cityId") int cityId);

    List<CommissionUser> getProvinceLevelUsersByCityId(@Param("cityId") int cityId);
}
