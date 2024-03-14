<template>
  <div>
    <el-form-item v-for="(item, index) in items" :key="index" :label="item.label">
      <!-- 根据 item 的类型渲染不同的表单项 -->
      <el-input v-if="item.type === 'input'" v-model="item.value" :placeholder="item.placeholder" :disabled="disabled"></el-input>
      <el-select v-else-if="item.type === 'select'" v-model="item.value" :placeholder="item.placeholder" :disabled="disabled">
        <el-option v-for="option in item.options" :key="option.dictValue" :label="option.dictLabel" :value="option.dictValue"></el-option>
      </el-select>
      <!-- 其他表单项类型 -->
    </el-form-item>
  </div>
</template>

<script>
export default {
  props: {
    items: {
      type: Array,
      default: () => []
    },
    disabled: {
      type: Boolean,
      default: false // 设置默认值为 false，如果不传递该属性，默认为 false
    }

  },
  // created() {
  //   console.log("this.items", this.items)
  // },
  // methods:{
  //
  // },
};
</script>
