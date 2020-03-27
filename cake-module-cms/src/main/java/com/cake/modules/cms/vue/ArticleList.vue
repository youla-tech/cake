<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="审核">
              <a-input placeholder="请输入审核" v-model="queryParam.auditFlag"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="createDate">
              <a-input placeholder="请输入createDate" v-model="queryParam.createDate"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="deleteFlag">
              <a-input placeholder="请输入deleteFlag" v-model="queryParam.deleteFlag"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="updateDate">
              <a-input placeholder="请输入updateDate" v-model="queryParam.updateDate"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="文章内容">
              <a-input placeholder="请输入文章内容" v-model="queryParam.content"></a-input>
            </a-form-item>
          </a-col>
          </template>
          <a-col :md="6" :sm="8" >
            <span style="float: left;overflow: hidden;" class="table-page-search-submitButtons">
              <a-button type="primary" @click="searchQuery" icon="search">查询</a-button>
              <a-button type="primary" @click="searchReset" icon="reload" style="margin-left: 8px">重置</a-button>
              <a @click="handleToggleSearch" style="margin-left: 8px">
                {{ toggleSearchStatus ? '收起' : '展开' }}
                <a-icon :type="toggleSearchStatus ? 'up' : 'down'"/>
              </a>
            </span>
          </a-col>

        </a-row>
      </a-form>
    </div>

    <!-- 操作按钮区域 -->
    <div class="table-operator">
      <a-button @click="handleAdd" type="primary" icon="plus">新增</a-button>
      <a-button type="primary" icon="download" @click="handleExportXls('Article')">导出</a-button>
      <a-upload name="file" :showUploadList="false" :multiple="false" :headers="tokenHeader" :action="importExcelUrl" @change="handleImportExcel">
        <a-button type="primary" icon="import">导入</a-button>
      </a-upload>
      <a-dropdown v-if="selectedRowKeys.length > 0">
        <a-menu slot="overlay">
          <a-menu-item key="1" @click="batchDel"><a-icon type="delete"/>删除</a-menu-item>
        </a-menu>
        <a-button style="margin-left: 8px"> 批量操作 <a-icon type="down" /></a-button>
      </a-dropdown>
    </div>

    <!-- table区域-begin -->
    <div>
      <div class="ant-alert ant-alert-info" style="margin-bottom: 16px;">
        <i class="anticon anticon-info-circle ant-alert-icon"></i> 已选择 <a style="font-weight: 600">{{ selectedRowKeys.length }}</a>项
        <a style="margin-left: 24px" @click="onClearSelected">清空</a>
      </div>

      <a-table
        ref="table"
        size="middle"
        bordered
        rowKey="id"
        :columns="columns"
        :dataSource="dataSource"
        :pagination="ipagination"
        :loading="loading"
        :rowSelection="{selectedRowKeys: selectedRowKeys, onChange: onSelectChange}"
        @change="handleTableChange">

        <span slot="action" slot-scope="text, record">
          <a @click="handleEdit(record)">编辑</a>

          <a-divider type="vertical" />
          <a-dropdown>
            <a class="ant-dropdown-link">更多 <a-icon type="down" /></a>
            <a-menu slot="overlay">
              <a-menu-item>
                <a-popconfirm title="确定删除吗?" @confirm="() => handleDelete(record.id)">
                  <a>删除</a>
                </a-popconfirm>
              </a-menu-item>
            </a-menu>
          </a-dropdown>
        </span>

      </a-table>
    </div>
    <!-- table区域-end -->

    <!-- 表单区域 -->
    <article-modal ref="modalForm" @ok="modalFormOk"></article-modal>
  </a-card>
</template>

<script>
  import ArticleModal from './modules/ArticleModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "ArticleList",
    mixins:[JeecgListMixin],
    components: {
      ArticleModal
    },
    data () {
      return {
        description: 'Article管理页面',
        // 表头
        columns: [
          {
            title: '#',
            dataIndex: '',
            key:'rowIndex',
            width:60,
            align:"center",
            customRender:function (t,r,index) {
              return parseInt(index)+1;
            }
           },
		   {
            title: '审核',
            align:"center",
            dataIndex: 'auditFlag'
           },
		   {
            title: 'createDate',
            align:"center",
            dataIndex: 'createDate'
           },
		   {
            title: 'deleteFlag',
            align:"center",
            dataIndex: 'deleteFlag'
           },
		   {
            title: 'updateDate',
            align:"center",
            dataIndex: 'updateDate'
           },
		   {
            title: '文章内容',
            align:"center",
            dataIndex: 'content'
           },
		   {
            title: '封面图片',
            align:"center",
            dataIndex: 'coverImageUrl'
           },
		   {
            title: '跳转url',
            align:"center",
            dataIndex: 'href'
           },
		   {
            title: '排序',
            align:"center",
            dataIndex: 'orderNo'
           },
		   {
            title: '发布者',
            align:"center",
            dataIndex: 'publisher'
           },
		   {
            title: '资源来源',
            align:"center",
            dataIndex: 'sourceFrom'
           },
		   {
            title: '摘要',
            align:"center",
            dataIndex: 'summary'
           },
		   {
            title: '标题',
            align:"center",
            dataIndex: 'title'
           },
		   {
            title: 'columnInfoId',
            align:"center",
            dataIndex: 'columnInfoId'
           },
		   {
            title: '是否审核',
            align:"center",
            dataIndex: 'isAudit'
           },
		   {
            title: '是否置顶',
            align:"center",
            dataIndex: 'isTop'
           },
		   {
            title: '访问数',
            align:"center",
            dataIndex: 'viewCount'
           },
		   {
            title: 'rootColumnInfoId',
            align:"center",
            dataIndex: 'rootColumnInfoId'
           },
		   {
            title: '文章类型（内容文章、外链文章、广告位）',
            align:"center",
            dataIndex: 'type'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/cms/article/list",
          delete: "/cms/article/delete",
          deleteBatch: "/cms/article/deleteBatch",
          exportXlsUrl: "cms/article/exportXls",
          importExcelUrl: "cms/article/importExcel",
       },
    }
  },
  computed: {
    importExcelUrl: function(){
      return `${window._CONFIG['domianURL']}/${this.url.importExcelUrl}`;
    }
  },
    methods: {
     
    }
  }
</script>
<style scoped>
  @import '~@assets/less/common.less'
</style>