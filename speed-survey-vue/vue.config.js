const { defineConfig } = require('@vue/cli-service')
const mockGet = require('./mock/index.js');
module.exports = defineConfig({
  publicPath:'/',
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
  // proxy: 'http://localhost:5000',
  devServer:{
    proxy:{
      '/questionnaire':{
        target:'http://localhost:5000',
        changeOrigin: true
      }
    },
    onBeforeSetupMiddleware: function (devServer) {
      if (!devServer) {
        throw new Error('webpack-dev-server is not defined');
      }
      mockGet(devServer.app);
      // devServer.app.get('/some/path', function (req, res) {
      //   res.json({ custom: 'response' });
      // });
    },
  }
  

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


