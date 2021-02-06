const reportModule = {
    state: {
        currentAppointment: null,
        appointmentReport:{
            examinationInformation : '',
            prescribedDrugs:[]
        }
    },

    mutations:{
        setCurrentAppointment(state,payload){
            state.currentAppointment = payload;
        },
        setExaminationInformation(state,payload){
            state.appointmentReport.examinationInformation = payload
        },
        addPrescribedDrug(state,payload){
            state.appointmentReport.prescribedDrugs.push(payload)
        },
        clearPrescribedDrugs(state){
            state.appointmentReport.prescribedDrugs = []
        },
        clearAppointmentReport(state){
            state.currentAppointment = null
            state.appointmentReport.examinationInformation = ''
            state.appointmentReport.prescribedDrugs = []

        }
    }
}

export default reportModule