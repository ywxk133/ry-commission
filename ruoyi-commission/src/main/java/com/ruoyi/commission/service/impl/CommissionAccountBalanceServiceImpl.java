package com.ruoyi.commission.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import com.ruoyi.commission.domain.*;
import com.ruoyi.commission.domain.dto.OrderRequestDTO;
import com.ruoyi.commission.enums.RankLevel;
import com.ruoyi.commission.enums.TransactionType;
import com.ruoyi.commission.service.*;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionAccountBalanceMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户余额Service业务层处理
 * 
 * @author xing
 * @date 2025-02-13
 */
@Service
public class CommissionAccountBalanceServiceImpl implements ICommissionAccountBalanceService
{
    private static final Logger logger  =  LoggerFactory.getLogger(CommissionAccountBalanceServiceImpl.class);
    @Autowired
    private CommissionAccountBalanceMapper commissionAccountBalanceMapper;


    /**
     * 查询用户余额
     * 
     * @param userId 用户余额主键
     * @return 用户余额
     */
    @Override
    public CommissionAccountBalance selectCommissionAccountBalanceByUserId(Long userId)
    {
        return commissionAccountBalanceMapper.selectCommissionAccountBalanceByUserId(userId);
    }

    /**
     * 查询用户余额列表
     * 
     * @param commissionAccountBalance 用户余额
     * @return 用户余额
     */
    @Override
    public List<CommissionAccountBalance> selectCommissionAccountBalanceList(CommissionAccountBalance commissionAccountBalance)
    {
        return commissionAccountBalanceMapper.selectCommissionAccountBalanceList(commissionAccountBalance);
    }

    /**
     * 新增用户余额
     * 
     * @param commissionAccountBalance 用户余额
     * @return 结果
     */
    @Override
    public int insertCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance)
    {
        commissionAccountBalance.setCreateTime(DateUtils.getNowDate());
        return commissionAccountBalanceMapper.insertCommissionAccountBalance(commissionAccountBalance);
    }

    /**
     * 修改用户余额
     * 
     * @param commissionAccountBalance 用户余额
     * @return 结果
     */
    @Override
    public int updateCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance)
    {
        commissionAccountBalance.setUpdateTime(DateUtils.getNowDate());
        return commissionAccountBalanceMapper.updateCommissionAccountBalance(commissionAccountBalance);
    }

    /**
     * 批量删除用户余额
     * 
     * @param userIds 需要删除的用户余额主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAccountBalanceByUserIds(Long[] userIds)
    {
        return commissionAccountBalanceMapper.deleteCommissionAccountBalanceByUserIds(userIds);
    }

    /**
     * 删除用户余额信息
     * 
     * @param userId 用户余额主键
     * @return 结果
     */
    @Override
    public int deleteCommissionAccountBalanceByUserId(Long userId)
    {
        return commissionAccountBalanceMapper.deleteCommissionAccountBalanceByUserId(userId);
    }

    /**
     * 查询用户余额
     */
    @Override
    public BigDecimal getBalance(Long userId) {
        return commissionAccountBalanceMapper.getBalanceByUserId(userId);
    }

    /**
     * 增加用户余额
     * @param commissionAccountBalance
     * @return
     */
    @Override
    public int addCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance) {
        return commissionAccountBalanceMapper.addCommissionAccountBalance(commissionAccountBalance);
    }
    /**
     *  减少用户余额
     */
    @Override
    public int decreaseCommissionAccountBalance(CommissionAccountBalance commissionAccountBalance) {
        return commissionAccountBalanceMapper.decreaseCommissionAccountBalance(commissionAccountBalance);
    }



}
