<template>
<b-container>
    <b-col>
        <b-row class="mt-4" align-h="center">
            <b-col sm="2">
                <label for="datetime-select">
                    Date and time:
                </label>
            </b-col>
            <b-col sm="2">
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
            </b-col>
        </b-row>
        <b-row class="mt-4" align-h="center">
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
        </b-row>
        <b-row class="mt-4" align-h="center">
            <b-col sm="4">
                <b-button variant="success" class="btn btn-primary ml-2"
                :disabled="selectedDuration == null || selectedDuration == 'Choose a duration...'"
                @click="scheduleNewCounseling">
                    Schedule
                </b-button>
            </b-col>
        </b-row>
    </b-col>
</b-container>

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
import { getRoleId } from '../../../helpers/jwt'
export default {
    name:'CounselingReport',
    data:function(){
        return{
            date:getTomorrowsDate(),
            minDate:getTomorrowsDate(),
            selectedDuration: null,
            currentAppointment: null
            
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
        pharmacistSelected: function (event) {
            this.selectedPharmacist = event.target.value
        },
        durationSelected: function (event) {
            this.selectedDuration = event.target.value
        },

        scheduleNewCounseling: function () {
            let createdAppointmentDto = {
                pharmacyId: this.currentAppointment.pharmacyId,
                employeeId: getRoleId(),
                patientId: this.currentAppointment.patientId,
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
         this.currentAppointment = this.$store.state.report.currentAppointment
    }
}
</script>

<style scoped>
</style>