package com.ruoyi.commission.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.ruoyi.commission.domain.dto.OrderRequestDTO;
import com.ruoyi.commission.enums.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionAccountTransactionMapper;
import com.ruoyi.commission.domain.CommissionAccountTransaction;
import com.ruoyi.commission.service.ICommissionAccountTransactionService;

/**
 * 余额流水Service业务层处理
 * 
 * @author xing
 */
@Service
public class CommissionAccountTransactionServiceImpl implements ICommissionAccountTransactionService 
{
    @Autowired
    private CommissionAccountTransactionMapper commissionAccountTransactionMapper;

    /**
     * 查询余额流水
     * 
     * @param transactionId 余额流水主键
     * @return 余额流水
     */
    @Override
    public CommissionAccountTransaction selectCommissionAccountTransactionByTransactionId(Long transactionId)
    {
        return commissionAccountTransactionMapper.selectCommissionAccountTransactionByTransactionId(transactionId);
    }

    /**
     * 查询余额流水列表
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 余额流水
     */
    @Override
    public List<CommissionAccountTransaction> selectCommissionAccountTransactionList(CommissionAccountTransaction commissionAccountTransaction)
    {
        return commissionAccountTransactionMapper.selectCommissionAccountTransactionList(commissionAccountTransaction);
    }

    /**
     * 新增余额流水
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 结果
     */
    @Override
    public int insertCommissionAccountTransaction(CommissionAccountTransaction commissionAccountTransaction)
    {
        return commissionAccountTransactionMapper.insertCommissionAccountTransaction(commissionAccountTransaction);
    }
    /**
     * 记录余额流水
     */
    @Override
    public int saveBalanceTransaction(Long userId, BigDecimal amount, BigDecimal newBalance, TransactionType transactionType, String description, Integer isIncrease, Long adminId) {
        CommissionAccountTransaction transaction = new CommissionAccountTransaction();
        transaction.setUserId(userId);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(newBalance);
        transaction.setTransactionType(transactionType.getValue());
        transaction.setTransactionTime(new Date());
        transaction.setDescription(description);
        transaction.setAdminId(adminId);
        transaction.setIsIncrease(isIncrease);
        return commissionAccountTransactionMapper.insertCommissionAccountTransaction(transaction);
    }
    /**
     * 订单相关余额流水记录
     */
    @Override
    public int saveBalanceTransaction(Long userId, BigDecimal amount, BigDecimal newBalance, TransactionType transactionTypee, BigDecimal orderAmount,String orderAddress,Integer orderDistrictId,String description) {

        CommissionAccountTransaction transaction = new CommissionAccountTransaction();
        transaction.setUserId(userId);
        transaction.setOrderAddress(orderAddress);
        transaction.setOrderDistrictId(orderDistrictId);
        transaction.setProductPrice(orderAmount);
        transaction.setAmount(amount);
        transaction.setBalanceAfter(newBalance);
        transaction.setTransactionType(transactionTypee.getValue());
        transaction.setTransactionTime(new Date());
        transaction.setDescription(description);
        if(TransactionType.ORDER==transactionTypee){
            transaction.setIsIncrease(2);
        }else {
            transaction.setIsIncrease(1);
        }
        return commissionAccountTransactionMapper.insertCommissionAccountTransaction(transaction);
    }

    @Override
    public Integer checkWhetherTheCarIsRePurchased(OrderRequestDTO orderRequestDTO) {
//        orderRequestDTO.setOrderAmount(orderRequestDTO.getOrderAmount().negate());
        return commissionAccountTransactionMapper.checkWhetherTheCarIsRePurchased(orderRequestDTO);
    }


    /**
     * 修改余额流水
     * 
     * @param commissionAccountTransaction 余额流水
     * @return 结果
     */
    @Override
    public int updateCommissionAccountTransaction(CommissionAccountTransaction commissionAccountTransaction)
    {
        return commissionAccountTransactionMapper.updateCommissionAccountTransaction(commissionAccountTransaction);
    }

    /**
     * 批量删除余额流水
     * 
     * @param transactionIds 需要删除的余额流水主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAccountTransactionByTransactionIds(Long[] transactionIds)
    {
        return commissionAccountTransactionMapper.deleteCommissionAccountTransactionByTransactionIds(transactionIds);
    }

    /**
     * 删除余额流水信息
     * 
     * @param transactionId 余额流水主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAccountTransactionByTransactionId(Long transactionId)
    {
        return commissionAccountTransactionMapper.deleteCommissionAccountTransactionByTransactionId(transactionId);
    }
}
