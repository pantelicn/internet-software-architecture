var host = 'http://localhost:8080'
if (process.env.NODE_ENV == 'production')
    host = 'https://isa-pharmacy.herokuapp.com'

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
let rating = '/api/rating'
let locale = '/api/locale'
let promotion = '/api/promotion'
let complaint = '/api/complaint'
let order = '/api/purchase-order'
let offer = '/api/offer'
let supplier = '/api/supplier'
let admin = '/api/admin'

export const api = {
    auth: { 
        login: host + auth + '/login', 
        hasLoggedBefore: host + auth + '/logged',
        changePassword: host + auth + '/change-password',
        register: host + auth + "/register"
    }
    ,
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
        all: host + appointments + '/all',
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
        },
        patientHistory: {
            examinations: host + appointments + '/patient/examination-history',
            counselings: host + appointments + '/patient/counseling-history'
        }
        
    },
    drugs: {
        root: host + drugs,
        search: host + drugs + '/search',
        patientSearch: host + drugs + '/patient-search',
        reservations: host + drugs + '/reservations/',
        dispense: host + drugs + '/reservations/dispense/',
        simple: host + drugs + '/simple'
    },
    storedDrugs: {
        root: host + storedDrugs,
        isAvailable : host + storedDrugs + '/is-available'
    },
    employees: {
        registerDermatologist : host + employees + "/register-dermatologist",
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
        isAllergic: host + patients + '/is-allergic',
        allergies: host + patients + '/allergies'
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
        root: host + pharmacies,
        addAdmin: host + pharmacies + '/admin'
    },
    rating: {
        root: host + rating,
        employee: host + rating + '/employee',
        drug: host + rating + '/drug',
        pharmacy: host + rating + '/pharmacy'
    },
    locale: {
        root: host + locale,
        cities: host + locale + "/cities",
        countries: host + locale + "/countries"
    },
    promotion: {
        root: host + promotion,
        subscribe: host + promotion + '/subscribe',
        unsubscribe: host + promotion + '/unsubscribe',
        isSubscribed: host + promotion + '/subscribed',
        pharmacies: host + promotion + "/subscribed"
    },
    complaint: {
        root: host + complaint,
        respond: host + complaint + '/respond'
    },
    order: {
        allActive: host + order,
    },
    offer: {
        root: host + offer,
        status: host + offer + "/status",
        check: host + offer + "/check",
    },
    supplier: {
        root: host + supplier,
    },
    admin: {
        root: host + admin
    }
}