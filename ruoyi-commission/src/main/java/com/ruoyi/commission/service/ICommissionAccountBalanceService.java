package com.ruoyi.commission.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.commission.domain.CommissionAccountBalance;
import com.ruoyi.commission.domain.dto.OrderRequestDTO;

/**
 * 用户余额Service接口
 * 
 * @author xing
 * @date 2025-02-13
 */
public interface ICommissionAccountBalanceService 
{
    /**
     * 查询用户余额
     * 
     * @param userId 用户余额主键
     * @return 用户余额
     */
    public CommissionAccountBalance selectCommissionAccountBalanceByUserId(Long userId);

    /**
     * 查询用户余额列表
     * 
     * @param commissionAccountBalance 用户余额
     * @return 用户余额集合
     */
    public List<CommissionAccountBalance> selectCommissionAccountBalanceList(CommissionAccountBalance commissionAccountBalance);

    /**
     * 新增用户余额
     * 
     * @param commissionAccountBalance 用户余额
     * @return 结果
     */
    public int insertCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance);

    /**
     * 修改用户余额
     * 
     * @param commissionAccountBalance 用户余额
     * @return 结果
     */
    public int updateCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance);

    /**
     * 批量删除用户余额
     * 
     * @param userIds 需要删除的用户余额主键集合
     * @return 结果
     */
    public int deleteCommissionAccountBalanceByUserIds(Long[] userIds);

    /**
     * 删除用户余额信息
     * 
     * @param userId 用户余额主键
     * @return 结果
     */
    public int deleteCommissionAccountBalanceByUserId(Long userId);
    /**
     * 查询用户余额
     */
    BigDecimal getBalance(Long userId);
    /**
     * 增加用户余额
     * @param commissionAccountBalance
     * @return
     */
    int addCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance);

    /**
     *  减少用户余额
     */
    int decreaseCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance);


}
