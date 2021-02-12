<template>
    <div class="container">
        <div class="row">
            <div class=col>
                <h5>Rate dermatologists</h5>
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr v-for="dermatologist in dermatologists" v-bind:key="dermatologist.id">
                            <td>
                                {{dermatologist.name + ' ' + dermatologist.lastName}}
                            </td>
                            <td>
                                <StarRating v-model="dermatologist.rating" :star-size="20" @rating-selected="rateEmployee(dermatologist)"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class=col>
                <h5>Rate pharmacists</h5>
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr v-for="pharmacist in pharmacists" v-bind:key="pharmacist.id">
                            <td>
                                {{pharmacist.name + ' ' + pharmacist.lastName}}
                            </td>
                            <td>
                                <StarRating v-model="pharmacist.rating" :star-size="20" @rating-selected="rateEmployee(pharmacist)"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class=col>
                <h5>Rate drugs</h5>
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr v-for="drug in drugs" v-bind:key="drug.id">
                            <td>
                                {{drug.name}}
                            </td>
                            <td>
                                {{drug.manufacturer}}
                            </td>
                            <td>
                                <StarRating v-model="drug.rating" :star-size="20" @rating-selected="rateDrug(drug)"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class=col>
                <h5>Rate pharmacies</h5>
                <table class="table table-striped table-dark">
                    <tbody>
                        <tr v-for="pharmacy in pharmacies" v-bind:key="pharmacy.id">
                            <td>
                                {{pharmacy.name}}
                            </td>
                            <td>
                                <StarRating v-model="pharmacy.rating" :star-size="20" @rating-selected="ratePharmacy(pharmacy)"/>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</template>

<script>
import axios from 'axios'
import { api } from '../../api.js'
import StarRating from 'vue-star-rating'

export default {
    data: function () {
        return {
            pharmacists: [],
            dermatologists: [],
            drugs: [],
            pharmacies: []
        }
    },
    components: {
        StarRating
    },
    methods: {
        fetchRateable: function () {
            axios.get(api.rating.root)
            .then(response => {
                let rateable = response.data
                this.pharmacists = rateable.pharmacists
                this.dermatologists = rateable.dermatologists
                this.drugs = rateable.drugs
                this.pharmacies = rateable.pharmacies
            })
        },
        rateEmployee: function (employee) {
            let dto = {
                employeeId: employee.employeeId,
                rating: employee.rating
            }
            axios.post(api.rating.employee, dto)
            .then(() => {
                this.$toast.open("Employee rated.")
            })
            .catch()
        },
        ratePharmacy: function (pharmacy) {
            let dto = {
                id: pharmacy.id,
                rating: pharmacy.rating
            }
            axios.post(api.rating.pharmacy, dto)
            .then(() => {
                this.$toast.open("Pharmacy rated.")
            })
            .catch()
        },
        rateDrug: function (drug) {
            let dto = {
                id: drug.id,
                rating: drug.rating
            }
            axios.post(api.rating.drug, dto)
            .then(() => {
                this.$toast.open("Drug rated.")
            })
            .catch()
        }
    },
    mounted: function () {
        this.fetchRateable()
    }
}
</script>