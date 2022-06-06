const { defineConfig } = require('@vue/cli-service')
module.exports = defineConfig({
  transpileDependencies: true,
  // less
  css: {
    loaderOptions: {
      less: {
        javascriptEnabled: true
      }
    }
  },
  // 配置代理
  devServer: {
    proxy: 'http://localhost:5000'
  },

  /*
    =============================================================================================================
    ================================================== 生产部署 ==================================================
    =============================================================================================================
  */

  //publicPath: process.env.NODE_ENV === 'production' ? '/wenjuanya/' : '/',

  //打包优化
  //productionSourceMap: false,

  /* 空间分析
  chainWebpack(config) {
    config.plugin('webpack-bundle-analyzer').use(require('webpack-bundle-analyzer').BundleAnalyzerPlugin)
  },
  */

  /* CDN引入
  configureWebpack: {
    externals: {
      'vue': 'Vue',
      'vuex': 'Vuex',
      'vue-router': 'VueRouter',
      'axios': 'axios',
      'moment': 'moment',
      'ant-design-vue': 'antd',
      // '@ant-design-vue/pro-layout/es': 'ProLayout'
    },
  },
  */
})


