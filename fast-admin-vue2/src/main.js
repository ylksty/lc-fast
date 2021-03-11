import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from '@/store'
import VueCookie from 'vue-cookie'            // api: https://github.com/alfhen/vue-cookie
import './plugins/element.js'
import '@/element-ui'                         // api: https://github.com/ElemeFE/element
import '@/assets/scss/index.scss'
import httpRequest from './utils/httpRequest' // api: https://github.com/axios/axios
import cloneDeep from 'lodash/cloneDeep'

Vue.use(VueCookie)
Vue.config.productionTip = false

// 挂载全局
Vue.prototype.$http = httpRequest // ajax请求方法

// 保存整站vuex本地储存初始状态
window.SITE_CONFIG['storeState'] = cloneDeep(store.state)

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')


// new Vue({
//   router,
//   store,
//   render: h => h(App)
// }).$mount('#app')
