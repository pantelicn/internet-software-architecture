const host = 'http://localhost:8080'

//REST endpoints
let appointments = '/api/appointments'
let drugs = '/api/drugs'
let employees = '/api/employees'
let scheduling = '/api/schedule'
let patients = '/api/patients'


export const api = {
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
        patient: host + appointments + '/patient',
        cancel: host + appointments + '/cancel',
        history: {
            examinations: host + appointments + '/examinations/patient-history/',
            counselings: host + appointments + '/counselings/patient-history/'
        }
    },
    drugs: {
        root: host + drugs
    },
    employees: {
        dermatologists: {
            basicInfo: host + employees + '/dermatologists/basic-info/'
        },
        pharmacists: {
            basicInfo: host + employees + '/pharmacists/basic-info/'
        }
    },
    scheduling: {
        root: host + scheduling,
        predefined: host + scheduling + '/predefined',
        newExamination: host + scheduling + '/examination',
        newCounseling: host + scheduling + '/counseling'
    },
    patients: {
        root: host + patients,
        appointed: host + patients + '/appointed-by/',
        profilePreview: host + patients + '/profile-preview/'
    }
}