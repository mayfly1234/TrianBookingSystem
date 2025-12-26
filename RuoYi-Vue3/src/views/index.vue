<template>
  <div class="app-container home">
    <el-row :gutter="20">
      <el-col :sm="24" :lg="24">
        <div class="page-title">
          <h1>铁路客票订票系统</h1>
          <p>便捷购票 · 安心出行</p>
        </div>
        <hr /> 
      </el-col>
    </el-row>

    <el-row :gutter="20">
      <el-col :sm="24" :lg="12" style="padding-left: 20px">
        <el-card class="ticket-search-card">
          <template v-slot:header>
            <span class="card-header">车票查询</span>
          </template>
          <el-form :inline="true" :model="searchForm" class="search-form">
            <el-form-item label="出发地">
              <el-input v-model="searchForm.from" placeholder="请输入出发站（如：北京）"></el-input>
            </el-form-item>
            <el-form-item label="目的地">
              <el-input v-model="searchForm.to" placeholder="请输入到达站（如：上海）"></el-input>
            </el-form-item>
            <el-form-item label="出发日期">
              <el-date-picker
                v-model="searchForm.date"
                type="date"
                placeholder="选择出发日期"
                format="YYYY-MM-DD"
                value-format="YYYY-MM-DD"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="车次类型">
              <el-select v-model="searchForm.trainType" placeholder="选择车次类型">
                <el-option label="全部" value="all"></el-option>
                <el-option label="高铁(G)" value="G"></el-option>
                <el-option label="动车(D)" value="D"></el-option>
                <el-option label="特快(T)" value="T"></el-option>
                <el-option label="快速(K)" value="K"></el-option>
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" icon="el-icon-search" @click="searchTicket">查询车票</el-button>
            </el-form-item>
          </el-form>
        </el-card>
      </el-col>
    </el-row>

    <!-- 功能入口模块（适配你模板的布局结构） -->
    <el-row :gutter="20" style="margin-top: 20px; padding-left: 20px;">
      <el-col :xs="12" :sm="8" :md="6" :lg="4" v-for="(item, index) in functionList" :key="index">
        <el-card class="function-card">
          <div class="function-item" @click="goFunction(item.path)">
            <i :class="item.icon"></i>
            <span>{{ item.name }}</span>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script setup name="Index">
import { ref, onMounted, onActivated } from 'vue'
import { ElMessage } from 'element-plus'

const version = ref('3.8.7')

const searchForm = ref({
  from: '',   
  to: '',     
  date: '',    
  trainType: 'all' 
})

const functionList = ref([
  { name: '车票预订', icon: 'el-icon-tickets', path: '/ticket/book' },
  { name: '订单查询', icon: 'el-icon-document', path: '/order/query' },
  { name: '退票改签', icon: 'el-icon-refresh-left', path: '/ticket/refund' },
  { name: '乘客管理', icon: 'el-icon-user', path: '/passenger/manage' },
  { name: '行程规划', icon: 'el-icon-map-location', path: '/trip/plan' },
  { name: '公告信息', icon: 'el-icon-bell', path: '/notice' }
])

// 4. 初始化方法（修复返回首页空白问题）
const initPage = () => {
  // 重置查询表单（每次进入首页清空输入）
  searchForm.value = {
    from: '',
    to: '',
    date: '',
    trainType: 'all'
  }
}


onMounted(() => {
  initPage()
})

onActivated(() => {
  initPage()
})

function goTarget(url) {
  window.open(url, '__blank')
}

const searchTicket = () => {
  if (!searchForm.value.from || !searchForm.value.to || !searchForm.value.date) {
    ElMessage.warning('请填写出发地、目的地和出发日期！')
    return
  }
  ElMessage.success(`查询 ${searchForm.value.from} → ${searchForm.value.to} ${searchForm.value.date} 的车票`)
}


const goFunction = (path) => {
  ElMessage.info(`前往功能：${path}（可在此处添加路由跳转逻辑）`)
}
</script>

<style scoped lang="scss">
.home {
  blockquote {
    padding: 10px 20px;
    margin: 0 0 20px;
    font-size: 17.5px;
    border-left: 5px solid #eee;
  }
  hr {
    margin-top: 20px;
    margin-bottom: 20px;
    border: 0;
    border-top: 1px solid #eee;
  }
  .col-item {
    margin-bottom: 20px;
  }

  ul {
    padding: 0;
    margin: 0;
  }

  font-family: "open sans", "Helvetica Neue", Helvetica, Arial, sans-serif;
  font-size: 13px;
  color: #676a6c;
  overflow-x: hidden;

  ul {
    list-style-type: none;
  }

  h4 {
    margin-top: 0px;
  }

  h2 {
    margin-top: 10px;
    font-size: 26px;
    font-weight: 100;
  }

  p {
    margin-top: 10px;

    b {
      font-weight: 700;
    }
  }

  .update-log {
    ol {
      display: block;
      list-style-type: decimal;
      margin-block-start: 1em;
      margin-block-end: 1em;
      margin-inline-start: 0;
      margin-inline-end: 0;
      padding-inline-start: 40px;
    }
  }

  padding: 20px;
  background-color: #f5f5f5;
  min-height: calc(100vh - 60px);

  
  .page-title {
    text-align: center;
    margin-bottom: 10px;
    h1 {
      color: #d92121; // 12306红色主色调
      font-size: 32px;
      margin: 0;
    }
    p {
      color: #666;
      font-size: 16px;
      margin-top: 8px;
    }
  }

  // 车票查询卡片样式
  .ticket-search-card {
    margin-bottom: 15px;
    .card-header {
      font-size: 18px;
      font-weight: 500;
      color: #333;
    }
    .search-form {
      padding: 10px 0;
      .el-form-item {
        margin-bottom: 15px;
      }
      .el-button {
        background-color: #d92121;
        border-color: #d92121;
        &:hover {
          background-color: #c51f1f;
          border-color: #c51f1f;
        }
      }
    }
  }


  .function-card {
    height: 120px;
    cursor: pointer;
    margin-bottom: 15px;
    .function-item {
      height: 100%;
      display: flex;
      flex-direction: column;
      align-items: center;
      justify-content: center;
      i {
        font-size: 32px;
        color: #d92121;
        margin-bottom: 8px;
      }
      span {
        font-size: 16px;
        color: #333;
      }
    }
    &:hover {
      box-shadow: 0 2px 12px rgba(0, 0, 0, 0.1);
    }
  }
}
</style>
