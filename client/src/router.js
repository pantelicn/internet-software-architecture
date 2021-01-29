import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import ExaminationReport from './views/dermatologist/report/ExaminationReport.vue'
import ScheduleExamination from './views/ScheduleExamination.vue'

export const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: '/',
            component: HelloWorld
        },
        {
            path: '/schedule',
            component: ScheduleExamination
        }
        ,
        {
            path: '/examination-report',
            name: 'examination-report',
            component: ExaminationReport
        }
    ]
})