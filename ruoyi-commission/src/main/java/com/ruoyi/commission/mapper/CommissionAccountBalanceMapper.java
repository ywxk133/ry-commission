package com.ruoyi.commission.mapper;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.commission.domain.CommissionAccountBalance;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户余额Mapper接口
 * 
 * @author xing
 * @date 2025-02-13
 */
@Mapper
public interface CommissionAccountBalanceMapper 
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
     * 删除用户余额
     * 
     * @param userId 用户余额主键
     * @return 结果
     */
    public int deleteCommissionAccountBalanceByUserId(Long userId);

    /**
     * 批量删除用户余额
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommissionAccountBalanceByUserIds(Long[] userIds);

    BigDecimal getBalanceByUserId(Long userId);

    int addCommissionAccountBalance(@Param("commissionAccountBalance") CommissionAccountBalance commissionAccountBalance);

    int decreaseCommissionAccountBalance(@Param("commissionAccountBalance") CommissionAccountBalance commissionAccountBalance);
}
