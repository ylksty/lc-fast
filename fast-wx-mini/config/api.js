// 以下是业务服务器API地址
// 本机开发时使用
var WxApiRoot = 'http://localhost:8081/wx/';
// 局域网测试使用
// var WxApiRoot = 'http://192.168.1.3:8080/wx/';
// 云平台部署时使用
// var WxApiRoot = 'http://122.51.199.160:8080/wx/';
// 云平台上线时使用
// var WxApiRoot = 'https://www.menethil.com.cn/wx/';

module.exports = {
  IndexUrl: WxApiRoot + 'home/index', //首页数据接口

  AuthLoginByWeixin: WxApiRoot + 'auth/login_by_weixin', //微信登录
};