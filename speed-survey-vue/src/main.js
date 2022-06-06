import Vue from 'vue'
// import {
//   Layout, Menu, Card, Avatar, Icon, PageHeader, List, Row, Col, Tag, Tooltip, Button, Form, Input, Checkbox,
//   Popover, Select, notification, Progress, Result, message
// } from 'ant-design-vue';


import App from '@/App.vue'

import router from '@/router'
import ProLayout from '@ant-design-vue/pro-layout'
import store from './store/'

import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";

import antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css';
import { notification } from 'ant-design-vue'
import { message } from 'ant-design-vue'
import './plugins/element.js'

Vue.use(ElementUI);
Vue.use(antd);
Vue.config.productionTip = false

// Vue.use(Layout)
// Vue.use(Menu)
// Vue.use(Card)
// Vue.use(Avatar)
// Vue.use(Icon)
// Vue.use(PageHeader)
// Vue.use(List)
// Vue.use(Row)
// Vue.use(Col)
// Vue.use(Tag)
// Vue.use(Tooltip)
// Vue.use(Button)
// Vue.use(Form)
// Vue.use(Input)
// Vue.use(Checkbox)
// Vue.use(Popover)
// Vue.use(Select)
// Vue.use(Progress)
// Vue.use(Result)


Vue.prototype.$notification = notification
Vue.prototype.$message = message

Vue.component('pro-layout', ProLayout)





new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
