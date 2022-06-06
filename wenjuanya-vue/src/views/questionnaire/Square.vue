<template>
  <a-row :gutter="16" style="min-width: 242px">
    <a-col :xs="24" :lg="18">
      <div class="left-header">
        <a-page-header :ghost="false" title="问卷广场"></a-page-header>
      </div>
      <div style="margin-bottom: 16px"></div>
      <div class="left-main">
        <a-list
          :grid="{ gutter: 16, xs: 1, sm: 2, md: 3, lg: 3, xl: 3, xxl: 4 }"
          :data-source="questionnaires"
          :loading="questionnaires.length == 0"
        >
          <div
            slot="loadMore"
            style="
              text-align: center;
              margin: 12px 0;
              height: 32px;
              line-height: 32px;
            "
          >
            <router-link to="/questionnaire" v-if="questionnaires.length != 0">
              <a-button type="primary"><a-icon type="fire" />加载更多</a-button>
            </router-link>
          </div>

          <a-list-item slot="renderItem" slot-scope="item">
            <a-card hoverable>
              <router-link
                :to="{
                  path: '/questionnaire/detail',
                  query: { id: `${item.id}` },
                }"
              >
                <a-card-meta
                  :title="item.title"
                  :description="
                    item.description != null ? item.description : '暂无描述'
                  "
                >
                  <a-avatar
                    style="border-radius: 0"
                    shape="square"
                    slot="avatar"
                    :size="80"
                    src="http://qny.yyangjiao.top/logo.png"
                  />
                </a-card-meta>
              </router-link>
              <template slot="actions" class="ant-card-actions">
                <span> <a-icon type="heart" /> 收藏</span>
                <span><a-icon key="edit" type="edit" />填写</span>
                <span><a-icon key="share-alt" type="share-alt" />分享</span>
              </template>
            </a-card>
          </a-list-item>
        </a-list>
      </div>
    </a-col>

    <a-col :xs="24" :lg="6">
      <div class="right-main">
        <a-card>
          <a-card-meta title="问卷鸭" description="一款新型问卷调查工具">
            <template #avatar>
              <a-avatar
                style="border-radius: 0"
                shape="square"
                :size="80"
                src="http://qny.yyangjiao.top/logo.png"
              />
            </template>
          </a-card-meta>
        </a-card>
        <div style="margin-bottom: 16px"></div>
        <a-card title="支持我们">
          <a-card-meta>
            <template #description>
              <!-- <span>支持我们，做个鸭子；</span>
              <a href="#">联系我们，做个鸭子；</a>
              <a href="#">关于我们，做个鸭子；</a> -->
            </template>
            <template #avatar>
              <a-avatar
                style="border-radius: 0"
                shape="square"
                :size="96"
                src="http://qny.yyangjiao.top/support.png"
              />
            </template>
          </a-card-meta>
        </a-card>
        <div style="margin-bottom: 16px"></div>
        <a-card title="热门问卷">
          <a-list :data-source="hotQuestionnaire">
            <a-list-item slot="renderItem" slot-scope="item">
              <a-list-item-meta :title="item.name" description="参与人数: 115">
                <template #avatar>
                  <a-avatar
                    style="border-radius: 0"
                    shape="square"
                    src="http://qny.yyangjiao.top/logo.png"
                  />
                </template>
              </a-list-item-meta>
            </a-list-item>
          </a-list>
        </a-card>
      </div>
    </a-col>
  </a-row>
</template>

<script>
import { getAllInPublic } from "@/api/questionnaire";
const questionnaires = [];
const hotQuestionnaire = [
  {
    name: "个人信息统计",
  },
  {
    name: "毕业意向统计",
  },
  {
    name: "疫情健康填报",
  },
  {
    name: "创新学分统计",
  },
];
export default {
  data() {
    return {
      questionnaires,
      hotQuestionnaire,
    };
  },
  methods: {
    GetAllInPublic() {
      return new Promise((resolve, reject) => {
        getAllInPublic()
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
    this.GetAllInPublic()
      .then((res) => (this.questionnaires = res.data))
      .catch((err) => this.requestFailed(err));
  },
};
</script>

<style scoped>
.left-header {
  width: 100%;
}

.left-main {
  width: 100%;
}

.tag-list {
  margin-top: 16px;
  text-align: right;
  height: 60px;
}
</style>