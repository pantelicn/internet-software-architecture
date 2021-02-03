<template>
    <b-container>
        <b-list-group v-if="patient">
            <b-list-group-item>PID: {{patient.pid}}</b-list-group-item>
            <b-list-group-item>First name: {{patient.firstName}}</b-list-group-item>
            <b-list-group-item>Last name: {{patient.lastName}}</b-list-group-item>
            <b-list-group-item>Gender: {{patient.gender}}</b-list-group-item>
            <b-list-group-item>Phone number: {{patient.phoneNumber}}</b-list-group-item>
            <b-list-group-item>Date of birth:
                {{patient.dateOfBirth}}
            </b-list-group-item>
            <b-list-group-item>Address: {{patient.address}}</b-list-group-item>
            <b-list-group-item>Username: {{patient.username}}</b-list-group-item>
            <b-list-group-item>Email: {{patient.email}}</b-list-group-item>
        </b-list-group>
    </b-container>
</template>

<script>
import {api} from '../../api.js'
import moment from 'moment'
import axios from 'axios'
export default {
    name: 'PatientProfileInfo',
    props: {
        patientId: Number,
    },
    data(){
        return{
            patient:null
        }
    },
    methods:{
        fetchPatient(){
            axios.get(api.patients.profilePreview + this.patientId).then(res=>{
                this.patient = res.data
                this.patient.dateOfBirth = moment(new Date(this.patient.dateOfBirth)).format("MM/DD/YYYY")
            })
        }
    },
    mounted(){
        this.fetchPatient()
    }

}
</script>

<style>

</style>