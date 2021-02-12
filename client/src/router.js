import { getRole, isAuthenticated } from './helpers/jwt.js'
import VueRouter from 'vue-router'
import InsufficientPermissions from './views/unauthorized/InsufficientPermissions.vue'
import UnauthHome from './views/unauthorized/UnauthHome.vue'
import DermatologistHome from './views/dermatologist/DermatologistHome.vue'
import PharmacistHome from './views/pharmacist/PharmacistHome.vue'
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
import TimeOffRequest from './views/timeoff/TimeOffRequest.vue'
import DrugDispensing from './views/pharmacist/drug-dispensing/DrugDispensing.vue'
import WorkingCalendar from './views/employee/calendar/WorkingCalendar.vue'
import Profile from './views/person/Profile.vue'
import Drugs from './views/patient/Drugs.vue'
import Appointments from './views/patient/Appointments.vue'
import ScheduleCounseling from './views/patient/ScheduleCounseling.vue'
import Pharmacies from './views/patient/Pharmacies.vue'
import PatientHome from './views/patient/PatientHome.vue'
import PatientProfile from './views/patient/PatientProfile.vue'
import Feedback from './views/patient/Feedback.vue'
import Registration from './views/unauthorized/Registration'
import ExaminationHistory from './views/patient/ExaminationHistory.vue'
import CounselingHistory from './views/patient/CounselingHistory.vue'
import DrugSearch from "@/views/DrugSearch";
import ComplaintResponse from "@/views/sysadmin/ComplaintResponse";
import SysAdminHome from "@/views/sysadmin/SysAdminHome";
import SupplierHome from "@/views/supplier/SupplierHome";
import SupplierOffers from "@/views/supplier/SupplierOffers";
import SupplierOrders from "@/views/supplier/SupplierOrders";
import SupplierProfile from "@/views/supplier/SupplierProfile";
import RegisterSupplier from "@/views/sysadmin/RegisterSupplier";
import RegisterPharmacy from "@/views/sysadmin/RegisterPharmacy";
import RegisterPhAdmin from "@/views/sysadmin/RegisterPhAdmin";
import AddDrugs from "@/views/sysadmin/AddDrugs";
import RegisterSysAdmin from "@/views/sysadmin/RegisterSysAdmin";
import RegisterDermatologist from "@/views/sysadmin/RegisterDermatologist";

const router = new VueRouter({
    mode: 'hash',
    routes: [
        {
            path: '/',
            component: UnauthHome,
            name: 'unauth-home',
            children: [
                {
                    path: '',
                    name: 'login',
                    meta: { unauthorized:true },
                    component: Login
                },
                {
                    path: 'pharmacies',
                    meta: { unauthorized:true },
                    component: Pharmacies
                },
                {
                    path: 'drugs',
                    meta: { noSupply: true},
                    component: DrugSearch
                },
                {
                    path: 'register',
                    name: 'register',
                    meta: { unauthorized:true },
                    component: Registration
                },
            ],
        },
        {
            path: '/insufficient-permissions',
            component: InsufficientPermissions,
            name: 'insufficient-permissions'
        },
        {
            path: '/dermatologist',
            name: 'dermatologist',
            component: DermatologistHome,
            meta: { requiresDermaAuth: true},
            children:[
                {
                    path: '',
                    name: 'upcoming-examinations',
                    component: UpcomingExaminations,
                    meta: { requiresDermaAuth: true}
                },
                {
                    path: 'examined-patients',
                    name: 'examined-patients',
                    component: ExaminedPatients,
                    meta: { requiresDermaAuth: true}
                },
                {
                    path: 'time-off',
                    name: 'dermatologist-time-off',
                    component: TimeOffRequest,
                    meta: { requiresDermaAuth: true}
                },
                {
                    path: 'my-calendar',
                    name: 'my-calendar',
                    component: WorkingCalendar,
                    meta: { requiresPharmaAuth:true }
                },
                {
                    path: 'my-profile',
                    name: 'dermatologist-profile',
                    component: Profile,
                    meta: { requiresDermaAuth: true }
                },
                {
                    path: 'examination-report',
                    name: 'examination-report',
                    component: ExaminationReport,
                    meta: { requiresDermaAuth: true},
                    children: [
                        {
                            path: '',
                            name: 'exam-report-step-one',
                            component: ExamReportStepOne,
                            meta: { requiresDermaAuth: true}
                        },
                        {
                            path: 'examination-info',
                            name: 'exam-report-step-two',
                            component: ExamReportStepTwo,
                            meta: { requiresDermaAuth: true}
                        },
                        {
                            path: 'drug-prescription',
                            name: 'exam-report-step-three',
                            component: ExamReportStepThree,
                            meta: { requiresDermaAuth: true}
                        },
                        {
                            path: 'exam-scheduling',
                            name: 'exam-report-step-four',
                            component: ExamReportStepFour,
                            meta: { requiresDermaAuth: true}
                        },

                    ]
                },
            ]
        },
        {
            path: '/sys',
            name: 'sys-root',
            component: SysAdminHome,
            meta: { requiresSysAuth: true },
            children: [
                {
                    path: 'complaints',
                    name: 'complaint-responses',
                    meta: { requiresSysAuth: true },
                    component: ComplaintResponse,
                },
                {
                    path: 'register-supplier',
                    name: 'register-supplier',
                    component: RegisterSupplier
                },
                {
                    path: 'register-pharmacy',
                    name: 'register-pharmacy',
                    component: RegisterPharmacy
                },
                {
                    path: 'register-ph-admin',
                    name: 'register-ph-admin',
                    component: RegisterPhAdmin
                },
                {
                    path: 'drugs',
                    name: 'drugs',
                    component: AddDrugs
                },
                {
                    path: 'register-sys-admin',
                    name: 'register-sys-admin',
                    component: RegisterSysAdmin
                },
                {
                    path: 'register-dermatologist',
                    name: 'register-dermatologist',
                    component: RegisterDermatologist
                }
            ]
        },
        {
            path: '/supplier',
            name: 'supplier-root',
            component: SupplierHome,
            meta: { requiresSupplier: true },
            children: [
                {
                    path: '',
                    name: 'offers',
                    component: SupplierOffers
                },
                {
                    path: 'profile',
                    name: 'profile',
                    component: SupplierProfile,
                },
                {
                    path: 'orders',
                    name: 'orders',
                    component: SupplierOrders
                },

            ]
        },
        {
            path: '/pharmacist',
            name: 'pharmacist',
            component: PharmacistHome,
            meta: { requiresPharmaAuth: true },
            children: [
                {
                    path: '',
                    name: 'upcoming-counselings',
                    component: UpcomingCounselings,
                    meta: { requiresPharmaAuth: true }
                },
                {
                    path: 'counseled-patients',
                    name: 'counseled-patients',
                    component: CounseledPatients,
                    meta: { requiresPharmaAuth: true }
                },
                {
                    path: 'my-calendar',
                    name: 'my-calendar',
                    component: WorkingCalendar,
                    meta: { requiresPharmaAuth:true }
                },
                {
                    path: 'time-off',
                    name: 'pharmacist-time-off',
                    component: TimeOffRequest,
                    meta: { requiresPharmaAuth: true }
                },
                {
                    path: 'drug-dispensing',
                    name: 'drug-dispensing',
                    component: DrugDispensing,
                    meta: { requiresPharmaAuth: true }
                }
                ,
                {
                    path: 'my-profile',
                    name: 'pharmacist-profile',
                    component: Profile,
                    meta: { requiresPharmaAuth: true }
                },
                {
                    path: 'counseling-report',
                    name: 'counseling-report',
                    component: CounselingReport,
                    meta: { requiresPharmaAuth: true },
                    children: [
                        {
                            path: '',
                            name: 'counseling-report-step-one',
                            component: CounselingReportStepOne,
                            meta: { requiresPharmaAuth: true }
                        },
                        {
                            path: 'counseling-info',
                            name: 'counseling-report-step-two',
                            component: CounselingReportStepTwo,
                            meta: { requiresPharmaAuth: true }
                        },
                        {
                            path: 'drug-prescription',
                            name: 'counseling-report-step-three',
                            component: CounselingReportStepThree,
                            meta: { requiresPharmaAuth: true }
                        },
                        {
                            path: 'counseling-scheduling',
                            name: 'counseling-report-step-four',
                            component: CounselingReportStepFour,
                            meta: { requiresPharmaAuth: true }
                        },

                    ]
                },
            ]
        },
        {
            path: '/patient',
            component: PatientHome,
            meta: { requiresPatientAuth: true },
            children: [
                {
                    path: '',
                    component: Pharmacies
                },
                {
                    path: 'profile',
                    component: PatientProfile
                },
                {
                    path: 'appointments',
                    component: Appointments
                },
                {
                    path: 'drugs',
                    component: Drugs
                },
                {
                    path: 'schedule/examination/:id',
                    component: ScheduleExamination
                },
                {
                    path: 'schedule/counseling',
                    component: ScheduleCounseling
                },
                {
                    path: 'feedback',
                    component: Feedback
                },
                {
                    path: 'history/examinations',
                    component: ExaminationHistory
                },
                {
                    path: 'history/counselings',
                    component: CounselingHistory
                }
            ]
        },
    ]
})

router.beforeEach((to, from, next) => {
    if(to.matched.some( record => record.meta.unauthorized )){
        if( getRole() !== null ) {
            if( getRole() === 'ROLE_DERMATOLOGIST' ) {
                next({ name: 'upcoming-examinations' });
            }
            else if( getRole() === 'ROLE_PHARMACIST') {
                next({ name: 'upcoming-counselings' });
            }
            else if (getRole() === 'ROLE_PATIENT') {
                next({ path: '/patient'});
            }
            else if (getRole() === 'ROLE_SYS_ADMIN') {
                next({ path:'/sys' });
            }
            else if(getRole() === 'ROLE_SUPPLIER') {
                next( { path: '/supplier' })
            }
        }
        else next();

    }
    else if( to.matched.some(record => record.meta.requiresDermaAuth)) {
        if( getRole() !== 'ROLE_DERMATOLOGIST' ) {
            next({ name: 'insufficient-permissions' })
        }
        else next();
    }
    else if( to.matched.some(record => record.meta.requiresPharmaAuth)) {
        if( getRole() !== 'ROLE_PHARMACIST') {
            next({ name: 'insufficient-permissions' })
        }
        else next();
    }
    else if(to.matched.some(record => record.meta.requiresPatientAuth)) {
        if (getRole() !== 'ROLE_PATIENT') {
            next({ name: 'insufficient-permissions' })
        }
        else next()
    }
    else if(to.matched.some(record => record.meta.requiresAuth)) {
        if(!isAuthenticated()) {
            next({ name: 'login' })
        }
        else next();
    }
    else if(to.matched.some(record => record.meta.noSupply)) {
        if (getRole() === "ROLE_SUPPLIER") {
            next({ name: 'insufficient-permissions' })
        }
        if (getRole() === "ROLE_PATIENT") {
            next( { path: "patient/drugs"} )
        }
        else next();
    }
    else if(to.matched.some(record => record.meta.requiresSysAuth)) {
        if (getRole() === "ROLE_SYS_ADMIN") {
            next();
        }
        else next({ name: 'insufficient-permissions' })
    }
    else if(to.matched.some(record => record.meta.requiresSupplier)) {
        if (getRole() === "ROLE_SUPPLIER") {
            next();
        }
        else next({ name: 'insufficient-permissions' });
    }

    else next()



})

export { router }
