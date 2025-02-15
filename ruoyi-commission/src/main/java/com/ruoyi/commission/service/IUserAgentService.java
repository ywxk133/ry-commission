package com.ruoyi.commission.service;

import java.util.List;

import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.domain.UserAgent;

/**
 * 用户代理管理Service接口
 * 
 * @author xing
 * @date 2025-02-14
 */
public interface IUserAgentService 
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
     * 批量删除用户代理管理
     * 
     * @param userIds 需要删除的用户代理管理主键集合
     * @return 结果
     */
    public int deleteUserAgentByUserIds(Long[] userIds);

    /**
     * 删除用户代理管理信息
     * 
     * @param userId 用户代理管理主键
     * @return 结果
     */
    public int deleteUserAgentByUserId(Long userId);

    public List<CommissionUser> getCityLevelUsers(Integer orderDistrictId);

    public List<CommissionUser> getProvinceLevelUsersByCityId(int cityId);
}
