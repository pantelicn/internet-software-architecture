<template>
    <div class="container">
        <div class="row d-flex justify-content-between">
            <div v-for="pharmacy in pharmacies" v-bind:key="pharmacy.id" class="col-3 border border-success m-3 p-2" @click="schedule(pharmacy.id)">
                <b class="lead">{{pharmacy.name}}</b>
                <div class="text-light">{{pharmacy.address.city.name + ', ' + pharmacy.address.streetName}}</div>
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
            pharmacies: []
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
            this.$router.push('/patient-schedule/examination/' + pharmacyId)
        }
    }
}
</script>