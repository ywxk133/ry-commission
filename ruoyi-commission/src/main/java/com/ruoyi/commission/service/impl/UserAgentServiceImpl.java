package com.ruoyi.commission.service.impl;

import java.util.Collections;
import java.util.List;

import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.UserAgentMapper;
import com.ruoyi.commission.domain.UserAgent;
import com.ruoyi.commission.service.IUserAgentService;

/**
 * 用户代理管理Service业务层处理
 * 
 * @author xing
 * @date 2025-02-14
 */
@Service
public class UserAgentServiceImpl implements IUserAgentService 
{
    @Autowired
    private UserAgentMapper userAgentMapper;

    /**
     * 查询用户代理管理
     * 
     * @param userId 用户代理管理主键
     * @return 用户代理管理
     */
    @Override
    public UserAgent selectUserAgentByUserId(Long userId)
    {
        return userAgentMapper.selectUserAgentByUserId(userId);
    }

    /**
     * 查询用户代理管理列表
     * 
     * @param userAgent 用户代理管理
     * @return 用户代理管理
     */
    @Override
    public List<UserAgent> selectUserAgentList(UserAgent userAgent)
    {
        return userAgentMapper.selectUserAgentList(userAgent);
    }

    /**
     * 新增用户代理管理
     * 
     * @param userAgent 用户代理管理
     * @return 结果
     */
    @Override
    public int insertUserAgent(UserAgent userAgent)
    {
        userAgent.setCreateTime(DateUtils.getNowDate());
        return userAgentMapper.insertUserAgent(userAgent);
    }

    /**
     * 修改用户代理管理
     * 
     * @param userAgent 用户代理管理
     * @return 结果
     */
    @Override
    public int updateUserAgent(UserAgent userAgent)
    {
        userAgent.setUpdateTime(DateUtils.getNowDate());
        return userAgentMapper.updateUserAgent(userAgent);
    }

    /**
     * 批量删除用户代理管理
     * 
     * @param userIds 需要删除的用户代理管理主键
     * @return 结果
     */
    @Override
    public int deleteUserAgentByUserIds(Long[] userIds)
    {
        return userAgentMapper.deleteUserAgentByUserIds(userIds);
    }

    /**
     * 删除用户代理管理信息
     * 
     * @param userId 用户代理管理主键
     * @return 结果
     */
    @Override
    public int deleteUserAgentByUserId(Long userId)
    {
        return userAgentMapper.deleteUserAgentByUserId(userId);
    }

    @Override
    public List<CommissionUser> getCityLevelUsers(Integer orderDistrictId) {
        return userAgentMapper.getCityLevelUsers(orderDistrictId);
    }
    @Override
    // 根据市级ID查询对应省级代理下的用户
    public List<CommissionUser> getProvinceLevelUsersByCityId(int cityId) {
        return userAgentMapper.getProvinceLevelUsersByCityId(cityId);
    }
}
