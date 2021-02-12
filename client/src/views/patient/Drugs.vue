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

    <div>
      <b-modal size="lg" :title="drugSpecModal.title" :id="drugSpecModal.id" ok-only>
        <drug-specification :drug="drugSpecModal.drug"></drug-specification>
      </b-modal>
    </div>

    <div v-if="view == 'reserve'">
        <div class="form-inline d-flex justify-content-center m-3">
            <input type="text" class="form-control" placeholder="Search" v-model="searchString"/>
            <button class="btn btn-success ml-1" @click="search">Search</button>

            <select class="form-control ml-5" v-model="filters.selectedDrugType">
                <option
                  v-for="drugType in filters.drugTypes"
                  v-bind:key="drugType"
                  v-bind:value="drugType"
                >
                  {{drugType}}
                </option>
            </select>

            <select class="form-control ml-2" v-model="filters.selectedRating">
              <option
                  v-for="rating in filters.ratings"
                  v-bind:key="rating"
                  v-bind:value="rating"
              >
                {{rating}}
              </option>
            </select>

            <b-button class="btn-success ml-2 pl-5 pr-5 pt-2 pb-2" @click="filter">
                Filter
            </b-button>

        </div>
        <div v-if="displayedDrugs.length > 0" class="d-flex justify-content-center p-1">
            <table class="table table-striped table-dark">
                <tr>
                    <th>
                        Name
                    </th>
                    <th>
                        Manufacturer
                    </th>
                    <th>
                      Drug type
                    </th>
                    <th>
                      Rating
                    </th>
                    <th></th>
                </tr>
                <tbody>
                    <tr v-for="drug in distinct(displayedDrugs)" v-bind:key="drug.id">
                        <td>{{drug.name}}</td>
                        <td>{{drug.manufacturer}}</td>
                        <td>{{drug.drugType}}</td>
                        <td>{{drug.rating === 0 ? "N/A" : drug.rating}}</td>
                        <td><button class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" @click="select(drug.drugId)">Reserve</button></td>
                        <td><button class="btn btn-sm btn-info" @click="showSpecification(drug, $event.target)">Specification</button></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
    <div v-else>
        <div v-if="reservations.length > 0" class="d-flex justify-content-center p-1">
            <table class="table table-striped table-dark">
                <tr>
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
                </tr>
                <tbody>
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
                </tbody>
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
import DrugSpecification from "@/components/report/DrugSpecification";

export default {
    data: function () {
        return {
            view: 'reserve',

            displayedDrugs: [],
            allDrugs: [],

            filters: {
                drugTypes: [],
                ratings: [],
                selectedDrugType: "All",
                selectedRating: "All"
            },

            searchString: '',
            selectedDrug: null,
            availableStoredDrugs: [],
            selectedStoredDrug: null,
            quantity: 1,
            minDate: new Date(),
            date: new Date(),
            reservations: [],

            drugSpecModal: {
              id: 'drug-specifications-modal',
              drug: undefined,
              title: ''
            },
        }
    },
    components: {
        DatePicker, DrugSpecification
    },
    mounted: function () {
        this.fetchReservations()
    },
    methods: {
        search: function () {
            axios.get(api.drugs.patientSearch + '/' + this.searchString)
            .then(response => {
                this.allDrugs = response.data;
                this.displayedDrugs = this.allDrugs

                this.filters.drugTypes = ["All"];
                this.filters.ratings = ["All"];
                this.filters.selectedDrugType = "All";
                this.filters.selectedRating = "All";

                this.allDrugs.forEach(drug => {
                    if (!this.filters.drugTypes.includes(drug.drugType)) {
                        this.filters.drugTypes.push(drug.drugType);
                    }
                    if (!this.filters.ratings.includes(drug.rating)) {
                        this.filters.ratings.push(drug.rating);
                    }
                });
            })
        },
        filter: function() {
            this.displayedDrugs = [];
            for (let i = 0; i < this.allDrugs.length; i++) {
                let drug = this.allDrugs[i];
                if (this.filters.selectedRating !== "All" && this.filters.selectedRating !== drug.rating) {
                    continue;
                }
                if (this.filters.selectedDrugType !== "All" && this.filters.selectedDrugType !== drug.drugType) {
                    continue;
                }
                this.displayedDrugs.push(drug);
            }
        },
        select: function (drugId) {
            this.availableStoredDrugs = []
            this.displayedDrugs.forEach(drug => {
                if (drug.drugId === drugId) {
                    this.availableStoredDrugs.push(drug)
                }
            })
            this.selectedDrug = this.availableStoredDrugs[0]
            this.date = new Date()
            this.quantity = 1
        },
        showSpecification: function(drug, button) {
            console.log(drug);
            this.drugSpecModal.drug = drug
            this.drugSpecModal.title = 'Drug ' + drug.name
            this.$root.$emit('bv::show::modal', this.drugSpecModal.id, button)
            this.$bvModal.show("drug-specifications-modal")
        },
        reserve: function () {
            let dto = {
                storedDrugId: this.selectedDrug.storedDrugId,
                quantity: this.quantity,
                pickUpBefore: this.date
            }
            axios.post(api.drugs.reservations, dto)
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
            axios.delete(api.drugs.reservations + reservationId)
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
            axios.get(api.drugs.reservations)
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