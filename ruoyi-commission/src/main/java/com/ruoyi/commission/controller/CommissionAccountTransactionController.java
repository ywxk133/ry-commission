package com.ruoyi.commission.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
import com.ruoyi.commission.domain.CommissionAccountTransaction;
import com.ruoyi.commission.service.ICommissionAccountTransactionService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 余额流水Controller
 * 
 * @author xing
 * @date 2025-02-13
 */
@RestController
@RequestMapping("/commission/commission_account_transaction")
public class CommissionAccountTransactionController extends BaseController
{
    @Autowired
    private ICommissionAccountTransactionService commissionAccountTransactionService;

    /**
     * 查询余额流水列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:list')")
    @GetMapping("/list")
    public TableDataInfo list(CommissionAccountTransaction commissionAccountTransaction)
    {
        startPage();
        List<CommissionAccountTransaction> list = commissionAccountTransactionService.selectCommissionAccountTransactionList(commissionAccountTransaction);
        return getDataTable(list);
    }

    /**
     * 导出余额流水列表
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:export')")
    @Log(title = "余额流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, CommissionAccountTransaction commissionAccountTransaction)
    {
        List<CommissionAccountTransaction> list = commissionAccountTransactionService.selectCommissionAccountTransactionList(commissionAccountTransaction);
        ExcelUtil<CommissionAccountTransaction> util = new ExcelUtil<CommissionAccountTransaction>(CommissionAccountTransaction.class);
        util.exportExcel(response, list, "余额流水数据");
    }

    /**
     * 获取余额流水详细信息
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:query')")
    @GetMapping(value = "/{transactionId}")
    public AjaxResult getInfo(@PathVariable("transactionId") Long transactionId)
    {
        return success(commissionAccountTransactionService.selectCommissionAccountTransactionByTransactionId(transactionId));
    }

    /**
     * 新增余额流水
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:add')")
    @Log(title = "余额流水", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody CommissionAccountTransaction commissionAccountTransaction)
    {
        return toAjax(commissionAccountTransactionService.insertCommissionAccountTransaction(commissionAccountTransaction));
    }

    /**
     * 修改余额流水
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:edit')")
    @Log(title = "余额流水", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody CommissionAccountTransaction commissionAccountTransaction)
    {
        return toAjax(commissionAccountTransactionService.updateCommissionAccountTransaction(commissionAccountTransaction));
    }

    /**
     * 删除余额流水
     */
    @PreAuthorize("@ss.hasPermi('commission:commission_account_transaction:remove')")
    @Log(title = "余额流水", businessType = BusinessType.DELETE)
	@DeleteMapping("/{transactionIds}")
    public AjaxResult remove(@PathVariable Long[] transactionIds)
    {
        return toAjax(commissionAccountTransactionService.deleteCommissionAccountTransactionByTransactionIds(transactionIds));
    }
}
