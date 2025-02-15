package com.ruoyi.commission.service;

import java.util.List;
import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.domain.dto.OrderRequestDTO;

/**
 * 用户管理Service接口
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
public interface ICommissionUserService 
{
    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    public CommissionUser selectCommissionUserByUserId(Long userId);

    /**
     * 查询用户管理列表
     * 
     * @param commissionUser 用户管理
     * @return 用户管理集合
     */
    public List<CommissionUser> selectCommissionUserList(CommissionUser commissionUser);

    /**
     * 新增用户管理
     * 
     * @param commissionUser 用户管理
     * @return 结果
     */
    public int insertCommissionUser(CommissionUser commissionUser);

    /**
     * 修改用户管理
     * 
     * @param commissionUser 用户管理
     * @return 结果
     */
    public int updateCommissionUser(CommissionUser commissionUser);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键集合
     * @return 结果
     */
    public int deleteCommissionUserByUserIds(Long[] userIds);

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteCommissionUserByUserId(Long userId);

    List<CommissionUser> queryAllRefereesOfTheUser(Long userId);

    public int userSimulationOrder(OrderRequestDTO orderRequestDTO);

}
