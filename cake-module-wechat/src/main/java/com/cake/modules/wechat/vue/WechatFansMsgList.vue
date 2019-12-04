<template>
  <a-card :bordered="false">

    <!-- 查询区域 -->
    <div class="table-page-search-wrapper">
      <a-form layout="inline">
        <a-row :gutter="24">

          <a-col :md="6" :sm="8">
            <a-form-item label="内容">
              <a-input placeholder="请输入内容" v-model="queryParam.content"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="头像地址">
              <a-input placeholder="请输入头像地址" v-model="queryParam.headimgUrl"></a-input>
            </a-form-item>
          </a-col>
        <template v-if="toggleSearchStatus">
        <a-col :md="6" :sm="8">
            <a-form-item label="是否已回复">
              <a-input placeholder="请输入是否已回复" v-model="queryParam.isRes"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="微信素材ID">
              <a-input placeholder="请输入微信素材ID" v-model="queryParam.mediaId"></a-input>
            </a-form-item>
          </a-col>
          <a-col :md="6" :sm="8">
            <a-form-item label="消息类型">
              <a-input placeholder="请输入消息类型" v-model="queryParam.msgType"></a-input>
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
      <a-button type="primary" icon="download" @click="handleExportXls('wechat  fans msg')">导出</a-button>
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
    <wechatFansMsg-modal ref="modalForm" @ok="modalFormOk"></wechatFansMsg-modal>
  </a-card>
</template>

<script>
  import WechatFansMsgModal from './modules/WechatFansMsgModal'
  import { JeecgListMixin } from '@/mixins/JeecgListMixin'

  export default {
    name: "WechatFansMsgList",
    mixins:[JeecgListMixin],
    components: {
      WechatFansMsgModal
    },
    data () {
      return {
        description: 'wechat  fans msg管理页面',
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
            title: '内容',
            align:"center",
            dataIndex: 'content'
           },
		   {
            title: '头像地址',
            align:"center",
            dataIndex: 'headimgUrl'
           },
		   {
            title: '是否已回复',
            align:"center",
            dataIndex: 'isRes'
           },
		   {
            title: '微信素材ID',
            align:"center",
            dataIndex: 'mediaId'
           },
		   {
            title: '消息类型',
            align:"center",
            dataIndex: 'msgType'
           },
		   {
            title: '昵称',
            align:"center",
            dataIndex: 'nickname'
           },
		   {
            title: '用户标识',
            align:"center",
            dataIndex: 'openid'
           },
		   {
            title: '本地图片路径',
            align:"center",
            dataIndex: 'picPath'
           },
		   {
            title: '微信图片URL',
            align:"center",
            dataIndex: 'picUrl'
           },
		   {
            title: '最近一条回复内容',
            align:"center",
            dataIndex: 'resContent'
           },
		   {
            title: '微信账号ID',
            align:"center",
            dataIndex: 'wxAccountId'
           },
          {
            title: '操作',
            dataIndex: 'action',
            align:"center",
            scopedSlots: { customRender: 'action' },
          }
        ],
		url: {
          list: "/wechat/wechatFansMsg/list",
          delete: "/wechat/wechatFansMsg/delete",
          deleteBatch: "/wechat/wechatFansMsg/deleteBatch",
          exportXlsUrl: "wechat/wechatFansMsg/exportXls",
          importExcelUrl: "wechat/wechatFansMsg/importExcel",
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