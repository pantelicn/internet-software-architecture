<template>
<div class="container my-1">
    <div class="row border border-success rounded">
        <div v-if="appointment.type == 'Examination'" class="bg-success rounded-left p-2">Dermatologist examination</div>
        <div v-else class="bg-info rounded-left p-2">Pharmacist counseling</div>
        <div class="bg-dark rounded-left text-success p-2 ml-1">Time:</div>
        <div class="col p-2"> {{formatTime(appointment.start)}} </div>
        <div class="bg-dark rounded-left text-success p-2 ml-1">Date:</div>
        <div class="col p-2">{{formatDate(appointment.start)}}</div>
        <div class="bg-dark rounded-left text-success p-2 ml-1">Doctor:</div>
        <div class="col-md-4 p-2">
            {{appointment.employeeName}}
            {{appointment.employeeLastName}}
        </div>
        <button class="btn btn-secondary" @click="cancel">Cancel</button>
    </div>
</div>
</template>

<script>
import { format } from 'date-fns'
import { api } from '../api.js'
import axios from 'axios'

export default {
    props: {
        appointment: Object
    },
    methods: {
        formatDate: function (dateString) {
            return format(new Date(dateString), 'd.M.yyyy.')
        },
        formatTime: function (dateString) {
            return format(new Date(dateString), 'hh:mm')
        },
        cancel: function () {
            axios.get(api.appointments.cancel + '/' + this.appointment.id)
            .then(() => this.$toast.open("Appointment canceled."))
            .catch(error => {
                if(error.response.status == 400)
                    this.$toast.error(error.response.data)
            })
        }
    }
}
</script>