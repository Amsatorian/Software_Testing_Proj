<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="员工姓名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入员工姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="联系方式" prop="contactNumber">
        <el-input
          v-model="queryParams.contactNumber"
          placeholder="请输入联系方式"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="电子邮箱" prop="email">
        <el-input
          v-model="queryParams.email"
          placeholder="请输入电子邮箱"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="职位" prop="position">
        <el-input
          v-model="queryParams.position"
          placeholder="请输入职位"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="入职日期" prop="hireDate">
        <el-date-picker clearable
          v-model="queryParams.hireDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择入职日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['Employee:employee:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['Employee:employee:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['Employee:employee:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['Employee:employee:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="employeeList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="员工ID" align="center" prop="employeeId" />
      <el-table-column label="员工姓名" align="center" prop="name" />
      <el-table-column label="联系方式" align="center" prop="contactNumber" />
      <el-table-column label="电子邮箱" align="center" prop="email" />
      <el-table-column label="职位" align="center" prop="position" />
      <el-table-column label="入职日期" align="center" prop="hireDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.hireDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['Employee:employee:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['Employee:employee:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改Employee对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="employeeRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="员工姓名" prop="name">
          <el-input v-model="form.name" placeholder="请输入员工姓名" />
        </el-form-item>
        <el-form-item label="联系方式" prop="contactNumber">
          <el-input v-model="form.contactNumber" placeholder="请输入联系方式" />
        </el-form-item>
        <el-form-item label="电子邮箱" prop="email">
          <el-input v-model="form.email" placeholder="请输入电子邮箱" />
        </el-form-item>
        <el-form-item label="职位" prop="position">
          <el-input v-model="form.position" placeholder="请输入职位" />
        </el-form-item>
        <el-form-item label="入职日期" prop="hireDate">
          <el-date-picker clearable
            v-model="form.hireDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择入职日期">
          </el-date-picker>
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Employee">
import { listEmployee, getEmployee, delEmployee, addEmployee, updateEmployee } from "@/api/Employee/employee";

const { proxy } = getCurrentInstance();

const employeeList = ref([]);
const open = ref(false);
const loading = ref(true);
const showSearch = ref(true);
const ids = ref([]);
const single = ref(true);
const multiple = ref(true);
const total = ref(0);
const title = ref("");

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    name: null,
    contactNumber: null,
    email: null,
    position: null,
    hireDate: null,
    status: null,
  },
  rules: {
    name: [
      { required: true, message: "员工姓名不能为空", trigger: "blur" }
    ],
    position: [
      { required: true, message: "职位不能为空", trigger: "blur" }
    ],
    hireDate: [
      { required: true, message: "入职日期不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
    createTime: [
      { required: true, message: "创建时间不能为空", trigger: "blur" }
    ],
    updateTime: [
      { required: true, message: "更新时间不能为空", trigger: "blur" }
    ]
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询Employee列表 */
function getList() {
  loading.value = true;
  listEmployee(queryParams.value).then(response => {
    employeeList.value = response.rows;
    total.value = response.total;
    loading.value = false;
  });
}

// 取消按钮
function cancel() {
  open.value = false;
  reset();
}

// 表单重置
function reset() {
  form.value = {
    employeeId: null,
    name: null,
    contactNumber: null,
    email: null,
    position: null,
    hireDate: null,
    status: null,
    createTime: null,
    updateTime: null
  };
  proxy.resetForm("employeeRef");
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1;
  getList();
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef");
  handleQuery();
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.employeeId);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加Employee";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _employeeId = row.employeeId || ids.value
  getEmployee(_employeeId).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改Employee";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["employeeRef"].validate(valid => {
    if (valid) {
      if (form.value.employeeId != null) {
        updateEmployee(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addEmployee(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功");
          open.value = false;
          getList();
        });
      }
    }
  });
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _employeeIds = row.employeeId || ids.value;
  proxy.$modal.confirm('是否确认删除Employee编号为"' + _employeeIds + '"的数据项？').then(function() {
    return delEmployee(_employeeIds);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('Employee/employee/export', {
    ...queryParams.value
  }, `employee_${new Date().getTime()}.xlsx`)
}

getList();
</script>
