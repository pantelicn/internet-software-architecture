<template>
    <div class="container">
        <div class="my-2">
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
        <div class="row d-flex justify-content-between">
            <div v-for="pharmacy in pharmacies" v-bind:key="pharmacy.id" class="col-3 border border-success rounded m-3 p-2" @click="schedule(pharmacy.id)">
                <b class="lead">{{pharmacy.name}}</b>
                <div class="text-green">{{pharmacy.address.city.name + ', ' + pharmacy.address.streetName}}</div>
                <div class="d-flex justify-content-center"><StarRating v-bind:rating="pharmacy.rating" :read-only="true" :increment="0.1" :star-size="20"/></div>
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
            nameAscending: false,
            cityAscending: false,
            ratingAscending: false
        }
    },
    components: {
        StarRating
    },
    mounted: function () {
        axios.get(api.pharmacies.root)
        .then(response => {
            this.pharmacies = response.data
        })
        .catch()
    },
    methods: {
        schedule: function (pharmacyId) {
            this.$router.push('/patient/schedule/examination/' + pharmacyId)
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
        }
    }
}
</script>

<style scoped>
.text-green {
    color: #42b983;
}
</style>