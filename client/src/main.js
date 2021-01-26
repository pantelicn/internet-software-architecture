import Vue from 'vue'
import App from './App.vue'
import { router } from './router.js'
import VueRouter from 'vue-router'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'

Vue.use(VueRouter)

new Vue({
  router,
  render: h => h(App),
}).$mount('#app')
