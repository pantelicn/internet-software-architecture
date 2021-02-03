import Vue from 'vue'
import App from './App.vue'
import { router } from './router.js'
import VueRouter from 'vue-router'
import VueToast from 'vue-toast-notification'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-toast-notification/dist/theme-sugar.css';
import {BootstrapVue} from 'bootstrap-vue'

Vue.use(VueToast, {
  position: 'top'
})
Vue.use(VueRouter)
Vue.use(BootstrapVue)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
