import VueRouter from 'vue-router'
import HelloWorld from './components/HelloWorld.vue'
import ExaminationReport from './views/dermatologist/report/ExaminationReport.vue'
import CounselingReport from './views/pharmacist/report/CounselingReport.vue'
import ScheduleExamination from './views/patient/ScheduleExamination.vue'
import ExaminedPatients from './views/dermatologist/patients/ExaminedPatients.vue'
import CounseledPatients from './views/pharmacist/patients/CounseledPatients.vue'
import UpcomingExaminations from './views/dermatologist/examinations/UpcomingExaminations.vue'
import ExamReportStepOne from './views/dermatologist/report/steps/ExamReportStepOne.vue'
import ExamReportStepTwo from './views/dermatologist/report/steps/ExamReportStepTwo.vue'
import ExamReportStepThree from './views/dermatologist/report/steps/ExamReportStepThree.vue'
import ExamReportStepFour from './views/dermatologist/report/steps/ExamReportStepFour.vue'
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
            path: '/counseling-report',
            name: 'counseling-report',
            component: CounselingReport
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
            path: '/patient-drugs',
            component: Drugs
        }
    ]
})