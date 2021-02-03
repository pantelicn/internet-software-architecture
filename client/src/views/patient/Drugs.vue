<template>
<div class="container">
    <div class="modal fade" id="modal" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content text-dark">
                <div class="modal-header">
                    <h5 class="modal-title">Reserve drug</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-left" v-if="selectedDrug">
                    <b>Drug:</b> {{selectedDrug.name}}<br/><hr>
                    <b>Manufacturer:</b> {{selectedDrug.manufacturer}}<br/><hr>
                    <b>Price:</b> {{formatPrice(selectedDrug.price.amount.amount) + selectedDrug.price.amount.currency}}<br/><hr>
                    <div class="form-inline">
                        <b class="mr-2">Select Pharmacy:</b>
                        <select class="form-control" v-model="selectedDrug">
                            <option 
                                v-for="storedDrug in availableStoredDrugs" 
                                v-bind:key="storedDrug.storedDrugId" 
                                v-bind:value="storedDrug">
                                {{storedDrug.pharmacyName}}
                            </option>
                        </select>
                    </div>
                    <hr>
                    <div class="form-inline">
                        <b class="mr-2">Select quantity:</b><input type="number" min="1" v-bind:max="selectedDrug.quantity" class="form-control col-sm-2" v-model="quantity"/>
                        <div class="ml-2 text-info"><b>{{selectedDrug.quantity}} avaliable</b></div>
                    </div>
                    <hr>
                    <div class="form-inline">
                        <b class="mr-2">Pick up before:</b>
                        <DatePicker
                            id="date-picker"
                            timezone="UTC"
                            :min-date="minDate"
                            v-model="date"
                            color="green"
                            is-dark
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
                </div>
                <div v-else>
                    <b>No drug selected.</b>
                </div>
                <div class="modal-footer">
                    <div v-if="selectedDrug">
                        <b>Total price:</b> {{formatPrice(selectedDrug.price.amount.amount * quantity) + selectedDrug.price.amount.currency}}
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="reserve">Confirm</button>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Abort</button>
                </div>
            </div>
        </div>
    </div>
    <div class="btn-group btn-group-toggle my-1" data-toggle="buttons">
        <label class="btn btn-info" active>
            <input type="radio" v-model="view" value="reserve" checked> Reserve drug
        </label>
        <label class="btn btn-info">
            <input type="radio" v-model="view" value="view"> Your reservations
        </label>
    </div>
    <div v-if="view == 'reserve'">
        <div class="form-inline d-flex justify-content-center m-3">
            <input type="text" class="form-control" placeholder="Search" v-model="searchString"/>
            <button class="btn btn-success ml-1" @click="search">Search</button>
        </div>
        <div v-if="drugs.length > 0" class="d-flex justify-content-center p-1">
            <table class="border border-success">
                <tr class="border-bottom border-success">
                    <th>
                        Name
                    </th>
                    <th>
                        Manufacturer
                    </th>
                    <th></th>
                <tr>
                <tr v-for="drug in distinct(drugs)" v-bind:key="drug.id">
                    <td>{{drug.name}}</td>
                    <td>{{drug.manufacturer}}</td>
                    <td><button class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" @click="select(drug.drugId)">Reserve</button></td>
                </tr>
            </table>
        </div>
    </div>
    <div v-else>
        <div v-if="reservations.length > 0" class="d-flex justify-content-center p-1">
            <table class="border border-success">
                <tr class="border-bottom border-success">
                    <th>
                        Id
                    </th>
                    <th>
                        Name
                    </th>
                    <th>
                        Manufacturer
                    </th>
                    <th>
                        Pharmacy
                    </th>
                    <th>
                        Quantity
                    </th>
                    <th>
                        Total
                    </th>
                    <th>
                        Pick up before
                    </th>
                    <th></th>
                <tr>
                <tr v-for="reservation in reservations" v-bind:key="reservation.reservationId">
                    <td>{{reservation.reservationId}}</td>
                    <td>{{reservation.name}}</td>
                    <td>{{reservation.manufacturer}}</td>
                    <td>{{reservation.pharmacyName}}</td>
                    <td>{{reservation.quantity}}</td>
                    <td>{{formatPrice(reservation.price.amount.amount * reservation.quantity) + reservation.price.amount.currency}}</td>
                    <td>{{formatDate(reservation.pickUpBefore)}}</td>
                    <td><button v-if="isCancelable(reservation.pickUpBefore)" class="btn btn-sm btn-success" data-toggle="modal" @click="cancel(reservation.reservationId)">Cancel</button></td>
                </tr>
            </table>
        </div>
        <div class="m-4" v-else><b>You have no reservations.</b></div>
    </div>
</div>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import { format, isPast, subDays } from 'date-fns'

export default {
    data: function () {
        return {
            view: 'reserve',
            drugs: [],
            searchString: '',
            selectedDrug: null,
            availableStoredDrugs: [],
            selectedStoredDrug: null,
            quantity: 1,
            minDate: new Date(),
            date: new Date(),
            reservations: []
        }
    },
    components: {
        DatePicker
    },
    mounted: function () {
        this.fetchReservations()
    },
    methods: {
        search: function () {
            axios.get(api.drugs.search + '/' + this.searchString)
            .then(response => {
                this.drugs = response.data
            })
        },
        select: function (drugId) {
            this.availableStoredDrugs = []
            this.drugs.forEach(drug => {
                if (drug.drugId == drugId) {
                    this.availableStoredDrugs.push(drug)
                }
            })
            this.selectedDrug = this.availableStoredDrugs[0]
            this.date = new Date()
            this.quantity = 1
        },
        reserve: function () {
            let dto = {
                storedDrugId: this.selectedDrug.storedDrugId,
                quantity: this.quantity,
                pickUpBefore: this.date
            }
            axios.post(api.drugs.reservations + '/1', dto)
            .then(() => {
                this.$toast.open('Successfully reserved.')
                this.search()
                this.fetchReservations()
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        cancel: function (reservationId) {
            axios.delete(api.drugs.reservations + '/' + reservationId)
            .then(() => {
                this.fetchReservations()
                this.$toast.open("Reservation successfully canceled.")
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        },
        distinct: function (drugs) {
            return drugs.filter((x, i, a) => a.map(drug => drug.drugId).indexOf(x.drugId) === i)
        },
        formatPrice: function (price) {
            return parseFloat(price).toFixed(2)
        },
        formatDate: function (date) {
            return format(new Date(date), "dd.MM.yyyy.")
        },
        fetchReservations: function () {
            axios.get(api.drugs.reservations + '/1')
            .then(response => {
                this.reservations = response.data
            })
        },
        isCancelable: function (date) {
            return !isPast(subDays(new Date(date), 1))
        }
    }
}
</script>

<style scoped>
th,td {
    padding: 4px;
}
</style>