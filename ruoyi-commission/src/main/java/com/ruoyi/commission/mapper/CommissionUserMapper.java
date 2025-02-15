package com.ruoyi.commission.mapper;

import java.util.List;
import com.ruoyi.commission.domain.CommissionUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户管理Mapper接口
 * 
 * @author ruoyi
 * @date 2025-02-13
 */
@Mapper
public interface CommissionUserMapper 
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
     * 删除用户管理
     * 
     * @param userId 用户管理主键
     * @return 结果
     */
    public int deleteCommissionUserByUserId(Long userId);

    /**
     * 批量删除用户管理
     * 
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteCommissionUserByUserIds(Long[] userIds);

    List<CommissionUser> queryAllRefereesOfTheUser(Long userId);
}
