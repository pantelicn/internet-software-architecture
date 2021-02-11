<template>
<div>
    <div class="border border-secondary bg-dark hoverable">
        {{formatDate(appointment.start)}}<br>
        {{formatTime(appointment.start)}}<br>
        {{formatPrice(appointment.price)}}<br>
        Dr. {{appointment.employeeName + ' ' + appointment.employeeLastName}}<br>
    </div>
    <button v-if="!scheduled" class="btn btn-success btn-sm hidden w-100 mb-1" @click="scheduleAppointment">Schedule</button>
    <div v-else class="hidden w-100 mb-1 text-success">Scheduled</div>
</div>

</template>

<script>
import { format } from 'date-fns'
import { api } from '../../../api.js'
import axios from 'axios'

export default {
    data: function () {
        return {
            scheduled: false
        }
    },
    props: {
        appointment: Object,
    },
    methods: {
        formatDate: function (dateString) {
            return format(new Date(dateString), 'd.M.yyyy.')
        },
        formatTime: function (dateString) {
            return format(new Date(dateString), 'hh:mm')
        },
        formatPrice: function (price) {
            return parseFloat(price.amount).toFixed(2) + price.currency
        },
        scheduleAppointment: function () {
            let dto = {
                appointmentId: this.appointment.id,
            }
            axios.put(api.scheduling.predefined + '/patient', dto)
            .then(() => {
                this.$toast.open('Examination successfully scheduled!')
                this.scheduled = true
            })
            .catch(error => {
                if (error.response.status == 400)
                    this.$toast.error(error.response.data)
            })
        }
    }
}
</script>

<style scoped>
.hidden {
    display: none;
}

.hidden:hover {
    display: block;
}

.hoverable:hover + .hidden {
    display: block;
}
</style>