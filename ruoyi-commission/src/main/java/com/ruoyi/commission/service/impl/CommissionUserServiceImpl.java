package com.ruoyi.commission.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ruoyi.commission.domain.*;
import com.ruoyi.commission.domain.dto.OrderRequestDTO;
import com.ruoyi.commission.enums.AgentLevel;
import com.ruoyi.commission.enums.TransactionType;
import com.ruoyi.commission.service.*;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionUserMapper;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Service
public class CommissionUserServiceImpl implements ICommissionUserService 
{
    private static final Logger logger = LoggerFactory.getLogger(CommissionUserServiceImpl.class);
    @Autowired
private ICommissionAccountBalanceService commissionAccountBalanceService;
    @Autowired
    private CommissionUserMapper commissionUserMapper;

    @Autowired
    private ICommissionUserSalesSummaryService commissionUserSalesSummaryService;

    @Autowired
    private IUserAgentService userAgentService;

    @Autowired
    private ICommissionAccountTransactionService commissionAccountTransactionService;

    @Autowired
    private ICommissionMembershipConfigService commissionMembershipConfigService;

    @Autowired
    private ICommissionLevelUpgradeConfigService commissionLevelUpgradeConfigService;
    @Autowired
    private ICommissionAgentConfigService commissionAgentConfigService;

    /**
     * 查询用户管理
     * 
     * @param userId 用户管理主键
     * @return 用户管理
     */
    @Override
    public CommissionUser selectCommissionUserByUserId(Long userId)
    {
        return commissionUserMapper.selectCommissionUserByUserId(userId);
    }

    /**
     * 查询用户管理列表
     * 
     * @param commissionUser 用户管理
     * @return 用户管理
     */
    @Override
    public List<CommissionUser> selectCommissionUserList(CommissionUser commissionUser)
    {
        return commissionUserMapper.selectCommissionUserList(commissionUser);
    }

    /**
     * 新增用户管理
     *
     * @param commissionUser 用户管理
     * @return 结果
     */
    @Transactional
    @Override
    public int insertCommissionUser(CommissionUser commissionUser)
    {
        // 新用户无资格设置用户级别
        commissionUser.setRankLevel(null);
        // 1. 加密密码
        commissionUser.setPassword(SecurityUtils.encryptPassword(commissionUser.getPassword()));
        // 2.校验推荐人是否存在
        Boolean existenceOrNot = whetherAReferenceExists(commissionUser.getParentId());
        if(existenceOrNot){
            throw  new ServiceException("推荐人不存在");
        }
        commissionUser.setCreateTime(DateUtils.getNowDate());
        int result = commissionUserMapper.insertCommissionUser(commissionUser);
        if(result<=0){
           return result;
        }
        Long userId = commissionUser.getUserId();

        // 3. 创建余额
        Integer balanceResult = createAccountBalance(userId);
        if (balanceResult <= 0) {
            throw new RuntimeException("新增用户失败");
        }
        // 4. 创建用户营销额
        Integer userCommissionResult = createCommission(userId);
        if (userCommissionResult <= 0) {
            throw new RuntimeException("新增用户失败");
        }
        // 5 添加代理配置
        Integer commissionResult = createCommissionConfig(userId);
        if (commissionResult <= 0) {
            throw new RuntimeException("新增用户失败");
        }
        return result;
    }

    private Boolean whetherAReferenceExists(Long parentId) {
        logger.info("查询推荐人");
        //可优化为 单独查询的sql
         return  selectCommissionUserByUserId(parentId)==null ;
    }

    private Integer createCommissionConfig(Long userId) {
        UserAgent userAgent = new UserAgent();
        userAgent.setAgentLevel(null);
        userAgent.setCityId(null);
        userAgent.setProvinceId(null);
        userAgent.setIsEnabled(0);
        userAgent.setUserId(userId);

        return userAgentService.insertUserAgent(userAgent);
    }

    private Integer createCommission(Long userId) {
        CommissionUserSalesSummary commissionUserSalesSummary = new CommissionUserSalesSummary();
        commissionUserSalesSummary.setUserId(userId);
        commissionUserSalesSummary.setDirectSales(BigDecimal.ZERO);
        commissionUserSalesSummary.setPersonalSales(BigDecimal.ZERO);
        commissionUserSalesSummary.setTeamSales(BigDecimal.ZERO);
        return commissionUserSalesSummaryService.insertCommissionUserSalesSummary(commissionUserSalesSummary);
    }

    // 创建用户余额方法
    private Integer createAccountBalance(Long userId) {
        CommissionAccountBalance commissionAccountBalance = new CommissionAccountBalance();
        commissionAccountBalance.setUserId(userId);
        // TODO xing 后期余额默认值优化为后台配置
        commissionAccountBalance.setBalance(BigDecimal.ZERO);
        return commissionAccountBalanceService.insertCommissionAccountBalance(commissionAccountBalance);
    }
    /**
     * 修改用户管理
     * 
     * @param commissionUser 用户管理
     * @return 结果
     */
    @Override
    public int updateCommissionUser(CommissionUser commissionUser)
    {
        commissionUser.setUpdateTime(DateUtils.getNowDate());
        // 2.校验推荐人是否存在
        Boolean existenceOrNot = whetherAReferenceExists(commissionUser.getParentId());
        if(existenceOrNot){
            throw  new ServiceException("推荐人不存在");
        }
        return commissionUserMapper.updateCommissionUser(commissionUser);
    }

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的用户管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionUserByUserIds(Long[] userIds)
    {
        return commissionUserMapper.deleteCommissionUserByUserIds(userIds);
    }

    /**
     * 删除用户管理信息
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    @Override
    public int deleteCommissionUserByUserId(Long userId)
    {
        return commissionUserMapper.deleteCommissionUserByUserId(userId);
    }

    @Override
    public List<CommissionUser> queryAllRefereesOfTheUser(Long userId) {
        return commissionUserMapper.queryAllRefereesOfTheUser(userId);
    }


    /**
     * 模拟下单
     * @param orderRequestDTO
     * @return
     */
    @Override
    @Transactional
    public int userSimulationOrder(OrderRequestDTO orderRequestDTO) {
        BigDecimal actualPrice = orderRequestDTO.getOrderAmount();
        Long userId = orderRequestDTO.getUserId();
        logger.debug("模拟下单开始: 用户ID = {}, 订单金额 = {}", userId, actualPrice);

        // 检查是否是复购
        Integer checked = commissionAccountTransactionService.checkWhetherTheCarIsRePurchased(orderRequestDTO);
        CommissionMembershipConfig commissionMembershipConfig = commissionMembershipConfigService.checkIfTheUserHasAMatchingOffer(userId);
        if (checked > 0 && commissionMembershipConfig != null) {
            logger.debug("commissionMembershipConfig :{}",commissionMembershipConfig);
            BigDecimal repurchasePercentage = commissionMembershipConfig.getRepurchasePercentage();
            if (repurchasePercentage != null) {
                actualPrice = actualPrice.multiply(repurchasePercentage);
                logger.debug("复购奖励已应用: 新订单金额 = {}", actualPrice);
            }
        }

        // 检查余额是否足够
        BigDecimal balance = commissionAccountBalanceService.getBalance(userId);
        logger.debug("检查余额: 当前余额 = {}, 需要支付 = {}", balance, actualPrice);
        if (balance.compareTo(actualPrice) < 0) {
            logger.error("余额不足: 当前余额 = {}, 需要支付 = {}", balance, actualPrice);
            throw new ServiceException("余额不足");
        }

        // 扣款
        logger.debug("开始扣款...");
        processBalanceUpdate(userId, actualPrice, TransactionType.ORDER, orderRequestDTO);

        // 查询会员奖励
        List<CommissionUser> referrers = queryAllRefereesOfTheUser(userId);
        referrers.remove(0);
        // 销售额记录
        commissionUserSalesSummaryService.recordOrderSales(referrers, userId, actualPrice);

        if (referrers.size() > 0) {
            CommissionUser commissionUser = referrers.get(0);
            // 直推奖励
            logger.debug("处理直推奖励: 推荐人ID = {}", commissionUser.getUserId());
            processDirectPushReward(commissionUser, actualPrice, orderRequestDTO);

            // 团队极差奖励
            logger.debug("处理团队极差奖励...");
            processTeamDiffReward(referrers, actualPrice, orderRequestDTO);
        }

        // 代理提成计算
        logger.debug("开始计算代理提成...");
        processAgentCommissions(orderRequestDTO, actualPrice);

        logger.debug("模拟下单成功: 用户ID = {}", userId);
        return 1;
    }

    private void processBalanceUpdate(Long userId, BigDecimal amount, TransactionType type, OrderRequestDTO orderRequestDTO) {
        try {
            // 判断是付款（订单）还是奖励（其他类型） 判断是否是付款订单类型
            boolean isOrderPayment = type == TransactionType.ORDER;

            // 如果是付款订单类型，金额为负，表示扣款；否则，金额为正，表示奖励加款
            BigDecimal finalAmount = amount;

            logger.debug("处理余额更新: 用户ID = {}, 操作类型 = {}, 操作金额 = {}", userId, type, finalAmount);

            CommissionAccountBalance commissionAccountBalance = new CommissionAccountBalance();
            // 设置金额
            commissionAccountBalance.setBalance(finalAmount);
            commissionAccountBalance.setUserId(userId);
            commissionAccountBalance.setUpdateTime(DateUtils.getNowDate());

            // 付款时，调用减少余额方法；奖励时，调用增加余额方法
            int isSuccessful;
            if (isOrderPayment) {
                // 扣款操作
                isSuccessful = commissionAccountBalanceService.decreaseCommissionAccountBalance(commissionAccountBalance);
            } else {
                // 奖励增加金额
                isSuccessful = commissionAccountBalanceService.addCommissionAccountBalance(commissionAccountBalance);
            }

            // 检查余额更新是否成功
            if (isSuccessful <= 0) {
                throw new ServiceException(isOrderPayment ? "扣款失败" : "奖励发放失败");
            }

            // 获取更新后的余额
            BigDecimal newBalance = commissionAccountBalanceService.getBalance(userId);
            logger.debug("余额更新成功: 用户ID = {}, 新余额 = {}", userId, newBalance);

            // 记录交易流水
            commissionAccountTransactionService.saveBalanceTransaction(userId,
                    isOrderPayment ? amount.negate() : amount, newBalance, type,
                    isOrderPayment ? orderRequestDTO.getOrderAmount():null,
                    orderRequestDTO.getAddress(),
                    orderRequestDTO.getOrderDistrictId(),
                    isOrderPayment  ? "付款订单" : "奖励发放");
        } catch (Exception e) {
            logger.error("余额处理失败: 用户ID = {}, 错误信息 = {}", userId, e.getMessage());
            throw new ServiceException("余额处理失败: " + e.getMessage());
        }
    }

    private void processDirectPushReward(CommissionUser commissionUser, BigDecimal actualPrice, OrderRequestDTO orderRequestDTO) {
        try {
            logger.debug("处理直推奖励: 推荐人ID = {}", commissionUser.getUserId());
            CommissionMembershipConfig memberRewardAllocation = commissionMembershipConfigService.checkIfTheUserHasAMatchingOffer(commissionUser.getUserId());
            if (memberRewardAllocation != null) {
                BigDecimal directPushReward = memberRewardAllocation.getDirectPushReward();
                if (directPushReward != null) {
                    BigDecimal incentivePrice = actualPrice.multiply(directPushReward);
                    logger.debug("直推奖励金额 = {}", incentivePrice);
                    processBalanceUpdate(commissionUser.getUserId(), incentivePrice, TransactionType.DIRECT_PUSH_REWARD, orderRequestDTO);
                }
            }
        } catch (Exception e) {
            logger.error("直推奖励处理失败: 错误信息 = {}", e.getMessage());
            throw new ServiceException("直推奖励处理失败: " + e.getMessage());
        }
    }

    private void processTeamDiffReward(List<CommissionUser> referrers, BigDecimal actualPrice, OrderRequestDTO orderRequestDTO) {
        try {
            logger.debug("处理团队极差奖励...");
            Map<String, CommissionLevelUpgradeConfig> commissionLevelUpgradeConfigMap = commissionLevelUpgradeConfigService.levelConfiguration();
            for (CommissionUser referrer : referrers) {
                if (referrer.getRankLevel() != null) {
                    CommissionLevelUpgradeConfig commissionLevelUpgradeConfig = commissionLevelUpgradeConfigMap.get(referrer.getRankLevel());
                    if (commissionLevelUpgradeConfig != null) {
                        BigDecimal teamDiffReward = commissionLevelUpgradeConfig.getTeamDiffReward();
                        if (teamDiffReward != null) {
                            BigDecimal rangeReward = actualPrice.multiply(teamDiffReward);
                            logger.debug("团队极差奖励: 推荐人ID = {}, 奖励金额 = {}", referrer.getUserId(), rangeReward);
                            processBalanceUpdate(referrer.getUserId(), rangeReward, TransactionType.TEAM_DIFF_REWARD, orderRequestDTO);
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("团队极差奖励处理失败: 错误信息 = {}", e.getMessage());
            throw new ServiceException("团队极差奖励处理失败: " + e.getMessage());
        }
    }

    private void processAgentCommissions(OrderRequestDTO orderRequestDTO, BigDecimal actualPrice) {
        try {
            logger.debug("开始处理代理提成...");
            List<CommissionUser> cityLevelUsers = userAgentService.getCityLevelUsers(orderRequestDTO.getOrderDistrictId());
            List<CommissionUser> provinceLevelUser = userAgentService.getProvinceLevelUsersByCityId(orderRequestDTO.getOrderDistrictId());
            List<CommissionAgentConfig> commissionAgentConfigs = commissionAgentConfigService.selectCommissionAgentConfigList(null);
            logger.debug("省代理 {}:",provinceLevelUser);
            logger.debug("市代理 {}:",cityLevelUsers);
            logger.debug("代理配置 {}:",commissionAgentConfigs);
            if (commissionAgentConfigs.size() > 0) {
                for (CommissionAgentConfig commissionAgentConfig : commissionAgentConfigs) {
                    BigDecimal totalCommission = commissionAgentConfig.getCommissionRate().multiply(actualPrice).setScale(2, RoundingMode.HALF_UP);
                    // 省级代理
                    if (commissionAgentConfig.getAgentLevel().equals(AgentLevel.PROVINCE.getDescription())) {
                        if (provinceLevelUser != null && !provinceLevelUser.isEmpty()) {
                            BigDecimal provinceCommissionPerUser = totalCommission.divide(new BigDecimal(provinceLevelUser.size()), 2, RoundingMode.HALF_UP);
                            for (CommissionUser user : provinceLevelUser) {
                                logger.debug("省级代理提成: 用户ID = {}, 提成金额 = {}", user.getUserId(), provinceCommissionPerUser);
                                processBalanceUpdate(user.getUserId(), provinceCommissionPerUser, TransactionType.AGENCY_SHARING, orderRequestDTO);
                            }
                        } else {
                            logger.error("没有省级代理");
//                            throw new ServiceException("没有省级代理");
                        }
                    }
                        // 市级代理
                    if (commissionAgentConfig.getAgentLevel().equals(AgentLevel.CITY.getDescription())) {
                        if (cityLevelUsers != null && !cityLevelUsers.isEmpty()) {
                            BigDecimal cityCommissionPerUser = totalCommission.divide(new BigDecimal(cityLevelUsers.size()), 2, RoundingMode.HALF_UP);
                            for (CommissionUser user : cityLevelUsers) {
                                logger.debug("市级代理提成: 用户ID = {}, 提成金额 = {}", user.getUserId(), cityCommissionPerUser);
                                processBalanceUpdate(user.getUserId(), cityCommissionPerUser, TransactionType.AGENCY_SHARING, orderRequestDTO);
                            }
                        } else {
                            logger.error("没有市级代理");
//                            throw new ServiceException("没有市级代理");
                        }
                    }
                }
            }
        } catch (Exception e) {
            logger.error("代理提成处理失败: 错误信息 = {}", e.getMessage());
            throw new ServiceException("代理提成处理失败: " + e.getMessage());
        }
    }

}
