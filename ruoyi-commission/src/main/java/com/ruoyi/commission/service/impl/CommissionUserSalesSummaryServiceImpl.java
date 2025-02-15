package com.ruoyi.commission.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.common.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionUserSalesSummaryMapper;
import com.ruoyi.commission.domain.CommissionUserSalesSummary;
import com.ruoyi.commission.service.ICommissionUserSalesSummaryService;

/**
 * 用户销售额管理Service业务层处理
 * 
 * @author xing
 * @date 2025-02-14
 */
@Service
public class CommissionUserSalesSummaryServiceImpl implements ICommissionUserSalesSummaryService
{
    private static final Logger log = LoggerFactory.getLogger(CommissionUserSalesSummaryServiceImpl.class);
    @Autowired
    private CommissionUserSalesSummaryMapper commissionUserSalesSummaryMapper;

    /**
     * 查询用户销售额管理
     *
     * @param userId 用户销售额管理主键
     * @return 用户销售额管理
     */
    @Override
    public CommissionUserSalesSummary selectCommissionUserSalesSummaryByUserId(Long userId)
    {
        return commissionUserSalesSummaryMapper.selectCommissionUserSalesSummaryByUserId(userId);
    }

    /**
     * 查询用户销售额管理列表
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 用户销售额管理
     */
    @Override
    public List<CommissionUserSalesSummary> selectCommissionUserSalesSummaryList(CommissionUserSalesSummary commissionUserSalesSummary)
    {
        return commissionUserSalesSummaryMapper.selectCommissionUserSalesSummaryList(commissionUserSalesSummary);
    }

    /**
     * 新增用户销售额管理
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 结果
     */
    @Override
    public int insertCommissionUserSalesSummary(CommissionUserSalesSummary commissionUserSalesSummary)
    {
        commissionUserSalesSummary.setCreateTime(DateUtils.getNowDate());
        return commissionUserSalesSummaryMapper.insertCommissionUserSalesSummary(commissionUserSalesSummary);
    }

    /**
     * 修改用户销售额管理
     * 
     * @param commissionUserSalesSummary 用户销售额管理
     * @return 结果
     */
    @Override
    public int updateCommissionUserSalesSummary(CommissionUserSalesSummary commissionUserSalesSummary)
    {
        commissionUserSalesSummary.setUpdateTime(DateUtils.getNowDate());
        return commissionUserSalesSummaryMapper.updateCommissionUserSalesSummary(commissionUserSalesSummary);
    }

    /**
     * 批量删除用户销售额管理
     * 
     * @param userIds 需要删除的用户销售额管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionUserSalesSummaryByUserIds(Long[] userIds)
    {
        return commissionUserSalesSummaryMapper.deleteCommissionUserSalesSummaryByUserIds(userIds);
    }

    /**
     * 删除用户销售额管理信息
     * 
     * @param userId 用户销售额管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionUserSalesSummaryByUserId(Long userId)
    {
        return commissionUserSalesSummaryMapper.deleteCommissionUserSalesSummaryByUserId(userId);
    }

    @Override
    public void recordOrderSales(List<CommissionUser> referrers, Long userId, BigDecimal actualAmountPaid) {
        log.debug("开始执行 recordOrderSales 方法: 用户ID={}, 实际支付金额={}", userId, actualAmountPaid);

        // 记录个人消费金额增加
        log.debug("正在更新用户ID为{}的个人消费金额", userId);
        commissionUserSalesSummaryMapper.personalConsumptionIncreased(userId, actualAmountPaid);
        if (referrers.size() > 0) {
            // 记录团队销售额增加
            log.debug("正在增加推荐人的团队销售额:{}",referrers);
            for (CommissionUser referrer : referrers) {
                commissionUserSalesSummaryMapper.updateTeamSales(referrer.getUserId(), actualAmountPaid);
            }
            // 记录直推销售额增加
            Long directReferrer = referrers.get(0).getUserId();
            log.debug("正在增加直推用户ID={}的销售额", directReferrer);
            commissionUserSalesSummaryMapper.increaseDirectSales(directReferrer, actualAmountPaid);
        }

        log.debug("完成了用户ID={}的 recordOrderSales 方法", userId);
    }

}
