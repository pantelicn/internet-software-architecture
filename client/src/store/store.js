import Vuex from 'vuex';
import Vue from 'vue';
import reportModule from './report/index.js'
import authModule from './auth/index.js'

Vue.use(Vuex);

export const store = new Vuex.Store({
    modules: {
        report : reportModule,
        auth : authModule
    }
})