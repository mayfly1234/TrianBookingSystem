<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="车次ID" prop="scheduleId">
        <el-input
          v-model="queryParams.scheduleId"
          placeholder="请输入车次ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点名称" prop="stationName">
        <el-input
          v-model="queryParams.stationName"
          placeholder="请输入站点名称"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站点顺序" prop="stationOrder">
        <el-input
          v-model="queryParams.stationOrder"
          placeholder="请输入站点顺序"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="到站时间" prop="arriveTime">
        <el-input
          v-model="queryParams.arriveTime"
          placeholder="请输入到站时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="发车时间" prop="departTime">
        <el-input
          v-model="queryParams.departTime"
          placeholder="请输入发车时间"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="站台号" prop="platformNo">
        <el-input
          v-model="queryParams.platformNo"
          placeholder="请输入站台号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="停靠时长" prop="stopDuration">
        <el-input
          v-model="queryParams.stopDuration"
          placeholder="请输入停靠时长"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="累计里程" prop="cumulativeMileage">
        <el-input
          v-model="queryParams.cumulativeMileage"
          placeholder="请输入累计里程"
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
          v-hasPermi="['station:station:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['station:station:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['station:station:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['station:station:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="stationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键ID" align="center" prop="stationId" />
      <el-table-column label="车次ID" align="center" prop="scheduleId" />
      <el-table-column label="站点名称" align="center" prop="stationName" />
      <el-table-column label="站点顺序" align="center" prop="stationOrder" />
      <el-table-column label="到站时间" align="center" prop="arriveTime" />
      <el-table-column label="发车时间" align="center" prop="departTime" />
      <el-table-column label="站台号" align="center" prop="platformNo" />
      <el-table-column label="停靠时长" align="center" prop="stopDuration" />
      <el-table-column label="累计里程" align="center" prop="cumulativeMileage" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['station:station:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['station:station:remove']">删除</el-button>
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

    <!-- 添加或修改车次经停站点对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="stationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车次ID" prop="scheduleId">
          <el-input v-model="form.scheduleId" placeholder="请输入车次ID" />
        </el-form-item>
        <el-form-item label="站点名称" prop="stationName">
          <el-input v-model="form.stationName" placeholder="请输入站点名称" />
        </el-form-item>
        <el-form-item label="站点顺序" prop="stationOrder">
          <el-input v-model="form.stationOrder" placeholder="请输入站点顺序" />
        </el-form-item>
        <el-form-item label="到站时间" prop="arriveTime">
          <el-input v-model="form.arriveTime" placeholder="请输入到站时间" />
        </el-form-item>
        <el-form-item label="发车时间" prop="departTime">
          <el-input v-model="form.departTime" placeholder="请输入发车时间" />
        </el-form-item>
        <el-form-item label="站台号" prop="platformNo">
          <el-input v-model="form.platformNo" placeholder="请输入站台号" />
        </el-form-item>
        <el-form-item label="停靠时长" prop="stopDuration">
          <el-input v-model="form.stopDuration" placeholder="请输入停靠时长" />
        </el-form-item>
        <el-form-item label="累计里程" prop="cumulativeMileage">
          <el-input v-model="form.cumulativeMileage" placeholder="请输入累计里程" />
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

<script setup name="Station">
import { listStation, getStation, delStation, addStation, updateStation } from "@/api/station/station"

const { proxy } = getCurrentInstance()

const stationList = ref([])
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
    scheduleId: null,
    stationName: null,
    stationOrder: null,
    arriveTime: null,
    departTime: null,
    platformNo: null,
    stopDuration: null,
    cumulativeMileage: null,
  },
  rules: {
    scheduleId: [
      { required: true, message: "车次ID不能为空", trigger: "blur" }
    ],
    stationName: [
      { required: true, message: "站点名称不能为空", trigger: "blur" }
    ],
    stationOrder: [
      { required: true, message: "站点顺序不能为空", trigger: "blur" }
    ],
    cumulativeMileage: [
      { required: true, message: "累计里程不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询车次经停站点列表 */
function getList() {
  loading.value = true
  listStation(queryParams.value).then(response => {
    stationList.value = response.rows
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
    stationId: null,
    scheduleId: null,
    stationName: null,
    stationOrder: null,
    arriveTime: null,
    departTime: null,
    platformNo: null,
    stopDuration: null,
    cumulativeMileage: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("stationRef")
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
  ids.value = selection.map(item => item.stationId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加车次经停站点"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _stationId = row.stationId || ids.value
  getStation(_stationId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改车次经停站点"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["stationRef"].validate(valid => {
    if (valid) {
      if (form.value.stationId != null) {
        updateStation(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addStation(form.value).then(response => {
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
  const _stationIds = row.stationId || ids.value
  proxy.$modal.confirm('是否确认删除车次经停站点编号为"' + _stationIds + '"的数据项？').then(function() {
    return delStation(_stationIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('station/station/export', {
    ...queryParams.value
  }, `station_${new Date().getTime()}.xlsx`)
}

getList()
</script>
