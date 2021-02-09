<template>
    <b-col>
        <b-row align-h="center" class="mt-5"> 
            <h3>Patient shown up?</h3>
        </b-row>
        <b-row align-h="center" class="mt-4" >
            <b-col sm="1">
                <b-button variant="success" @click = "proceed()"> Yes </b-button>
            </b-col>
            <b-col sm="1">
                <b-button variant="danger" @click = "cancelAppointment()" > No </b-button>
            </b-col>
        </b-row>
    </b-col>
</template>

<script>
import axios from 'axios'
import {api} from '../../../../api.js'
export default {
    name:"ExamReportStepOne",
    methods: {
        proceed(){
            this.$router.push({ name: 'exam-report-step-two' })
        },
        cancelAppointment(){
            let examinationId = this.$store.state.report.currentAppointment.appointmentId
            axios.put(api.appointments.freeUp.examination + examinationId)
            .then(response=>{
                this.$store.commit('clearReport')
                this.$router.push({ name: 'upcoming-examinations' })
            })

        }
    },
    beforeRouteEnter (to, from, next) {
        if(!(from.name == 'upcoming-examinations' || from.name == 'my-calendar'))
            next({ name: 'upcoming-examinations' })
        else
            next()

    }
}
</script>

<style>

</style>