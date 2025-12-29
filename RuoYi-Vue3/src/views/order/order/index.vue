<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="订单号" prop="orderNo">
        <el-input
          v-model="queryParams.orderNo"
          placeholder="请输入订单号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="车次号" prop="trainNo">
        <el-input
          v-model="queryParams.trainNo"
          placeholder="请输入车次号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="时刻表ID" prop="scheduleId">
        <el-input
          v-model="queryParams.scheduleId"
          placeholder="请输入时刻表ID"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="出发站" prop="startStation">
        <el-input
          v-model="queryParams.startStation"
          placeholder="请输入出发站"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="到达站" prop="endStation">
        <el-input
          v-model="queryParams.endStation"
          placeholder="请输入到达站"
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
      <el-form-item label="乘客姓名" prop="passengerName">
        <el-input
          v-model="queryParams.passengerName"
          placeholder="请输入乘客姓名"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="身份证号" prop="idCard">
        <el-input
          v-model="queryParams.idCard"
          placeholder="请输入身份证号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="手机号" prop="phone">
        <el-input
          v-model="queryParams.phone"
          placeholder="请输入手机号"
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
      <el-form-item label="座位号" prop="seatNo">
        <el-input
          v-model="queryParams.seatNo"
          placeholder="请输入座位号"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="票价" prop="ticketPrice">
        <el-input
          v-model="queryParams.ticketPrice"
          placeholder="请输入票价"
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
          v-hasPermi="['order:order:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['order:order:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['order:order:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['order:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="主键" align="center" prop="id" />
      <el-table-column label="订单号" align="center" prop="orderNo" />
      <el-table-column label="车次号" align="center" prop="trainNo" />
      <el-table-column label="时刻表ID" align="center" prop="scheduleId" />
      <el-table-column label="出发站" align="center" prop="startStation" />
      <el-table-column label="到达站" align="center" prop="endStation" />
      <el-table-column label="发车日期" align="center" prop="departDate" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.departDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="乘客姓名" align="center" prop="passengerName" />
      <el-table-column label="身份证号" align="center" prop="idCard" />
      <el-table-column label="手机号" align="center" prop="phone" />
      <el-table-column label="车厢号" align="center" prop="carriageNo" />
      <el-table-column label="座位号" align="center" prop="seatNo" />
      <el-table-column label="座位类型" align="center" prop="seatType" />
      <el-table-column label="票价" align="center" prop="ticketPrice" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['order:order:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['order:order:remove']">删除</el-button>
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

    <!-- 添加或修改火车订票对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="orderRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="form.phone" placeholder="请输入手机号" />
        </el-form-item>
        <el-form-item label="车厢号" prop="carriageNo">
          <el-input v-model="form.carriageNo" placeholder="请输入车厢号" />
        </el-form-item>
        <el-form-item label="座位号" prop="seatNo">
          <el-input v-model="form.seatNo" placeholder="请输入座位号" />
        </el-form-item>
        <el-form-item label="票价" prop="ticketPrice">
          <el-input v-model="form.ticketPrice" placeholder="请输入票价" />
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

<script setup name="Order">
import { listOrder, getOrder, delOrder, addOrder, updateOrder } from "@/api/order/order"

const { proxy } = getCurrentInstance()

const orderList = ref([])
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
    orderNo: null,
    trainNo: null,
    scheduleId: null,
    startStation: null,
    endStation: null,
    departDate: null,
    passengerName: null,
    idCard: null,
    phone: null,
    carriageNo: null,
    seatNo: null,
    seatType: null,
    ticketPrice: null,
  },
  rules: {
    orderNo: [
      { required: true, message: "订单号不能为空", trigger: "blur" }
    ],
    trainNo: [
      { required: true, message: "车次号不能为空", trigger: "blur" }
    ],
    passengerName: [
      { required: true, message: "乘客姓名不能为空", trigger: "blur" }
    ],
    idCard: [
      { required: true, message: "身份证号不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询火车订票列表 */
function getList() {
  loading.value = true
  listOrder(queryParams.value).then(response => {
    orderList.value = response.rows
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
    id: null,
    orderNo: null,
    trainNo: null,
    scheduleId: null,
    startStation: null,
    endStation: null,
    departDate: null,
    passengerName: null,
    idCard: null,
    phone: null,
    carriageNo: null,
    seatNo: null,
    seatType: null,
    ticketPrice: null,
    createTime: null
  }
  proxy.resetForm("orderRef")
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
  ids.value = selection.map(item => item.id)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加火车订票"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _id = row.id || ids.value
  getOrder(_id).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改火车订票"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["orderRef"].validate(valid => {
    if (valid) {
      if (form.value.id != null) {
        updateOrder(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addOrder(form.value).then(response => {
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
  const _ids = row.id || ids.value
  proxy.$modal.confirm('是否确认删除火车订票编号为"' + _ids + '"的数据项？').then(function() {
    return delOrder(_ids)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('order/order/export', {
    ...queryParams.value
  }, `order_${new Date().getTime()}.xlsx`)
}

getList()
</script>
