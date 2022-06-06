<template>
  <pro-layout
    :menus="menus"
    :collapsed="collapsed"
    :theme="theme"
    :layout="layout"
    :contentWidth="contentWidth"
    :auto-hide-header="autoHideHeader"
    :mediaQuery="query"
    :isMobile="isMobile"
    :handleMediaQuery="handleMediaQuery"
    :handleCollapse="handleCollapse"
  >
    <template v-slot:menuHeaderRender>
      <div>
        <img src="http://qny.yyangjiao.top/logo.png" />
        <h1>问卷鸭</h1>
      </div>
    </template>
    <template v-slot:rightContentRender>
      <div class="header-right">
        <a-input-search
          placeholder="搜索问卷"
          style="width: 200px"
          @search="searchQuestionnaire"
        />

        <router-link to="/user/login">
          <span class="action account">
            <a-avatar
              style="border-radius: 0"
              shape="square"
              slot="avatar"
              :size="32"
              src="http://qny.yyangjiao.top/logo.png"
            ></a-avatar>
            <span style="margin-left: 8px">yangjiao</span>
          </span>
        </router-link>
      </div>
    </template>
    <template v-slot:footerRender>
      <global-footer />
    </template>
    <router-view />
  </pro-layout>
</template>

<script>
import menuRouterMap from "@/config/menu";
import Vue from "vue";

import GlobalFooter from "@/components/GlobalFooter";
Vue.component("global-footer", GlobalFooter);

export default {
  name: "BasicLayout",
  data() {
    return {
      menus: [],
      collapsed: false,
      autoHideHeader: false,
      query: {},
      layout: "topmenu",
      contentWidth: "Fluid",
      theme: "light",
      isMobile: false,
    };
  },
  created() {
    this.menus = menuRouterMap.find((item) => item.path === "/").children;
  },
  methods: {
    handleMediaQuery(query) {
      this.query = query;
      if (this.isMobile && !query["screen-xs"]) {
        this.isMobile = false;
        return;
      }
      if (!this.isMobile && query["screen-xs"]) {
        this.isMobile = true;
        this.collapsed = false;
      }
    },
    handleCollapse(collapsed) {
      this.collapsed = collapsed;
    },
    searchQuestionnaire() {},
  },
  components: {},
};
</script>
<style scoped>
.header-right {
  min-width: 220px;
  display: flex;
  float: right;
  margin-left: auto;
  overflow: hidden;
  align-items: center;
  padding-right: 8px;
}
.header-right .action {
  display: flex;
  align-items: center;
  height: 100%;
  padding: 0 12px;
  cursor: pointer;
}
.header-right .account {
  color: rgba(0, 0, 0, 0.65);
  vertical-align: top;
}
</style>