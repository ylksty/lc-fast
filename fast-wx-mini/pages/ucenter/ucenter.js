// logs.js
const util = require('../../utils/util.js')
var app = getApp();


Page({
  data: {
    logs: []
  },
  methods: {
  },
  onShow() {
    console.log(app.globalData.hasLogin)
    if(!app.globalData.hasLogin) {
      wx.navigateTo({
        url: "/pages/auth/login/login"
      });
    }
  },
  onLoad() {
    // wx.login({
    //   success (res) {
    //     if (res.code) {
    //       console.log(res)
    //       //发起网络请求
    //       wx.request({
    //         url: 'https://test.com/onLogin',
    //         data: {
    //           code: res.code
    //         }
    //       })
    //     } else {
    //       console.log('登录失败！' + res.errMsg)
    //     }
    //   }
    // })

    this.setData({
      logs: (wx.getStorageSync('logs') || []).map(log => {
        return util.formatTime(new Date(log))
      })
    })
  }
})
