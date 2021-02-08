<template>
<div class="container">
    <h3>Search for medications.</h3>
    <div class="form-inline d-flex justify-content-center m-3">
        <input type="text" class="form-control" placeholder="Search" v-model="searchString"/>
        <button class="btn btn-success ml-1" @click="search">Search</button>
    </div>
    <div v-if="drugs.length > 0" class="d-flex justify-content-center p-1">
        <table class="table table-striped table-dark ">
            <tr>
                <th>
                    Name
                </th>
                <th>
                    Manufacturer
                </th>
            </tr>
            <tbody>
                <tr v-for="drug in distinct(drugs)" v-bind:key="drug.id">
                    <td>{{drug.name}}</td>
                    <td>{{drug.manufacturer}}</td>
                </tr>
            </tbody>
        </table>
    </div>
</div>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'
import { format } from 'date-fns'

export default {
    data: function () {
        return {
            view: 'reserve',
            drugs: [],
            searchString: '',
            reservations: []
        }
    },
    methods: {
        search: function () {
            axios.get(api.drugs.patientSearch + '/' + this.searchString)
            .then(response => {
                this.drugs = response.data
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
        }
    }
}
</script>

<style scoped>
th,td {
    padding: 4px;
}
</style>