<template>
  <div class="app-container">
    <!-- 查询筛选 -->
    <el-form :model="queryForm" inline class="mb-4">
      <el-form-item label="出发站">
        <el-select v-model="queryForm.startStation" placeholder="请选择" clearable>
          <el-option v-for="station in stationList" :key="station" :label="station" :value="station"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="到达站">
        <el-select v-model="queryForm.endStation" placeholder="请选择" clearable>
          <el-option v-for="station in stationList" :key="station" :label="station" :value="station"></el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="发车日期">
        <el-date-picker
          v-model="queryForm.departDate"
          type="date"
          placeholder="选择日期"
          value-format="YYYY-MM-DD"
          :disabled-date="disabledPastDate"
        ></el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="handleQuery">查询</el-button>
        <el-button @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 车次列表 -->
    <el-table :data="trainList" border stripe v-loading="loading" style="width: 100%">
      <el-table-column prop="trainNo" label="列车号" align="center"></el-table-column>
      <el-table-column prop="trainType" label="列车类型" align="center"></el-table-column>
      <el-table-column prop="startStation" label="出发站" align="center"></el-table-column>
      <el-table-column prop="endStation" label="到达站" align="center"></el-table-column>
      <el-table-column prop="departDate" label="发车日期" align="center"></el-table-column>
      <el-table-column prop="startTime" label="发车时间" align="center"></el-table-column>
      <el-table-column prop="arriveTime" label="到达时间" align="center"></el-table-column>
      <el-table-column prop="totalDuration" label="总时长" align="center"></el-table-column>

      <!-- 二等座 -->
      <el-table-column label="二等座" align="center">
        <template #default="scope">
          <div v-if="getSeatInfo(scope.row, '二等座')">
            剩余：{{ getSeatInfo(scope.row, '二等座').remainSeats }}张<br>
            票价：¥{{ getSeatInfo(scope.row, '二等座').price }}
          </div>
          <div v-else>无</div>
        </template>
      </el-table-column>

      <!-- 一等座 -->
      <el-table-column label="一等座" align="center">
        <template #default="scope">
          <div v-if="getSeatInfo(scope.row, '一等座')">
            剩余：{{ getSeatInfo(scope.row, '一等座').remainSeats }}张<br>
            票价：¥{{ getSeatInfo(scope.row, '一等座').price }}
          </div>
          <div v-else>无</div>
        </template>
      </el-table-column>

      <!-- 查看经停 -->
      <el-table-column label="操作" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" @click="viewStation(scope.row)">查看经停</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 经停弹窗 -->
    <el-dialog v-model="stationDialogVisible" title="经停站点" width="800px">
      <el-table :data="currentStationList" border stripe>
        <el-table-column prop="stationOrder" label="序号" align="center"></el-table-column>
        <el-table-column prop="stationName" label="站点名" align="center"></el-table-column>
        <el-table-column prop="arriveTime" label="到站时间" align="center"></el-table-column>
        <el-table-column prop="departTime" label="发车时间" align="center"></el-table-column>
        <el-table-column prop="stopDuration" label="停靠时长" align="center"></el-table-column>
        <el-table-column prop="mileage" label="累计里程(公里)" align="center"></el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>

<script setup>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { queryTrain, getAllStations } from '@/api/trainquery/onekey'

// 加载状态
const loading = ref(false)
// 车次列表
const trainList = ref([])
// 站点列表
const stationList = ref([])
// 查询表单
const queryForm = reactive({
  startStation: '',
  endStation: '',
  departDate: ''
})
// 弹窗
const stationDialogVisible = ref(false)
const currentStationList = ref([])

// 初始化
onMounted(async () => {
  await loadStations()
  handleQuery()
})

// 加载站点
const loadStations = async () => {
  try {
    const res = await getAllStations()
    stationList.value = res.data
  } catch (e) {
    ElMessage.error('加载站点失败')
  }
}

// 查询车次
const handleQuery = async () => {
  loading.value = true
  try {
    const res = await queryTrain({
      startStation: queryForm.startStation,
      endStation: queryForm.endStation,
      departDate: queryForm.departDate
    })
    trainList.value = res.data
    ElMessage.success('查询成功')
  } catch (e) {
    ElMessage.error('查询失败')
  } finally {
    loading.value = false
  }
}

// 重置
const resetQuery = () => {
  queryForm.startStation = ''
  queryForm.endStation = ''
  queryForm.departDate = ''
  handleQuery()
}

// 禁止过去日期
const disabledPastDate = (time) => {
  return time.getTime() < Date.now() - 8.64e7
}

// 获取座位信息
const getSeatInfo = (row, type) => {
  return row.seatList?.find(item => item.seatType === type)
}

// 查看经停
const viewStation = (row) => {
  currentStationList.value = row.stationList
  stationDialogVisible.value = true
}
</script>