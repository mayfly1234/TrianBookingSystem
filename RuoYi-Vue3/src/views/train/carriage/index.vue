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
      <el-form-item label="车厢号" prop="carriageNo">
        <el-input
          v-model="queryParams.carriageNo"
          placeholder="请输入车厢号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="总座位数" prop="totalSeats">
        <el-input
          v-model="queryParams.totalSeats"
          placeholder="请输入总座位数"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="可用座位数" prop="availableSeats">
        <el-input
          v-model="queryParams.availableSeats"
          placeholder="请输入可用座位数"
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
          v-hasPermi="['carriage:carriage:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['carriage:carriage:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['carriage:carriage:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['carriage:carriage:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="carriageList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="carriageId" />
      <el-table-column label="车次ID" align="center" prop="scheduleId" />
      <el-table-column label="车厢号" align="center" prop="carriageNo" />
      <el-table-column label="车厢类型" align="center" prop="carriageType" />
      <el-table-column label="总座位数" align="center" prop="totalSeats" />
      <el-table-column label="可用座位数" align="center" prop="availableSeats" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['carriage:carriage:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['carriage:carriage:remove']">删除</el-button>
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

    <!-- 添加或修改车厢信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="carriageRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="车次ID" prop="scheduleId">
          <el-input v-model="form.scheduleId" placeholder="请输入车次ID" />
        </el-form-item>
        <el-form-item label="车厢号" prop="carriageNo">
          <el-input v-model="form.carriageNo" placeholder="请输入车厢号" />
        </el-form-item>
        <el-form-item label="总座位数" prop="totalSeats">
          <el-input v-model="form.totalSeats" placeholder="请输入总座位数" />
        </el-form-item>
        <el-form-item label="可用座位数" prop="availableSeats">
          <el-input v-model="form.availableSeats" placeholder="请输入可用座位数" />
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

<script setup name="Carriage">
import { listCarriage, getCarriage, delCarriage, addCarriage, updateCarriage } from "@/api/carriage/carriage"

const { proxy } = getCurrentInstance()

const carriageList = ref([])
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
    carriageNo: null,
    carriageType: null,
    totalSeats: null,
    availableSeats: null,
  },
  rules: {
    scheduleId: [
      { required: true, message: "车次ID不能为空", trigger: "blur" }
    ],
    carriageNo: [
      { required: true, message: "车厢号不能为空", trigger: "blur" }
    ],
    carriageType: [
      { required: true, message: "车厢类型不能为空", trigger: "change" }
    ],
    totalSeats: [
      { required: true, message: "总座位数不能为空", trigger: "blur" }
    ],
    availableSeats: [
      { required: true, message: "可用座位数不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询车厢信息列表 */
function getList() {
  loading.value = true
  listCarriage(queryParams.value).then(response => {
    carriageList.value = response.rows
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
    carriageId: null,
    scheduleId: null,
    carriageNo: null,
    carriageType: null,
    totalSeats: null,
    availableSeats: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("carriageRef")
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
  ids.value = selection.map(item => item.carriageId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加车厢信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _carriageId = row.carriageId || ids.value
  getCarriage(_carriageId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改车厢信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["carriageRef"].validate(valid => {
    if (valid) {
      if (form.value.carriageId != null) {
        updateCarriage(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addCarriage(form.value).then(response => {
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
  const _carriageIds = row.carriageId || ids.value
  proxy.$modal.confirm('是否确认删除车厢信息编号为"' + _carriageIds + '"的数据项？').then(function() {
    return delCarriage(_carriageIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('carriage/carriage/export', {
    ...queryParams.value
  }, `carriage_${new Date().getTime()}.xlsx`)
}

getList()
</script>
