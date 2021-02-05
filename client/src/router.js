import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import Login from './views/unauthorized/Login.vue'
import ExaminationReport from './views/dermatologist/report/ExaminationReport.vue'
import CounselingReport from './views/pharmacist/report/CounselingReport.vue'
import ScheduleExamination from './views/patient/ScheduleExamination.vue'
import ExaminedPatients from './views/dermatologist/patients/ExaminedPatients.vue'
import CounseledPatients from './views/pharmacist/patients/CounseledPatients.vue'
import UpcomingExaminations from './views/dermatologist/examinations/UpcomingExaminations.vue'
import UpcomingCounselings from './views/pharmacist/counselings/UpcomingCounselings.vue'
import ExamReportStepOne from './views/dermatologist/report/steps/ExamReportStepOne.vue'
import ExamReportStepTwo from './views/dermatologist/report/steps/ExamReportStepTwo.vue'
import ExamReportStepThree from './views/dermatologist/report/steps/ExamReportStepThree.vue'
import ExamReportStepFour from './views/dermatologist/report/steps/ExamReportStepFour.vue'
import CounselingReportStepOne from './views/pharmacist/report/steps/CounselingReportStepOne.vue'
import CounselingReportStepTwo from './views/pharmacist/report/steps/CounselingReportStepTwo.vue'
import CounselingReportStepThree from './views/pharmacist/report/steps/CounselingReportStepThree.vue'
import CounselingReportStepFour from './views/pharmacist/report/steps/CounselingReportStepFour.vue'
import Drugs from './views/patient/Drugs.vue'
import Appointments from './views/patient/Appointments.vue'

export const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: '/',
            component: HelloWorld
        },
        {
            path: '/login',
            component:Login
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
            path: '/counseling-report',
            name: 'counseling-report',
            component: CounselingReport,
            children: [
                {
                    path: '',
                    name: 'counseling-report-step-one',
                    component: CounselingReportStepOne
                },
                {
                    path: 'counseling-info',
                    name: 'counseling-report-step-two',
                    component: CounselingReportStepTwo
                },
                {
                    path: 'drug-prescription',
                    name: 'counseling-report-step-three',
                    component: CounselingReportStepThree
                },
                {
                    path: 'counseling-scheduling',
                    name: 'counseling-report-step-four',
                    component: CounselingReportStepFour
                },

            ]
        },
        {
            path: '/examination-report',
            name: 'examination-report',
            component: ExaminationReport,
            children: [
                {
                    path: '',
                    name: 'exam-report-step-one',
                    component: ExamReportStepOne
                },
                {
                    path: 'examination-info',
                    name: 'exam-report-step-two',
                    component: ExamReportStepTwo
                },
                {
                    path: 'drug-prescription',
                    name: 'exam-report-step-three',
                    component: ExamReportStepThree
                },
                {
                    path: 'exam-scheduling',
                    name: 'exam-report-step-four',
                    component: ExamReportStepFour
                },

            ]
        },
        {
            path: '/examined-patients',
            name: 'examined-patients',
            component: ExaminedPatients
        },
        {
            path: '/counseled-patients',
            name: 'counseled-patients',
            component: CounseledPatients
        },
        {
            path: '/upcoming-examinations',
            name: 'upcoming-examinations',
            component: UpcomingExaminations
        },
        {
            path: '/upcoming-counselings',
            name: 'upcoming-counselings',
            component: UpcomingCounselings
        },
		{
            path: '/patient-drugs',
            component: Drugs
        }
    ]
})