import VueRouter from 'vue-router'
import Vue from 'vue'

import routerMap from '@/config/router.config'
import menuRouterMap from '@/config/menu'



Vue.use(VueRouter)


export default new VueRouter({
    mode: 'history',
    base: process.env.NODE_ENV === 'production' ? '/wenjuanya' : '/',
    routes: routerMap.concat(menuRouterMap)
})
