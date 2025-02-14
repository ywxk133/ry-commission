package com.ruoyi.commission.service.impl;

import java.math.BigDecimal;
import java.util.List;

import com.ruoyi.commission.domain.CommissionAccountBalance;
import com.ruoyi.commission.service.ICommissionAccountBalanceService;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.commission.mapper.CommissionUserMapper;
import com.ruoyi.commission.domain.CommissionUser;
import com.ruoyi.commission.service.ICommissionUserService;
import org.springframework.transaction.annotation.Transactional;

/**
 * 用户管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Service
public class CommissionUserServiceImpl implements ICommissionUserService 
{ @Autowired
private ICommissionAccountBalanceService commissionAccountBalanceService;
    @Autowired
    private CommissionUserMapper commissionUserMapper;

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
        return commissionUserMapper.insertCommissionUser(commissionUser);
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
}
