<template>
<div>
    <div class="container d-flex justify-content-between my-3">
        <button class="btn btn-success">Back</button>
        <button class="btn btn-success">Next</button>
    </div>
    <div>
        <AppointmentCalendar v-bind:appointments="freeAppointments"/>
    </div>
</div>
</template>

<script>
import AppointmentCalendar from '../components/AppointmentCalendar.vue'
import axios from 'axios'
import { api } from '../api.js'

export default {
    data: function () {
        return {
            freeAppointments: []
        }
    },
    components: {
        AppointmentCalendar
    },
    mounted: function () {
        axios.get(api.appointments.root + '/1')
        .then(response => {
            this.freeAppointments = response.data
        })
        .catch()
    }
}
</script>