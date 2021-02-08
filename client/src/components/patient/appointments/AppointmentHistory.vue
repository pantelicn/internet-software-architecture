<template>
<b-container>
  <b-table
    striped hover
    :items="appointments"
    :fields="fields"
    >

  </b-table>
</b-container>
</template>

<script>
import {api} from '../../../api.js'
import axios from 'axios'
import moment from 'moment'
export default {
    name: 'AppointmentHistory',
    props: {
        patientId: Number,
        appointmentType: String
    },
    data() {
        return {
            fields:[
                { key:'appointmentId', label: 'Appointment Identifier', sortable: true },
                { key:'doctorFullName', label: 'Doctor', sortable: true },
                { key:'price', label: 'Price', sortable: true },
                { key:'start', label: 'Appointment began', sortable: true },
                { key:'end', label: 'Appointment ended', sortable: true },
                { key:'pharmacy', label: 'Pharmacy', sortable: true },
            ],
            appointments: []
        }
    },
    methods:{
        fetchAppointmentHistory:function(){
            if(this.appointmentType=="examination")
                this.fetchExaminationHistory()
            else
                this.fetchCounselingHistory()
        },
        fetchExaminationHistory:function(){
            axios.get(api.appointments.history.examinations + this.patientId).then(res=>{
                this.fillAppointments(res.data)
            })
        },
        fetchCounselingHistory:function(){
            axios.get(api.appointments.history.counselings + "?patientId=" + this.patientId)
            .then(res=>{
                this.fillAppointments(res.data)
            })
        },
        fillAppointments:function(data){
             data.forEach(element => {
                    this.appointments.push({
                        appointmentId: element.appointmentId,
                        doctorFullName: element.doctorsFirstName + " " + element.doctorsLastName,
                        price: element.price,
                        start: moment(new Date(element.start)).format("MM/DD/YYYY HH:mm"),
                        end: moment(new Date(element.end)).format("MM/DD/YYYY HH:mm"),
                        pharmacy: element.pharmacyName
                    })
                });
        }
        
    },
    mounted() {
        this.fetchAppointmentHistory()
    },
}
</script>

<style>

</style>