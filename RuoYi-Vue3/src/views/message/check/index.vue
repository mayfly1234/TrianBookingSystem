<template>
  <div class="app-container">
    <!-- 1. 查询表单区域（美化样式） -->
    <el-form 
      :inline="true" 
      :model="queryParams" 
      class="query-form" 
      @keyup.enter="handleQuery"
    >
      <el-form-item label="公告内容">
        <el-input
          v-model="queryParams.infoContent"
          placeholder="请输入公告关键词"
          clearable
          size="small"
          class="search-input"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" size="small" icon="el-icon-search" @click="handleQuery">查询</el-button>
        <el-button size="small" icon="el-icon-refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <!-- 2. 公告列表区域（优化表格样式） -->
    <el-table
      v-loading="loading"
      :data="messageList"
      border
      stripe
      highlight-current-row
      @row-click="handleRowClick"
      class="notice-table"
    >
      <el-table-column label="信息编号" prop="infoId" align="center" width="100" />
      <el-table-column label="公告内容" prop="infoContent" align="center">
        <template v-slot="scope">
          <el-tooltip 
            :content="scope.row.infoContent || '无内容'" 
            placement="top" 
            effect="dark"
            max-width="500px"
          >
            <!-- 列表中仅展示纯文本摘要（避免HTML标签干扰） -->
            <div class="content-summary">
              {{ stripHtml(scope.row.infoContent)?.length > 80 
                ? stripHtml(scope.row.infoContent).substring(0, 80) + '...' 
                : (stripHtml(scope.row.infoContent) || '无内容') }}
            </div>
          </el-tooltip>
        </template>
      </el-table-column>
      <el-table-column label="发布时间" prop="publishTime" align="center" width="180">
        <template v-slot="scope">
          {{ scope.row.publishTime ? parseTime(scope.row.publishTime, '{y}-{m}-{d} {h}:{i}:{s}') : '无' }}
        </template>
      </el-table-column>
      <el-table-column label="操作" align="center" width="100">
        <template v-slot="scope">
          <el-button
            type="primary"
            icon="el-icon-view"
            size="mini"
            @click="handleView(scope.row)"
            class="view-btn"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 3. 分页组件（美化样式） -->
    <div class="pagination-container">
      <pagination
        v-show="total > 0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
    </div>

    <!-- 4. 公告详情弹窗（渲染HTML富文本+样式优化） -->
    <el-dialog
      title="公告详情"
      v-model="openView"
      width="800px"
      append-to-body
      destroy-on-close
      class="notice-dialog"
      :close-on-click-modal="false"
    >
      <el-form :model="messageCheck" label-width="80px" disabled class="detail-form">
        <el-form-item label="信息编号">
          <span>{{ messageCheck.infoId || '无' }}</span>
        </el-form-item>
        <el-form-item label="发布时间">
          <span>{{ messageCheck.publishTime ? parseTime(messageCheck.publishTime, '{y}-{m}-{d} {h}:{i}:{s}') : '无' }}</span>
        </el-form-item>
        <el-form-item label="公告内容">
          <!-- 核心：v-html渲染HTML富文本，加样式重置 -->
          <div class="rich-content" v-html="messageCheck.infoContent || '无内容'"></div>
        </el-form-item>
      </el-form>
      <template v-slot:footer>
        <el-button type="primary" @click="openView = false">确 定</el-button>
      </template>
    </el-dialog>
  </div>
</template>

<script>
import { listMessageCheck, getMessageCheck } from "@/api/message/check";

export default {
  name: "MessageCheck",
  data() {
    return {
      loading: false,
      openView: false,
      messageList: [],
      total: 0,
      messageCheck: {},
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        infoContent: ""
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    // 查询列表
    getList() {
      this.loading = true;
      listMessageCheck(this.queryParams)
        .then(response => {
          this.messageList = response.rows || [];
          this.total = response.total || 0;
        })
        .catch(error => {
          console.error("公告列表查询失败：", error);
          this.messageList = [];
          this.total = 0;
          this.$message.error("查询公告列表失败");
        })
        .finally(() => {
          this.loading = false;
        });
    },

    // 点击查询
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },

    // 重置查询
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
        pageSize: 10,
        infoContent: ""
      };
      this.getList();
    },

    // 查看详情
    handleView(row) {
      if (!row?.infoId) return;
      this.messageCheck = {};
      this.openView = true;
      getMessageCheck(row.infoId)
        .then(response => {
          this.messageCheck = response.data || {};
        })
        .catch(error => {
          console.error("公告详情查询失败：", error);
          this.$message.error("查询公告详情失败");
        });
    },

    // 行点击查看详情
    handleRowClick(row) {
      this.handleView(row);
    },

    // 工具方法：去除HTML标签，仅保留纯文本（列表摘要用）
    stripHtml(html) {
      if (!html) return "";
      return html.replace(/<[^>]+>/g, "").replace(/&nbsp;/g, " ");
    }
  }
};
</script>

<style scoped lang="scss">
// 全局容器样式
.app-container {
  padding: 20px;
  background: #f5f7fa;
  min-height: calc(100vh - 100px);
}

// 查询表单样式
.query-form {
  padding: 15px 20px;
  background: #fff;
  border-radius: 8px;
  margin-bottom: 20px;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);

  .search-input {
    width: 300px;
    ::v-deep .el-input__inner {
      border-radius: 4px;
      height: 32px;
      line-height: 32px;
    }
  }

  .el-button {
    margin-left: 10px;
    border-radius: 4px;
  }
}

// 表格样式
.notice-table {
  background: #fff;
  border-radius: 8px;
  overflow: hidden;
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.05);

  ::v-deep .el-table__header {
    th {
      background: #f8f9fa;
      color: #333;
      font-weight: 600;
      border-bottom: 1px solid #e6e6e6;
    }
  }

  ::v-deep .el-table__row {
    height: 60px;
    &:hover {
      background: #f9fafc;
    }
  }

  ::v-deep .el-table-cell {
    padding: 12px 0;
    font-size: 14px;
    color: #666;
  }

  .content-summary {
    font-size: 14px;
    color: #666;
    line-height: 1.5;
  }

  .view-btn {
    ::v-deep .el-button--primary {
      background: #409eff;
      border-color: #409eff;
      border-radius: 4px;
      &:hover {
        background: #66b1ff;
        border-color: #66b1ff;
      }
    }
  }
}

// 分页样式
.pagination-container {
  margin-top: 20px;
  text-align: right;
  ::v-deep .el-pagination {
    .el-pager li {
      border-radius: 4px;
      margin: 0 2px;
    }
    .el-pager li.active {
      background: #409eff;
      color: #fff;
    }
    .el-pagination__sizes .el-input .el-input__inner {
      border-radius: 4px;
    }
  }
}

// 弹窗样式
.notice-dialog {
  ::v-deep .el-dialog__header {
    border-bottom: 1px solid #e6e6e6;
    padding-bottom: 10px;
    .el-dialog__title {
      font-size: 16px;
      font-weight: 600;
      color: #333;
    }
  }

  ::v-deep .el-dialog__body {
    padding: 20px;
  }

  .detail-form {
    .el-form-item {
      margin-bottom: 15px;
      label {
        color: #333;
        font-weight: 500;
      }
      .rich-content {
        width: 100%;
        max-height: 400px;
        overflow-y: auto;
        padding: 15px;
        border: 1px solid #e6e6e6;
        border-radius: 8px;
        background: #f9f9f9;
        line-height: 1.8;
        font-size: 14px;
        color: #333;

        // 富文本样式重置（适配各类标签）
        h1 {
          font-size: 20px;
          color: #333;
          margin: 10px 0;
          font-weight: 600;
        }
        p {
          margin: 8px 0;
          color: #333;
        }
        strong {
          font-weight: 600;
          color: #333;
        }
        blockquote {
          margin: 10px 0;
          padding: 10px 15px;
          background: #f0f8ff;
          border-left: 4px solid #409eff;
          border-radius: 4px;
        }
        br {
          line-height: 1.5;
        }
        a {
          color: #409eff;
          text-decoration: none;
          &:hover {
            text-decoration: underline;
          }
        }
      }
    }
  }

  ::v-deep .el-dialog__footer {
    border-top: 1px solid #e6e6e6;
    padding-top: 15px;
    .el-button--primary {
      background: #409eff;
      border-color: #409eff;
      border-radius: 4px;
      padding: 8px 20px;
      &:hover {
        background: #66b1ff;
        border-color: #66b1ff;
      }
    }
  }
}
</style>
