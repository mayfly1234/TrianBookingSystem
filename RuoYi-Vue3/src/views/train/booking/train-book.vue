<template>
  <div class="train-book-simple">
    <!-- 1. 车次查询区（复用一站式查询逻辑） -->
    <el-card title="车次查询">
      <el-form :model="queryForm" inline @submit.prevent="queryTrain">
        <el-form-item label="出发站">
          <el-input v-model="queryForm.startStation" placeholder="如：北京南站"></el-input>
        </el-form-item>
        <el-form-item label="到达站">
          <el-input v-model="queryForm.endStation" placeholder="如：上海虹桥站"></el-input>
        </el-form-item>
        <el-form-item label="发车日期">
          <el-date-picker v-model="queryForm.departDate" type="date" placeholder="选择日期"></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="queryTrain">查询</el-button>
        </el-form-item>
      </el-form>

      <!-- 查询结果 -->
      <el-table :data="trainList" border style="margin-top:10px" @row-click="selectTrain">
        <el-table-column prop="trainNo" label="车次" />
        <el-table-column prop="startStation" label="出发站" />
        <el-table-column prop="endStation" label="到达站" />
        <el-table-column prop="startTime" label="发车时间" />
        <el-table-column label="操作">
          <template #default="scope">
            <el-button type="primary" size="small" @click="chooseTrain(scope.row)">选择该车次</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 2. 订票信息填写区（选中车次后显示） -->
    <el-card title="订票信息" v-if="selectedTrain" style="margin-top:20px">
      <el-form :model="bookForm" :rules="bookRules" ref="bookRef" label-width="80px">
        <!-- 车次信息（只读） -->
        <el-form-item label="选中车次">
          <el-input v-model="bookForm.trainNo" disabled />
        </el-form-item>
        <el-form-item label="行程信息">
          <el-input v-model="bookForm.route" disabled />
        </el-form-item>

        <!-- 乘客信息 -->
        <el-form-item label="乘客姓名" prop="passengerName">
          <el-input v-model="bookForm.passengerName" />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input v-model="bookForm.idCard" />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="bookForm.phone" />
        </el-form-item>

        <!-- 座位信息（简化：手动输入，无需可视化） -->
        <el-form-item label="车厢号" prop="carriageNo">
          <el-input v-model="bookForm.carriageNo" placeholder="如：01" />
        </el-form-item>
        <el-form-item label="座位号" prop="seatNo">
          <el-input v-model="bookForm.seatNo" placeholder="如：01A" />
        </el-form-item>
        <el-form-item label="座位类型" prop="seatType">
          <el-select v-model="bookForm.seatType">
            <el-option label="二等座" value="二等座"></el-option>
            <el-option label="一等座" value="一等座"></el-option>
          </el-select>
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item>
          <el-button type="primary" @click="submitBook">提交订票</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive } from 'vue'
import { ElMessage } from 'element-plus'
import axios from 'axios'

// 1. 查询表单
const queryForm = ref({
  startStation: '',
  endStation: '',
  departDate: ''
})

// 2. 车次列表
const trainList = ref([])

// 3. 选中的车次
const selectedTrain = ref(null)

// 4. 订票表单
const bookForm = reactive({
  trainNo: '',        // 车次号
  scheduleId: '',     // 车次ID
  route: '',          // 行程信息
  passengerName: '',  // 乘客姓名
  idCard: '',         // 身份证号
  phone: '',          // 手机号
  carriageNo: '',     // 车厢号
  seatNo: '',         // 座位号
  seatType: '',       // 座位类型
  startStation: '',   // 出发站
  endStation: '',     // 到达站
  departDate: ''      // 发车日期
})

// 5. 表单校验规则
const bookRules = ref({
  passengerName: [{ required: true, message: '请输入乘客姓名', trigger: 'blur' }],
  idCard: [{ required: true, message: '请输入身份证号', trigger: 'blur' }],
  phone: [{ pattern: /^1[3-9]\d{9}$/, message: '请输入正确手机号', trigger: 'blur' }],
  carriageNo: [{ required: true, message: '请输入车厢号', trigger: 'blur' }],
  seatNo: [{ required: true, message: '请输入座位号', trigger: 'blur' }],
  seatType: [{ required: true, message: '请选择座位类型', trigger: 'change' }]
})

// 6. 表单Ref
const bookRef = ref(null)

// ========== 核心方法 ==========
// 1. 查询车次（复用一站式查询接口）
const queryTrain = () => {
  if (!queryForm.value.startStation || !queryForm.value.endStation || !queryForm.value.departDate) {
    ElMessage.warning('请填写完整查询条件')
    return
  }
  axios.get('/trainquery/onekey/query', { params: queryForm.value })
    .then(res => {
      if (res.data.code === 200) {
        trainList.value = res.data.data
      } else {
        ElMessage.error(res.data.msg)
      }
    })
    .catch(err => {
      ElMessage.error('查询车次失败：' + err.message)
    })
}

// 2. 选择车次，填充基础信息
const chooseTrain = (row) => {
  selectedTrain.value = row
  // 填充订票表单基础信息
  bookForm.trainNo = row.trainNo
  bookForm.scheduleId = row.scheduleId
  bookForm.route = `${row.startStation} → ${row.endStation} ${row.departDate}`
  bookForm.startStation = row.startStation
  bookForm.endStation = row.endStation
  bookForm.departDate = row.departDate
}

// 3. 提交订票（核心：入库乘客+订单数据）
const submitBook = () => {
  bookRef.value.validate((valid) => {
    if (!valid) return

    // 组装提交数据
    const submitData = {
      // 乘客信息
      passenger: {
        passengerName: bookForm.passengerName,
        idCard: bookForm.idCard,
        phone: bookForm.phone
      },
      // 订单信息
      order: {
        trainNo: bookForm.trainNo,
        scheduleId: bookForm.scheduleId,
        startStation: bookForm.startStation,
        endStation: bookForm.endStation,
        departDate: bookForm.departDate,
        carriageNo: bookForm.carriageNo,
        seatNo: bookForm.seatNo,
        seatType: bookForm.seatType,
        // 简化：票价按里程计算（二等座1元/公里，一等座1.5元/公里）
        ticketPrice: selectedTrain.value.totalMileage * (bookForm.seatType === '二等座' ? 1 : 1.5)
      }
    }

    // 调用后端接口入库
    axios.post('/trainbook/simple/submit', submitData)
      .then(res => {
        if (res.data.code === 200) {
          ElMessage.success('订票成功！订单号：' + res.data.data.orderNo)
          // 重置表单
          resetForm()
        } else {
          ElMessage.error(res.data.msg)
        }
      })
      .catch(err => {
        ElMessage.error('订票失败：' + err.message)
      })
  })
}

// 重置表单
const resetForm = () => {
  bookRef.value.resetFields()
  selectedTrain.value = null
  trainList.value = []
  queryForm.value = {
    startStation: '',
    endStation: '',
    departDate: ''
  }
}
</script>

<style scoped>
.train-book-simple {
  padding: 20px;
}
</style>