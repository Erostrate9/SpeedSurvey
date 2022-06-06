<template>
  <a-result :isSuccess="true" :content="false" :title="username">
    <template #sub-title></template>
    <template #extra>
      <a-button size="large" type="primary">查看邮箱</a-button>
      <a-button size="large" style="margin-left: 8px" @click="goHomeHandle"
        >返回首页</a-button
      >
    </template>
  </a-result>
</template>

<script>
export default {
  name: "RegisterResult",
  data() {
    return {
      form: {},
    };
  },
  computed: {
    username() {
      const v = (this.form && this.form.username) || "xxx";
      return `你的账户：${v} 注册成功`;
    },
    email() {
      const v = (this.form && this.form.email) || "xxx";
      return `你的邮箱：${v} 注册成功`;
    },
  },
  created() {
    this.form = this.$route.params;
  },
  methods: {
    goHomeHandle() {
      this.$router.push({ name: "login" });
    },
  },
};
</script>

<style scoped>
</style>
