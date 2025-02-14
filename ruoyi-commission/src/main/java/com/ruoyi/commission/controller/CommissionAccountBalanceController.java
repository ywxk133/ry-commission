package com.ruoyi.commission.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.commission.domain.CommissionAccountTransaction;
import com.ruoyi.commission.enums.TransactionType;
import com.ruoyi.commission.service.ICommissionAccountTransactionService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.commission.domain.CommissionAccountBalance;
import com.ruoyi.commission.service.ICommissionAccountBalanceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户余额Controller
 * 
 * @author xing
 * @date 2025-02-13
 */
@RestController
@RequestMapping("/commission/commission_account_balance")
public class CommissionAccountBalanceController extends BaseController
{
    @Autowired
    private ICommissionAccountBalanceService commissionAccountBalanceService;

    @Autowired
    ICommissionAccountTransactionService commissionAccountTransactionService;
    /**
     * 查询用户余额列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionAccountBalance commissionAccountBalance)
    {
        startPage();
        List<CommissionAccountBalance> list = commissionAccountBalanceService.selectCommissionAccountBalanceList(commissionAccountBalance);
        return getDataTable(list);
    }

    /**
     * 导出用户余额列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:export')")
    @Log(title = "用户余额", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionAccountBalance commissionAccountBalance)
    {
        List<CommissionAccountBalance> list = commissionAccountBalanceService.selectCommissionAccountBalanceList(commissionAccountBalance);
        ExcelUtil<CommissionAccountBalance> util = new ExcelUtil<CommissionAccountBalance>(CommissionAccountBalance.class);
        util.exportExcel(response, list, "用户余额数据");
    }

    /**
     * 获取用户余额详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:query')")
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(commissionAccountBalanceService.selectCommissionAccountBalanceByUserId(userId));
    }

    /**
     * 新增用户余额
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:add')")
    @Log(title = "用户余额", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionAccountBalance commissionAccountBalance)
    {
        return toAjax(commissionAccountBalanceService.insertCommissionAccountBalance(commissionAccountBalance));
    }

    /**
     * 修改用户余额 该方法未启用
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:edit')")
    @Log(title = "用户余额", businessType = BusinessType.UPDATE)
    @PutMapping("/update")
    public AjaxResult updateBalance(@RequestBody CommissionAccountBalance commissionAccountBalance) {

//        return toAjax(commissionAccountBalanceService.updateCommissionAccountBalance(commissionAccountBalance));
        return toAjax(0);
    }

    /**
     * 增加用户余额
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:edit')")
    @Log(title = "用户余额", businessType = BusinessType.UPDATE)
    @PutMapping("/increase")
    public AjaxResult increaseBalance(@RequestBody CommissionAccountBalance commissionAccountBalance) {
        // 获取操作前余额
//        BigDecimal oldBalance = commissionAccountBalanceService.getBalance(commissionAccountBalance.getUserId());
        BigDecimal transactionAmount =commissionAccountBalance.getBalance();
        if(transactionAmount==null || transactionAmount.compareTo(BigDecimal.ZERO)<0){
            return AjaxResult.error("金额异常");
        }
        // 增加余额
        AjaxResult result = toAjax(commissionAccountBalanceService.addCommissionAccountBalance(commissionAccountBalance));

        // 获取操作后余额
        BigDecimal newBalance = commissionAccountBalanceService.getBalance(commissionAccountBalance.getUserId());
        // 记录日志，记录操作前后余额
        Long adminId = getLoginUser().getUserId();;
        commissionAccountTransactionService.saveBalanceTransaction(commissionAccountBalance.getUserId(),
                transactionAmount, newBalance,
                TransactionType.MANUAL_ADJUSTMENT,
                "管理员增加余额",
                1,adminId);
        return result;
    }

    /**
     * 减少用户余额
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:edit')")
    @Log(title = "用户余额", businessType = BusinessType.UPDATE)
    @PutMapping("/decrease")
    public AjaxResult decreaseBalance(@RequestBody CommissionAccountBalance commissionAccountBalance) {
        // 获取操作前余额
        BigDecimal oldBalance = commissionAccountBalanceService.getBalance(commissionAccountBalance.getUserId());
        BigDecimal transactionAmount =commissionAccountBalance.getBalance();
        if(transactionAmount==null || transactionAmount.compareTo(BigDecimal.ZERO)<0){
            return AjaxResult.error("金额异常");
        }
        if(oldBalance.compareTo(transactionAmount)<0){
            return AjaxResult.error("余额不足");
        }
        // 减少余额
        AjaxResult result = toAjax(commissionAccountBalanceService.decreaseCommissionAccountBalance(commissionAccountBalance));

        // 获取操作后余额
        BigDecimal newBalance = commissionAccountBalanceService.getBalance(commissionAccountBalance.getUserId());

        // 记录日志，记录操作前后余额
        Long adminId = getLoginUser().getUserId();;
        commissionAccountTransactionService.saveBalanceTransaction(commissionAccountBalance.getUserId(),
                transactionAmount, newBalance,
                TransactionType.MANUAL_ADJUSTMENT,
                "管理员减少金额",
                0,adminId);

        return result;
    }


    /**
     * 删除用户余额 未启用
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_balance:remove')")
    @Log(title = "用户余额", businessType = BusinessType.DELETE)
	@DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
//        return toAjax(commissionAccountBalanceService.deleteCommissionAccountBalanceByUserIds(userIds));
        return toAjax(0);
    }

}
