<template>
  <div class="content_style">
    <div v-for="(item, j) in loopForm" :key="item.key" class="form_content">
      <el-form class="form" :ref="`loopForm-${item.key}`" :model="item" label-width="100px">
        <el-button class="delete_class_form" type="text" @click="deleteForm(loopForm, item.key)">删除</el-button>
        <el-row>
          <el-col :span="12">
            <el-form-item label="操作类型" prop="action" label-width="100px">
              <el-select v-model="item.action" placeholder="请选择操作类型">
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
              <el-select v-model="item.operate" placeholder="请选择操作函数">
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
        <el-row>
          <el-col :span="24">
            <el-form-item label="函数参数">
              <el-input v-model="item.parameter" prop="parameter" type="textarea" placeholder="请输入函数参数"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
      </el-form>
    </div>
    <el-button plain type="text" icon="el-icon-plus" class="add_form_class" @click="addForm(loopForm, 'loopFormItem')"></el-button>
    <div class="button_group">
      <el-button type="primary" class="reset" size="small" @click="reset">重置</el-button>
      <el-button type="primary" class="confirm" size="small" @click="confirm">确定</el-button>
    </div>
  </div>
</template>
<script>
export default {
  dicts: ['mock_msg_type', 'mock_mapping_action', 'mock_function'],
  data() {
    return {
      loopForm: [this.getDefaultForm(new Date().getTime())],
    }
  },
  methods: {
    getDefaultForm(baseKey) {
      return {
        key: `${baseKey}-form`,
      }
    },
    deleteForm(item, key) {
      const index = item.findIndex(v => v.key === key)
      if(index !== -1) {
        item.splice(index, 1)
      }
    },
    addForm(item, type) {
      const baseKey = new Date().getTime()
      const addMethod = {
        loopFormItem: this.getDefaultForm
      }[type]
      console.log(addMethod);
      console.log(addMethod(baseKey));
      console.log(item);
      item.push(addMethod(baseKey))
    },
    reset() {
      this.loopForm = [this.getDefaultForm(new Date().getTime())];
    },
    confirm() {
      this.$confirm('确定提交?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        console.log(this.loopForm);
        this.$message({
          type: 'success',
          message: '提交成功'
        });
      }).catch(() => {});
    }
  }

}
</script>
