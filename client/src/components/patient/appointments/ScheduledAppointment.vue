<template>
<div class="container my-1">
    <div class="row border border-success rounded">
        <div v-if="appointment.type == 'Examination'" class="bg-success rounded-left col-3 p-3">Dermatologist examination</div>
        <div v-else class="bg-info rounded-left col-3 p-3">Pharmacist counseling</div>
        <div class="bg-dark rounded-left text-success p-3 ml-1">Time:</div>
        <div class="col-1 p-3"> {{formatTime(appointment.start)}} </div>
        <div class="bg-dark rounded-left text-success p-3 ml-1">Date:</div>
        <div class="col col-1 py-3 mr-2">{{formatDate(appointment.start)}}</div>
        <div class="bg-dark rounded-left text-success p-3 ml-1">Doctor:</div>
        <div class="col-md-3 p-3">
            {{appointment.employeeName}}
            {{appointment.employeeLastName}}
        </div>
        <div class="col">
            <button v-if="isCancelable()" class="btn btn-secondary my-2" @click="cancel">Cancel</button>
        </div>
    </div>
</div>
</template>

<script>
import { format, subHours, isPast } from 'date-fns'
import { api } from '../../../api.js'
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
            .then(() => {
                this.$toast.open("Appointment canceled.")
                this.$emit('update')
            })
            .catch(error => {
                if(error.response.status == 400)
                    this.$toast.error(error.response.data)
            })
        },
        isCancelable: function () {
            return !isPast(subHours(new Date(this.appointment.start), 24))
        }
    }
}
</script>