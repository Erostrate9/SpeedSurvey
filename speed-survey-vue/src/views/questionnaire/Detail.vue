<template>
  <div style="background-color: #f0f2f5">
    <a-row>
      <a-col style="height: 100vh" :xs="0" :md="0" :lg="2" :xl="4"></a-col>
      <a-col class="container" :xs="24" :md="24" :lg="20" :xl="16">
        <div class="header">
          <div class="logo">
            <img src="http://qny.yyangjiao.top/logo.png" alt="" />
            <span>问卷鸭</span>
          </div>
          <div class="description">一款新型问卷调查工具</div>
        </div>
        <a-spin :spinning="state.spinning">
          <div class="main">
            <!-- <div style="background-color: rgba(0, 0, 0, 0.05); height: 4px"></div> -->
            <h1
              style="
                text-align: center;
                margin-top: 40px;
                font-size: 30px;
                font-weight: normal;
              "
            >
              {{ questionnaire.title }}
            </h1>
            <p>{{ questionnaire.description }}</p>
            <a-form
              :form="form"
              @submit="handleSubmit"
              layout="horizontal"
              :colon="false"
            >
              <a-form-item
                v-for="questionComplete in questionCompletes"
                :key="questionComplete.question.id"
              >
                <span slot="label">
                  <span style="padding-right: 10px"
                    ><b
                      >{{
                        questionComplete.question.orderNumber >= 10
                          ? questionComplete.question.orderNumber
                          : "0" + questionComplete.question.orderNumber
                      }}.</b
                    ></span
                  >
                  <span>
                    <b>{{ questionComplete.question.title }}</b>
                  </span>
                </span>
                <a-input
                  style="width: 260px"
                  v-if="questionComplete.question.type == 0"
                  v-decorator="[
                    questionComplete.question.title,
                    {
                      rules: [
                        {
                          required: questionComplete.question.isRequired,
                          message: '请填入内容',
                        },
                      ],
                      validateTrigger: 'blur',
                    },
                  ]"
                >
                </a-input>
                <a-radio-group
                  v-else-if="questionComplete.question.type == 1"
                  v-decorator="[
                    questionComplete.question.title,
                    {
                      rules: [
                        {
                          required: questionComplete.question.isRequired,
                          message: '请选择一项',
                        },
                      ],
                      validateTrigger: 'blur',
                    },
                  ]"
                >
                  <a-radio
                    style="display: block"
                    v-for="option in questionComplete.options"
                    :key="option.id"
                    :value="option.content"
                  >
                    {{ option.content }}
                  </a-radio>
                </a-radio-group>
                <a-checkbox-group
                  v-else-if="questionComplete.question.type == 2"
                  v-decorator="[
                    questionComplete.question.title,
                    {
                      rules: [
                        {
                          required: questionComplete.question.isRequired,
                          message: '请至少选择一项',
                        },
                      ],
                      validateTrigger: 'blur',
                    },
                  ]"
                >
                  <a-checkbox
                    style="display: block; margin: 0"
                    v-for="option in questionComplete.options"
                    :key="option.id"
                    :value="option.content"
                  >
                    {{ option.content }}
                  </a-checkbox>
                </a-checkbox-group>
              </a-form-item>
              <a-form-item class="submit" v-if="!state.spinning">
                <a-button
                  type="primary"
                  htmlType="submit"
                  :loading="state.submitBtnLoading"
                  :disabled="state.submitBtnDisabled"
                  >提交
                </a-button></a-form-item
              >
            </a-form>
          </div>
        </a-spin>
      </a-col>
      <a-col style="height: 100vh" :xs="0" :md="0" :lg="2" :xl="4"></a-col>
    </a-row>
  </div>
</template>

<script>
import { getDetail, submit } from "@/api/questionnaire";
export default {
  data() {
    return {
      form: this.$form.createForm(this, { name: "questionnaire" }),
      state: {
        submitBtnLoading: false,
        submitBtnDisabled: false,
        spinning: true,
      },
      questionnaire: {},
      questionCompletes: [],
      ansers: [],
    };
  },
  methods: {
    handleSubmit(e) {
      e.preventDefault();
      const {
        form: { validateFields },
        Submit,
        state,
      } = this;
      state.submitBtnLoading = true;
      state.submitBtnDisabled = true;
      validateFields({ force: true }, (err, values) => {
        if (!err) {
          var index = 0;
          for (var key in values) {
            if (this.questionCompletes[index].question.type == 0) {
              this.ansers[index].text = values[key];
            } else if (this.questionCompletes[index].question.type == 1) {
              this.questionCompletes[index].options.forEach((option) => {
                if (option.content == values[key]) {
                  this.ansers[index].optionId = option.id;
                }
              });
            } else if (this.questionCompletes[index].question.type == 2) {
              this.ansers[index].optionIds = [];
              this.questionCompletes[index].options.forEach((option) => {
                if (values[key].includes(option.content)) {
                  this.ansers[index].optionIds.push(option.id);
                }
              });
            }
            index++;
          }
          Submit(2, this.ansers)
            .then((res) => {
              this.$notification.success({
                message: "成功",
                description: res.msg,
              });
              state.submitBtnLoading = false;
            })
            .catch((err) => {
              this.requestFailed(err);
              setTimeout(() => {
                state.submitBtnLoading = false;
                state.submitBtnDisabled = false;
              }, 600);
            });
        } else {
          setTimeout(() => {
            state.submitBtnLoading = false;
            state.submitBtnDisabled = false;
          }, 600);
        }
      });
    },
    GetDetail(parameter) {
      return new Promise((resolve, reject) => {
        getDetail(parameter)
          .then((response) => {
            if (response.code == 0) {
              resolve(response);
            } else if (response.code == 500) {
              reject(response);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
    },
    Submit(userId, answers) {
      return new Promise((resolve, reject) => {
        submit(userId, answers)
          .then((response) => {
            if (response.code == 0) {
              resolve(response);
            } else if (response.code == 500) {
              reject(response);
            }
          })
          .catch((error) => {
            reject(error);
          });
      });
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
  mounted() {
    var parameter = {
      id: this.$route.query.id,
    };
    this.GetDetail(parameter)
      .then((res) => {
        this.questionnaire = res.data.questionnaire;
        this.questionCompletes = res.data.questionCompletes;
        this.state.spinning = false;
        this.questionCompletes.forEach((questionComplete) => {
          var answer = {
            questionId: questionComplete.question.id,
            type: questionComplete.question.type,
          };
          this.ansers.push(answer);
        });
      })
      .catch((err) => this.requestFailed(err));
  },
};
</script>

<style>
.container {
  background-color: #fff;
}
.main {
  margin: 40px;
}
.header {
  background-color: #f0f2f5;
  height: 100px;
  text-align: center;
  padding: 17px;
}
.logo {
  height: 44px;
  display: flex;
  align-items: center;
  vertical-align: top;
  justify-content: center;
}
.logo img {
  height: 44px;
  width: 44px;
  margin-right: 16px;
}
.logo span {
  font-size: 33px;
  color: rgba(0, 0, 0, 0.85);
  font-weight: 600;
}
.submit {
  width: 64px;
  margin: 0 auto;
  display: flex;
  justify-content: center;
}
</style>