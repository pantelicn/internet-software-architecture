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
                                <StarRating v-model="dermatologist.rating" :star-size="20"/>
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
                                <StarRating v-model="pharmacist.rating" :star-size="20"/>
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
                                <StarRating v-model="drug.rating" :star-size="20"/>
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
                                <StarRating v-model="pharmacy.rating" :star-size="20"/>
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
        }
    },
    mounted: function () {
        this.fetchRateable()
    }
}
</script>