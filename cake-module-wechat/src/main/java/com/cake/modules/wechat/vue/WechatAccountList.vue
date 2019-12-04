<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="公众号账户">
              <a-input placeholder="请输入公众号账户" v-model="queryParam.account"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="加密密钥">
              <a-input placeholder="请输入加密密钥" v-model="queryParam.aeskey"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="公众号appid">
              <a-input placeholder="请输入公众号appid" v-model="queryParam.appid"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="公众号密钥">
              <a-input placeholder="请输入公众号密钥" v-model="queryParam.appsecret"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="公众号名称">
              <a-input placeholder="请输入公众号名称" v-model="queryParam.name"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('wechat account')">导出</a-button>
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
    <wechatAccount-modal ref="modalForm" @ok="modalFormOk"></wechatAccount-modal>
  </a-card>
</template>

<script>
  import WechatAccountModal from './modules/WechatAccountModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WechatAccountList",
    mixins:[JeecgListMixin],
    components: {
      WechatAccountModal
    },
    data () {
      return {
        description: 'wechat account管理页面',
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
            title: '公众号账户',
            align:"center",
            dataIndex: 'account'
           },
		   {
            title: '加密密钥',
            align:"center",
            dataIndex: 'aeskey'
           },
		   {
            title: '公众号appid',
            align:"center",
            dataIndex: 'appid'
           },
		   {
            title: '公众号密钥',
            align:"center",
            dataIndex: 'appsecret'
           },
		   {
            title: '公众号名称',
            align:"center",
            dataIndex: 'name'
           },
		   {
            title: '二维码图片URL',
            align:"center",
            dataIndex: 'qrUrl'
           },
		   {
            title: '公众号token',
            align:"center",
            dataIndex: 'token'
           },
		   {
            title: '公众号url',
            align:"center",
            dataIndex: 'url'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wechat/wechatAccount/list",
          delete: "/wechat/wechatAccount/delete",
          deleteBatch: "/wechat/wechatAccount/deleteBatch",
          exportXlsUrl: "wechat/wechatAccount/exportXls",
          importExcelUrl: "wechat/wechatAccount/importExcel",
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