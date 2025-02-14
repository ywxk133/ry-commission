package com.ruoyi.commission.service;

import java.math.BigDecimal;
import java.util.List;
import com.ruoyi.commission.domain.CommissionAccountTransaction;
import com.ruoyi.commission.enums.TransactionType;

/**
 * 余额流水Service接口
 * 
 * @author xing
 * @date 2025-02-13
 */
public interface ICommissionAccountTransactionService 
{
    /**
     * 查询余额流水
     * 
     * @param transactionId 余额流水主键
     * @return 余额流水
     */
    public CommissionAccountTransaction selectCommissionAccountTransactionByTransactionId(Long transactionId);

    /**
     * 查询余额流水列表
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 余额流水集合
     */
    public List<CommissionAccountTransaction> selectCommissionAccountTransactionList(CommissionAccountTransaction commissionAccountTransaction);

    /**
     * 新增余额流水
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 结果
     */
    public int insertCommissionAccountTransaction(CommissionAccountTransaction commissionAccountTransaction);

    /**
     * 修改余额流水
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 结果
     */
    public int updateCommissionAccountTransaction(CommissionAccountTransaction commissionAccountTransaction);

    /**
     * 批量删除余额流水
     * 
     * @param transactionIds 需要删除的余额流水主键集合
     * @return 结果
     */
    public int deleteCommissionAccountTransactionByTransactionIds(Long[] transactionIds);

    /**
     * 删除余额流水信息
     * 
     * @param transactionId 余额流水主键
     * @return 结果
     */
    public int deleteCommissionAccountTransactionByTransactionId(Long transactionId);

    public int saveBalanceTransaction(Long userId, BigDecimal amount, BigDecimal newBalance, TransactionType transactionType, String description, Integer isIncrease, Long adminId) ;
}
