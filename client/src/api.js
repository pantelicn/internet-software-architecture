const host = 'http://localhost:8080'

//REST endpoints
let appointments = '/api/appointments'
let drugs = '/api/drugs'
let employees = '/api/employees'
let scheduling = '/api/schedule'


export const api = {
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
        patient: host + appointments + '/patient',
        cancel: host + appointments + '/cancel'
    },
    drugs: {
        root: host + drugs,
        search: host + drugs + '/search',
        reserve: host + drugs + '/reserve'
    },
    employees: {
        dermatologists: {
            basicInfo: host + employees + '/dermatologists/basic-info/'
        }
    },
    scheduling: {
        root: host + scheduling,
        predefined: host + scheduling + '/predefined',
        newExamination: host + scheduling + '/examination'
    }
}