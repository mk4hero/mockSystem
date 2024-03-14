<template>
  <div class="app-container">
    <el-row class="top_Anchors" :gutter="20">
      <el-col :span="12" :xs="24">
        <el-form :model="leftQueryParams" ref="leftQueryParams" :rules="rules" size="small" :inline="true" label-width="auto">
          <div style="text-align: center;">
            <el-form-item label="业务号" prop="busiCode">
              <el-input
                v-model="leftQueryParams.busiCode"
                placeholder="请输入业务号"
                clearable
                size="small"
                prefix-icon="el-icon-search"
                style="width: 150px; margin-bottom: 1px"
              />
            </el-form-item>
            <el-form-item label="报文类型" prop="msgType">
              <el-select
                v-model="leftQueryParams.msgType"
                placeholder="请选择报文类型"
                clearable
                size="small"
                prefix-icon="el-icon-search"
                style="width: 150px; margin-bottom: 1px"
              >
                <el-option
                  v-for="dict in dict.type.mock_msg_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary"
                           icon="el-icon-search" size="mini"
                           @click="getLeftMsgTree">搜索</el-button>
                <el-button icon="el-icon-refresh"
                           size="mini"
                           @click="leftResetQuery">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
        <el-tree
          ref="leftTree"
          :data="leftMsgOptions"
          :props="defaultProps"
          :expand-on-click-node="false"
          :default-expand-all="true"
          :node-key="'id'"
          check-strictly
          highlight-current
          show-checkbox
          icon-class="el-icon-s-help"
          @check="leftHandleNodeClick"
        >
          <span slot-scope="{ data }">
            <template v-if="data.children">
              <div v-if="data.children.length > 0">
                <!-- <i class="el-icon-folder" :style="'font-size: 14px; padding: 0 5px 0 5px'"/> -->
                <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                  <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                    <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>
                <span>{{ data.label }}</span>
                <el-button type="danger" round size="mini" v-if="data.havaRule" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">我在这里</el-button>

                <!--针对定长字符串区分可循环字段-->
                <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
              </div>

              <div v-else>
                <i class="leaf-node-line"></i>
                <!-- <i class="el-icon-folder" :style="'padding: 0 5px 0 5px'"/> -->
                <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                  <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                    <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>
                <span>{{ data.label }}</span>
                <el-button type="danger" round size="mini" v-if="data.havaRule" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">我在这里</el-button>

                <!--针对定长字符串区分可循环字段-->
                <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
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
                <el-button type="danger" round size="mini" v-if="data.havaRule" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">我在这里</el-button>

                <!--针对定长字符串区分可循环字段-->
                <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
              </div>
            </template>
          </span>
        </el-tree>
      </el-col>
      <el-col :span="12" :xs="24">
        <el-form :model="rightQueryParams" ref="rightQueryParams" :rules="rules" size="small" :inline="true" label-width="auto">
          <div style="text-align: center;">
            <el-form-item label="业务号" prop="busiCode">
              <el-input
                v-model="rightQueryParams.busiCode"
                placeholder="请输入业务号"
                clearable
                size="small"
                prefix-icon="el-icon-search"
                style="width: 150px; margin-bottom: 1px"
              />
            </el-form-item>
            <el-form-item label="报文类型" prop="msgType">
              <el-select
                v-model="rightQueryParams.msgType"
                placeholder="请选择报文类型"
                clearable
                size="small"
                prefix-icon="el-icon-search"
                style="width: 150px; margin-bottom: 1px"
              >
                <el-option
                  v-for="dict in dict.type.mock_msg_type"
                  :key="dict.value"
                  :label="dict.label"
                  :value="dict.value"
                />
              </el-select>
            </el-form-item>
            <el-form-item>
              <el-button type="primary"
                         icon="el-icon-search" size="mini"
                         @click="getRightMsgTree">搜索</el-button>
              <el-button icon="el-icon-refresh"
                         size="mini"
                         @click="rightResetQuery">重置</el-button>
            </el-form-item>
          </div>
        </el-form>
        <div class="head-container">
          <el-tree
            ref="rightTree"
            :data="rightMsgOptions"
            :props="defaultProps"
            :expand-on-click-node="false"
            :default-expand-all="true"
            check-strictly
            node-key="id"
            highlight-current
            show-checkbox
            icon-class="el-icon-s-help"
            @check="rightHandleNodeClick"
          >
            <span slot-scope="{ data }">
              <template v-if="data.children">
                <div v-if="data.children.length > 0">

                  <!-- 这是一个小图标 -->
                  <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                    <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                      <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>

                  <span>{{ data.label }}</span>
                  <el-button type="primary" round size="mini" v-if="data.havaRule"
                             @click="heightLight(data)"
                             style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">存在映射关系</el-button>

                  <!--针对定长字符串区分可循环字段-->
                <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
                </div>

                <div v-else>
                  <i class="leaf-node-line"></i>

                  <!-- 这是一个小图标 -->
                  <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                    <svg viewBox="64 64 896 896" data-icon="apartment" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                      <path d="M908 640H804V488c0-4.4-3.6-8-8-8H548v-96h108c8.8 0 16-7.2 16-16V80c0-8.8-7.2-16-16-16H368c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h108v96H228c-4.4 0-8 3.6-8 8v152H116c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16H292v-88h440v88H620c-8.8 0-16 7.2-16 16v288c0 8.8 7.2 16 16 16h288c8.8 0 16-7.2 16-16V656c0-8.8-7.2-16-16-16zm-564 76v168H176V716h168zm84-408V140h168v168H428zm420 576H680V716h168v168z"></path></svg></i>

                  <span>{{ data.label }}</span>
                  <el-button type="primary" round size="mini" v-if="data.havaRule"
                             @click="heightLight(data)"
                             style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">存在映射关系</el-button>

                  <!--针对定长字符串区分可循环字段-->
                <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
                </div>
              </template>

              <template v-else>
                <div style="margin-left: 0px;">
                  <i class="leaf-node-line"></i>

                  <!-- 这是一个小图标 -->
                  <i :style="'font-size: 13px; padding: 0 5px 0 5px'">
                    <svg viewBox="64 64 896 896" data-icon="deployment-unit" width="1em" height="1em" fill="currentColor" aria-hidden="true" focusable="false" class="">
                      <path d="M888.3 693.2c-42.5-24.6-94.3-18-129.2 12.8l-53-30.7V523.6c0-15.7-8.4-30.3-22-38.1l-136-78.3v-67.1c44.2-15 76-56.8 76-106.1 0-61.9-50.1-112-112-112s-112 50.1-112 112c0 49.3 31.8 91.1 76 106.1v67.1l-136 78.3c-13.6 7.8-22 22.4-22 38.1v151.6l-53 30.7c-34.9-30.8-86.8-37.4-129.2-12.8-53.5 31-71.7 99.4-41 152.9 30.8 53.5 98.9 71.9 152.2 41 42.5-24.6 62.7-73 53.6-118.8l48.7-28.3 140.6 81c6.8 3.9 14.4 5.9 22 5.9s15.2-2 22-5.9L674.5 740l48.7 28.3c-9.1 45.7 11.2 94.2 53.6 118.8 53.3 30.9 121.5 12.6 152.2-41 30.8-53.6 12.6-122-40.7-152.9zm-673 138.4a47.6 47.6 0 0 1-65.2-17.6c-13.2-22.9-5.4-52.3 17.5-65.5a47.6 47.6 0 0 1 65.2 17.6c13.2 22.9 5.4 52.3-17.5 65.5zM522 463.8zM464 234a48.01 48.01 0 0 1 96 0 48.01 48.01 0 0 1-96 0zm170 446.2l-122 70.3-122-70.3V539.8l122-70.3 122 70.3v140.4zm239.9 133.9c-13.2 22.9-42.4 30.8-65.2 17.6-22.8-13.2-30.7-42.6-17.5-65.5s42.4-30.8 65.2-17.6c22.9 13.2 30.7 42.5 17.5 65.5z"></path></svg></i>

                  <span>{{ data.label }}</span>
                  <el-button type="primary" round size="mini" v-if="data.havaRule"
                             @click="heightLight(data)"
                             style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">存在映射关系</el-button>

                  <!--针对定长字符串区分可循环字段-->
                  <el-button type="success" round size="mini" v-if="data.sign == '1'" style="font-size: 13px; padding: 0 5px 0 5px;margin-left: 50px;">循环字段</el-button>
                </div>
              </template>
            </span>
          </el-tree>
        </div>
      </el-col>
    </el-row>
    <el-divider><i class="el-icon-sugar"></i></el-divider>
    <el-row :gutter="10">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
        >新增</el-button>
      </el-col>
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="success"-->
<!--          plain-->
<!--          icon="el-icon-edit"-->
<!--          size="mini"-->
<!--          :disabled="single"-->
<!--          @click="handleUpdate"-->
<!--        >修改</el-button>-->
<!--      </el-col>-->
<!--      <el-col :span="1.5">-->
<!--        <el-button-->
<!--          type="danger"-->
<!--          plain-->
<!--          icon="el-icon-delete"-->
<!--          size="mini"-->
<!--          :disabled="multiple"-->
<!--          @click="handleDelete"-->
<!--        >删除</el-button>-->
<!--      </el-col>-->

      <right-toolbar :showSearch.sync="showSearch" @queryTable="flushTableList"></right-toolbar>

    </el-row>

    <el-row :gutter="10" class="bottom_Anchors">
      <el-table v-loading="loading" :data="tableData">
<!--        <el-table-column type="selection" key="id" width="50" align="center" />-->
        <el-table-column align="center" key="sourceBusiCode" prop="sourceBusiCode" label="原交易码" :width="flexColumnWidth('sourceBusiCode',tableData)" />
        <el-table-column align="center" key="sourceMsgType" prop="sourceMsgType" label="原报文类型" />
        <el-table-column align="center" key="sourceFieldName" prop="sourceFieldName" label="原报文字段名称" />
        <el-table-column align="center" key="sourcePath" prop="sourcePath" label="原报文节点绝对路径" />
        <el-table-column align="center" key="targetPath" prop="targetPath" label="目标报文节点绝对路径" />
        <el-table-column align="center" key="targetFieldName" prop="targetFieldName" label="目标报文字段名称" />
        <el-table-column align="center" key="targetMsgType" prop="targetMsgType" label="目标报文类型" />
        <el-table-column align="center" key="targetBusiCode" prop="targetBusiCode" label="目标交易码" />
        <el-table-column align="center" label="操作" fixed="right">
          <template slot-scope="scope">
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
            >编辑</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
            >删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-row>

    <!-- 添加节点映射对话框 -->
    <el-dialog :title="title" :visible.sync="open" append-to-body width="900px">
      <el-divider><i class="el-icon-sugar"></i></el-divider>
      <div v-for="(item, j) in loopForm" :key="item.key" class="form_content">
        <el-descriptions class="margin-top" :column="3" size="small" border >
          <template v-for="(item, ind) in form.sourceNodeArray">
            <el-descriptions-item :key="ind">
              <template slot="label">
                <i class="el-icon-s-help"></i>
                原字段名称
              </template>
              {{ item.label }}
            </el-descriptions-item>
            <el-descriptions-item :key="ind">
              <template slot="label">
                <i class="el-icon-location"></i>
                原字段路径
              </template>
              {{ item.xpath }}
            </el-descriptions-item>
            <el-descriptions-item :key="ind">
              <template slot="label">
                <i class="el-icon-s-flag"></i>
                映射用序号
              </template>
              {{ item.number }}
            </el-descriptions-item>
          </template>
          <el-descriptions-item :key="item.key">
            <template slot="label">
              <i class="el-icon-help"></i>
              目标字段名称
            </template>
            {{ item.label }}
          </el-descriptions-item>
          <el-descriptions-item :key="item.key">
            <template slot="label">
              <i class="el-icon-location"></i>
              目标字段路径
            </template>
            {{ item.xpath }}
          </el-descriptions-item>
          <el-descriptions-item :key="item.key">
            <template slot="label">
              <i class="el-icon-s-flag"></i>
              映射用序号
            </template>
            {{ item.number }}
          </el-descriptions-item>
        </el-descriptions>
        <el-form class="form" :ref="`loopForm-${item.key}`" :model="item" label-position="top" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="操作类型" prop="action" label-width="100px">
                <el-select clearable v-model="item.action" placeholder="请选择操作类型">
                  <el-option
                    v-for="dict in dict.type.mock_mapping_action"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="操作函数" prop="operate" label-width="100px">
                <el-select clearable style="width: auto"
                           v-model="item.operate" placeholder="请选择操作函数" @change="selectChange($event, item)">
                  <el-option
                    v-for="dict in dict.type.mock_function"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row v-if="item.funcRemarkFlag">
            <el-col :span="24">
              <el-alert
                title="函数说明"
                type="info"
                show-icon
                center
                :description="item.funcRemark"
                close-text="知道了">
              </el-alert>
            </el-col>
          </el-row>

          <el-row v-if="item.funcRemarkFlag">
            <el-col :span="24">
              <div class="dynamic-form-items-container">
                <dynamic-form-items :items="item.parameterInputPart" v-if="item.operate"></dynamic-form-items>
                <dynamic-form-items :items="item.parameterSelectPart" v-if="item.operate"></dynamic-form-items>
              </div>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="原定长字符串字段对齐方式" prop="sourceAlignment" label-width="auto" v-if="item.sourceAlignmentFlag">
                <el-select clearable v-model="item.sourceAlignment" placeholder="请选择对齐方式">
                  <el-option
                    v-for="dict in dict.type.mock_flStr_Alignment"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                    :title="dict.raw.remark"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="原定长字符串占位符" prop="sourcePlaceholders" label-width="auto" v-if="item.sourcePlaceholdersFlag">
                <el-input v-model="item.sourcePlaceholders" placeholder="请输入占位符"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="目标定长字符串字段对齐方式" prop="targetAlignment" label-width="auto" v-if="item.targetAlignmentFlag">
                <el-select clearable v-model="item.targetAlignment" placeholder="请选择对齐方式">
                  <el-option
                    v-for="dict in dict.type.mock_flStr_Alignment"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                    :title="dict.raw.remark"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="目标定长字符串占位符" prop="targetPlaceholders" label-width="auto" v-if="item.targetPlaceholdersFlag">
                <el-input v-model="item.targetPlaceholders" placeholder="请输入占位符"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>
        <el-divider><i class="el-icon-sugar"></i></el-divider>
      </div>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 修改查看节点映射对话框 -->
    <el-dialog :title="modifyTitle" :visible.sync="modifyOpen" append-to-body width="1400px">
      <el-divider><i class="el-icon-sugar"></i></el-divider>
        <el-descriptions class="margin-top" :column="5" size="small" border >
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-help"></i>
              原字段业务号
            </template>
            {{ modifyForm.sourceBusiCode }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-help"></i>
              原字段报文类型
            </template>
            {{ modifyForm.sourceMsgType }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-help"></i>
              原字段名称
            </template>
            {{ modifyForm.sourceFieldName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location"></i>
              原字段路径
            </template>
            {{ modifyForm.sourcePath }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-flag"></i>
              映射用序号
            </template>
            {{ modifyForm.sourceNumber }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-help"></i>
              目标字段业务号
            </template>
            {{ modifyForm.targetBusiCode }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-help"></i>
              目标字段报文类型
            </template>
            {{ modifyForm.targetMsgType }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-help"></i>
              目标字段名称
            </template>
            {{ modifyForm.targetFieldName }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-location"></i>
              目标字段路径
            </template>
            {{ modifyForm.targetPath }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label">
              <i class="el-icon-s-flag"></i>
              映射用序号
            </template>
            {{ modifyForm.targetNumber }}
          </el-descriptions-item>
        </el-descriptions>
        <el-form class="form" :model="modifyForm" label-position="top" label-width="100px">
          <el-row>
            <el-col :span="12">
              <el-form-item label="操作类型" prop="action" label-width="100px">
                <el-select clearable v-model="modifyForm.action" placeholder="请选择操作类型" :disabled="isLook">
                  <el-option
                    v-for="dict in dict.type.mock_mapping_action"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="操作函数" prop="operate" label-width="100px">
                <el-select clearable style="width: auto" :disabled="isLook"
                           v-model="modifyForm.operate" placeholder="请选择操作函数" @change="selectChange($event, modifyForm)">
                  <el-option
                    v-for="dict in dict.type.mock_function"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row v-if="modifyForm.funcRemarkFlag">
            <el-col :span="24">
              <el-alert
                title="函数说明"
                type="info"
                show-icon
                center
                :description="modifyForm.funcRemark"
                close-text="知道了">
              </el-alert>
            </el-col>
          </el-row>

          <el-row v-if="modifyForm.funcRemarkFlag">
            <el-col :span="24">
              <div class="dynamic-form-items-container">
                <dynamic-form-items :disabled="isLook" :items="modifyForm.parameterInputPart" v-if="modifyForm.operate"></dynamic-form-items>
                <dynamic-form-items :disabled="isLook" :items="modifyForm.parameterSelectPart" v-if="modifyForm.operate"></dynamic-form-items>
              </div>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="原定长字符串字段对齐方式" prop="sourceAlignment" label-width="auto" v-if="modifyForm.sourceAlignment">
                <el-select clearable v-model="modifyForm.sourceAlignment" placeholder="请选择对齐方式" :disabled="isLook">
                  <el-option
                    v-for="dict in dict.type.mock_flStr_Alignment"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                    :title="dict.raw.remark"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="原定长字符串占位符" prop="sourcePlaceholders" label-width="auto" v-if="modifyForm.sourcePlaceholders">
                <el-input v-model="modifyForm.sourcePlaceholders" placeholder="请输入占位符" :disabled="isLook"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="12">
              <el-form-item label="目标定长字符串字段对齐方式" prop="targetAlignment" label-width="auto" v-if="modifyForm.targetAlignment">
                <el-select clearable v-model="modifyForm.targetAlignment" placeholder="请选择对齐方式" :disabled="isLook">
                  <el-option
                    v-for="dict in dict.type.mock_flStr_Alignment"
                    :key="dict.value"
                    :label="dict.label"
                    :value="dict.value"
                    :title="dict.raw.remark"
                  ></el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="12">
              <el-form-item label="目标定长字符串占位符" prop="targetPlaceholders" label-width="auto" v-if="modifyForm.targetPlaceholders">
                <el-input v-model="modifyForm.targetPlaceholders" placeholder="请输入占位符" :disabled="isLook"></el-input>
              </el-form-item>
            </el-col>
          </el-row>
        </el-form>

        <el-divider><i class="el-icon-sugar"></i></el-divider>

      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

  </div>
</template>

<script>
import {addMapping, delMapping, getMsgTreeSelect, mappingList, modifyMapping} from "@/api/mock/mappingInfo.js";
import Treeselect from "@riophae/vue-treeselect";
import {listInfo} from "@/api/mock/functionInfo";
import {getDicts} from "@/api/system/dict/data";
import DynamicFormItems from "@/components/DynamicFormItems/index.vue";

const ACTION_MAPPING_HIGHLIGHT = 'mappingHightLight';
const ACTION_RELOAD_HAVERULE = 'reloadHavaRule';

export default {
  name: "moduleList",
  components: {Treeselect, DynamicFormItems},
  // 字典数据列表
  dicts: ['mock_msg_type', 'mock_mapping_action', 'mock_function', 'mock_flStr_Alignment'],
  mounted() {
    // 页面加载完成后执行获取函数列表方法
    this.getFuncList();
  },
  data(){
    return {
      /** 页面基础数据 */
      // 遮罩层
      loading: false,
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      /** 映射关系列表数据 */
      tableData: undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 100,
        sourceBusiCode: undefined,
        sourceMsgType: undefined,
        targetBusiCode: undefined,
        targetMsgType: undefined
      },
      /** 报文树数据 */
      rules: {
        busiCode: [
          { required: true, message: "业务号不能为空", trigger: "blur" },
        ],
        msgType: [
          { required: true, message: "报文类型不能为空", trigger: "blur" },
        ],
      },
      // 左侧报文树选项
      leftMsgOptions: undefined,
      // 右侧报文树选项
      rightMsgOptions: undefined,
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
        havaRule: false,
        mappingNumber: 0,
      },
      leftQueryParams: {
        busiCode: "74215",
        msgType: "xml",
      },
      rightQueryParams: {
        busiCode: "74215",
        msgType: "xml",
      },
      leftTreeSelectFlag: false,
      rightTreeSelectFlag: false,
      leftNodeArray: [],
      rightNodeArray: [],
      /** 新增映射关系弹出页数据 */
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 表单参数
      form: {},
      modifyTitle: "",
      modifyOpen: false,
      modifyForm: {},
      isLook: false,
      loopForm: [],
      /** 映射函数部分 */
      functioParamterCount: 0,
      funcInfoList: undefined,
      funcInfoTotal: undefined,
      addDicts: {},
      addFormItemInfo: {},
      jsonObjParameter: {},
    }
  },
  methods: {
    /** 查询左侧下拉树结构 */
    getLeftMsgTree() {
      this.loading = true;
      this.$refs.leftQueryParams.validate(valid => {
        if (!valid) {
          return false;
        }
        let params = this.leftQueryParams;
        getMsgTreeSelect(params).then(response => {
          this.leftMsgOptions = response.data;
          this.loading = false;
          this.leftTreeSelectFlag = true;
          this.flushTableList();
        });
      });
    },
    /** 查询右侧下拉树结构 */
    getRightMsgTree() {
      this.loading = true;
      this.$refs.rightQueryParams.validate(valid => {
        if (!valid) {
          return false;
        }
        let params = this.rightQueryParams;
        getMsgTreeSelect(params).then(response => {
          this.rightMsgOptions = response.data;
          this.loading = false;
          this.rightTreeSelectFlag = true;
          this.flushTableList();
        });
      });
    },
    /** 左侧下拉树重置清空 */
    leftResetQuery() {
      this.leftTreeSelectFlag = false;
      this.leftQueryParams.busiCode = undefined;
      this.leftQueryParams.msgType = undefined;
      this.leftMsgOptions = undefined;
      this.leftNodeArray = [];
    },
    /** 右侧下拉树重置清空 */
    rightResetQuery() {
      this.rightTreeSelectFlag = false;
      this.rightQueryParams.busiCode = undefined;
      this.rightQueryParams.msgType = undefined;
      this.rightMsgOptions = undefined;
      this.rightNodeArray = [];
    },
    /** 自适应表格列宽 */
    flexColumnWidth (str, arr1, flag = 'max') {
      // str为该列的字段名(传字符串);tableData为该表格的数据源(传变量);
      // flag为可选值，可不传该参数,传参时可选'max'或'equal',默认为'max'
      // flag为'max'则设置列宽适配该列中最长的内容,flag为'equal'则设置列宽适配该列中第一行内容的长度。
      str = str + ''
      let columnContent = ''
      if (!arr1 || !arr1.length || arr1.length === 0 || arr1 === undefined) {
        return
      }
      if (!str || !str.length || str.length === 0 || str === undefined) {
        return
      }
      if (flag === 'equal') {
        // 获取该列中第一个不为空的数据(内容)
        for (let i = 0; i < arr1.length; i++) {
          if (arr1[i][str].length > 0) {
            // console.log('该列数据[0]:', arr1[0][str])
            columnContent = arr1[i][str]
            break
          }
        }
      } else {
        // 获取该列中最长的数据(内容)
        let index = 0
        for (let i = 0; i < arr1.length; i++) {
          if (arr1[i][str] === null) {
            return
          }
          const now_temp = arr1[i][str] + ''
          const max_temp = arr1[index][str] + ''
          if (now_temp.length > max_temp.length) {
            index = i
          }
        }
        columnContent = arr1[index][str]
      }
      // console.log('该列数据[i]:', columnContent)
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
          flexWidth += 8
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
    /** 底部报表查询 */
    getList() {
      this.loading = true;
      this.queryParams.sourceBusiCode = this.leftQueryParams.busiCode;
      this.queryParams.sourceMsgType = this.leftQueryParams.msgType;
      this.queryParams.targetBusiCode = this.rightQueryParams.busiCode;
      this.queryParams.targetMsgType = this.rightQueryParams.msgType;

      mappingList(this.queryParams).then(response => {
          this.tableData = response.rows;
          // 右侧树 进行处理，存在映射规则的节点增加标记
          this.traverseRightTree(this.rightMsgOptions, ACTION_MAPPING_HIGHLIGHT);

          this.total = response.total;
          this.loading = false;
        }
      );
    },
    traverseRightTree(nodes, action) {
      if (!nodes || nodes.length === 0) return;
      nodes.forEach(node => {
        if(action == ACTION_MAPPING_HIGHLIGHT){
          for(let item of this.tableData){
            if(item.targetNumber == node.number){
              node.havaRule = true;
              node.mappingNumber = item.sourceNumber;
            }
          }
        }else if(action == ACTION_RELOAD_HAVERULE){
          node.havaRule = false;
        }
        if (node.children && node.children.length > 0) {
          this.traverseRightTree(node.children, action);
        }
      });
      // 这个动作是为了el-tree重新渲染
      this.rightMsgOptions = [...this.rightMsgOptions]
    },
    traverseLeftTree(nodes, mappingNumber, action) {
      if (!nodes || nodes.length === 0) return;
      nodes.forEach(node => {
        if(action == ACTION_MAPPING_HIGHLIGHT){
          if(mappingNumber == node.number){
            node.havaRule = !node.havaRule;
          }
        }else if(action == ACTION_RELOAD_HAVERULE){
          node.havaRule = false;
        }

        if (node.children && node.children.length > 0) {
          this.traverseLeftTree(node.children, mappingNumber, action);
        }
      });
      // 这个动作是为了el-tree重新渲染
      this.rightMsgOptions = [...this.rightMsgOptions]
    },

    /** 通过映射关系，使左侧树节点高亮 */
    heightLight(data){
      this.traverseLeftTree(this.leftMsgOptions, data.mappingNumber, ACTION_MAPPING_HIGHLIGHT);
      // 这个动作是为了el-tree重新渲染
      this.leftMsgOptions = [...this.leftMsgOptions]
    },
    /** 获取映射函数列表 */
    getFuncList(){
      let param = {}
      listInfo(param).then(response => {
        this.funcInfoList = response.rows;
        this.funcInfoTotal = response.total;

        for(var item of this.funcInfoList){
          if(item.optionData != undefined && item.optionData != null && item.optionData != ""){
            getDicts(item.optionData).then(responseIn => {
              this.addDicts[responseIn.data[0].dictType] = responseIn.data;
            });
          }
          if(item.parameterType != undefined && item.parameterType != null && item.parameterType != ""){
            getDicts(item.parameterType).then(responseIn => {
              this.addFormItemInfo[responseIn.data[0].dictType] = responseIn.data;
            });
          }
        }
      })
    },
    /** 刷新底部表格 */
    flushTableList(){
      if(this.leftTreeSelectFlag && this.rightTreeSelectFlag){
        this.traverseLeftTree(this.leftMsgOptions, 0, ACTION_RELOAD_HAVERULE);
        this.traverseRightTree(this.rightMsgOptions, ACTION_RELOAD_HAVERULE);
        this.getList();
      }
    },
    /** 左侧树节点选择触发动作 */
    leftHandleNodeClick(data, SelectedObj) {
      this.leftNodeArray = SelectedObj.checkedNodes;
    },
    /** 右侧树节点选择触发动作 */
    rightHandleNodeClick(data, SelectedObj) {
      this.rightNodeArray = SelectedObj.checkedNodes;
      this.loopForm = [];
      for( var i = 0 ; i < this.rightNodeArray.length ; i++ ){
        this.$set(this.rightNodeArray[i],'parameterInputPart',[])
        this.$set(this.rightNodeArray[i],'parameterSelectPart',[])

        this.$set(this.rightNodeArray[i], 'funcRemarkFlag', false)
        this.$set(this.rightNodeArray[i], 'funcRemark', "")
        if(this.leftQueryParams.msgType == "flStr"){
          this.$set(this.rightNodeArray[i],'sourceAlignmentFlag', true)
          this.$set(this.rightNodeArray[i],'sourceAlignment', "")
          this.$set(this.rightNodeArray[i],'sourcePlaceholdersFlag', true)
          this.$set(this.rightNodeArray[i],'sourcePlaceholders', "")
          if(this.rightQueryParams.msgType == "flStr"){
            this.$set(this.rightNodeArray[i],'targetAlignmentFlag', true)
            this.$set(this.rightNodeArray[i],'targetAlignment', "")
            this.$set(this.rightNodeArray[i],'targetPlaceholdersFlag', true)
            this.$set(this.rightNodeArray[i],'targetPlaceholders', "")
          }
        }
        let loopFormItem = this.rightNodeArray[i];
        loopFormItem.key = i + "-loopFormItem";
        this.loopForm.push(loopFormItem);
      }
    },
    /** 节点映射规则新增 */
    handleAdd() {
      if(this.leftNodeArray.length <= 0){
        this.$notify({
          title: '警告',
          message: '请在原报文树中选择一个节点',
          type: 'warning'
        });
      }else if(this.leftNodeArray.length > 1){
        this.$notify({
          title: '警告',
          message: '当前仅支持从原报文树中做一对一或一对多映射，请确认原报文树中是否仅选择了唯一节点',
          type: 'warning'
        });
      }else if(this.rightNodeArray.length <= 0){
        this.$notify({
          title: '警告',
          message: '请在目标报文树中选择任意数量的节点作为目标节点',
          type: 'warning'
        });
      }else{
        this.reset();
        this.form.sourceNodeArray = this.leftNodeArray;
        this.form.targetNodeArray = this.rightNodeArray;
        this.open = true;
        this.title = "处理字段映射";
      }
    },
    /** 节点映射规则修改 */
    handleUpdate(row) {
      this.modifyReset();
      this.modifyForm = row;
      let jsonStrParameter = row.parameter;
      this.jsonObjParameter = JSON.parse(jsonStrParameter);

      this.$set(this.modifyForm, 'parameterInputPart',[])
      this.$set(this.modifyForm, 'parameterSelectPart',[])
      this.$set(this.modifyForm, 'sourceAlignment', this.jsonObjParameter.sourceAlignment)
      this.$set(this.modifyForm, 'sourcePlaceholders', this.jsonObjParameter.sourcePlaceholders)
      this.$set(this.modifyForm, 'targetAlignment', this.jsonObjParameter.targetAlignment)
      this.$set(this.modifyForm, 'targetPlaceholders', this.jsonObjParameter.targetPlaceholders)

      this.selectChange(row.operate, this.modifyForm);

      this.modifyTitle = "修改字段映射规则";
      this.isLook = false;
    },
    /** 节点映射规则查看 */
    look(row){
      this.modifyReset();
      this.modifyForm = row;
      let jsonStrParameter = row.parameter;
      this.jsonObjParameter = JSON.parse(jsonStrParameter);

      this.$set(this.modifyForm, 'parameterInputPart',[])
      this.$set(this.modifyForm, 'parameterSelectPart',[])
      this.$set(this.modifyForm, 'sourceAlignment', this.jsonObjParameter.sourceAlignment)
      this.$set(this.modifyForm, 'sourcePlaceholders', this.jsonObjParameter.sourcePlaceholders)
      this.$set(this.modifyForm, 'targetAlignment', this.jsonObjParameter.targetAlignment)
      this.$set(this.modifyForm, 'targetPlaceholders', this.jsonObjParameter.targetPlaceholders)

      this.selectChange(row.operate, this.modifyForm);

      this.modifyTitle = "查看字段映射规则";
      this.isLook = true;
    },
    modifyReset(){
      this.modifyForm = {};
      this.jsonObjParameter = {};
      this.modifyOpen = true;
    },
    /** 节点映射规则删除 */
    handleDelete(row) {
      delMapping(row.id).then(response => {
        this.$modal.msgSuccess("删除成功");
        this.getRightMsgTree();
      });
    },
    /** 提交按钮 */
    submitForm: function() {
      // this.$refs["form"].validate(valid => {
      //   if (valid) {
          if (this.modifyForm.id != undefined) {
            modifyMapping(this.processingForUpdate(this.modifyForm)).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.modifyOpen = false;
              this.getList();
            });
          } else {
            addMapping(this.processing(this.loopForm)).then(response => {
              this.$message({
                showClose: true,
                message: '添加成功',
                type: 'success'
              });
              this.open = false;
              this.getList();
              this.$refs.leftTree.setCheckedKeys([]);
              this.$refs.rightTree.setCheckedKeys([]);
            });
          }
        // }
      // });
    },

    /** 节点映射规则参数json格式处理 */
    processing(from){
      let jsonList = [];
      for(let rightNode of from){
        let leftNode = this.leftNodeArray[0];
        let mocksysMappingInfo = {
          "sourceBusiCode" : leftNode.busiCode,
          "sourceMsgType" : leftNode.msgType,
          "sourceFieldName" : leftNode.label,
          "sourceNumber" : leftNode.number,
          "sourcePath" : leftNode.path,
          "sourceSign" : leftNode.sign,
          "sourceLocation" : leftNode.location,
          "sourceLength" : leftNode.length,
          "sourceLoopCount" : leftNode.loopCount,
          "sourceLoopLength" : leftNode.loopLength,
        }

        var num = 1;
        mocksysMappingInfo.targetBusiCode = rightNode.busiCode;
        mocksysMappingInfo.targetMsgType = rightNode.msgType;
        mocksysMappingInfo.targetFieldName = rightNode.label;
        mocksysMappingInfo.targetNumber = rightNode.number;
        mocksysMappingInfo.targetPath = rightNode.path;
        mocksysMappingInfo.targetSign = rightNode.sign;
        mocksysMappingInfo.targetLocation = rightNode.location;
        mocksysMappingInfo.targetLength = rightNode.length;
        mocksysMappingInfo.targetLoopCount = rightNode.loopCount;
        mocksysMappingInfo.targetLoopLength = rightNode.loopLength;

        mocksysMappingInfo.action = rightNode.action;
        mocksysMappingInfo.operate = rightNode.operate;

        var parameter = {}

        if(rightNode.sourceAlignmentFlag){
          parameter.sourceAlignment = rightNode.sourceAlignment;
        }
        if(rightNode.sourcePlaceholdersFlag){
          parameter.sourcePlaceholders = rightNode.sourcePlaceholders;
        }
        if(rightNode.targetAlignmentFlag){
          parameter.targetAlignment = rightNode.targetAlignment;
        }
        if(rightNode.targetPlaceholdersFlag){
          parameter.targetPlaceholders = rightNode.targetPlaceholders;
        }

        if(rightNode.parameterSelectPart != null){
          if(rightNode.parameterSelectPart.length > 0){
            for(let parameterSelect of rightNode.parameterSelectPart){
              parameter["value" + num] = parameterSelect.value;
              num++;
            }
          }
        }
        if(rightNode.parameterInputPart != null){
          if(rightNode.parameterInputPart.length > 0){
            for(let parameterInput of rightNode.parameterInputPart){
              parameter["value" + num] = parameterInput.value;
              num++;
            }
          }
        }
        mocksysMappingInfo.parameter = JSON.stringify(parameter);
        jsonList.push(mocksysMappingInfo);
      }
      return jsonList;
    },

    processingForUpdate(from){
      let mocksysMappingInfo = {
        "id": from.id,
        "sourceBusiCode" : from.sourceBusiCode,
        "sourceMsgType" : from.sourceMsgType,
        "sourceFieldName" : from.sourceFieldName,
        "sourceNumber" : from.sourceNumber,
        "sourcePath" : from.sourcePath,
        "sourceSign" : from.sourceSign,
        "sourceLocation" : from.sourceLocation,
        "sourceLength" : from.sourceLength,
        "sourceLoopCount" : from.sourceLoopCount,
        "sourceLoopLength" : from.sourceLoopLength,

      }

      var num = 1;
      mocksysMappingInfo.targetBusiCode = from.targetBusiCode;
      mocksysMappingInfo.targetMsgType = from.targetMsgType;
      mocksysMappingInfo.targetFieldName = from.targetFieldName;
      mocksysMappingInfo.targetNumber = from.targetNumber;
      mocksysMappingInfo.targetPath = from.targetPath;
      mocksysMappingInfo.targetSign = from.targetSign;
      mocksysMappingInfo.targetLocation = from.targetLocation;
      mocksysMappingInfo.targetLength = from.targetLength;
      mocksysMappingInfo.targetLoopCount = from.targetLoopCount;
      mocksysMappingInfo.targetLoopLength = from.targetLoopLength;

      mocksysMappingInfo.action = from.action;
      mocksysMappingInfo.operate = from.operate;

      var parameter = {}

      if(from.sourceAlignment){
        parameter.sourceAlignment = from.sourceAlignment;
      }
      if(from.sourcePlaceholders){
        parameter.sourcePlaceholders = from.sourcePlaceholders;
      }
      if(from.targetAlignment){
        parameter.targetAlignment = from.targetAlignment;
      }
      if(from.targetPlaceholders){
        parameter.targetPlaceholders = from.targetPlaceholders;
      }

      if(from.parameterSelectPart != null){
        if(from.parameterSelectPart.length > 0){
          for(let parameterSelect of from.parameterSelectPart){
            parameter["value" + num] = parameterSelect.value;
            num++;
          }
        }
      }
      if(from.parameterInputPart != null){
        if(from.parameterInputPart.length > 0){
          for(let parameterInput of from.parameterInputPart){
            parameter["value" + num] = parameterInput.value;
            num++;
          }
        }
      }
      mocksysMappingInfo.parameter = JSON.stringify(parameter);
      return mocksysMappingInfo;
    },
    /** 取消按钮 */
    cancel() {
      this.loopForm = [];
      this.open = false;
      this.modifyOpen = false;
      this.reset();
      this.$refs.leftTree.setCheckedKeys([]);
      this.$refs.rightTree.setCheckedKeys([]);
      this.leftNodeArray = [];
      this.rightNodeArray = [];
    },
    /** 新增表单重置 */
    reset() {
      this.form = {
        action: undefined,
        operate: undefined,
        parameter: undefined,
        sourceNodeArray: [],
        targetNodeArray: [],
      };
      this.resetForm("form");
    },
/*************************************************************************************/
    selectChange(value, node){
      node.parameterInputPart = [];
      node.parameterSelectPart = [];
      let count = 0;
      let optionName = "";
      let parameterInfo = "";
      // 循环 报文模板函数数据表，经过函数名称比对，找到对应的函数信息
      for(let item of this.funcInfoList){
        if(item.name == value){
          // 拿到了参数个数
          count = item.parameterCount
          // 拿到了参数类型
          parameterInfo = item.parameterType;
          // 如果带下拉选，此项有值
          optionName = item.optionData;
        }
      }
      let parameterType = this.addFormItemInfo[parameterInfo];
      let baseKey = new Date().getTime()

      if( parameterType != null && parameterType != undefined ){
        for(let i = 0 ; i < parameterType.length ; i++ ){
          if(parameterType[i].dictValue == "input"){
            let parameterItem = {
              label: "函数参数" + (i+1),
              key : i + baseKey + "-InputPart",
              placeholder : parameterType[i].dictLabel,
              type: "input",
              value: this.jsonObjParameter["value" + (i + 1)]
            };
            node.parameterInputPart.push(parameterItem)
          }else if(parameterType[i].dictValue == "select"){
            let parameterItem = {
              label: "函数参数" + (i+1),
              key : i + baseKey + "-SelectPart",
              placeholder : parameterType[i].dictLabel,
              options : this.addDicts[optionName],
              type: "select",
              value: this.jsonObjParameter["value" + (i + 1)]
            };
            node.parameterSelectPart.push(parameterItem)
          }
        }
      }

      /** 增加函数方法使用说明 */
      for(let item of this.dict.type.mock_function){
        if(item.value == value){
          node.funcRemarkFlag = true;
          node.funcRemark = item.raw.remark;
        }
      }
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

  .dynamic-form-items-container {
    /* 设置容器的位置 */
    position: relative;
    top: -20px; /* 例如，向上偏移 20px，可以根据实际情况调整 */
  }

  input::placeholder {
    color: #999; /* 设置空格颜色为灰色 */
  }

  input {
    letter-spacing: 2px; /* 增加空格大小 */
    background-color: rgba(0, 0, 0, 0.1); /* 设置空格背景色为浅灰色 */
  }
</style>
