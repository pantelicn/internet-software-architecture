<template>
    <div class="container my-3">
        <History v-bind:appointments="appointments" @sort-price="sortByPrice" @sort-duration="sortByDuration" @sort-date="sortByDate"/>
    </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../api.js'
import History from '../../components/patient/appointments/History.vue'
import { compareAsc, compareDesc } from 'date-fns'

export default {
    data: function () {
        return {
            appointments: [],
            priceAscending: false,
            durationAscending: false,
            dateAscending: false
        }
    },
    components: {
        History
    },
    mounted: function () {
        axios.get(api.appointments.patientHistory.examinations)
        .then(response => {
            this.appointments = response.data
        })
        .catch()
    },
    methods: {
        sortByPrice: function () {
            this.appointments.sort((a1, a2) => {
                if (a1.price.amount > a2.price.amount)
                    return this.priceAscending ? -1 : 1
                else if (a1.price.amount < a2.price.amount)
                    return this.priceAscending ? 1 : -1
                return 0
            })
            this.priceAscending = !this.priceAscending
        },
        sortByDuration: function () {
            this.appointments.sort((a1, a2) => {
                if (a1.durationMinutes > a2.durationMinutes)
                    return this.durationAscending ? -1 : 1
                else if (a1.durationMinutes < a2.durationMinutes)
                    return this.durationAscending ? 1 : 1
                return 0
            })
            this.durationAscending = !this.durationAscending
        },
        sortByDate: function () {
            this.appointments.sort((a1, a2) => {
                if (this.dateAscending)
                    return compareAsc(new Date(a1.start), new Date(a2.start))
                return compareDesc(new Date(a1.start), new Date(a2.start))
            })
            this.dateAscending = !this.dateAscending
        }
    },
}
</script>