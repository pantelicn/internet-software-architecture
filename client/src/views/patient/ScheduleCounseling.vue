<template>
<div class="container">
    <div class="modal fade" id="modal" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content text-dark">
                <div class="modal-header">
                    <h5 class="modal-title">Schedule counseling with a pharmacist</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-left" v-if="selectedPharmacists.length > 0">
                    <b>Pharmacy:</b> {{selectedPharmacists[0].pharmacyName}}<br/><hr>
                    <b>Address:</b> {{selectedPharmacists[0].address.city.name + ', ' + selectedPharmacists[0].address.streetName}}<br/><hr>
                    <b>Price:</b> {{formatPrice(selectedPharmacists[0].counselingPrice.amount) + selectedPharmacists[0].counselingPrice.currency}}<br/><hr>
                    <div>
                        <b class="mr-2">Select Pharmacist:</b><br>
                        <div class="btn-group-vertical w-100">
                            <table class="table table-hover w-100">
                                <tr>
                                    <th @click="sortByName" style="cursor: pointer">
                                        Name
                                    </th>
                                    <th @click="sortByRating" style="cursor: pointer" class="text-center">
                                        Rating
                                    </th>
                                </tr>
                                <tbody>
                                    <tr 
                                        v-for="pharmacist in selectedPharmacists" 
                                        v-bind:key="pharmacist.id" 
                                        v-bind:class="{ 'bg-success': pharmacist.id == selectedPharmacist.id }"
                                        @click="selectedPharmacist = pharmacist"
                                        style="cursor: pointer;">
                                        <td>
                                            {{pharmacist.name}}
                                            {{pharmacist.lastName}}
                                        </td>
                                        <td class="text-center">
                                            {{pharmacist.rating}}
                                        </td>
                                    </tr>
                                </tbody>
                            </table>
                        </div>
                        
                    </div>
                </div>
                <div v-else>
                    <b>No pharmacy selected.</b>
                </div>
                <div class="modal-footer">
                    <div v-if="selectedPharmacist">
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="schedule">Schedule</button>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Abort</button>
                </div>
            </div>
        </div>
    </div>

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
                <th class="p-2 mx-2" @click="sortByPharmacyRating" style="cursor: pointer;">
                    Rating
                </th>
                <th class="p-2 mx-2" @click="sortByPrice" style="cursor: pointer;">
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
                    <button class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" @click="select(pharmacy.pharmacyId)">Schedule</button>
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
            pharmacists: [],
            selectedPharmacists: [],
            selectedPharmacist: '',
            nameAscending: false,
            ratingAscending: false,
            pharmacyRatingAscending: false,
            priceAscending: false
        }
    },
    components: {
        DatePicker
    },
    methods: {
        schedule: function () {
            let dto = {
                pharmacyId: this.selectedPharmacist.pharmacyId,
                employeeId: this.selectedPharmacist.id,
                start: this.date,
            }
            axios.post(api.scheduling.patientCounseling, dto)
            .then(() => {
                this.$toast.open("Counseling successfully scheduled.")
                this.pharmacists = []
                this.selectedPharmacists = []
                this.selectedPharmacist = ''
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        select: function (pharmacyId) {
            this.selectedPharmacists = []
            this.pharmacists.forEach(p => {
                if (p.pharmacyId == pharmacyId)
                    this.selectedPharmacists.push(p)
            })
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
        },
        sortByName: function () {
            this.selectedPharmacists.sort((p1, p2) => {
                if (this.nameAscending)
                    return (p2.name + p2.lastName).localeCompare(p1.name + p1.lastName)
                return (p1.name + p1.lastName).localeCompare(p2.name + p2.lastName)
            })
            this.nameAscending = !this.nameAscending
        },
        sortByRating: function () {
            this.selectedPharmacists.sort((p1, p2) => {
                if (p1.rating < p2.rating)
                    return this.ratingAscending ? -1 : 1
                else if (p1.rating > p2.rating)
                    return this.ratingAscending ? 1 : -1
                return 0
            })
            this.ratingAscending = !this.ratingAscending
        },
        sortByPrice: function () {
            this.pharmacists.sort((p1, p2) => {
                if (p1.counselingPrice.amount < p2.counselingPrice.amount)
                    return this.priceAscending ? -1 : 1
                else if (p1.counselingPrice.amount > p2.counselingPrice.amount)
                    return this.priceAscending ? 1 : -1
                return 0
            })
            this.priceAscending = !this.priceAscending
        },
        sortByPharmacyRating: function () {
            this.pharmacists.sort((p1, p2) => {
                if (p1.pharmacyRating < p2.pharmacyRating)
                    return this.pharmacyRatingAscending ? -1 : 1
                else if (p1.pharmacyRating > p2.pharmacyRating)
                    return this.pharmacyRatingAscending ? 1 : -1
                return 0
            })
            this.pharmacyRatingAscending = !this.pharmacyRatingAscending
        }
    }
}
</script>