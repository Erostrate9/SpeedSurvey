<template>
  <div class="main">
    <a-form
      id="formLogin"
      class="user-layout-login"
      ref="formLogin"
      :form="form"
      @submit="handleSubmit"
    >
      <a-form-item>
        <a-input
          size="large"
          type="text"
          placeholder="用户名"
          v-decorator="[
            'username',
            {
              rules: [{ required: true, message: '请输入用户名' }],
              validateTrigger: 'blur',
            },
          ]"
        >
          <a-icon
            slot="prefix"
            type="user"
            :style="{ color: 'rgba(0,0,0,.25)' }"
          />
        </a-input>
      </a-form-item>
      <a-form-item>
        <a-input-password
          size="large"
          placeholder="密码"
          v-decorator="[
            'password',
            {
              rules: [{ required: true, message: '请输入密码' }],
              validateTrigger: 'blur',
            },
          ]"
        >
          <a-icon
            slot="prefix"
            type="lock"
            :style="{ color: 'rgba(0,0,0,.25)' }"
          />
        </a-input-password>
      </a-form-item>

      <a-form-item>
        <a-checkbox v-decorator="['rememberMe', { valuePropName: 'checked' }]"
          >记住密码</a-checkbox
        >
        <router-link
          :to="{ name: 'recover', params: { user: '' } }"
          class="forge-password"
          style="float: right"
          >忘记密码</router-link
        >
      </a-form-item>

      <a-form-item style="margin-top: 24px">
        <a-button
          size="large"
          type="primary"
          htmlType="submit"
          class="login-button"
          :loading="state.loginBtn"
          :disabled="state.loginBtn"
          >登录
        </a-button>
        <router-link class="register-link" :to="{ name: 'register' }">
          注册用户
        </router-link>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { timeFix } from "@/utils/util";

export default {
  components: {},
  data() {
    return {
      form: this.$form.createForm(this),
      state: {
        loginBtn: false,
      },
    };
  },
  created() {},
  methods: {
    ...mapActions(["Login"]),

    handleSubmit(e) {
      console.log(e);

      e.preventDefault();
      const {
        form: { validateFields },
        state,
        Login,
      } = this;
      console.log({ validateFields });
      state.loginBtn = true;
      const validateFieldsKey = ["username", "password"];

      validateFields(validateFieldsKey, { force: true }, (err, values) => {
        console.log(err);
        console.log(values);
        if (!err) {
          console.log("Login Form...", values);
          const loginParams = { ...values };
          Login(loginParams)
            .then((res) => this.loginSuccess(res))
            .catch((err) => this.requestFailed(err))
            .finally(() => {
              state.loginBtn = false;
            });
        } else {
          setTimeout(() => {
            state.loginBtn = false;
          }, 600);
        }
      });
    },
    loginSuccess(res) {
      console.log(res);
      this.$router.push({ path: "/" });
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: "欢迎",
          description: `${timeFix()}，欢迎回来`,
        });
      }, 1000);
      this.isLoginError = false;
    },
    requestFailed(err) {
      this.isLoginError = true;
      this.$notification["error"]({
        message: "错误",
        description: err.msg || "请求出现错误，请稍后再试",
        duration: 4,
      });
    },
  },
  mounted() {},
};
</script>

<style lang="less" scoped>
.register-link {
  float: right;
  line-height: 40px;
}
.user-layout-login {
  label {
    font-size: 14px;
  }

  .forge-password {
    font-size: 14px;
  }

  button.login-button {
    padding: 0 15px;
    font-size: 16px;
    height: 40px;
    width: 50%;
  }

  .register-container {
    .register {
      float: right;
    }
  }
}
</style>
