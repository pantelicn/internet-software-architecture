import Vue from 'vue'
import App from './App.vue'
import { router } from './router.js'
import VueRouter from 'vue-router'
import VueToast from 'vue-toast-notification'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'vue-toast-notification/dist/theme-sugar.css';

Vue.use(VueToast, {
  position: 'top'
})
Vue.use(VueRouter)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
