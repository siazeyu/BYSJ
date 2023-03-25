<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="用户ID" prop="userId">
        <el-input
          v-model="queryParams.userId"
          placeholder="请输入用户ID"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学分类型" prop="creditType">
        <el-input
          v-model="queryParams.creditType"
          placeholder="请输入学分类型"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="学分值" prop="credit">
        <el-input
          v-model="queryParams.credit"
          placeholder="请输入学分值"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="状态" prop="statue">
        <el-select v-model="queryParams.statue" placeholder="请选择状态" clearable>
          <el-option
            v-for="dict in dict.type.sys_credit_status"
            :key="dict.value"
            :label="dict.label"
            :value="dict.value"
          />
        </el-select>
      </el-form-item>
      <el-form-item label="申请时间">
        <el-date-picker
          v-model="daterangeCreditDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
      </el-form-item>
      <el-form-item label="完成时间">
        <el-date-picker
          v-model="daterangeFinalDate"
          style="width: 240px"
          value-format="yyyy-MM-dd"
          type="daterange"
          range-separator="-"
          start-placeholder="开始日期"
          end-placeholder="结束日期"
        ></el-date-picker>
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
          v-hasPermi="['credit:credit:add']"
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
          v-hasPermi="['credit:credit:edit']"
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
          v-hasPermi="['credit:credit:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['credit:credit:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="creditList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="id" align="center" prop="id" />
      <el-table-column label="用户ID" align="center" prop="userId" />
      <el-table-column label="学分类型" align="center" prop="creditType" />
      <el-table-column label="学分值" align="center" prop="credit" />
      <el-table-column label="申请附件" align="center" prop="requestFile" />
      <el-table-column label="状态" align="center" prop="statue">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.sys_credit_status" :value="scope.row.statue"/>
        </template>
      </el-table-column>
      <el-table-column label="申请时间" align="center" prop="creditDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.creditDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="完成时间" align="center" prop="finalDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.finalDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['credit:credit:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['credit:credit:remove']"
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

    <!-- 添加或修改学分申请对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="用户ID" prop="userId">
          <el-input v-model="form.userId" placeholder="请输入用户ID" />
        </el-form-item>
        <el-form-item label="学分类型" prop="creditType">
          <el-input v-model="form.creditType" placeholder="请输入学分类型" />
        </el-form-item>
        <el-form-item label="学分值" prop="credit">
          <el-input v-model="form.credit" placeholder="请输入学分值" />
        </el-form-item>
        <el-form-item label="申请附件" prop="requestFile">
          <file-upload v-model="form.requestFile"/>
        </el-form-item>
        <el-form-item label="状态" prop="statue">
          <el-radio-group v-model="form.statue">
            <el-radio
              v-for="dict in dict.type.sys_credit_status"
              :key="dict.value"
              :label="parseInt(dict.value)"
            >{{dict.label}}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="申请时间" prop="creditDate">
          <el-date-picker clearable
            v-model="form.creditDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择申请时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="完成时间" prop="finalDate">
          <el-date-picker clearable
            v-model="form.finalDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择完成时间">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listCredit, getCredit, delCredit, addCredit, updateCredit } from "@/api/credit/credit";

export default {
  name: "Credit",
  dicts: ['sys_credit_status'],
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
      // 学分申请表格数据
      creditList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 完成时间时间范围
      daterangeCreditDate: [],
      // 完成时间时间范围
      daterangeFinalDate: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        userId: null,
        creditType: null,
        credit: null,
        requestFile: null,
        statue: null,
        creditDate: null,
        finalDate: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        userId: [
          { required: true, message: "用户ID不能为空", trigger: "blur" }
        ],
        creditType: [
          { required: true, message: "学分类型不能为空", trigger: "blur" }
        ],
        credit: [
          { required: true, message: "学分值不能为空", trigger: "blur" }
        ],
        requestFile: [
          { required: true, message: "申请附件不能为空", trigger: "blur" }
        ],
        statue: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询学分申请列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeCreditDate && '' != this.daterangeCreditDate) {
        this.queryParams.params["beginCreditDate"] = this.daterangeCreditDate[0];
        this.queryParams.params["endCreditDate"] = this.daterangeCreditDate[1];
      }
      if (null != this.daterangeFinalDate && '' != this.daterangeFinalDate) {
        this.queryParams.params["beginFinalDate"] = this.daterangeFinalDate[0];
        this.queryParams.params["endFinalDate"] = this.daterangeFinalDate[1];
      }
      listCredit(this.queryParams).then(response => {
        this.creditList = response.rows;
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
        id: null,
        userId: null,
        creditType: null,
        credit: null,
        requestFile: null,
        statue: null,
        creditDate: null,
        finalDate: null
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
      this.daterangeCreditDate = [];
      this.daterangeFinalDate = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加学分申请";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getCredit(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改学分申请";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateCredit(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addCredit(this.form).then(response => {
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
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除学分申请编号为"' + ids + '"的数据项？').then(function() {
        return delCredit(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('credit/credit/export', {
        ...this.queryParams
      }, `credit_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
