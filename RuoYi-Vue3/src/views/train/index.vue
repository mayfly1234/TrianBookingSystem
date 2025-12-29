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
        <!-- 出发站：下拉框 -->
        <el-form-item label="出发站" required>
          <el-select
            v-model="queryForm.startStation"
            placeholder="请选择出发站"
            style="width: 180px"
            clearable
          >
            <el-option v-for="station in stationList" :key="station" :label="station" :value="station"></el-option>
          </el-select>
        </el-form-item>
        <!-- 到达站：下拉框 -->
        <el-form-item label="到达站" required>
          <el-select
            v-model="queryForm.endStation"
            placeholder="请选择到达站"
            style="width: 180px"
            clearable
          >
            <el-option v-for="station in stationList" :key="station" :label="station" :value="station"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="发车日期" required>
          <el-date-picker
            v-model="queryForm.departDate"
            type="date"
            placeholder="选择发车日期"
            style="width: 180px"
            value-format="YYYY-MM-DD"
            :default-value="new Date()"
            :disabled-date="disabledPastDate"
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
        <!-- 新增：展示二等座票价 -->
        <el-table-column label="二等座票价" width="100">
          <template #default="scope">
            {{ getSeatInfo(scope.row, '二等座')?.price || '-' }}
          </template>
        </el-table-column>
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
          <el-select v-model="bookForm.seatType" placeholder="请选择座位类型" @change="updateTicketPrice">
            <el-option label="二等座" value="二等座" />
            <el-option label="一等座" value="一等座" />
            <el-option label="商务座" value="商务座" />
          </el-select>
        </el-form-item>

        <!-- 票价：从后端seatList读取 -->
        <el-form-item label="票价(元)">
          <el-input v-model="bookForm.ticketPrice" disabled />
        </el-form-item>

        <!-- 提交按钮：修复禁用条件，新增scheduleId校验 -->
        <el-form-item style="margin-left: 100px">
          <el-button 
            type="primary" 
            @click="handleSubmit"
            :disabled="!selectedTrain || !bookForm.ticketPrice || bookForm.ticketPrice === '' || !bookForm.scheduleId"
            :loading="submitLoading"
          >
            提交订票
          </el-button>
          <el-button @click="resetBookForm">重置</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script setup>
import { ref, reactive, watch, onMounted } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
// 引入正确的车次查询/站点API（和能正常显示票价的组件一致）
import { queryTrain, getAllStations } from '@/api/trainquery/onekey'
// 订票提交API
import { submitBookInfo } from '@/api/train'

// ===================== 响应式数据 =====================
// 加载状态
const loading = ref(false)
const submitLoading = ref(false)
// 站点列表（从后端加载）
const stationList = ref([])
// 查询表单
const queryForm = reactive({
  startStation: '',
  endStation: '',
  departDate: ''
})
// 车次列表（包含seatList）
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

// ===================== 核心方法 =====================
// 初始化：加载站点+默认日期
onMounted(async () => {
  await loadStations()
  // 设置默认日期为今日
  queryForm.departDate = new Date().toISOString().split('T')[0]
})

// 加载站点列表
const loadStations = async () => {
  try {
    const res = await getAllStations()
    stationList.value = res.data || []
  } catch (e) {
    ElMessage.error('加载站点失败：' + (e.message || '网络异常'))
  }
}

// 禁止选择过去的日期
const disabledPastDate = (time) => {
  return time.getTime() < Date.now() - 8.64e7 // 8.64e7 = 24*60*60*1000（一天毫秒数）
}

// 获取座位信息（修复：解析JSON字符串，兼容后端返回格式）
const getSeatInfo = (row, type) => {
  if (!row || !row.seatList) return null
  // 解析后端返回的JSON字符串为数组
  let seatList = []
  try {
    seatList = typeof row.seatList === 'string' ? JSON.parse(row.seatList) : row.seatList
  } catch (e) {
    console.error('【解析座位信息失败】：', e, '原始数据：', row.seatList)
    return null
  }
  return seatList.find(item => item.seatType === type)
}

// 更新票价（核心：从后端seatList读取，避免空值）
const updateTicketPrice = () => {
  if (!selectedTrain.value || !bookForm.seatType) {
    bookForm.ticketPrice = ''
    return
  }
  const seatInfo = getSeatInfo(selectedTrain.value, bookForm.seatType)
  // 确保票价是字符串（避免数字类型直接赋值），无值则置空
  bookForm.ticketPrice = seatInfo?.price ? String(seatInfo.price) : ''
  
  // 无票价时提示
  if (!bookForm.ticketPrice) {
    ElMessage.warning(`该车次暂无【${bookForm.seatType}】票价，请选择其他座位类型或车次`)
  }
}

// 处理车次查询（新增：打印后端返回的ID字段，便于排查）
const handleQuery = async () => {
  // 基础校验
  if (!queryForm.startStation) {
    ElMessage.warning('请选择出发站！')
    return
  }
  if (!queryForm.endStation) {
    ElMessage.warning('请选择到达站！')
    return
  }
  if (!queryForm.departDate) {
    ElMessage.warning('请选择发车日期！')
    return
  }

  loading.value = true
  try {
    const res = await queryTrain({
      startStation: queryForm.startStation,
      endStation: queryForm.endStation,
      departDate: queryForm.departDate
    })
    trainList.value = res.data || []
    
    // 调试：打印后端返回的车次ID字段信息
    if (trainList.value.length > 0) {
      const firstTrain = trainList.value[0]
      console.log('【后端返回的车次字段信息】：', {
        scheduleId: firstTrain.scheduleId,        // 驼峰字段
        schedule_id: firstTrain.schedule_id,      // 下划线字段
        hasScheduleId: 'scheduleId' in firstTrain,
        hasScheduleIdUnderline: 'schedule_id' in firstTrain,
        totalMileage: firstTrain.totalMileage     // 里程字段
      })
    }

    if (trainList.value.length === 0) {
      ElMessage.info('未查询到符合条件的车次，请检查查询条件！')
    } else {
      ElMessage.success(`查询成功，共找到 ${trainList.value.length} 趟车次`)
    }
  } catch (error) {
    console.error('【查询异常】：', error)
    ElMessage.error(`查询失败：${error.message || '网络请求异常'}`)
  } finally {
    loading.value = false
  }
}

// 重置查询表单
const resetQuery = () => {
  queryForm.startStation = ''
  queryForm.endStation = ''
  queryForm.departDate = new Date().toISOString().split('T')[0]
  trainList.value = []
  selectedTrain.value = null
  // 清空订票表单
  resetBookForm()
  ElMessage.info('查询条件已重置')
}

// 选择车次（行点击）
const selectTrain = (row) => {
  chooseTrain(row)
}

// 选择车次（按钮点击：核心修复scheduleId取值逻辑）
const chooseTrain = (row) => {
  // 调试：打印原始数据，定位ID字段问题
  console.log('【选择车次-原始数据】：', row)
  console.log('【车次ID字段检查】：', {
    scheduleId: row.scheduleId,
    schedule_id: row.schedule_id
  })

  if (!row) {
    ElMessage.error('车次数据异常，无法选择！')
    return
  }
  
  selectedTrain.value = row
  // 填充基础信息，兜底空值
  bookForm.trainNo = row.trainNo || ''
  
  // 核心修复：兼容驼峰/下划线字段 + 仅过滤null/undefined（不过滤数字0）
  const scheduleId = row.scheduleId ?? row.schedule_id ?? ''
  bookForm.scheduleId = scheduleId === null || scheduleId === undefined ? '' : String(scheduleId)
  
  bookForm.startStation = row.startStation || ''
  bookForm.endStation = row.endStation || ''
  bookForm.departDate = row.departDate || ''
  bookForm.route = `${row.startStation || ''} → ${row.endStation || ''} ${row.departDate || ''}`
  
  // 初始化座位类型和票价
  bookForm.seatType = '二等座'
  updateTicketPrice()

  // 优化提示：明确说明ID异常原因
  if (!bookForm.scheduleId) {
    ElMessage.warning('⚠️ 车次ID为空！可能原因：\n1. 后端未返回scheduleId/schedule_id字段\n2. 字段值为null/undefined')
  } else {
    ElMessage.success(`已选择车次：${row.trainNo || '未知'}（ID：${bookForm.scheduleId}）`)
  }
}

// 重置订票表单（修复：scheduleId赋值逻辑）
const resetBookForm = () => {
  if (bookFormRef.value) {
    bookFormRef.value.resetFields()
  }
  
  // 保留车次基础信息（兜底空值）
  bookForm.trainNo = selectedTrain.value?.trainNo || ''
  
  // 修复：兼容驼峰/下划线字段 + 不过滤数字0
  const scheduleId = selectedTrain.value?.scheduleId ?? selectedTrain.value?.schedule_id ?? ''
  bookForm.scheduleId = scheduleId === null || scheduleId === undefined ? '' : String(scheduleId)
  
  bookForm.route = selectedTrain.value ? `${selectedTrain.value.startStation || ''} → ${selectedTrain.value.endStation || ''} ${selectedTrain.value.departDate || ''}` : ''
  bookForm.startStation = selectedTrain.value?.startStation || ''
  bookForm.endStation = selectedTrain.value?.endStation || ''
  bookForm.departDate = selectedTrain.value?.departDate || ''
  
  // 重置座位和票价
  bookForm.seatType = '二等座'
  updateTicketPrice()
  
  ElMessage.info('订票表单已重置')
}

// 处理订票提交（核心修复：精准校验scheduleId）
const handleSubmit = async () => {
  if (!bookFormRef.value) return
  submitLoading.value = true

  try {
    // 1. 表单基础校验
    await bookFormRef.value.validate()
    
    // 2. 核心兜底校验（避免空字符串提交）
    if (!selectedTrain.value) {
      throw new Error('请先选择有效车次！')
    }
    
    // 精准校验：排除空字符串、'undefined'、'null'
    if (['', 'undefined', 'null'].includes(bookForm.scheduleId)) {
      throw new Error('车次ID为空/无效！请重新选择车次（或联系后端检查接口返回）')
    }
    
    if (!bookForm.ticketPrice || bookForm.ticketPrice === '') {
      throw new Error('票价为空，无法提交！请重新选择座位类型')
    }
    
    // 校验票价是否为有效数字
    const priceNum = Number(bookForm.ticketPrice)
    if (isNaN(priceNum) || priceNum <= 0) {
      throw new Error(`票价格式错误：${bookForm.ticketPrice}（必须是大于0的数字）`)
    }

    // 3. 确认提交
    await ElMessageBox.confirm(
      '您确定要提交订票信息吗？提交后将生成订单并写入数据库！',
      '订票确认',
      {
        confirmButtonText: '确认提交',
        cancelButtonText: '取消',
        type: 'warning'
      }
    )
    
    // 4. 组装提交数据（确保字段类型正确，兜底空值）
    const submitData = {
      passenger: {
        passengerName: bookForm.passengerName || '',
        idCard: bookForm.idCard || '',
        phone: bookForm.phone || ''
      },
      order: {
        trainNo: bookForm.trainNo || '',
        // scheduleId转字符串/数字（根据后端要求，这里转字符串兜底）
        scheduleId: bookForm.scheduleId || '',
        startStation: bookForm.startStation || '',
        endStation: bookForm.endStation || '',
        departDate: bookForm.departDate || '',
        carriageNo: bookForm.carriageNo || '',
        seatNo: bookForm.seatNo || '',
        seatType: bookForm.seatType || '',
        // 票价转为数字（后端期望数字类型，避免空字符串）
        ticketPrice: priceNum
      }
    }

    // 调试：打印提交数据（方便排查）
    console.log('【最终提交数据】：', JSON.stringify(submitData, null, 2))
    
    // 5. 调用API提交
    const res = await submitBookInfo(submitData)
    if (res.code === 200) {
      ElMessage.success(`订票成功！您的订单号是：${res.data.orderNo || '未知'}`)
      // 重置所有表单
      resetQuery()
    } else {
      throw new Error(res.msg || '订票失败，后端返回异常')
    }
  } catch (error) {
    // 取消提交不报错
    if (error.type !== 'cancel') {
      console.error('【提交异常】：', error)
      ElMessage.error(`提交失败：${error.message || '系统异常，请稍后重试'}`)
    }
  } finally {
    submitLoading.value = false
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