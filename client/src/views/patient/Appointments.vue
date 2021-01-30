<template>
    <div class="container">
        <ScheduledAppointment v-for="appointment in appointments" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
    </div>
</template>

<script>
import ScheduledAppointment from '../../components/ScheduledAppointment.vue'
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
    mounted: function () {
        //TODO - Get patient id from headers
        axios.get(api.appointments.patient + '/1')
        .then(response => {
            this.appointments = response.data
        })
    }
}
</script>