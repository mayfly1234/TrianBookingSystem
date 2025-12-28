<template>
  <div class="train-book-container">
    <!-- 车次查询区域 -->
    <el-card title="车次查询" shadow="hover" class="mb-4">
      <el-form 
        :model="queryForm" 
        inline 
        @submit.prevent="handleQuery"
        class="query-form"
      >
        <el-form-item label="出发站" required>
          <el-input
            v-model="queryForm.startStation"
            placeholder="请输入出发站（如：北京南站）"
            style="width: 180px"
            clearable
            @input="clearEmptyValue"
          />
        </el-form-item>
        <el-form-item label="到达站" required>
          <el-input
            v-model="queryForm.endStation"
            placeholder="请输入到达站（如：上海虹桥站）"
            style="width: 180px"
            clearable
            @input="clearEmptyValue"
          />
        </el-form-item>
        <el-form-item label="发车日期" required>
          <el-date-picker
            v-model="queryForm.departDate"
            type="date"
            placeholder="选择发车日期"
            style="width: 180px"
            value-format="YYYY-MM-DD"
            :default-value="new Date()"
            @change="clearEmptyValue"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleQuery">查询车次</el-button>
          <el-button @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>

      <!-- 车次列表 -->
      <el-table
        :data="trainList"
        border
        stripe
        v-loading="loading"
        @row-click="selectTrain"
        empty-text="暂无符合条件的车次数据"
        style="width: 100%"
      >
        <el-table-column prop="trainNo" label="车次号" width="100" />
        <el-table-column prop="startStation" label="出发站" width="120" />
        <el-table-column prop="endStation" label="到达站" width="120" />
        <el-table-column prop="startTime" label="发车时间" width="120" />
        <el-table-column prop="arriveTime" label="到达时间" width="120" />
        <el-table-column prop="totalMileage" label="里程(公里)" width="100" />
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button
              type="primary"
              size="small"
              @click="chooseTrain(scope.row)"
            >
              选择
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>

    <!-- 订票信息填写区域 -->
    <el-card
      title="订票信息填写"
      shadow="hover"
      v-if="selectedTrain"
      class="book-form-card"
    >
      <el-form
        :model="bookForm"
        :rules="bookRules"
        ref="bookFormRef"
        label-width="100px"
        label-position="right"
      >
        <!-- 车次基础信息（只读） -->
        <el-form-item label="选中车次">
          <el-input v-model="bookForm.trainNo" disabled />
        </el-form-item>
        <el-form-item label="行程信息">
          <el-input
            v-model="bookForm.route"
            disabled
            placeholder="如：北京南站 → 上海虹桥站 2025-12-28"
          />
        </el-form-item>

        <!-- 乘客信息 -->
        <el-form-item label="乘客姓名" prop="passengerName">
          <el-input
            v-model="bookForm.passengerName"
            placeholder="请输入乘客真实姓名"
            maxlength="10"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="身份证号" prop="idCard">
          <el-input
            v-model="bookForm.idCard"
            placeholder="请输入18位身份证号"
            maxlength="18"
            show-word-limit
          />
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input
            v-model="bookForm.phone"
            placeholder="请输入11位手机号"
            maxlength="11"
            show-word-limit
          />
        </el-form-item>

        <!-- 座位信息 -->
        <el-form-item label="车厢号" prop="carriageNo">
          <el-input
            v-model="bookForm.carriageNo"
            placeholder="如：01、02"
            maxlength="2"
          />
        </el-form-item>
        <el-form-item label="座位号" prop="seatNo">
          <el-input
            v-model="bookForm.seatNo"
            placeholder="如：01A、05B"
            maxlength="3"
          />
        </el-form-item>
        <el-form-item label="座位类型" prop="seatType">
          <el-select v-model="bookForm.seatType" placeholder="请选择座位类型">
            <el-option label="二等座" value="二等座" />
            <el-option label="一等座" value="一等座" />
            <el-option label="商务座" value="商务座" />
          </el-select>
        </el-form-item>

        <!-- 票价（自动计算） -->
        <el-form-item label="票价(元)">
          <el-input v-model="bookForm.ticketPrice" disabled />
        </el-form-item>

        <!-- 提交按钮 -->
        <el-form-item style="margin-left: 100px">
          <el-button type="primary" @click="handleSubmit">提交订票</el-button>
          <el-button @click="resetBookForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
// 引入API层
import { queryTrainList, submitBookInfo } from '@/api/train'

// ===================== 响应式数据 =====================
// 查询表单
const queryForm = ref({
  startStation: '',
  endStation: '',
  departDate: ''
})

// 加载状态
const loading = ref(false)

// 车次列表
const trainList = ref([])

// 选中的车次
const selectedTrain = ref(null)

// 订票表单
const bookForm = reactive({
  trainNo: '',
  scheduleId: '',
  route: '',
  passengerName: '',
  idCard: '',
  phone: '',
  carriageNo: '',
  seatNo: '',
  seatType: '',
  ticketPrice: '',
  startStation: '',
  endStation: '',
  departDate: ''
})

// 表单校验规则
const bookRules = ref({
  passengerName: [
    { required: true, message: '请输入乘客姓名', trigger: 'blur' },
    { min: 2, max: 10, message: '姓名长度需在2-10个字符之间', trigger: 'blur' }
  ],
  idCard: [
    { required: true, message: '请输入身份证号', trigger: 'blur' },
    {
      pattern: /^[1-9]\d{5}(18|19|20)\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$/,
      message: '请输入正确的18位身份证号',
      trigger: 'blur'
    }
  ],
  phone: [
    { required: true, message: '请输入手机号', trigger: 'blur' },
    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的11位手机号', trigger: 'blur' }
  ],
  carriageNo: [
    { required: true, message: '请输入车厢号', trigger: 'blur' },
    { pattern: /^[0-9]{1,2}$/, message: '车厢号为1-2位数字', trigger: 'blur' }
  ],
  seatNo: [
    { required: true, message: '请输入座位号', trigger: 'blur' },
    { pattern: /^[0-9]{1,2}[A-Za-z]$/, message: '座位号格式如：01A、05B', trigger: 'blur' }
  ],
  seatType: [
    { required: true, message: '请选择座位类型', trigger: 'change' }
  ]
})

// 表单Ref
const bookFormRef = ref(null)

// ===================== 监听事件 =====================
// 监听座位类型变化，自动计算票价
watch(
  () => bookForm.seatType,
  (newVal) => {
    if (selectedTrain.value && newVal) {
      // 票价倍率
      const priceRate = {
        '二等座': 1,
        '一等座': 1.5,
        '商务座': 3
      }
      // 计算票价并保留2位小数
      bookForm.ticketPrice = (selectedTrain.value.totalMileage * priceRate[newVal]).toFixed(2)
    }
  },
  { immediate: true } // 立即执行
)

// 清空空字符串（避免传''导致后端识别为null）
const clearEmptyValue = () => {
  Object.keys(queryForm.value).forEach(key => {
    if (queryForm.value[key] === '') {
      queryForm.value[key] = undefined
    }
  })
}

// ===================== 核心方法 =====================
// 初始化页面
onMounted(() => {
  // 设置默认日期为今日
  const today = new Date().toISOString().split('T')[0]
  queryForm.value.departDate = today
  console.log('【页面初始化】默认日期：', today)
})

// 重置查询表单
const resetQuery = () => {
  queryForm.value = {
    startStation: '',
    endStation: '',
    departDate: new Date().toISOString().split('T')[0]
  }
  trainList.value = []
  selectedTrain.value = null
  ElMessage.info('查询条件已重置')
}

// 处理车次查询
const handleQuery = async () => {
  console.log('【触发查询】查询参数：', queryForm.value)
  
  // 基础非空校验
  if (!queryForm.value.startStation) {
    ElMessage.warning('请输入出发站！')
    return
  }
  if (!queryForm.value.endStation) {
    ElMessage.warning('请输入到达站！')
    return
  }
  if (!queryForm.value.departDate) {
    ElMessage.warning('请选择发车日期！')
    return
  }

  // 开始加载
  loading.value = true
  try {
    // 调用API查询车次
    const res = await queryTrainList(queryForm.value)
    console.log('【查询结果】：', res)
    
    // 若依统一返回格式判断
    if (res.code === 200) {
      trainList.value = res.data || []
      if (trainList.value.length === 0) {
        ElMessage.info('未查询到符合条件的车次，请检查查询条件！')
      } else {
        ElMessage.success(`查询成功，共找到 ${trainList.value.length} 趟车次`)
      }
    } else {
      ElMessage.error(`查询失败：${res.msg || '接口返回异常'}`)
    }
  } catch (error) {
    // 捕获所有异常（网络/接口/解析错误）
    console.error('【查询异常】：', error)
    ElMessage.error(`查询异常：${error.message || '网络请求失败'}`)
  } finally {
    // 结束加载
    loading.value = false
  }
}

// 选择车次（行点击）
const selectTrain = (row) => {
  chooseTrain(row)
}

// 选择车次（按钮点击）
const chooseTrain = (row) => {
  console.log('【选择车次】：', row)
  selectedTrain.value = row
  
  // 填充订票表单基础信息
  bookForm.trainNo = row.trainNo || ''
  bookForm.scheduleId = row.scheduleId || ''
  bookForm.startStation = row.startStation || ''
  bookForm.endStation = row.endStation || ''
  bookForm.departDate = row.departDate || ''
  bookForm.route = `${row.startStation || ''} → ${row.endStation || ''} ${row.departDate || ''}`
  
  // 初始化票价（默认二等座）
  bookForm.seatType = '二等座'
  bookForm.ticketPrice = row.totalMileage ? (row.totalMileage * 1).toFixed(2) : ''
  
  ElMessage.info(`已选择车次：${row.trainNo}`)
}

// 重置订票表单
const resetBookForm = () => {
  if (bookFormRef.value) {
    bookFormRef.value.resetFields()
  }
  
  // 保留车次基础信息
  bookForm.trainNo = selectedTrain.value?.trainNo || ''
  bookForm.scheduleId = selectedTrain.value?.scheduleId || ''
  bookForm.route = selectedTrain.value ? `${selectedTrain.value.startStation} → ${selectedTrain.value.endStation} ${selectedTrain.value.departDate}` : ''
  bookForm.startStation = selectedTrain.value?.startStation || ''
  bookForm.endStation = selectedTrain.value?.endStation || ''
  bookForm.departDate = selectedTrain.value?.departDate || ''
  
  // 重置票价和座位类型
  bookForm.seatType = '二等座'
  bookForm.ticketPrice = selectedTrain.value?.totalMileage ? (selectedTrain.value.totalMileage * 1).toFixed(2) : ''
  
  ElMessage.info('订票表单已重置')
}

// 处理订票提交
const handleSubmit = async () => {
  if (!bookFormRef.value) return
  
  try {
    // 表单校验
    await bookFormRef.value.validate()
    
    // 确认提交
    await ElMessageBox.confirm(
      '您确定要提交订票信息吗？提交后将生成订单并写入数据库！',
      '订票确认',
      {
        confirmButtonText: '确认提交',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )
    
    // 组装提交数据
    const submitData = {
      passenger: {
        passengerName: bookForm.passengerName,
        idCard: bookForm.idCard,
        phone: bookForm.phone
      },
      order: {
        trainNo: bookForm.trainNo,
        scheduleId: bookForm.scheduleId,
        startStation: bookForm.startStation,
        endStation: bookForm.endStation,
        departDate: bookForm.departDate,
        carriageNo: bookForm.carriageNo,
        seatNo: bookForm.seatNo,
        seatType: bookForm.seatType,
        ticketPrice: bookForm.ticketPrice
      }
    }
    
    // 调用API提交订票
    const res = await submitBookInfo(submitData)
    console.log('【提交结果】：', res)
    
    if (res.code === 200) {
      ElMessage.success(`订票成功！您的订单号是：${res.data.orderNo || '未知'}`)
      // 重置所有表单
      resetQuery()
      resetBookForm()
    } else {
      ElMessage.error(`订票失败：${res.msg || '提交数据异常'}`)
    }
  } catch (error) {
    // 取消提交不报错
    if (error.type !== 'cancel') {
      console.error('【提交异常】：', error)
      ElMessage.error(`提交失败：${error.message || '系统异常，请稍后重试'}`)
    }
  }
}
</script>

<style scoped>
.train-book-container {
  padding: 20px;
  background-color: #f5f7fa;
  min-height: calc(100vh - 60px);
}

.query-form {
  margin-bottom: 16px;
}

.book-form-card {
  margin-top: 20px;
}

.mb-4 {
  margin-bottom: 16px !important;
}
</style>