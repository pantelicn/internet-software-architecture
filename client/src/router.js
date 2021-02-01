import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import ExaminationReport from './views/dermatologist/report/ExaminationReport.vue'
import ScheduleExamination from './views/patient/ScheduleExamination.vue'
import Appointments from './views/patient/Appointments.vue'
import Drugs from './views/patient/Drugs.vue'

export const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: '/',
            component: HelloWorld
        },
        {
            path: '/patient-schedule',
            component: ScheduleExamination
        },
        {
            path: '/patient-appointments',
            component: Appointments
        },
        {
            path: '/examination-report',
            name: 'examination-report',
            component: ExaminationReport
        },
        {
            path: '/patient-drugs',
            component: Drugs
        }
    ]
})