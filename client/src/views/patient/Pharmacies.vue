<template>
    <div class="row">
        <div class="my-2 col-3 ml-2">
            <div class="w-75 text-right">
                <div class="m-2">
                    Sort by:
                    <div class="btn-group btn-group-toggle" data-toggle="buttons">
                        <label class="btn btn-info">
                            <input type="radio" @click="sortByName"> Name
                        </label>
                        <label class="btn btn-info">
                            <input type="radio" @click="sortByCity"> City
                        </label>
                        <label class="btn btn-info">
                            <input type="radio" @click="sortByRating"> Rating
                        </label>
                    </div>
                </div>
                <hr>
                <div class="m-2">
                    <div class="d-flex">
                        <div class="mr-1">
                            <input class="form-control" placeholder="Name" v-model="nameSearch">
                        </div>
                        <div>
                            <input class="form-control" placeholder="City" v-model="citySearch">
                        </div>
                    </div>
                </div>
                <div>
                    <button class="btn btn-info m-2" @click="searchPharmacies">Search</button>
                </div>
                <hr>
                <div class="m-2 d-flex justify-content-between">
                    <input type="range" class="form-range" min="0" max="5" step="0.1" v-model="rating">
                    {{rating}}
                </div>
                <div class="m-2">
                    <button class="btn btn-info ml-1" @click="filterPharmacies">Filter by rating</button>
                </div>
                <hr>
                <div class="m-2">
                    <button class="btn btn-success ml-1" @click="reset">Reset</button>
                </div>
            </div>
        </div>
        <div class="col-6">
            <div class="row d-flex justify-content-between">
                <div v-for="pharmacy in filteredPharmacies" v-bind:key="pharmacy.id" class="col-3 border border-success rounded m-3 p-2" style="cursor: pointer;" @click="schedule(pharmacy.id)">
                    <b class="lead">{{pharmacy.name}}</b>
                    <div class="text-green">{{pharmacy.address.city.name + ', ' + pharmacy.address.streetName}}</div>
                    <div class="d-flex justify-content-center"><StarRating v-bind:rating="pharmacy.rating" :read-only="true" :increment="0.1" :star-size="20"/></div>
                </div>
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
            pharmacies: [],
            searchedPharmacies: [],
            filteredPharmacies: [],
            rating: 0,
            nameAscending: false,
            cityAscending: false,
            ratingAscending: false,
            nameSearch: '',
            citySearch: ''
        }
    },
    components: {
        StarRating,
    },
    mounted: function () {
        axios.get(api.pharmacies.root)
        .then(response => {
            this.pharmacies = response.data
            this.reset()
        })
        .catch()
    },
    methods: {
        schedule: function (pharmacyId) {
            this.$router.push('/patient/schedule/examination/' + pharmacyId)
        },
        reset: function () {
            this.filteredPharmacies = this.pharmacies
            this.searchedPharmacies = this.pharmacies
        },
        sortByName: function () {
            this.pharmacies.sort((p1, p2) => {
                if (this.nameAscending)
                    return p2.name.localeCompare(p1.name)
                return p1.name.localeCompare(p2.name)
            })
            this.nameAscending = !this.nameAscending
        },
        sortByCity: function () {
            this.pharmacies.sort((p1, p2) => {
                if (this.cityAscending)
                    return p2.address.city.name.localeCompare(p1.address.city.name)
                return p1.address.city.name.localeCompare(p2.address.city.name)
            })
            this.cityAscending = !this.cityAscending
        },
        sortByRating: function () {
            this.pharmacies.sort((p1, p2) => {
                if (p1.rating < p2.rating)
                    return this.ratingAscending ? -1 : 1
                else if (p1.rating > p2.rating)
                    return this.ratingAscending ? 1 : -1
                return 0
            })
            this.ratingAscending = !this.ratingAscending
        },
        searchPharmacies: function () {
            let searched = []
            this.pharmacies.forEach(p => {
                if (p.name.toLowerCase().includes(this.nameSearch.toLowerCase())
                    && p.address.city.name.toLowerCase().includes(this.citySearch.toLowerCase()))
                    searched.push(p)
            })
            this.searchedPharmacies = searched
            this.filteredPharmacies = searched
        },
        filterPharmacies: function () {
            let filtered = []
            this.searchedPharmacies.forEach(p => {
                if (p.rating >= this.rating)
                filtered.push(p)
            })
            this.filteredPharmacies = filtered
        }
    }
}
</script>

<style scoped>
.text-green {
    color: #42b983;
}
</style>