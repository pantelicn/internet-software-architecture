const host = 'http://localhost:8080'

//REST endpoints
let auth = "/api/auth"
let appointments = '/api/appointments'
let drugs = '/api/drugs'
let employees = '/api/employees'
let scheduling = '/api/schedule'
let patients = '/api/patients'
let person = '/api/person'
let storedDrugs = '/api/stored-drugs'
let timeOff = '/api/time-off'
let pharmacies = '/api/pharmacies'



export const api = {
    auth: { 
        login: host + auth + '/login', 
        hasLoggedBefore: host + auth + '/logged',
        changePassword: host + auth + '/change-password'
    }
    ,
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
        patient: host + appointments + '/patient',
        cancel: host + appointments + '/cancel',
        upcoming: host + appointments + '/upcoming/',
        appointmentReport: host + appointments + '/appointment-report',
        freeUp: {
            examination: host + appointments + '/examinations/free-up/',
            counseling: host + appointments + '/counselings/free-up/'
        },
        history: {
            examinations: host + appointments + '/examinations/patient-history/',
            counselings: host + appointments + '/counselings/patient-history/'
        }
        
    },
    drugs: {
        root: host + drugs,
        search: host + drugs + '/search',
        patientSearch: host + drugs + '/patient-search',
        reservations: host + drugs + '/reservations/',
        dispense: host + drugs + '/reservations/dispense/'
    },
    storedDrugs: {
        root: host + storedDrugs,
        isAvailable : host + storedDrugs + '/is-available'
    },
    employees: {
        employeeId : host + employees + '/employee-id/',
        myPharmacies: host + employees + '/my-pharmacies/'
    },
    scheduling: {
        root: host + scheduling,
        predefined: host + scheduling + '/predefined',
        newExamination: host + scheduling + '/examination',
        newCounseling: host + scheduling + '/counseling',
        patientCounseling: host + scheduling + '/counseling/patient',
        findPharmacists: host + scheduling + '/counseling/pharmacists'
    },
    patients: {
        root: host + patients,
        appointed: host + patients + '/appointed',
        profilePreview: host + patients + '/profile-preview/',
        isAllergic: host + patients + '/is-allergic'
        
    },
    person: {
        root: host + person,
        credentials: host + person + '/credentials',
        name: host + person + '/name'
    },
    timeOff: {
        root: host + timeOff
    },
    pharmacies: {
        root: host + pharmacies
    }
}