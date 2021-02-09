<template>
<div class="row mt-4">

  <div class="col-3">
    <b-button v-if="!isSubscribed" class="btn-warning mt-5 pl-4 pr-4 pt-2 pb-2" @click="subscribe">Subscribe to promotions</b-button>
    <b-button v-else class="btn-danger mt-5 pl-4 pr-4 pt-2 pb-2" @click="unsubscribe">Stop receiving promotions</b-button>
  </div>
  <div class="col-6 border border-success rounded p-3">
    Sort by:
    <div class="btn-group btn-group-toggle" data-toggle="buttons">
      <label class="btn btn-info">
        <input type="radio" @click="sortByPrice"> Price
      </label>
      <label class="btn btn-info">
        <input type="radio" @click="sortByRating"> Doctor rating
      </label>
    </div>
    <div class="container d-flex justify-content-between my-3">
      <button class="btn btn-success" @click="previousWeek">Back</button>
      <div class="border border-success rounded p-2 bg-dark dropdown-toggle" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{formatWeek(selectedDate)}}</div>
      <div class="dropdown-menu bg-dark" aria-labelledby="navbar-dropdown">
        <DatePicker value="null" v-model="selectedDate" v-on:change="updateWeek()" is-dark v-if="showDatePicker"/>
      </div>
      <button class="btn btn-success" @click="nextWeek">Next</button>
    </div>
    <div>
      <AppointmentCalendar v-bind:appointments="weeklyAppointments()"/>
    </div>
  </div>

</div>
</template>

<script>
import AppointmentCalendar from '../../components/patient/appointments/AppointmentCalendar.vue'
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import { format, startOfWeek, endOfWeek, subWeeks, addWeeks, isSameDay } from 'date-fns'
import axios from 'axios'
import { api } from '../../api.js'

export default {
    data: function () {
        return {
            freeAppointments: [],
            showDatePicker: true,
            selectedDate: new Date(),
            priceAscending: false,
            ratingAscending: false,

            isSubscribed: false
        }
    },
    components: {
        AppointmentCalendar, DatePicker
    },
    methods: {
        formatWeek: function (date) {
            return format(startOfWeek(date), 'd.M.yyyy.') + ' - ' + format(endOfWeek(date), 'd.M.yyyy.')
        },
        nextWeek: function () {
            this.selectedDate = addWeeks(this.selectedDate, 1)
        },
        previousWeek: function () {
            this.selectedDate = subWeeks(this.selectedDate, 1)
        },
        weeklyAppointments: function () {
            let weeklyAppointments = []
            this.freeAppointments.forEach(a => {
                if(isSameDay(startOfWeek(new Date(a.start)), startOfWeek(this.selectedDate))) {
                    weeklyAppointments.push(a)
                }
            })
            return weeklyAppointments
        },
        sortByPrice: function () {
            this.freeAppointments.sort((a1, a2) => {
                if (a1.price.amount < a2.price.amount) {
                    return this.priceAscending ? 1 : -1
                }
                if (a1.price.amount > a2.price.amount) {
                    return this.priceAscending ? -1 : 1
                }
                return 0
            })
            this.priceAscending = !this.priceAscending
        },
        sortByRating: function () {
            this.freeAppointments.sort((a1, a2) => {
                if (a1.employeeRating < a2.employeeRating) {
                    return this.ratingAscending ? 1 : -1
                }
                if (a1.employeeRating > a2.employeeRating) {
                    return this.ratingAscending ? -1 : 1
                }
                return 0
            })
            this.ratingAscending = !this.ratingAscending
        },
        subscribe() {
            axios.post(api.promotion.subscribe, {
                pharmacyId : this.getPharmacyId()
            })
            .then(response => {
                this.isSubscribed = true;
                this.$toast.success("Successfully subscribed.");
            })
            .catch(() => this.$toast.error("Failed to subscribe."))
        },
        unsubscribe() {
            axios.post(api.promotion.unsubscribe, {
                pharmacyId : this.getPharmacyId()
            })
                .then(response => {
                  this.isSubscribed = false;
                  this.$toast.success("Successfully unsubscribed.");
                })
                .catch(() => this.$toast.error("Failed to unsubscribe."))
        },
        checkIfSubscribed() {
            axios.get(api.promotion.isSubscribed + "/" + this.getPharmacyId())
                .then(response => {
                    this.isSubscribed = response.data;
                })
                .catch(() => this.$toast.open("Failed to check subscription."));
        },
        getPharmacyId() {
          return this.$route.params.id;
        }
    },
    mounted: function () {
        this.checkIfSubscribed();
        axios.get(api.appointments.root + '/' + this.$route.params.id)
        .then(response => {
            this.freeAppointments = response.data
        })
        .catch()
    }
}
</script>