<template>
<div>
    <h3>Your appointments</h3>
    <div class="container">
        <ScheduledAppointment v-for="appointment in appointments" v-bind:key="appointment.id" v-bind:appointment="appointment" @update="fetchAppointments"/>
    </div>
</div>
</template>

<script>
import ScheduledAppointment from '../../components/patient/appointments/ScheduledAppointment.vue'
import axios from 'axios'
import { api } from '../../api.js'

export default {
    data: function () {
        return {
            appointments: []
        }
    },
    components: {
        ScheduledAppointment
    },
    methods: {
        fetchAppointments: function () {
            axios.get(api.appointments.patient)
            .then(response => {
                this.appointments = response.data
            })
        }
    },
    mounted: function () {
        this.fetchAppointments()
    }
}
</script>