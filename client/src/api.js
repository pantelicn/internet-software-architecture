const host = 'http://localhost:8080'

//REST endpoints
let appointments = '/api/appointments'
let drugs = '/api/drugs'


export const api = {
    appointments: {
        root: host + appointments,
        free: host + appointments + '/free-examinations',
    },
    drugs: {
        root: host + drugs
    }
}