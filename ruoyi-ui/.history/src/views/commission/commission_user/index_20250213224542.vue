<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户名" prop="username">
        <el-input
          v-model="queryParams.username"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入邮箱"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select v-model="queryParams.gender" placeholder="请选择性别" clearable>
          <el-option
            v-for="dict in dict.type.sys_user_sex"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="创建时间" prop="createTime">
        <el-date-picker clearable
          v-model="queryParams.createTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="最后更新时间" prop="updateTime">
        <el-date-picker clearable
          v-model="queryParams.updateTime"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择最后更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="上级用户ID" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入上级用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="会员等级" prop="membershipLevel">
        <el-select v-model="queryParams.membershipLevel" placeholder="请选择会员等级" clearable>
          <el-option
            v-for="dict in dict.type.membership_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="用户级别" prop="rankLevel">
        <el-select v-model="queryParams.rankLevel" placeholder="请选择用户级别" clearable>
          <el-option
            v-for="dict in dict.type.rank_level"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['commission:commission_user:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['commission:commission_user:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['commission:commission_user:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['commission:commission_user:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="commission_userList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="用户名" align="center" prop="username" />
      <el-table-column label="邮箱" align="center" prop="email" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="性别" align="center" prop="gender">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.gender"/>
        </template>
      </el-table-column>
      <el-table-column label="创建时间" align="center" prop="createTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="最后更新时间" align="center" prop="updateTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="上级用户ID" align="center" prop="parentId" />
      <el-table-column label="会员等级" align="center" prop="membershipLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.membership_level" :value="scope.row.membershipLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="用户级别" align="center" prop="rankLevel">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.rank_level" :value="scope.row.rankLevel"/>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['commission:commission_user:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleAccountManagement(scope.row)"
            v-hasPermi="['commission:commission_user:edit']"
          >账户管理</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['commission:commission_user:remove']"
          >删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改用户管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="form.username" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input v-model="form.password" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入邮箱" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="性别" prop="gender">
          <el-radio-group v-model="form.gender">
            <el-radio
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="上级用户ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入上级用户ID" />
        </el-form-item>
        <el-form-item label="会员等级" prop="membershipLevel">
          <el-select v-model="form.membershipLevel" placeholder="请选择会员等级">
            <el-option
              v-for="dict in dict.type.membership_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="用户级别" prop="rankLevel">
          <el-select v-model="form.rankLevel" placeholder="请选择用户级别">
            <el-option
              v-for="dict in dict.type.rank_level"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            ></el-option>
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>

    </el-dialog>
    <!-- 账户管理弹框 -->
<el-dialog title="账户管理" :visible.sync="accountDialogVisible" width="500px">
  <el-form :model="accountInfo" label-width="100px">
    <el-form-item label="当前余额">
      <el-input v-model="accountInfo.balance" readonly></el-input>
    </el-form-item>
    <el-form-item label="调整余额">
      <el-input-number v-model="balanceAdjustment" :min="0" label="调整金额"></el-input-number>
    </el-form-item>
    <el-button @click="confirmBalanceAdjustment(1)">增加</el-button>
    <el-button @click="confirmBalanceAdjustment(2)">减少</el-button>
    <el-table :data="accountTransactions">
      <el-table-column label="交易时间" prop="transactionTime"></el-table-column>
      <el-table-column label="变动金额" prop="amount"></el-table-column>
      <el-table-column label="变动后的余额" prop="balanceAfter"></el-table-column>
      <el-table-column label="交易类型" prop="transactionType"></el-table-column>
      <el-table-column label="交易描述" prop="description"></el-table-column>
    </el-table>
  </el-form>
  <div slot="footer" class="dialog-footer">
    <el-button @click="cancelAccountManagement">取消</el-button>
  </div>
</el-dialog>
  </div>
</template>

<script>
import { listCommission_user, getCommission_user, delCommission_user, addCommission_user, updateCommission_user } from "@/api/commission/commission_user";
import { listCommission_account_transaction } from "@/api/commission/commission_account_transaction.js";
import { getCommission_account_balance,increaseBalance,decreaseBalance } from "@/api/commission/commission_account_balance.js";
export default {
  name: "Commission_user",
  dicts: ['membership_level', 'sys_user_sex', 'rank_level'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 用户管理表格数据
      commission_userList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        username: null,
        email: null,
        phone: null,
        gender: null,
        createTime: null,
        updateTime: null,
        parentId: null,
        membershipLevel: null,
        rankLevel: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        username: [
          { required: true, message: "用户名不能为空", trigger: "blur" }
        ],
        password: [
          { required: true, message: "密码不能为空", trigger: "blur" }
        ],
      },
      accountDialogVisible: false,  // 控制账户管理弹窗的显示
      accountInfo: {  // 账户余额信息
        balance: 0,
      },
      accountTransactions: [],  // 账户交易明细
      balanceAdjustment: 0,  // 调整的金额
      userid: null // 修改的用户
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询用户管理列表 */
    getList() {
      this.loading = true;
      listCommission_user(this.queryParams).then(response => {
        this.commission_userList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        userId: null,
        username: null,
        password: null,
        email: null,
        phone: null,
        gender: null,
        createTime: null,
        updateTime: null,
        parentId: null,
        membershipLevel: null,
        rankLevel: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.userId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加用户管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const userId = row.userId || this.ids
      getCommission_user(userId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改用户管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.userId != null) {
            updateCommission_user(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCommission_user(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const userIds = row.userId || this.ids;
      this.$modal.confirm('是否确认删除用户管理编号为"' + userIds + '"的数据项？').then(function() {
        return delCommission_user(userIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('commission/commission_user/export', {
        ...this.queryParams
      }, `commission_user_${new Date().getTime()}.xlsx`)
    },
     // 点击账户管理按钮后显示账户管理弹框
      handleAccountManagement() {
      const userId = this.getSelectedUserId();  // 获取当前选中的用户ID
      this.accountDialogVisible = true;
      this.getBalance(userId);  // 查询余额
      this.getTransactionDetails(userId);  // 查询交易明细
    },
    
    // 获取余额
    getBalance(userId) {
      getCommission_account_balance(userId).then(response => {
        this.accountInfo.balance = response.data.balance;  // 更新余额
      });
    },

    // 获取账户交易明细
    getTransactionDetails(userId) {
      listCommission_account_transaction({ userId }).then(response => {
        this.accountTransactions = response.data;  // 更新交易明细
      });
    },

    // 确认余额调整
    confirmBalanceAdjustment(type) {
      const userId = this.getSelectedUserId();
      const balanceAdjustment = this.balanceAdjustment;

      // 确认框
      this.$modal.confirm({
        title: '确认调整',
        content: `确认要${type === 1 ? '增加' : '减少'}余额 ${balanceAdjustment} 吗？`,
        onOk: () => {
          if (type === 1) { // 增加余额
            this.adjustBalance(type, balanceAdjustment, userId);
          } else if (type === 2) { // 减少余额
            this.adjustBalance(type, balanceAdjustment, userId);
          }
        },
      });
    },

    // 增加或减少余额
    adjustBalance(type, balanceAdjustment, userId) {
      if (type === 1) { // 增加余额
        increaseBalance({ userId, balance: balanceAdjustment }).then(() => {
          this.$modal.msgSuccess("余额增加成功");
          this.getBalance(userId);
          this.getTransactionDetails(userId);
        });
      } else if (type === 2) { // 减少余额
        decreaseBalance({ userId, balance: balanceAdjustment }).then(() => {
          this.$modal.msgSuccess("余额减少成功");
          this.getBalance(userId);
          this.getTransactionDetails(userId);
        });
      }
    },

    // 获取选中的用户ID（假设你已经实现了选择用户的功能）
    getSelectedUserId() {
      return this.selectedUserId;  // 获取当前选中的用户ID
    },

    // 取消账户管理弹框
    cancelAccountManagement() {
      this.accountDialogVisible = false;
    },

  }
};
</script>
