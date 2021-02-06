<template>
<div class="container">
    <div>
        <label for="datetime-select" class="mr-2 my-3">
            Date and time:
        </label>
        <DatePicker
            id="date-picker"
            timezone="UTC"
            :min-date="minDate"
            v-model="date"
            color="green"
            is-dark
            mode="dateTime"
        >
            <template v-slot="{ inputValue, inputEvents }">
                <input
                class="bg-white border px-2 py-1 rounded"
                :value="inputValue"
                v-on="inputEvents"
                />
            </template>
        </DatePicker>
    </div>
    
    <button class="btn btn-info my-3" @click="fetchPharmacists">List pharmacies</button>
</div>
</template>

<script>
import axios from 'axios'
import { addHours } from 'date-fns'
import { api } from '../../api.js'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'

export default {
    data: function () {
        return {
            date: addHours(new Date(), 24),
            minDate: addHours(new Date(), 24),
            pharmacists: []
        }
    },
    components: {
        DatePicker
    },
    methods: {
        schedule: function () {
            axios.post()
            .then(response => {
            })
            .catch()
        },
        fetchPharmacists: function () {
            axios.post(api.scheduling.findPharmacists, this.date)
            .then(response => {
                this.pharmacists = response.data
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        }
    }
}
</script>