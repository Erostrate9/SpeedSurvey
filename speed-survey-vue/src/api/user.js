import request from '@/utils/request'

const userApi = {
  Login: '/api/user/login',
  Register: '/api/user/register',
  GetCaptcha: '/api/mail/getCaptcha',

  // Logout: '/user/logout',
  // UserInfo: '/user/info',
}


export function login(parameter) {
  return request({
    url: userApi.Login,
    method: 'post',
    data: parameter
  })
}

export function register(userInfo, captcha) {
  return request({
    url: userApi.Register,
    method: 'post',
    data: {
      userInfo: userInfo,
      captcha: captcha
    }

  })
}

export function getCaptcha(parameter) {
  console.log(parameter)
  return request({
    url: userApi.GetCaptcha,
    method: 'get',
    params: parameter,
  })
}

/*
export function getInfo() {
  return request({
    url: userApi.UserInfo,
    method: 'get',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}


export function logout() {
  return request({
    url: userApi.Logout,
    method: 'post',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8'
    }
  })
}
*/
