import { login, register, getCaptcha } from '@/api/user'
// import { welcome } from '@/utils/util'

const user = {
  state: {
    token: '',
    name: '',
    // welcome: '',
    info: {}
  },

  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_INFO: (state, info) => {
      state.info = info
    }

  },

  actions: {
    // 登录
    Login({ commit }, userInfo) {
      return new Promise((resolve, reject) => {
        login(userInfo).then(response => {
          console.log(response)
          commit('SET_TOKEN', 'mock-token')
          if (response.code == 0) { //登陆成功
            resolve(response)
          } else if (response.code == 500) { //登陆失败
            reject(response)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },


    //注册
    // eslint-disable-next-line
    Register({ commit }, { userInfo, captcha }) {
      console.log(userInfo)
      console.log(captcha)
      return new Promise((resolve, reject) => {
        register(userInfo, captcha).then(response => {
          console.log(response)
          if (response.code == 0) { //注册成功
            resolve(response)
          } else if (response.code == 500) { //注册失败
            reject(response)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },

    //获取验证码
    // eslint-disable-next-line
    GetCaptcha({ commit }, emailTo) {
      return new Promise((resolve, reject) => {
        getCaptcha(emailTo).then(response => {
          console.log(response)
          if (response.code == 0) { //发送成功
            resolve(response)
          } else { //发送成功
            reject(response)
          }
        }).catch(error => {
          reject(error)
        })
      })
    },

    /*
    // 获取用户信息
    GetInfo({ commit }) {
      return new Promise((resolve, reject) => {
        getInfo().then(response => {
          const result = response.result
          commit('SET_NAME', { name: result.name })
          resolve(response)
        }).catch(error => {
          reject(error)
        })
      })
    },

    // 登出
    Logout({ commit, state }) {
      return new Promise((resolve) => {
        logout(state.token).then(() => {
          commit('SET_TOKEN', '')
          resolve()
        }).catch((err) => {
          console.log('logout fail:', err)
        }).finally(() => {
        })
      })
    }
    */

  }
}

export default user
