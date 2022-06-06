<template>
  <div class="main user-layout-register">
    <a-form
      ref="formRegister"
      :form="form"
      id="formRegister"
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

      <a-popover
        placement="rightTop"
        :trigger="['focus']"
        :getPopupContainer="(trigger) => trigger.parentElement"
        v-model="state.passwordLevelChecked"
      >
        <template slot="content">
          <div :style="{ width: '240px' }">
            <div :class="['user-register', passwordLevelClass]">
              {{ passwordLevelName }}
            </div>
            <a-progress
              :percent="state.percent"
              :showInfo="false"
              :strokeColor="passwordLevelColor"
            />
            <div style="margin-top: 10px">
              <span>至少输入6个字符并且避免使用太简单的密码</span>
            </div>
          </div>
        </template>
        <a-form-item>
          <a-input-password
            size="large"
            @click="handlePasswordInputClick"
            placeholder="密码"
            v-decorator="[
              'password',
              {
                rules: [
                  { required: true, message: '输入密码' },
                  { validator: this.handlePasswordLevel },
                ],
                validateTrigger: ['change', 'blur'],
              },
            ]"
          >
            <a-icon
              type="lock"
              slot="prefix"
              :style="{ color: 'rgba(0,0,0,.25)' }"
            />
          </a-input-password>
        </a-form-item>
      </a-popover>

      <a-form-item>
        <a-input-password
          size="large"
          placeholder="确认密码"
          v-decorator="[
            'password2',
            {
              rules: [
                { required: true, message: '再次输入密码' },
                { validator: this.handlePasswordCheck },
              ],
              validateTrigger: ['blur'],
            },
          ]"
        >
          <a-icon
            type="check"
            slot="prefix"
            :style="{ color: 'rgba(0,0,0,.25)' }"
          />
        </a-input-password>
      </a-form-item>
      <a-form-item>
        <a-input
          size="large"
          type="text"
          placeholder="邮箱"
          v-decorator="[
            'email',
            {
              rules: [
                {
                  required: true,
                  type: 'email',
                  message: '输入注册邮箱',
                },
              ],
              validateTrigger: ['blur'],
            },
          ]"
        >
          <a-icon
            type="mail"
            slot="prefix"
            :style="{ color: 'rgba(0,0,0,.25)' }"
          />
        </a-input>
      </a-form-item>
      <a-row :gutter="16">
        <a-col class="gutter-row" :span="16">
          <a-form-item>
            <a-input
              size="large"
              type="text"
              placeholder="验证码"
              v-decorator="[
                'captcha',
                {
                  rules: [{ required: true, message: '请输入验证码' }],
                  validateTrigger: 'blur',
                },
              ]"
            >
              <a-icon
                slot="prefix"
                type="message"
                :style="{ color: 'rgba(0,0,0,.25)' }"
              />
            </a-input>
          </a-form-item>
        </a-col>
        <a-col class="gutter-row" :span="8">
          <a-button
            class="getCaptcha"
            size="large"
            :disabled="state.smsSendBtn"
            @click.stop.prevent="getCaptcha"
            v-text="(!state.smsSendBtn && '获取验证码') || state.time + ' s'"
          ></a-button>
        </a-col>
      </a-row>

      <a-form-item>
        <a-button
          size="large"
          type="primary"
          htmlType="submit"
          class="register-button"
          :loading="state.registerBtn"
          :disabled="state.registerBtn"
          >注册
        </a-button>
        <router-link class="login-link" :to="{ name: 'login' }">
          使用已有账号登录
        </router-link>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import { mapActions } from "vuex";
import { scorePassword } from "@/utils/util";
import { deviceMixin } from "@/store/device-mixin";

const levelNames = {
  0: "密码长度过短",
  1: "密码强度: 低",
  2: "密码强度: 中",
  3: "密码强度：高",
};
const levelClass = {
  0: "error",
  1: "error",
  2: "warning",
  3: "success",
};
const levelColor = {
  0: "#ff0000",
  1: "#ff0000",
  2: "#ff7e05",
  3: "#52c41a",
};
export default {
  name: "Register",
  components: {},
  data() {
    return {
      form: this.$form.createForm(this),

      state: {
        time: 60,
        level: 0,
        passwordLevel: 0,
        passwordLevelChecked: false,
        percent: 0,
        progressColor: "#FF0000",
        registerBtn: false,
        smsSendBtn: false,
        registerFormInfo: {},
      },
    };
  },
  mixins: [deviceMixin],
  computed: {
    passwordLevelClass() {
      return levelClass[this.state.passwordLevel];
    },
    passwordLevelName() {
      return levelNames[this.state.passwordLevel];
    },
    passwordLevelColor() {
      return levelColor[this.state.passwordLevel];
    },
    passwordLength() {
      return this.form.getFieldValue("password");
    },
  },
  methods: {
    ...mapActions(["Register", "GetCaptcha"]),
    handlePasswordLevel(rule, value, callback) {
      if (value === undefined) {
        this.state.passwordLevelChecked = false;
        return callback();
      }
      if (value.length >= 6) {
        if (scorePassword(value) >= 0) {
          this.state.level = 1;
        }
        if (scorePassword(value) >= 60) {
          this.state.level = 2;
        }
        if (scorePassword(value) >= 80) {
          this.state.level = 3;
        }
      } else {
        this.state.level = 0;
      }
      this.state.passwordLevel = this.state.level;
      this.state.percent = this.state.level * 33;
      callback();
    },
    getCaptcha(e) {
      e.preventDefault();
      const {
        form: { validateFields },
        state,
        $message,
        $notification,
        GetCaptcha,
      } = this;

      validateFields(["email"], { force: true }, (err, values) => {
        if (!err) {
          state.smsSendBtn = true;

          const interval = window.setInterval(() => {
            if (state.time-- <= 0) {
              state.time = 60;
              state.smsSendBtn = false;
              window.clearInterval(interval);
            }
          }, 1000);

          const hide = $message.loading("验证码发送中..", 0);
          console.log(values.email);
          GetCaptcha({ email: values.email })
            .then((res) => {
              setTimeout(hide, 2500);
              $notification["success"]({
                message: "提示",
                description: res.msg,
                duration: 8,
              });
            })
            .catch((err) => {
              setTimeout(hide, 1);
              clearInterval(interval);
              state.time = 60;
              state.smsSendBtn = false;
              this.requestFailed(err);
            });
        }
      });
    },
    handlePasswordInputClick() {
      if (!this.isMobile) {
        this.state.passwordLevelChecked = true;
        return;
      }
      this.state.passwordLevelChecked = false;
    },
    handlePasswordCheck(rule, value, callback) {
      const password = this.form.getFieldValue("password");
      if (value && password && value.trim() !== password.trim()) {
        callback(new Error("两次输入的密码不匹配"));
      }
      callback();
    },

    handleSubmit(e) {
      e.preventDefault();
      const {
        form: { validateFields },
        state,
        Register,
      } = this;

      state.registerBtn = true;
      validateFields({ force: true }, (err, values) => {
        if (!err) {
          console.log("Register Form...", values);
          const registerParams = { ...values };
          const userInfo = {
            email: registerParams.email,
            username: registerParams.username,
            password: registerParams.password,
            tel: registerParams.tel,
          };
          const captcha = registerParams.captcha;
          //注册
          console.log(registerParams.captcha);
          Register({ userInfo, captcha })
            .then((res) => {
              state.registerFormInfo = { ...values };
              this.registerSuccess(res);
            })
            .catch((err) => this.requestFailed(err))
            .finally(() => {
              state.registerBtn = false;
              state.passwordLevelChecked = false;
            });
        } else {
          setTimeout(() => {
            state.registerBtn = false;
          }, 600);
        }
      });
    },
    registerSuccess(res) {
      this.$router.push({
        name: "registerResult",
        params: this.state.registerFormInfo,
      });
      // 延迟 1 秒显示欢迎信息
      setTimeout(() => {
        this.$notification.success({
          message: "欢迎",
          description: res.msg,
        });
      }, 1000);
      this.isRegisterError = false;
    },
    requestFailed(err) {
      this.isRegisterError = true;
      this.$notification["error"]({
        message: "错误",
        description: err.msg || "请求错误",
        duration: 4,
      });
    },
  },
  watch: {},
};
</script>
<style lang="less">
.user-register {
  &.error {
    color: #ff0000;
  }

  &.warning {
    color: #ff7e05;
  }

  &.success {
    color: #52c41a;
  }
}

.user-layout-register {
  .ant-input-group-addon:first-child {
    background-color: #fff;
  }
}
</style>
<style lang="less" scoped>
.user-layout-register {
  & > h3 {
    font-size: 16px;
    margin-bottom: 20px;
  }

  .getCaptcha {
    display: block;
    width: 100%;
    height: 40px;
  }

  .register-button {
    width: 50%;
  }

  .login-link {
    float: right;
    line-height: 40px;
  }
}
</style>
