<template>
 <b-col>
    <b-row align-h="center" class="mt-4">
        <h4><b-badge variant="success">Examination scheduling</b-badge></h4>
    </b-row>
    <b-row>
        <examination-scheduling></examination-scheduling>
    </b-row>
    <b-row align-h="center" class="mt-4" >
        <b-col sm="4">
            <b-button variant="success" @click="back()"> Go back to a drug prescription step </b-button>
        </b-col>
        <b-col sm="4">
            <b-button variant="success" @click="proceed()"> Finish an examination </b-button>
        </b-col>
        
    </b-row>
</b-col>
</template>

<script>
import ExaminationScheduling from './../../../../components/dermatologist/scheduling/ExaminationScheduling.vue'
import axios from 'axios'
import { api } from './../../../../api.js'
export default {
    name: 'ExamReportStepFour',
    components: {
        ExaminationScheduling
    },
    methods:{
        prepareReportSubmission(){
            let reportSubmissionDto = {
                appointmentId: this.$store.state.report.currentAppointment.appointmentId,
                appointmentInfo: this.$store.state.report.appointmentReport.examinationInformation,
                issueDate: new Date(),
                prescribedDrugs: []
            }
            let prescribedDrugs = this.$store.state.report.appointmentReport.prescribedDrugs
            prescribedDrugs.forEach(drugPrescription => {
                reportSubmissionDto.prescribedDrugs.push({
                        drugId:drugPrescription.drug.id,
                        duration:parseInt(drugPrescription.duration)
                    }
                )
            })
            return reportSubmissionDto
        },
        proceed(){
            let reportSubmissionDto = this.prepareReportSubmission()
            axios.put(api.appointments.appointmentReport,reportSubmissionDto)
            .then(res=>{
                 this.$toast.open('Report successfully submited!')
                 this.$store.commit('clearAppointmentReport')
                 this.$router.push({name : 'upcoming-examinations'})
            })
            
        },
        back(){
            this.$router.push({ name: 'exam-report-step-three' })
        }
    }
}
</script>

<style>

</style>