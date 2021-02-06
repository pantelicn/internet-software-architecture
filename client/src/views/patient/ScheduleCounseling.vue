<template>
<div class="container">
    <h6 class="m-2">Choose date and time at which you want to schedule a counseling with a pharmacist.</h6>
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
    <div v-if="pharmacists.length > 0" class="d-flex justify-content-center">
        <table class="border border-success">
            <tr class="border border-success">
                <th class="p-2 mx-2">
                    Name
                </th>
                <th class="p-2 mx-2">
                    Address
                </th>
                <th class="p-2 mx-2">
                    Rating
                </th>
                <th class="p-2 mx-2">
                    Counseling price
                </th>
                <th class="p-2 mx-2"></th>
            </tr>
            <tr v-for="pharmacy in getPharmacies()" v-bind:key="pharmacy.pharmacyId">
                <td class="p-2 mx-2">
                    {{pharmacy.pharmacyName}}
                </td>
                <td class="p-2 mx-2">
                    {{pharmacy.address.city.name + ', ' + pharmacy.address.streetName}}
                </td>
                <td class="p-2 mx-2">
                    {{pharmacy.pharmacyRating}}
                </td>
                <td class="p-2 mx-2">
                    {{formatPrice(pharmacy.counselingPrice.amount) + pharmacy.counselingPrice.currency}}
                </td>
                <td class="p-2 mx-2">
                    <button class="btn btn-sm btn-success">Schedule</button>
                </td>
            </tr>
        </table>
    </div>
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
                if(this.pharmacists.length === 0) {
                    this.$toast.error("No available pharmacies found.")
                }
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        getPharmacies: function () {
            return this.pharmacists
                .filter((x, i, a) => 
                        a.map(pharmacist => pharmacist.pharmacyId)
                            .indexOf(x.pharmacyId) === i)
        },
        formatPrice: function (price) {
            return parseFloat(price).toFixed(2)
        }
    }
}
</script>