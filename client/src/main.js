import Vue from 'vue'
import App from './App.vue'
import VueRouter from 'vue-router'
import Vuex from 'vuex'
import VueToast from 'vue-toast-notification'
import { router } from './router.js'
import { store } from './store/store.js'
import { BootstrapVue } from 'bootstrap-vue'
import 'bootstrap'
import 'bootstrap/dist/css/bootstrap.min.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'
import 'vue-toast-notification/dist/theme-sugar.css';
import interceptorsSetup from './helpers/interceptors.js'
import VCalendar from 'v-calendar'

interceptorsSetup()


Vue.use(VueRouter)
Vue.use(VCalendar)
Vue.use(BootstrapVue)
Vue.use(Vuex)
Vue.use(VueToast, {
  position: 'top'
})

new Vue({
  router,
  store,
  render: h => h(App),
}).$mount('#app')
