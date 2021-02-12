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
                    <button class="btn btn-danger ml-1" @click="reset">Reset</button>
                </div>
                <hr style="border-top: 1px solid red;">

              <div class="row ml-1 mt-2">
                  <p class="float-left text-dark
                  pl-4 pr-4 pt-2 pb-2 bg-warning" v-if="isPatient">Your subscriptions:</p>
                </div>
                <div>
                  <table class="w-100">
                    <tr v-for="subscription in subscribedPharmacies" :key="subscription.pharmacyId">
                      <td class="row w-100 ml-1 mt-2 border-warning border pr-2 pt-3 pb-3">
                        <div style="text-align: left;" class="col-7">
                          <span class="font-weight-bold" style="font-size: 20px;">
                              {{subscription.name}}
                          </span>
                          <br>
                          {{subscription.address.streetName}}
                          <br>
                          {{subscription.address.city.name}} {{subscription.address.city.postalCode}}
                          <br>
                          <span class="font-italic">
                            {{subscription.description}}
                          </span>
                        </div>
                        <div class="col-5 mt-4">
                          <b-button class="btn btn-warning" @click="schedule(subscription.pharmacyId)">Open</b-button>
                        </div>
                      </td>
                    </tr>

                  </table>
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
import {getRole} from "@/helpers/jwt";

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
            citySearch: '',
            subscribedPharmacies: [],
            isPatient: false
        }
    },
    components: {
        StarRating,
    },
    mounted: function () {
        this.isPatient = getRole() === "ROLE_PATIENT";
        if (this.isPatient) {
          this.getSubscribedPharmacies();
        }
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
        getSubscribedPharmacies() {
            axios.get(api.promotion.pharmacies)
                .then(response => {
                    this.subscribedPharmacies = response.data;
                })
                .catch(() => this.$toast.error("Failed to fetch subscriptions"))
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