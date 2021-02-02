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
                    <b>Price:</b> {{selectedDrug.price.amount.amount + selectedDrug.price.amount.currency}}<br/><hr>
                    <div class="form-inline">
                        <b class="mr-2">Select quantity:</b><input type="number" class="form-control col-sm-2" v-model="quantity"/>
                    </div>
                </div>
                <div v-else>
                    <b>No drug selected.</b>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                </div>
            </div>
        </div>
    </div>
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
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
                    <th>
                        Price
                    </th>
                    <th></th>
                <tr>
                <tr v-for="drug in drugs" v-bind:key="drug.id">
                    <td>{{drug.name}}</td>
                    <td>{{drug.manufacturer}}</td>
                    <td>{{drug.price.amount.amount + drug.price.amount.currency}}</td>
                    <td><button class="btn btn-sm btn-success" data-toggle="modal" data-target="#modal" @click="select(drug)">Reserve</button></td>
                </tr>
            </table>
        </div>
    </div>
    <div v-else>

    </div>
</div>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'

export default {
    data: function () {
        return {
            view: 'reserve',
            drugs: [],
            searchString: '',
            selectedDrug: null,
            quantity: 1
        }
    },
    methods: {
        search: function () {
            axios.get(api.drugs.search + '/' + this.searchString)
            .then(response => {
                this.drugs = response.data
            })
        },
        select: function (drug) {
            this.selectedDrug = drug
            this.quantity = 1
        },
        reserve: function (quantity) {
            
        }
    }
}
</script>

<style scoped>
th,td {
    padding: 4px;
}
</style>