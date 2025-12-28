<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="列车号" prop="trainNo">
        <el-input
          v-model="queryParams.trainNo"
          placeholder="请输入列车号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发车日期" prop="departDate">
        <el-date-picker clearable
          v-model="queryParams.departDate"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择发车日期">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="始发站" prop="startStation">
        <el-input
          v-model="queryParams.startStation"
          placeholder="请输入始发站"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="终点站" prop="endStation">
        <el-input
          v-model="queryParams.endStation"
          placeholder="请输入终点站"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总时长" prop="totalDuration">
        <el-input
          v-model="queryParams.totalDuration"
          placeholder="请输入总时长"
          clearable
          @keyup.enter="handleQuery"
        />
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
          v-hasPermi="['schedule:schedule:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['schedule:schedule:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['schedule:schedule:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['schedule:schedule:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="scheduleList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="scheduleId" />
      <el-table-column label="列车号" align="center" prop="trainNo" />
      <el-table-column label="发车日期" align="center" prop="departDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.departDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="始发站" align="center" prop="startStation" />
      <el-table-column label="终点站" align="center" prop="endStation" />
      <el-table-column label="总时长" align="center" prop="totalDuration" />
      <el-table-column label="车次状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['schedule:schedule:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['schedule:schedule:remove']">删除</el-button>
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

    <!-- 添加或修改车次信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="scheduleRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="列车号" prop="trainNo">
          <el-input v-model="form.trainNo" placeholder="请输入列车号" />
        </el-form-item>
        <el-form-item label="发车日期" prop="departDate">
          <el-date-picker clearable
            v-model="form.departDate"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择发车日期">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="始发站" prop="startStation">
          <el-input v-model="form.startStation" placeholder="请输入始发站" />
        </el-form-item>
        <el-form-item label="终点站" prop="endStation">
          <el-input v-model="form.endStation" placeholder="请输入终点站" />
        </el-form-item>
        <el-form-item label="总时长" prop="totalDuration">
          <el-input v-model="form.totalDuration" placeholder="请输入总时长" />
        </el-form-item>
        <el-form-item label="备注" prop="remark">
          <el-input v-model="form.remark" type="textarea" placeholder="请输入内容" />
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

<script setup name="Schedule">
import { listSchedule, getSchedule, delSchedule, addSchedule, updateSchedule } from "@/api/schedule/schedule"

const { proxy } = getCurrentInstance()

const scheduleList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    trainNo: null,
    departDate: null,
    startStation: null,
    endStation: null,
    totalDuration: null,
    status: null,
  },
  rules: {
    trainNo: [
      { required: true, message: "列车号不能为空", trigger: "blur" }
    ],
    departDate: [
      { required: true, message: "发车日期不能为空", trigger: "blur" }
    ],
    startStation: [
      { required: true, message: "始发站不能为空", trigger: "blur" }
    ],
    endStation: [
      { required: true, message: "终点站不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "车次状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询车次信息列表 */
function getList() {
  loading.value = true
  listSchedule(queryParams.value).then(response => {
    scheduleList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    scheduleId: null,
    trainNo: null,
    departDate: null,
    startStation: null,
    endStation: null,
    totalDuration: null,
    status: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("scheduleRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.scheduleId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加车次信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _scheduleId = row.scheduleId || ids.value
  getSchedule(_scheduleId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改车次信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["scheduleRef"].validate(valid => {
    if (valid) {
      if (form.value.scheduleId != null) {
        updateSchedule(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addSchedule(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _scheduleIds = row.scheduleId || ids.value
  proxy.$modal.confirm('是否确认删除车次信息编号为"' + _scheduleIds + '"的数据项？').then(function() {
    return delSchedule(_scheduleIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('schedule/schedule/export', {
    ...queryParams.value
  }, `schedule_${new Date().getTime()}.xlsx`)
}

getList()
</script>
