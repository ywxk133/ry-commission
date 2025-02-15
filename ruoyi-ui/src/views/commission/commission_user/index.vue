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
      <el-form-item label="推荐人用户Id" prop="parentId">
        <el-input
          v-model="queryParams.parentId"
          placeholder="请输入推荐人用户ID"
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
      <el-table-column label="推荐人用户ID" align="center" prop="parentId" />
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
            icon="el-icon-edit"
            @click="handleAgentManagement(scope.row)"
            v-hasPermi="['commission:commission_user:edit']"
          >代理管理</el-button>
           <!-- 模拟下单按钮 -->
        <el-button
          size="mini"
          type="text"
          icon="el-icon-edit"
          @click="openOrderDialog(scope.row)"
          v-hasPermi="['commission:commission_user:edit']"
        >模拟下单</el-button>
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
        <el-form-item label="密码"    :disabled="isEdit" prop="password">
          <el-input v-model="form.password" type="password" placeholder="请输入密码" />
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
        <el-form-item label="推荐人用户ID" prop="parentId">
          <el-input v-model="form.parentId" placeholder="请输入推荐人用户ID" />
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
        <el-form-item label="用户级别"  :disabled="isAdd" prop="rankLevel">
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
<el-dialog title="账户管理" :visible.sync="accountDialogVisible" width="1000px">
  <el-form :model="accountInfo" label-width="100px">
    <el-form-item label="当前余额">
      <el-input v-model="accountInfo.balance" readonly :style="{ width: '200px' }"></el-input>
    </el-form-item>
    
    <el-row :gutter="10">
      <el-col :span="12">
        <el-form-item label="调整余额">
          <el-input-number v-model="balanceAdjustment" :min="0" label="调整金额" style="width: 100%;" />
        </el-form-item>
      </el-col>

      <el-col :span="12">
        <el-form-item label="操作">
          <el-button @click="confirmBalanceAdjustment(1)" type="primary" style="width: 45%;">增加</el-button>
          <el-button @click="confirmBalanceAdjustment(2)" type="danger" style="width: 45%;">减少</el-button>
        </el-form-item>
      </el-col>
    </el-row>

    <el-table :data="accountTransactions" style="width: 100%">
  <el-table-column label="交易时间" prop="transactionTime"></el-table-column>
  <el-table-column label="变动金额" prop="amount"></el-table-column>
  <el-table-column label="变动后的余额" prop="balanceAfter"></el-table-column>
  <el-table-column label="交易类型" prop="transactionType">
    <template slot-scope="scope">
      {{ parseTransactionType(scope.row.transactionType) }}
    </template>
  </el-table-column>
  <el-table-column label="交易描述" prop="description"></el-table-column>

  <!-- 新增字段：商品价格 -->
  <el-table-column label="商品价格" prop="productPrice">
    <template slot-scope="scope">
      <span>{{ scope.row.productPrice }}</span>
    </template>
  </el-table-column>

  <!-- 新增字段：变更类型 -->
  <el-table-column label="变更类型" prop="isIncrease">
    <template slot-scope="scope">
      <!-- 根据变更类型展示：增加/扣除 -->
      <span>{{ scope.row.isIncrease === 1 ? '增加' : '扣除' }}</span>
    </template>
  </el-table-column>

  <!-- 新增字段：下单地址 -->
  <el-table-column label="下单地址" prop="orderAddress">
    <template slot-scope="scope">
      <span>{{ scope.row.orderAddress }}</span>
    </template>
  </el-table-column>
</el-table>

    <!-- 交易明细分页 -->
    <el-pagination
      v-show="accountTransactions.length > 0"
      :current-page="pagination.currentPage"
      :page-size="pagination.pageSize"
      :total="pagination.total"
      @current-change="handlePageChange"
      layout="total, prev, pager, next, jumper"
      style="margin-top: 20px"
    />
  </el-form>
  
  <div slot="footer" class="dialog-footer">
    <el-button @click="cancelAccountManagement">取消</el-button>
  </div>
</el-dialog>
<el-dialog title="代理管理" :visible.sync="agentDialogVisible" width="600px">
    <el-form :model="agentInfo" label-width="100px">
      <!-- 启用代理的选择 -->
      <el-form-item label="是否启用代理" prop="isAgent">
        <el-checkbox v-model="agentInfo.isAgent" label="是否为代理" @change="handleAgentChange"></el-checkbox>

      </el-form-item>

      <!-- 代理级别选择 -->
      <el-form-item label="代理级别" v-if="agentInfo.isAgent">
        <el-radio-group v-model="agentInfo.agentLevel" @input="shijian">
          <el-radio :label="1">省级代理</el-radio>
          <el-radio :label="2">市级代理</el-radio>
        </el-radio-group>
      </el-form-item>

      <!-- 省选择 -->
      <el-form-item label="选择省" v-if="agentInfo.isAgent">
        <el-select v-model="agentInfo.selectedProvince" placeholder="请选择省" @change="handleProvinceChange" clearable>
          <el-option
            v-for="province in provinces"
            :key="province.id"
            :label="province.name"
            :value="province.id"
          />
        </el-select>
      </el-form-item>

      <!-- 市选择，仅当市级代理时 -->
       <!-- v-if="agentInfo.isAgent && agentInfo.agentLevel === 2 && agentInfo.selectedProvince" -->
      <el-form-item label="选择市" v-if="agentInfo.isAgent && agentInfo.agentLevel == 2&& agentInfo.selectedProvince">
        <el-select v-model="agentInfo.selectedCity" placeholder="请选择市" clearable>
          <el-option
            v-for="city in cities"
            :key="city.id"
            :label="city.name"
            :value="city.id"
          />
        </el-select>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
        <el-button @click="cancelAgentManagement">取消</el-button>
        <el-button @click="submitAgentForm" type="primary">确认</el-button>
      </div>
  </el-dialog>

  <el-dialog title="模拟下单" :visible.sync="orderDialogVisible" width="500px">
  <el-form :model="orderForm" label-width="100px">
    <!-- 订单金额 -->
    <el-form-item label="订单金额" prop="orderAmount">
      <el-input-number v-model="orderForm.orderAmount" :min="0" label="金额" style="width: 100%;" />
    </el-form-item>

    <!-- 省选择 -->
    <el-form-item label="选择省">
      <el-select v-model="orderForm.selectedProvince" placeholder="请选择省" @change="handleProvinceChange" clearable>
        <el-option
          v-for="province in provinces"
          :key="province.id"
          :label="province.name"
          :value="province.id"
        />
      </el-select>
    </el-form-item>

    <!-- 市选择 -->
    <el-form-item label="选择市">
      <el-select v-model="orderForm.selectedCity" placeholder="请选择市" clearable>
        <el-option
          v-for="city in cities"
          :key="city.id"
          :label="city.name"
          :value="city.id"
        />
      </el-select>
    </el-form-item>

    <!-- 详细地址 -->
    <el-form-item label="详细地址" prop="address">
      <el-input v-model="orderForm.address" placeholder="请输入详细地址" />
    </el-form-item>
  </el-form>

  <div slot="footer" class="dialog-footer">
    <el-button @click="cancelOrderDialog">取消</el-button>
    <el-button type="primary" @click="submitOrderForm">确认下单</el-button>
  </div>
</el-dialog>

  </div>
</template>

<script>
import { listCommission_user, getCommission_user, delCommission_user, addCommission_user, updateCommission_user,userSimulationOrder } from "@/api/commission/commission_user";
import { listCommission_account_transaction } from "@/api/commission/commission_account_transaction.js";
import { getCommission_account_balance,increaseBalance,decreaseBalance } from "@/api/commission/commission_account_balance.js";
import { listCity } from "@/api/commission/city.js";
import { getAgent ,updateAgent} from "@/api/commission/agent.js";
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
      isEdit:true,
      isAdd:true,
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
      userid: null, // 余额操作的用户id
        // 分页信息
        pagination: {
        currentPage: 1,
        pageSize: 5,
        total: 0,
      },
        // 控制代理管理弹框的显示
        agentDialogVisible: false,

    // 代理信息模型
    agentInfo: {
      userId : null,
      isAgent: false,       // 是否启用代理
      agentLevel: 1,        // 默认代理级别（省级）
      selectedProvince: null, // 选中的省
      selectedCity: null,    // 选中的市
    },

    // 省市数据
    provinces: [],
    cities: [],
 // 控制模拟下单弹框的显示
    orderDialogVisible: false,
    
    // 模拟下单表单数据
    orderForm: {
      userId: null,            // 用户ID
      orderAmount: 0,          // 订单金额
      address: '',             // 详细地址
      selectedProvince: null,  // 省选择
      selectedCity: null,      // 市选择
      orderDistrictId: null    // 城市编号
    },

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
    // 解析变更类型
  parseChangeType(isIncrease) {
    return isIncrease === 1 ? "增加" : "扣除";
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
    lazyLoad(node, resolve) {
      console.log("lazyLoad 被调用")
      const { level } = node;
      console.log(`正在加载第 ${level} 级的数据`);

      // 动态加载数据：根据 `level` 和 `parentId` 加载对应的数据
      const parentId = node.value;  // 获取上一级的ID

      listCity({ parentId }).then(response => {
        if (response.code === 200) {
          const nodes = response.data.map(item => ({
            value: item.id,
            label: item.name,
            children: item.children || [],
          }));
          // 加载完数据后调用 `resolve` 方法
          resolve(nodes);
        }
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
   handleAccountManagement(row) {
      this.userId = row.userId;  // 获取当前选中的用户ID
      this.accountDialogVisible = true;
      this.getBalance(this.userId);  // 查询余额
      this.getTransactionDetails(this.userId, this.pagination.currentPage, this.pagination.pageSize);  // 查询交易明细
    },

    // 获取余额
    getBalance(userId) {
      getCommission_account_balance(userId).then(response => {
        this.accountInfo.balance = response.data.balance;  // 更新余额
      });
    },

    // 获取账户交易明细（分页）
    getTransactionDetails(userId, currentPage, pageSize) {
      listCommission_account_transaction({ userId, pageNum: currentPage, pageSize }).then(response => {
        if (response.code === 200) {
          this.accountTransactions = response.rows;
          this.pagination.total = response.total;  // 更新总条数
        } else {
          this.accountTransactions = [];
        }
      });
    },

    // 分页变化时触发
    handlePageChange(page) {
      this.pagination.currentPage = page;
      this.getTransactionDetails(this.userId, page, this.pagination.pageSize);  // 更新当前页的交易明细
    },

    // 确认余额调整
    confirmBalanceAdjustment(type) {
      const balanceAdjustment = this.balanceAdjustment;
      if (balanceAdjustment === 0) {
        this.$modal.msgWarning("调整金额不能为零");
        return;
      }

      this.$modal.confirm(`确认要对编号 ${this.userId} 的余额 ${type === 1 ? '增加' : '减少'} ${balanceAdjustment} 吗？`)
        .then(() => {
          this.adjustBalance(type, balanceAdjustment, this.userId);
        })
        .catch(() => {
          console.log('用户取消了余额调整');
        });
    },

    // 增加或减少余额
    adjustBalance(type, balanceAdjustment, userId) {
      if (type === 1) { // 增加余额
        increaseBalance({ userId, balance: balanceAdjustment }).then(() => {
          this.$modal.msgSuccess("余额增加成功");
          this.getBalance(userId);
          this.getTransactionDetails(userId, this.pagination.currentPage, this.pagination.pageSize);
        });
      } else if (type === 2) { // 减少余额
        decreaseBalance({ userId, balance: balanceAdjustment }).then(() => {
          this.$modal.msgSuccess("余额减少成功");
          this.getBalance(userId);
          this.getTransactionDetails(userId, this.pagination.currentPage, this.pagination.pageSize);
        });
      }
    },

    // 取消账户管理弹框
    cancelAccountManagement() {
      this.accountDialogVisible = false;
      this.userId = null;
      this.balanceAdjustment = 0;  // 重置余额调整输入框
    },

    // 解析交易类型为中文
    parseTransactionType(transactionType) {
      switch (transactionType) {
        case "recharge":
          return "充值";
        case "order":
          return "订单";
        case "refund":
          return "退款";
        case "manual_adjustment":
          return "管理员调整";
          case "direct_push_reward":
          return "直推奖励";
          case "team_differential_reward":
          return "团队分成";
          case "agency_sharing":
          return "代理分成";
        default:
          return transactionType; // 如果无法解析，返回原始值
      }
    },
   // 打开代理管理弹框并加载相关数据
   handleAgentManagement(row) {
      const userId = row.userId
      this.agentDialogVisible = true;
      this.getAgentInfo(row.userId);  // 获取代理信息
    },

    getAgentInfo(userId) {
  // 调用接口获取代理信息
  getAgent(userId).then(response => {
    if (response.code == 200) {
      const agentData = response.data;  // 获取到代理数据
      this.agentInfo.userId = userId;               // 设置用户ID

      // 设置代理信息
      this.agentInfo.isAgent = agentData.isEnabled ? agentData.isEnabled == 1 : false;  // 判断是否启用代理
      this.agentInfo.agentLevel = agentData.agentLevel;    // 代理级别
      this.agentInfo.selectedProvince = agentData.provinceId;  // 设置省ID
      this.agentInfo.selectedCity = agentData.cityId;         // 设置市ID

      // 根据代理级别加载省市数据
      if (this.agentInfo.isAgent) {
        this.loadProvincesAndCities(agentData.provinceId);  // 加载省市数据
      }
    }
  }).catch(error => {
    this.agentInfo.isAgent = false;
    console.error("获取代理信息失败", error);
  });
},
// 根据省ID加载对应的省市数据
loadProvincesAndCities(provinceId) {
  // 加载省级数据
  listCity({ parentId: 100000 }).then(response => {
    if (response.code == 200) {
      this.provinces = response.data; // 赋值省级数据

      // 如果是市级代理，确保根据省ID加载市级数据
      if (this.agentInfo.agentLevel == 2) {
        this.handleProvinceChange(provinceId);  // 如果是市级代理，加载市级数据
      }
    }
  });
},
  
// 省选择改变时，动态加载对应市级数据
handleProvinceChange(provinceId) {

  // 根据选择的省，加载对应的市级数据（仅当是市级代理时）
  if (this.agentInfo.agentLevel == 2) {
    listCity({ parentId: provinceId }).then(response => {
      if (response.code == 200) {
        this.cities = response.data; // 更新市级数据
      }
    });
  }
},

// 提交代理配置信息时，构建代理数据
async submitAgentForm() {
  const agentData = {
    userId: this.agentInfo.userId,  // 传递选中的 userId
    isEnabled: this.agentInfo.isAgent ? 1 : 0, // 代理启用状态（0禁用，1启用）
    agentLevel: this.agentInfo.agentLevel,     // 代理级别
    provinceId: this.agentInfo.selectedProvince, // 省级ID
    cityId: this.agentInfo.selectedCity,        // 市级ID
  };

  try {
    // 调用更新代理信息接口
    const res = await updateAgent(agentData);

    // 判断接口返回结果
    if (res.code === 200) {
      this.$modal.msgSuccess("代理信息提交成功");
      this.agentDialogVisible = false;
    } else {
      this.$modal.msgError("提交失败，请稍后再试");
    }
  } catch (error) {
    // 错误处理
    this.$modal.msgError("提交代理信息时发生错误，请稍后再试");
    console.error("提交代理信息失败", error);
  }
},
    // 取消代理管理弹框
    cancelAgentManagement() {
      this.agentDialogVisible = false;
      this.agentInfo = {
        isAgent: false,
        agentLevel: 1,       // 清空代理级别为默认省级代理
        selectedProvince: null,
        selectedCity: null,  // 清空市
      };
      this.userId = null;
      this.provinces = [];  // 清空省数据
      this.cities = [];     // 清空市数据
    },
    shijian(e){
      if(e ==1 ){
        this.cities = [];     // 清空市数据
        this.agentInfo. selectedCity= null;
      }else if(e==2){
        this.handleProvinceChange( this.agentInfo. selectedProvince);
      }
    },
    handleAgentChange() {
    if (this.agentInfo.isAgent) {
      // 如果启用代理，则加载省级数据
      this.loadProvinces();
    } else {
      // 如果禁用代理，清空省市选择数据
      this.provinces = [];
      this.cities = [];
      this.agentInfo.selectedProvince = null;
      this.agentInfo.selectedCity = null;
    }
  },
   // 请求省级数据
   loadProvinces() {
    listCity({ parentId: 100000 }).then(response => {
      if (response.code === 200) {
        this.provinces = response.data; // 更新省级数据
      }
    }).catch(error => {
      console.error("加载省级数据失败", error);
    });
  },
// 打开模拟下单弹窗
openOrderDialog(row) {
    // 填充用户ID
    this.orderForm.userId = row.userId;

    // 清空表单字段
    this.orderForm.orderAmount = 0;
    this.orderForm.address = '';
    this.orderForm.selectedProvince = null;
    this.orderForm.selectedCity = null;
    this.orderForm.orderDistrictId = null;

    // 加载省数据
    this.loadProvinces();

    // 打开弹窗
    this.orderDialogVisible = true;
  },

  // 加载省数据
  loadProvinces() {
    // 请求省级数据
    listCity({ parentId: 100000 }).then(response => {
      if (response.code == 200) {
        this.provinces = response.data; // 赋值省级数据
      }
    });
  },

  // 省选择变化时，加载对应的市数据
  handleProvinceChange(provinceId) {
    if (provinceId) {
      // 请求市级数据
      listCity({ parentId: provinceId }).then(response => {
        if (response.code == 200) {
          this.cities = response.data; // 赋值市级数据
        }
      });
    } else {
      this.cities = []; // 清空市级数据
    }
  },

  // 提交订单表单
  submitOrderForm() {
    // 检查表单字段
    if (!this.orderForm.selectedProvince || !this.orderForm.selectedCity || !this.orderForm.address || this.orderForm.orderAmount <= 0) {
      this.$message.warning('请填写完整的订单信息');
      return;
    }

    // 拼接完整地址
    const fullAddress = `${this.provinces.find(p => p.id === this.orderForm.selectedProvince).name} - ` +
                        `${this.cities.find(c => c.id === this.orderForm.selectedCity).name} - ${this.orderForm.address}`;

    // 更新表单中的完整地址
    this.orderForm.address = fullAddress;
    this.orderForm.orderDistrictId = this.orderForm.selectedCity;

    // 调用接口进行下单
    userSimulationOrder(this.orderForm).then(response => {
      if (response.code === 200) {
        this.$message.success('下单成功');
        this.orderDialogVisible = false; // 关闭弹窗
        // 可以在这里更新列表或做其他操作
      } else {
        this.$message.error('下单失败');
      }
    });
  },

  // 取消模拟下单弹框
  cancelOrderDialog() {
    this.orderDialogVisible = false;
  }

  },
};
</script>
