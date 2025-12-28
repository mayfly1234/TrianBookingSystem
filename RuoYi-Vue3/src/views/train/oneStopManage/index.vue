<template>
  <div class="app-container">
    <!-- 第一步：选择列车+车次（核心筛选） -->
    <el-form :inline="true" :model="queryForm" class="query-form mb20">
      <el-form-item label="列车号">
        <el-select v-model="queryForm.trainNo" placeholder="请选择列车号" @change="handleTrainChange">
          <el-option v-for="train in trainList" :key="train.trainNo" :label="train.trainNo" :value="train.trainNo" />
        </el-select>
      </el-form-item>
      <el-form-item label="发车日期">
        <el-date-picker v-model="queryForm.departDate" type="date" placeholder="请选择发车日期" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="loadTrainInfo">查询并加载</el-button>
      </el-form-item>
    </el-form>

    <!-- 第二步：多标签页整合所有信息（选择列车+车次后显示） -->
    <el-tabs v-if="activeTrain" v-model="activeTab" type="border-card" class="mt20">
      <!-- 标签1：列车基础信息 -->
      <el-tab-pane label="列车基础信息" name="basic">
        <train-basic-form :trainInfo="activeTrain" @save="handleBasicSave" />
      </el-tab-pane>
      
      <!-- 标签2：车次信息 -->
      <el-tab-pane label="车次信息" name="schedule">
        <train-schedule-form :scheduleInfo="activeSchedule" @save="handleScheduleSave" />
      </el-tab-pane>
      
      <!-- 标签3：经停站点信息 -->
      <el-tab-pane label="经停站点" name="station">
        <train-station-table :scheduleId="activeSchedule.scheduleId" @save="handleStationSave" />
      </el-tab-pane>
      
      <!-- 标签4：车厢信息 -->
      <el-tab-pane label="车厢信息" name="carriage">
        <train-carriage-table :scheduleId="activeSchedule.scheduleId" @save="handleCarriageSave" />
      </el-tab-pane>
      
      <!-- 标签5：座位信息 -->
      <el-tab-pane label="座位信息" name="seat">
        <train-seat-table :carriageList="activeCarriageList" @save="handleSeatSave" />
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
// 引入各子组件（复用之前的表单/表格组件）
import TrainBasicForm from "@/views/train/basic/components/basicForm.vue";
import TrainScheduleForm from "@/views/train/schedule/components/scheduleForm.vue";
import TrainStationTable from "@/views/train/station/components/stationTable.vue";
import TrainCarriageTable from "@/views/train/carriage/components/carriageTable.vue";
import TrainSeatTable from "@/views/train/seat/components/seatTable.vue";

export default {
  components: {
    TrainBasicForm,
    TrainScheduleForm,
    TrainStationTable,
    TrainCarriageTable,
    TrainSeatTable
  },
  data() {
    return {
      queryForm: { trainNo: "", departDate: "" }, // 筛选条件
      activeTab: "basic", // 当前激活的标签
      activeTrain: null, // 当前选中的列车基础信息
      activeSchedule: null, // 当前选中的车次信息
      activeCarriageList: [], // 当前车次的车厢列表
      trainList: [] // 所有列车号列表
    };
  },
  created() {
    // 初始化：加载所有列车号（用于下拉选择）
    this.loadTrainNoList();
  },
  methods: {
    // 加载所有列车号
    loadTrainNoList() {
      this.$http.get("/train/basic/listAll").then(res => {
        this.trainList = res.rows.map(item => ({ trainNo: item.trainNo }));
      });
    },
    // 选择列车后，加载关联的车次信息
    handleTrainChange() {
      this.activeSchedule = null;
    },
    // 加载列车的所有关联信息
    loadTrainInfo() {
      const { trainNo, departDate } = this.queryForm;
      if (!trainNo || !departDate) {
        this.$message.warning("请选择列车号和发车日期");
        return;
      }
      // 1. 加载列车基础信息
      this.$http.get(`/train/basic/getByNo?trainNo=${trainNo}`).then(res => {
        this.activeTrain = res.data;
      });
      // 2. 加载对应日期的车次信息
      this.$http.get(`/train/schedule/getByNoAndDate?trainNo=${trainNo}&departDate=${departDate}`).then(res => {
        this.activeSchedule = res.data;
        // 3. 加载车次关联的车厢信息
        this.$http.get(`/train/carriage/list?scheduleId=${this.activeSchedule.scheduleId}`).then(res => {
          this.activeCarriageList = res.rows;
        });
      });
    },
    // 保存基础信息（同步更新）
    handleBasicSave() {
      this.$message.success("列车基础信息保存成功");
    },
    // 保存车次信息（同步更新）
    handleScheduleSave() {
      this.$message.success("车次信息保存成功");
    },
    // 其他标签的保存方法...
    handleStationSave() {},
    handleCarriageSave() {},
    handleSeatSave() {}
  }
};
</script>