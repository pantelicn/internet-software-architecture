<template>
<div class="container mt-5" v-if="pharmacists">
    <div class="col justify-content-md-center">
        <div class="row">
            <div class="col-sm-2">
                <label for="pharmacist-select">Dermatologist:</label>
            </div>
            <div class="col-sm-2">
                <select id="pharmacist-select" @change="pharmacistSelected($event)">
                    <option selected>Choose a pharmacist...</option>
                    <option :value="pharmacist.id"
                    v-for="pharmacist in pharmacists"
                    :key="pharmacist.id">
                        {{pharmacist.firstName + " " + pharmacist.lastName}}
                    </option>
                </select>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-sm-2">
                <label for="datetime-select">
                    Date and time:
                </label>
            </div>
            <div class="col-sm-2">
                <DatePicker
                    id="date-picker"
                    timezone="UTC"
                    :min-date="minDate"
                    v-model="date"
                    color="green"
                    is-dark
                    mode="dateTime"
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
        <div class="row mt-4">
            <div class="col-sm-2">
                <label for="duration">
                    Duration:
                </label>
            </div>
            <div class="col-sm-2">
                <select id="duration" @change="durationSelected($event)">
                    <option selected> Choose a duration... </option>
                    <option value="15"> 15 minutes </option>
                    <option value="30"> 30 minutes </option>
                    <option value="45"> 45 minutes </option>
                    <option value="1"> 1 hour </option>
                </select>
            </div>
        </div>
        <div class="row mt-4">
            <div class="col-sm-4">
                <button type="button" class="btn btn-primary ml-2"
                :disabled="selectedPharmacist==null || selectedDuration==null"
                @click="scheduleNewCounseling">
                    Schedule
                </button>
            </div>
        </div>
    </div>
</div>

</template>
<script>
import axios from 'axios'
import {
    api
} from '../../../api.js'

export function getTomorrowsDate(){
    let date = new Date()
    date.setDate(date.getDate() + 1)
    return date
}

import DatePicker from 'v-calendar/lib/components/date-picker.umd'
export default {
    name:'CounselingReport',
    data:function(){
        return{
            date:getTomorrowsDate(),
            minDate:getTomorrowsDate(),
            pharmacists: null,
            selectedPharmacist: null,
            selectedDuration: null
            
        }
    },
    components:{DatePicker},
    methods:{
        getDurationString: function () {
            if (this.selectedDuration == "1")
                return "PT1H"
            else
                return "PT" + this.selectedDuration + "M"
        },
        getPharmacistsBasicInfo: function () {
            // TODO: Kada implementiras izvestaj namesti da se ne zakucava pharma
            axios.get(api.employees.pharmacists.basicInfo + 1)
                .then(response => {
                    this.pharmacists = response.data
                })
        },
        pharmacistSelected: function (event) {
            this.selectedPharmacist = event.target.value
        },
        durationSelected: function (event) {
            this.selectedDuration = event.target.value
        },
        scheduleNewCounseling: function () {
            let createdAppointmentDto = {
                pharmacyId: 1,
                employeeId: this.selectedPharmacist,
                patientId: 1,
                start: this.date,
                duration: this.getDurationString()
            }
            axios.post(api.scheduling.newCounseling, createdAppointmentDto)
                .then(res => {
                    this.$toast.open('Examination successfully scheduled!')
                })
                .catch(err => {
                    if (err.response.status == 400)
                        this.$toast.error(err.response.data)
                })
        }
    },
    mounted(){
        this.getPharmacistsBasicInfo();
    }
}
</script>

<style scoped>

.btn-primary{
    background-color: #42b983 !important;
    border-color:#164731 !important;
}
form {
    width: 50%;
}

</style>