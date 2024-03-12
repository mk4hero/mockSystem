<template>
  <div class="app-container">
    <el-row :gutter="20">
      <el-col>
        <el-form :model="queryParams" ref="queryParams" size="small" :rules="rules" :inline="true" v-show="showSearch" label-width="68px">
          <el-form-item label="交易码" prop="busiCode">
            <el-input
              v-model="queryParams.busiCode"
              placeholder="请输入交易码"
              clearable
              style="width: 240px"
            />
          </el-form-item>
          <el-form-item label="报文类型" prop="msgType" label-width="100px">
            <el-select
              v-model="queryParams.msgType"
              placeholder="报文类型"
              clearable
              style="width: 240px"
            >
              <el-option
                v-for="dict in dict.type.mock_msg_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item label="渠道" prop="channel">
            <el-select
              v-model="queryParams.channel"
              placeholder="渠道"
              clearable
              style="width: 240px"
            >
              <el-option
                v-for="dict in dict.type.sys_channel_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              />
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
            <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
          </el-form-item>
        </el-form>
        <el-divider> <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar> </el-divider>
        <el-row :gutter="10" class="mb8">
          <el-col :span="1.5">
            <el-button
              :disabled="treeBtnFlag"
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="handleAdd"
            >新增</el-button>
          </el-col>
          <el-col :span="1.5">
            <el-button
              :disabled="treeBtnFlag"
              type="primary"
              plain
              icon="el-icon-plus"
              size="mini"
              @click="lookLookTree"
            >展示报文树结构</el-button>
          </el-col>
        </el-row>

        <el-table v-loading="loading" :data="tableData" @selection-change="handleSelectionChange">
          <el-table-column type="selection"/>
          <el-table-column label="id"  key="id" prop="id" align="center" />
          <el-table-column label="交易码"  key="busiCode" prop="busiCode" align="center" />
          <el-table-column label="报文类型"  key="msgType" prop="msgType" align="center" >
            <template slot-scope="scope">
              <dict-tag :options="dict.type.mock_msg_type" :value="scope.row.msgType"/>
            </template>
          </el-table-column>
          <el-table-column label="字段名称"  key="fieldName" prop="fieldName" align="center" />
          <el-table-column label="路径"  key="path" prop="path" align="center"
                           :width="flexColumnWidth('path',tableData)"
                           v-if="msgTypeFlag != 'flStr'"
          />
          <el-table-column label="同名同级元素标识"  key="sign" prop="sign" align="center" v-if="msgTypeFlag == 'xml'"/>
          <el-table-column label="定长字符串字段起始位置"  key="location" prop="location" align="center" v-if="msgTypeFlag == 'flStr'"/>
          <el-table-column label="定长字符串字段长度"  key="length" prop="length" align="center" v-if="msgTypeFlag == 'flStr'"/>
          <el-table-column label="定长字符串可循环部分循环次数"  key="loopCount" prop="loopCount" align="center" v-if="msgTypeFlag == 'flStr'"/>
          <el-table-column label="定长字符串可循环部分单次字符长度"  key="loopLength" prop="loopLength" align="center" v-if="msgTypeFlag == 'flStr'"/>
          <el-table-column
            label="操作"
            width="160"
            class-name="small-padding fixed-width"
            align="center"
          >
            <template slot-scope="scope" v-if="scope.row.action != 1" >
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="look(scope.row)"
              >查看</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-edit"
                @click="handleUpdate(scope.row)"
              >修改</el-button>
              <el-button
                size="mini"
                type="text"
                icon="el-icon-delete"
                @click="handleDelete(scope.row)"
              >删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <pagination
          v-show="total>0"
          :total="total"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="getList"
        />
      </el-col>
    </el-row>
    <!-- 添加或修改用户配置对话框 -->
     <el-dialog :title="title" :visible.sync="open" :before-close="addModifyDialogClose"
                width="800px" append-to-body>
        <el-descriptions class="margin-top" title="字段映射信息" :column="3" size="small" border v-if="action != 'add'">
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            节点id
          </template>
          {{ form.id }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            交易码
          </template>
          {{ form.busiCode }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            渠道
          </template>
          {{ form.channel }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            报文类型
          </template>
          {{ form.msgType }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            字段名称
          </template>
          {{ form.fieldName }}
        </el-descriptions-item>
        <el-descriptions-item>
          <template slot="label">
            <i class="el-icon-s-help"></i>
            映射用序号字段
          </template>
          {{ form.number }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType != 'flStr'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            节点路径
          </template>
          {{ form.path }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType == 'xml'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            同级重名元素标识
          </template>
          {{ form.sign }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType != 'flStr'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            父节点id
          </template>
          {{ form.parentId }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType != 'flStr'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            是否为叶子节点
          </template>
          {{ form.isSonNode }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType == 'flStr'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            定长字符串起始位置
          </template>
          {{ form.location }}
        </el-descriptions-item>
        <el-descriptions-item v-if="form.msgType == 'flStr'">
          <template slot="label">
            <i class="el-icon-s-help"></i>
            定长字符串长度
          </template>
          {{ form.length }}
        </el-descriptions-item>
      </el-descriptions>
        <el-divider><i class="el-icon-sugar"></i></el-divider>
        <el-form ref="form" :model="form" v-if="action != 'look'" label-position="top" label-width="80px">
          <el-row>
            <el-col :span="8">
              <div style="text-align: center;">
                <el-form-item label="报文类型" prop="msgType" style="display: inline-block;margin: 0 auto;">
                  <el-select v-model="form.msgType" placeholder="请选择报文类型" :disabled="this.action == 'look'">
                    <el-option
                      v-for="dict in dict.type.mock_msg_type"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="8">
              <div style="text-align: center;">
                <el-form-item label="交易码" prop="busiCode" style="display: inline-block;margin: 0 auto;">
                  <el-input v-model="form.busiCode" placeholder="请输入交易码" maxlength="30" :disabled="this.action == 'look'"/>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="8">
              <div style="text-align: center;">
                <el-form-item label="字段名称" prop="fieldName" style="display: inline-block;margin: 0 auto;">
                  <el-input v-model="form.fieldName" placeholder="请输入字段名称" maxlength="30" :disabled="this.action == 'look'"/>
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row type="flex" align="bottom">
            <el-col :span="8">
              <div style="text-align: center;">
                <el-form-item label="渠道" prop="channel" style="display: inline-block;margin: 0 auto;" >
                  <el-select v-model="form.channel" placeholder="请选择渠道" clearable :disabled="this.action == 'look'">
                    <el-option
                      v-for="dict in dict.type.sys_channel_type"
                      :key="dict.value"
                      :label="dict.label"
                      :value="dict.value"
                    ></el-option>
                  </el-select>
                </el-form-item>
              </div>
            </el-col>
            <el-col :span="8" v-if="form.msgType != 'flStr'" >
              <div type="flex" justify="center" align="middle">
                <el-button style="display: inline-block; margin: 0 auto;"
                           type="primary" @click="openTreeDialog(form.fieldName)" :disabled="treeBtnFlag">选择父节点</el-button>
              </div>
            </el-col>
            <el-col :span="8" v-if="form.msgType != 'flStr'">
              <div style="text-align: center;">
                <el-form-item label="父节点id" prop="parentId" style="display: inline-block;margin: 0 auto;">
                  <el-input v-model="form.parentId" placeholder="请输入父节点id" maxlength="30" disabled />
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row type="flex" align="bottom">
            <el-col :span="24" v-if="form.msgType != 'flStr'">
              <div style="text-align: center;">
                <el-form-item label="字段路径" prop="path" style="display: grid; margin: 0 auto;">
                  <el-input v-model="form.path" placeholder="请输入路径" maxlength="30" disabled />
                </el-form-item>
              </div>
            </el-col>
          </el-row>

          <el-row type="flex" align="bottom">
            <el-col :span="12" v-if="form.msgType == 'xml'">
              <el-form-item label="同级重名元素标识" prop="sign" style="display: inline-block;margin: 0 auto;">
                <el-input v-model="form.sign" placeholder="请输入同级重名元素标识" maxlength="30" :disabled="this.action == 'look'" />
              </el-form-item>
            </el-col>
            <el-col :span="12" v-if="form.msgType != 'flStr'">
              <el-form-item label="是否为叶子节点" prop="isSonNode" style="display: inline-block;margin: 0 auto;">
                <el-select v-model="form.isSonNode" placeholder="请选择是否为叶子节点" :disabled="this.action == 'look'" >
                  <el-option
                    v-for="dict in dict.type.sys_yes_no"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row type="flex" align="bottom">
            <el-col :span="24" v-if="form.msgType == 'flStr'">
              <el-form-item label="定长字符串字段起始位置" prop="location">
                <el-input v-model="form.location" placeholder="请输入定长字符串字段起始位置" maxlength="30" :disabled="this.action == 'look'" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" v-if="form.msgType == 'flStr'">
              <el-form-item label="定长字符串字段长度" prop="length">
                <el-input v-model="form.length" placeholder="请输入定长字符串字段长度" maxlength="30" :disabled="this.action == 'look'" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" v-if="form.msgType == 'flStr'">
              <el-form-item label="定长字符串可循环部分循环次数" prop="loopCount">
                <el-input v-model="form.loopCount" placeholder="请输入定长字符串可循环部分循环次数" maxlength="30" :disabled="this.action == 'look'" />
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="24" v-if="form.msgType == 'flStr'">
              <el-form-item label="定长字符串可循环部分单次字符长度" prop="loopLength">
                <el-input v-model="form.loopLength" placeholder="请输入定长字符串可循环部分单次字符长度" maxlength="30" :disabled="this.action == 'look'" />
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm" :disabled="this.btnClick">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
    <!-- 展示报文树 -->
    <el-dialog title="报文树展示" :visible.sync="openTree" width="80%" :before-close="treeDialogClose">
      <el-tree
        ref="msgTree"
        :data="treeNodeInfo"
        :props="defaultProps"
        :expand-on-click-node="false"
        :default-expand-all="true"
        :node-key="'id'"
        check-strictly
        highlight-current
        :show-checkbox="lookTree"
        icon-class="el-icon-s-help"
        @check="handleNodeClick"
      >
          <span slot-scope="{ data }">
            <template v-if="data.children">
              <div v-if="data.children.length > 0">
                <!-- <i class="el-icon-folder" :style="'font-size: 14px; padding: 0 5px 0 5px'"/> -->
                <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                  <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                    <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>
                <span>{{ data.label }}</span>
              </div>

              <div v-else>
                <i class="leaf-node-line"></i>
                <!-- <i class="el-icon-folder" :style="'padding: 0 5px 0 5px'"/> -->
                <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                  <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                    <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>
                <span>{{ data.label }}</span>
              </div>
            </template>

            <template v-else>
              <div style="margin-left: 0px;">
                <i class="leaf-node-line"></i>
                <!-- <i class="el-icon-document" :style="'padding: 0 5px 0 5px'"></i> -->
                <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                  <svg viewBox="64 64 896 896" data-icon="deployment-unit" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                    <path d="M888.3 693.2c-42.5-24.6-94.3-18-129.2 12.8l-53-30.7V523.6c0-15.7-8.4-30.3-22-38.1l-136-78.3v-67.1c44.2-15 76-56.8 76-106.1 0-61.9-50.1-112-112-112s-112 50.1-112 112c0 49.3 31.8 91.1 76 106.1v67.1l-136 78.3c-13.6 7.8-22 22.4-22 38.1v151.6l-53 30.7c-34.9-30.8-86.8-37.4-129.2-12.8-53.5 31-71.7 99.4-41 152.9 30.8 53.5 98.9 71.9 152.2 41 42.5-24.6 62.7-73 53.6-118.8l48.7-28.3 140.6 81c6.8 3.9 14.4 5.9 22 5.9s15.2-2 22-5.9L674.5 740l48.7 28.3c-9.1 45.7 11.2 94.2 53.6 118.8 53.3 30.9 121.5 12.6 152.2-41 30.8-53.6 12.6-122-40.7-152.9zm-673 138.4a47.6 47.6 0 0 1-65.2-17.6c-13.2-22.9-5.4-52.3 17.5-65.5a47.6 47.6 0 0 1 65.2 17.6c13.2 22.9 5.4 52.3-17.5 65.5zM522 463.8zM464 234a48.01 48.01 0 0 1 96 0 48.01 48.01 0 0 1-96 0zm170 446.2l-122 70.3-122-70.3V539.8l122-70.3 122 70.3v140.4zm239.9 133.9c-13.2 22.9-42.4 30.8-65.2 17.6-22.8-13.2-30.7-42.6-17.5-65.5s42.4-30.8 65.2-17.6c22.9 13.2 30.7 42.5 17.5 65.5z"></path></svg></i>
                <span>{{ data.label }}</span>
              </div>
            </template>
          </span>
      </el-tree>
      <span slot="footer" class="dialog-footer" v-if="lookTree">
        <el-button @click="selectParentTreeNodeCancel()">取 消</el-button>
        <el-button type="primary" @click="selectParentTreeNode">确 定</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script>
import {addInfo, delInfo, listInfo, updateInfo} from "@/api/mock/moduleInfo";
import {delMapping, getMsgTreeSelect} from "@/api/mock/mappingInfo";

export default {
  name: "moduleList",
  dicts: ['mock_msg_type', 'mock_msg_action', 'sys_channel_type', 'mock_mapping_action', 'mock_function', 'sys_yes_no'],
  data() {
    return {
      // 遮罩层
      loading: false,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 报表表格数据
      tableData: null,
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        msgType: "xml",
      },
      rowsData: [],
      isLook: false,
      // 表单校验
      rules: {
        msgType: [
          { required: true, message: "报文类型不能为空", trigger: ['blur','change'] },
        ]
      },
      msgTypeFlag: "",
      busiCode: "",
      action: "",
      btnClick: false,
      /** 查询el-tree信息 */
      treeBtnFlag: true,
      selectTreeParams: {
        busiCode: "",
        msgType: "",
      },
      // 左侧报文树选项
      treeNodeInfo: undefined,
      defaultProps: {
        id: "id",
        busiCode: "busiCode",
        msgType: "msgType",
        label: "label",
        number: "number",
        path: "path",
        Xpath: "Xpath",
        sign: "sign",
        location: "location",
        length: "length",
        loopCount: "loopCount",
        loopLength: "loopLength",
        parentId: "parentId",
        children: "children",
        action: "",
        operate: "",
      },
      openTree: false,
      nodeArray: [],
      lookTree: true,
    };
  },
  mounted() {
    this.$notify({
      title: '提示',
      message: '请输入“交易码”并选择相应的“报文类型”后，点击查询按钮查询指定报文模板信息，方可进行新增节点操作',
      duration: 0
    });
  },
  methods: {
    getList() {
      this.$refs.queryParams.validate(valid => {
        if (!valid) {
          return false;
        }
        this.loading = true;
        listInfo(this.queryParams).then(response => {
          this.tableData = response.rows;
          this.total = response.total;
          this.loading = false;
          this.msgTypeFlag = this.queryParams.msgType;
          if(undefined != this.queryParams.busiCode && null != this.queryParams.busiCode && "" != this.queryParams.busiCode){
            this.busiCode = this.queryParams.busiCode;
            this.selectTreeParams.busiCode = this.busiCode;
            this.selectTreeParams.msgType = this.msgTypeFlag;
            getMsgTreeSelect(this.selectTreeParams).then(response => {
              this.treeNodeInfo = response.data;
              this.treeBtnFlag = false;
            });
          }
        })
      })
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.treeBtnFlag = true;
      this.reset();
      this.isLook = false;
      this.action = "look";
    },
    // 表单重置
    reset() {
      this.form = {
        id: undefined,
        msgType: this.msgTypeFlag,
        busiCode: undefined,
        channel: undefined,
        path: undefined,
        sign: undefined,
        fieldName: undefined,
        location: undefined,
        length: undefined,
        parentId: undefined,
        isSonNode: undefined,
        number: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.treeNodeInfo = undefined;
      this.treeBtnFlag = true;
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.queryParams = {
        pageNum: 1,
          pageSize: 10,
          msgType: "xml",
      },
      this.resetForm("queryForm");
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.rowsData = selection;
      this.single = selection.length != 1;
      this.multiple = !selection.length;
    },
    // 自适应表格列宽
    flexColumnWidth(str, tableData, flag = 'max') {
      // str为该列的字段名(传字符串);tableData为该表格的数据源(传变量);
      // flag为可选值，可不传该参数,传参时可选'max'或'equal',默认为'max'
      // flag为'max'则设置列宽适配该列中最长的内容,flag为'equal'则设置列宽适配该列中第一行内容的长度。
      str = str + ''
      let columnContent = ''
      if (!tableData || !tableData.length || tableData.length === 0 || tableData === undefined) {
        return
      }
      if (!str || !str.length || str.length === 0 || str === undefined) {
        return
      }
      if (flag === 'equal') {
        // 获取该列中第一个不为空的数据(内容)
        for (let i = 0; i < tableData.length; i++) {
          if (tableData[i][str].length > 0) {
            // console.log('该列数据[0]:', tableData[0][str])
            columnContent = tableData[i][str]
            break
          }
        }
      } else {
        // 获取该列中最长的数据(内容)
        let index = 0
        for (let i = 0; i < tableData.length; i++) {
          if (tableData[i][str] === null) {
            return
          }
          const now_temp = tableData[i][str] + ''
          const max_temp = tableData[index][str] + ''
          if (now_temp.length > max_temp.length) {
            index = i
          }
        }
        columnContent = tableData[index][str]
      }
      // 以下分配的单位长度可根据实际需求进行调整
      let flexWidth = 0
      for (const char of columnContent) {
        if ((char >= 'A' && char <= 'Z') || (char >= 'a' && char <= 'z')) {
          // 如果是英文字符，为字符分配8个单位宽度
          flexWidth += 8
        } else if (char >= '\u4e00' && char <= '\u9fa5') {
          // 如果是中文字符，为字符分配15个单位宽度
          flexWidth += 15
        } else {
          // 其他种类字符，为字符分配8个单位宽度
          flexWidth += 10
        }
      }
      if (flexWidth < 80) {
        // 设置最小宽度
        flexWidth = 80
      }
      // if (flexWidth > 250) {
      //   // 设置最大宽度
      //   flexWidth = 250
      // }
      return flexWidth + 'px'
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.isLook = false;
      this.action = "add";
      this.btnClick = false;
      this.title = "新增报文模板";
      this.form.id = undefined;
    },
    handleUpdate(row) {
      this.reset();
      this.form = row;
      this.open = true;
      this.isLook = false;
      this.action = "update";
      this.btnClick = false;
      this.title = "修改报文模板";
      this.selectTreeParams.busiCode = row.busiCode;
      this.selectTreeParams.msgType = row.msgType;
      getMsgTreeSelect(this.selectTreeParams).then(response => {
        this.treeNodeInfo = response.data;
        this.treeBtnFlag = false;
      });
    },
    look(row){
      this.reset();
      this.form = row;
      this.open = true;
      this.title = "查看报文模板";
      this.isLook = true;
      this.action = "look";
    },
    handleDelete(row) {
      delInfo(row.id).then(response => {
        this.$modal.msgSuccess("删除成功");
        this.getList();
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.btnClick = true;
          if (this.form.id != undefined) {
            updateInfo(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.treeBtnFlag = true;
              this.getList();
            });
          } else {
            if(this.form.isSonNode == "N"){
              this.form.isSonNode = false;
            }else{
              this.form.isSonNode = true;
            }
            addInfo(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.treeBtnFlag = true;
              this.getList();
            });
          }
        }
      });
    },
    openTreeDialog(fieldName){
      if(undefined == fieldName || null == fieldName){
        this.$notify({
          title: '警告',
          message: '请输入字段名称',
          type: 'warning'
        });
      }else{
        this.openTree = true;
      }
    },
    handleNodeClick(data, SelectedObj) {
      this.nodeArray = SelectedObj.checkedNodes;
    },
    selectParentTreeNodeCancel(){
      this.$refs.msgTree.setCheckedKeys([]);
      this.nodeArray = [];
      this.openTree = false;
    },
    selectParentTreeNode(){
      if( this.nodeArray.length > 1 ){
        this.$message({
          message: '请检查选中节点个数，只可以选择一个节点',
          type: 'warning'
        });
      }else{
        this.$confirm('当前正在选择父节点信息，是否确认?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.openTree = false;
          this.form.path = this.nodeArray[0].path + "/" + this.form.fieldName;
          this.form.parentId = this.nodeArray[0].parentId;
          this.$message({
            type: 'success',
            message: '父节点已修改'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消操作'
          });
        });
      }
    },
    addModifyDialogClose(){
      this.treeNodeInfo = undefined;
      this.treeBtnFlag = true;
      this.open = false;
      this.title = "";
    },
    treeDialogClose(){
      this.selectParentTreeNodeCancel();
      this.lookTree = true;
    },
    lookLookTree(){
      this.lookTree = false;
      this.openTree = true;
    },
  }
};
</script>
<style scoped lang="less">
.el-row {
  margin-top: 20px;
  margin-bottom: 20px
}
// 设置树形组件节点的定位和左内边距
.app-container /deep/ .el-tree-node {
  position: relative;
  padding-left: 13px;
}
// 设置树形组件节点的 before 伪类的样式
.app-container /deep/ .el-tree-node:before {
  width: 1px;
  height: 100%;
  content: '';
  position: absolute;
  top: -38px;
  bottom: 0;
  left: 0;
  right: auto;
  border-width: 1px;
  border-left: 1px solid #b8b9bb;
}

// 设置树形组件节点的 after 伪类的样式
.app-container /deep/ .el-tree-node:after {
  width: 13px;
  height: 13px;
  content: '';
  position: absolute;
  left: 0;
  right: auto;
  top: 12px;
  bottom: auto;
  border-width: 1px;
  border-top: 1px solid #b8b9bb;
}

// 设置树形组件首节点的左边框不显示
.app-container /deep/ .el-tree > .el-tree-node:before {
  border-left: none;
}

// 设置树形组件首节点的顶部边框不显示
.app-container /deep/ .el-tree > .el-tree-node:after {
  border-top: none;
}

// 设置树形组件末节点的 before 伪类的高度
.app-container /deep/ .el-tree .el-tree-node:last-child:before {
  height: 50px;
}

// 设置树形组件节点字体大小、以及取消左内边距
.app-container /deep/ .el-tree .el-tree-node__content {
  color: #000;
  font-size: 14px;
  padding-left: 0 !important;
}

// 设置树形组件孩子节点左内边距
.app-container /deep/ .el-tree .el-tree-node__children {
  padding-left: 11.5px;
}

// 设置树形组件复选框左右外边距
.app-container /deep/ .el-tree .el-tree-node__content > label.el-checkbox {
  margin: 0 5px 0 5px !important;
}

// 设置树形组件展开图标定位、图层、内边距
.app-container /deep/ .el-tree .el-tree-node__expand-icon {
  position: relative;
  z-index: 99;
}

// 设置树形组件叶子节点的默认图标不显示
.app-container /deep/ .el-tree .el-tree-node__expand-icon.is-leaf {
  display: none;
}

// 设置树形组件叶子节点的横线
.app-container /deep/ .el-tree .leaf-node-line {
  width: 23px;
  height: 13px;
  content: '';
  position: absolute;
  left: 13px;
  right: auto;
  top: 12px;
  bottom: auto;
  border-width: 1px;
  border-top: 1px solid #b8b9bb;
}

// 设置树形组件有叶子节点的左外边距
.app-container /deep/ .el-tree .el-tree-node__content:has(.is-leaf){
  // color: aqua;
  margin-left: 24px !important;
}
</style>
