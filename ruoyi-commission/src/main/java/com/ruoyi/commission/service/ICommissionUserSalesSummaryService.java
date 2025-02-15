package com.ruoyi.commission.service;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.domain.CommissionUserSalesSummary;

/**
 * 用户销售额管理Service接口
 * 
 * @author xing
 * @date 2025-02-14
 */
public interface ICommissionUserSalesSummaryService 
{
    /**
     * 查询用户销售额管理
     * 
     * @param userId 用户销售额管理主键
     * @return 用户销售额管理
     */
    public CommissionUserSalesSummary selectCommissionUserSalesSummaryByUserId(Long userId);

    /**
     * 查询用户销售额管理列表
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 用户销售额管理集合
     */
    public List<CommissionUserSalesSummary> selectCommissionUserSalesSummaryList(CommissionUserSalesSummary commissionUserSalesSummary);

    /**
     * 新增用户销售额管理
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 结果
     */
    public int insertCommissionUserSalesSummary(CommissionUserSalesSummary commissionUserSalesSummary);

    /**
     * 修改用户销售额管理
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 结果
     */
    public int updateCommissionUserSalesSummary(CommissionUserSalesSummary commissionUserSalesSummary);

    /**
     * 批量删除用户销售额管理
     * 
     * @param userIds 需要删除的用户销售额管理主键集合
     * @return 结果
     */
    public int deleteCommissionUserSalesSummaryByUserIds(Long[] userIds);

    /**
     * 删除用户销售额管理信息
     * 
     * @param userId 用户销售额管理主键
     * @return 结果
     */
    public int deleteCommissionUserSalesSummaryByUserId(Long userId);

    void recordOrderSales(List<CommissionUser> referrers, Long userId, BigDecimal actualAmountPaid);
}
