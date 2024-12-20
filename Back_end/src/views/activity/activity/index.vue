<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="活动名" prop="name">
        <el-input
          v-model="queryParams.name"
          placeholder="请输入活动名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动类型" prop="category">
        <el-input
          v-model="queryParams.category"
          placeholder="请输入活动类型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动全称" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入活动全称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动创建者" prop="creator">
        <el-input
          v-model="queryParams.creator"
          placeholder="请输入活动创建者"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动创建时间" prop="createdTime">
        <el-date-picker clearable
          v-model="queryParams.createdTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择活动创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动归档时间" prop="archivedTime">
        <el-date-picker clearable
          v-model="queryParams.archivedTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择活动归档时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动图片地址" prop="picUrls">
        <el-input
          v-model="queryParams.picUrls"
          placeholder="请输入活动图片地址"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="活动开始时间" prop="startTime">
        <el-date-picker clearable
          v-model="queryParams.startTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择活动开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="活动结束时间" prop="endTime">
        <el-date-picker clearable
          v-model="queryParams.endTime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择活动结束时间">
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
          v-hasPermi="['activity:activity:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['activity:activity:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['activity:activity:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['activity:activity:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="activityList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="${comment}" align="center" prop="id" />
      <el-table-column label="活动名" align="center" prop="name" />
      <el-table-column label="活动类型" align="center" prop="category" />
      <el-table-column label="活动全称" align="center" prop="title" />
      <el-table-column label="活动创建者" align="center" prop="creator" />
      <el-table-column label="活动摘要" align="center" prop="abstract" />
      <el-table-column label="活动详细内容" align="center" prop="detailedDesc" />
      <el-table-column label="状态：0表示已创建，1表示已发布，2表示已结束归档，3表示已删除" align="center" prop="status" />
      <el-table-column label="活动创建时间" align="center" prop="createdTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.createdTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动归档时间" align="center" prop="archivedTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.archivedTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动图片地址" align="center" prop="picUrls" />
      <el-table-column label="活动开始时间" align="center" prop="startTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="活动结束时间" align="center" prop="endTime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['activity:activity:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['activity:activity:remove']">删除</el-button>
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

    <!-- 添加或修改activity对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="activityRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="活动名" prop="name">
          <el-input v-model="form.name" placeholder="请输入活动名" />
        </el-form-item>
        <el-form-item label="活动类型" prop="category">
          <el-input v-model="form.category" placeholder="请输入活动类型" />
        </el-form-item>
        <el-form-item label="活动全称" prop="title">
          <el-input v-model="form.title" placeholder="请输入活动全称" />
        </el-form-item>
        <el-form-item label="活动创建者" prop="creator">
          <el-input v-model="form.creator" placeholder="请输入活动创建者" />
        </el-form-item>
        <el-form-item label="活动摘要" prop="abstract">
          <el-input v-model="form.abstract" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="活动详细内容" prop="detailedDesc">
          <el-input v-model="form.detailedDesc" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="活动创建时间" prop="createdTime">
          <el-date-picker clearable
            v-model="form.createdTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动归档时间" prop="archivedTime">
          <el-date-picker clearable
            v-model="form.archivedTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动归档时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动图片地址" prop="picUrls">
          <el-input v-model="form.picUrls" placeholder="请输入活动图片地址" />
        </el-form-item>
        <el-form-item label="活动开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="form.startTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="活动结束时间" prop="endTime">
          <el-date-picker clearable
            v-model="form.endTime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择活动结束时间">
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

<script setup name="Activity">
import { listActivity, getActivity, delActivity, addActivity, updateActivity } from "@/api/activity/activity";

const { proxy } = getCurrentInstance();

const activityList = ref([]);
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
    category: null,
    title: null,
    creator: null,
    abstract: null,
    detailedDesc: null,
    status: null,
    createdTime: null,
    archivedTime: null,
    picUrls: null,
    startTime: null,
    endTime: null
  },
  rules: {
    name: [
      { required: true, message: "活动名不能为空", trigger: "blur" }
    ],
    category: [
      { required: true, message: "活动类型不能为空", trigger: "blur" }
    ],
    creator: [
      { required: true, message: "活动创建者不能为空", trigger: "blur" }
    ],
    createdTime: [
      { required: true, message: "活动创建时间不能为空", trigger: "blur" }
    ],
  }
});

const { queryParams, form, rules } = toRefs(data);

/** 查询activity列表 */
function getList() {
  loading.value = true;
  listActivity(queryParams.value).then(response => {
    activityList.value = response.rows;
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
    id: null,
    name: null,
    category: null,
    title: null,
    creator: null,
    abstract: null,
    detailedDesc: null,
    status: null,
    createdTime: null,
    archivedTime: null,
    picUrls: null,
    startTime: null,
    endTime: null
  };
  proxy.resetForm("activityRef");
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
  ids.value = selection.map(item => item.id);
  single.value = selection.length != 1;
  multiple.value = !selection.length;
}

/** 新增按钮操作 */
function handleAdd() {
  reset();
  open.value = true;
  title.value = "添加activity";
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset();
  const _id = row.id || ids.value
  getActivity(_id).then(response => {
    form.value = response.data;
    open.value = true;
    title.value = "修改activity";
  });
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["activityRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateActivity(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功");
          open.value = false;
          getList();
        });
      } else {
        addActivity(form.value).then(response => {
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
  const _ids = row.id || ids.value;
  proxy.$modal.confirm('是否确认删除activity编号为"' + _ids + '"的数据项？').then(function() {
    return delActivity(_ids);
  }).then(() => {
    getList();
    proxy.$modal.msgSuccess("删除成功");
  }).catch(() => {});
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('activity/activity/export', {
    ...queryParams.value
  }, `activity_${new Date().getTime()}.xlsx`)
}

getList();
</script>
