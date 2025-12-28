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
      <el-form-item label="所属铁路局" prop="railwayBureau">
        <el-input
          v-model="queryParams.railwayBureau"
          placeholder="请输入所属铁路局"
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
          v-hasPermi="['basic:basic:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['basic:basic:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['basic:basic:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['basic:basic:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="basicList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="ID" align="center" prop="basicId" />
      <el-table-column label="列车号" align="center" prop="trainNo" />
      <el-table-column label="列车类型" align="center" prop="trainType" />
      <el-table-column label="所属铁路局" align="center" prop="railwayBureau" />
      <el-table-column label="状态" align="center" prop="status" />
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['basic:basic:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['basic:basic:remove']">删除</el-button>
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

    <!-- 添加或修改列车基础信息对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="basicRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="列车号" prop="trainNo">
          <el-input v-model="form.trainNo" placeholder="请输入列车号" />
        </el-form-item>
        <el-form-item label="所属铁路局" prop="railwayBureau">
          <el-input v-model="form.railwayBureau" placeholder="请输入所属铁路局" />
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

<script setup name="Basic">
import { listBasic, getBasic, delBasic, addBasic, updateBasic } from "@/api/basic/basic"

const { proxy } = getCurrentInstance()

const basicList = ref([])
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
    trainType: null,
    railwayBureau: null,
    status: null,
  },
  rules: {
    trainNo: [
      { required: true, message: "列车号不能为空", trigger: "blur" }
    ],
    trainType: [
      { required: true, message: "列车类型不能为空", trigger: "change" }
    ],
    railwayBureau: [
      { required: true, message: "所属铁路局不能为空", trigger: "blur" }
    ],
    status: [
      { required: true, message: "状态不能为空", trigger: "change" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询列车基础信息列表 */
function getList() {
  loading.value = true
  listBasic(queryParams.value).then(response => {
    basicList.value = response.rows
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
    basicId: null,
    trainNo: null,
    trainType: null,
    railwayBureau: null,
    status: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null,
    remark: null
  }
  proxy.resetForm("basicRef")
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
  ids.value = selection.map(item => item.basicId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加列车基础信息"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _basicId = row.basicId || ids.value
  getBasic(_basicId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改列车基础信息"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["basicRef"].validate(valid => {
    if (valid) {
      if (form.value.basicId != null) {
        updateBasic(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addBasic(form.value).then(response => {
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
  const _basicIds = row.basicId || ids.value
  proxy.$modal.confirm('是否确认删除列车基础信息编号为"' + _basicIds + '"的数据项？').then(function() {
    return delBasic(_basicIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('basic/basic/export', {
    ...queryParams.value
  }, `basic_${new Date().getTime()}.xlsx`)
}

getList()
</script>
