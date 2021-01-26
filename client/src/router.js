import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'

export const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: '/',
            component: HelloWorld
        }
    ]
})