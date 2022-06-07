<template>
  <div style="background-color: #f0f2f5">
    <a-row>
      <a-col style="height: 100vh" :xs="0" :md="0" :lg="2" :xl="4"></a-col>
      <a-col class="container" :xs="24" :md="24" :lg="20" :xl="16">
        <div class="header">
          <div class="logo">
            <img src="/logo.svg" alt="" />
            <span>速填</span>
          </div>
          <div class="description">一款新型问卷调查工具</div>
        </div>
        <a-spin :spinning="spinning">
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
            <a-form :form="form" @submit="handleSubmit" layout="horizontal">
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
                >
                </a-input>
                <a-radio-group v-else-if="questionComplete.question.type == 1">
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
              <a-form-item class="submit" v-if="!spinning">
                <a-button type="primary" htmlType="submit"
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
import { getDetail } from "@/api/questionnaire";
export default {
  data() {
    return {
      questionnaire: {},
      questionCompletes: [],
      spinning: true,
    };
  },
  methods: {
    handleSubmit() {},
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
    requestFailed(err) {
      this.isLoginError = true;
      this.$notification["error"]({
        message: "错误",
        description: err.msg || "请求出现错误，请稍后再试",
        duration: 4,
      });
    },
  },
  beforeCreate() {
    this.form = this.$form.createForm(this, { name: "questionnaire" });
  },
  mounted() {
    var parameter = {
      id: this.$route.query.id,
    };
    this.GetDetail(parameter)
      .then((res) => {
        this.questionnaire = res.data.questionnaire;
        this.questionCompletes = res.data.questionCompletes;
        this.spinning = false;
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