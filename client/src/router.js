import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import ExaminationReport from './views/dermatologist/report/ExaminationReport.vue'
import CounselingReport from './views/pharmacist/report/CounselingReport.vue'
import ScheduleExamination from './views/patient/ScheduleExamination.vue'
import Appointments from './views/patient/Appointments.vue'

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
        }
        ,
        {
            path:'/counseling-report',
            name: 'counseling-report',
            component: CounselingReport
        }
    ]
})