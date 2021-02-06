<template>
<b-container>
    <b-col>
        <b-col id="scheduling-opt" v-if="showOptions" class="mt-5 mb-5">
            <b-row align-h="center">
                <b-button variant="success" v-on:click="showPredefined">
                    Choose a predefined examination
                </b-button>
            </b-row>
            <b-row align-h="center" class="mt-2"> OR </b-row>
            <b-row align-h="center" class="mt-2">
                <b-button variant="success" v-on:click="showExamCreation">
                    Schedule a new examination by yourself
                </b-button>
            </b-row>
        </b-col>
        <b-col id="predefined-exams" v-if="predefined" class="mt-5 mb-5">
            <b-row align-h="center">
                <label  id="exam-select-label" for="examSelect">Examination:</label>
            </b-row>
            <b-row align-h="center" cols-md="4">
                <select class="form-control " id="dermaSelect" 
                @change="examinationSelected($event)">
                    <option selected> Choose an examination... </option>
                    <option v-for="exam in freeExaminations"
                            :key="exam.appointmentId"
                            :value="exam.appointmentId"> 
                        {{formatExamTime(exam)}}
                    </option>
                </select>
            </b-row>
            <b-row align-h="center" class="mt-4">
                <b-button variant="success" v-on:click="back">
                    Back
                </b-button>
                <b-button variant="success" class="ml-4" v-on:click="schedulePredefined()"
                :disabled="selectedExam==null || selectedExam=='Choose an examination...'">
                    Schedule
                </b-button>
            </b-row>
        </b-col>
        <b-col v-if="examCreation" class="mt-5 mb-5" >
            <b-row align-h="center">
                <label  class="mt-2">Date and time: </label>
            </b-row>
            <b-row align-h="center">
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
            </b-row>
            <b-row align-h="center" class="mt-2">
                <label for="duration-select">Duration:</label>
            </b-row>
            <b-row align-h="center" cols-md="4" >
                <select id="duration" class="form-control" @change="durationSelected($event)">
                    <option selected> Choose a duration... </option>
                    <option value="15"> 15 minutes </option>
                    <option value="30"> 30 minutes </option>
                    <option value="45"> 45 minutes </option>
                    <option value="1"> 1 hour </option>
                </select>
            </b-row>
            <b-row align-h="center mt-4">
                <b-button variant="success" v-on:click="back">
                    Back
                </b-button>
                <b-button variant="success" class="ml-4"
                :disabled="selectedDuration==null || selectedDuration ==='Choose a duration...' "
                @click="scheduleNewExamination">
                    Schedule
                </b-button>
            </b-row>
        </b-col>
    </b-col>
</b-container>

    
</template>

<script>
export function getTomorrowsDate() {
    let date = new Date()
    date.setDate(date.getDate() + 1)
    return date
}
import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import axios from 'axios'
import {
    format,
    addMilliseconds
} from 'date-fns'
import {
    api
} from '../../../api.js'

import { getRoleId, getRole } from '../../../helpers/jwt.js'
import moment from 'moment'
export default {
    name: "ExaminationScheduling",
    data: function () {
        return {
            showOptions: true,
            predefined: false,
            examCreation: false,
            minDate: getTomorrowsDate(),
            date: getTomorrowsDate(),
            freeExaminations: null,
            selectedDoctor: null,
            selectedExam: null,
            selectedDuration: null,
            currentAppointment: null,
        }
    },
    components: {
        DatePicker
    },
    methods: {
        showPredefined: function () {
            this.showOptions = false
            this.predefined = true
        },
        showExamCreation: function () {
            this.showOptions = false
            this.examCreation = true
        },
        back: function () {
            this.showOptions = true
            this.examCreation = false
            this.predefined = false
            this.selectedDoctor = null
            this.selectedExam = null
            this.selectedDuration = null
        },
        getFreeExaminations: function () {
            axios.get(api.appointments.free + "?pharmacyId=" + this.currentAppointment.pharmacyId)
                .then(response => {
                    this.freeExaminations = response.data
                })
        },
        examinationSelected: function (event) {
            this.selectedExam = event.target.value
        },
        durationSelected: function (event) {
            this.selectedDuration = event.target.value
        },
        formatExamTime: function (appointment) {
            let date = format(new Date(appointment.start), 'MM/dd/yyy')
            let start = format(new Date(appointment.start), 'HH:mm')
            let duration = moment.duration(appointment.duration)
            let end = format(addMilliseconds(new Date(appointment.start), duration), 'HH:mm')

            return date + " [" + start + " - " + end + "]"
        },
        getDurationString: function () {
            if (this.selectedDuration == "1")
                return "PT1H"
            else
                return "PT" + this.selectedDuration + "M"
        },

        schedulePredefined: function () {
            let appointmentReservationDto = {
                appointmentId: parseInt(this.selectedExam),
                patientId: this.currentAppointment.patientId
            }

            axios.put(api.scheduling.predefined, appointmentReservationDto)
                .then(res => {
                    this.$toast.open('Examination successfully scheduled!')
                    this.back()
                    this.getFreeExaminations()
                })
                .catch(err => {
                    if (err.response.status == 400)
                        this.$toast.error(err.response.data)
                })
        },
        scheduleNewExamination: function () {
            let createdAppointmentDto = {
                pharmacyId: this.currentAppointment.pharmacyId,
                employeeId: getRoleId(),
                patientId: this.currentAppointment.patientId,
                start: this.date,
                duration: this.getDurationString()
            }
            axios.post(api.scheduling.newExamination, createdAppointmentDto)
                .then(res => {
                    this.$toast.open('Examination successfully scheduled!')
                    this.back()
                    this.getFreeExaminations()
                })
                .catch(err => {
                    if (err.response.status == 400)
                        this.$toast.error(err.response.data)
                })
        }
    },
    mounted() {
        this.currentAppointment = this.$store.state.report.currentAppointment
        this.getFreeExaminations()
        
    }
}
</script>

<style scoped>

</style>