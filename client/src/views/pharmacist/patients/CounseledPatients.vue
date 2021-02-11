<template>
    <b-container>
        <b-row class="mt-5" align-h="center">
            <h2>Counseled patients</h2>
        </b-row>
        <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Patient:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Type to Search"
                    >
                    </b-form-input>

                    <b-input-group-append>
                        <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-col>
        <b-table 
            class="mt-2" 
            striped hover 
            :dark="true"
            :items="patients" 
            :fields="fields"
            :filter="filter"
            show-empty
            empty-filtered-text="There are no patients to show."
            :filter-included-fields="filterOn">
        

            <template #cell(profile)="row">
                <b-button size="sm" class="mr-1" @click="patientProfile(row.item,$event.target)">
                    Profile
                </b-button>
            </template>

            <template #cell(examHistory)="row">
                <b-button size="sm" @click="examinationHistory(row.item,$event.target)">
                    History
                </b-button>
            </template>

        </b-table>
        <b-modal :id="patientProfileModal.id" :title="patientProfileModal.title" ok-only>
            <patient-profile-info :patientId="patientProfileModal.patientId">
            </patient-profile-info>
        </b-modal>
        <b-modal :id="patientExaminationsModal.id" size="lg" :title="patientExaminationsModal.title" ok-only>
            <appointment-history
            :patientId="patientExaminationsModal.patientId" 
            appointmentType="counseling"></appointment-history>
        </b-modal>
    </b-container>
</template>

<script>
import PatientProfileInfo from '../../../components/patient/PatientProfileInfo.vue'
import AppointmentHistory from '../../../components/patient/appointments/AppointmentHistory.vue'
import axios from 'axios'
import {api} from '../../../api.js'
export default {
    name:'ExaminedPatients',
    components:{PatientProfileInfo,AppointmentHistory},
    data() {
        return {
            fields:[
                {
                    key:'pid',
                    label:'PID',
                    sortable:true
                },
                {
                    key:'fullName',
                    label:'Patients full name',
                    sortable:true
                },
                {
                    key:'profile',
                    label:'Patients profile'
                },
                {
                    key:'examHistory',
                    label:'Patients examination history'
                }
            ],
            patients: [],
            filter :null,
            filterOn: ['fullName'],
            patientProfileModal: {
                id: 'patient-profile-modal',
                patientId: null,
                title: ''
            },
            patientExaminationsModal: {
                id: 'patient-examinations-modal',
                patientId: null,
                title: ''
            }
        }
    },
    methods:{
        patientProfile:function(item,button){
            this.patientProfileModal.patientId = item.id
            this.patientProfileModal.title='Patient ' + item.fullName
            this.$root.$emit('bv::show::modal',this.patientProfileModal.id,button)
        },
        examinationHistory:function(item,button){
            this.patientExaminationsModal.patientId = item.id
            this.patientExaminationsModal.title='Examination history for a patient ' + item.fullName
            this.$root.$emit('bv::show::modal',this.patientExaminationsModal.id,button)
        },
        // TODO: namesti da se farmaceut ne zakucava
        fetchAppointedPatients:function(){
            axios.get(api.patients.appointed).then(res=>{
                res.data.forEach(element => {
                    this.patients.push({
                        id: element.id,
                        pid: element.pid,
                        fullName: element.firstName + " " + element.lastName

                    })
                });
            })
        }
    },
    mounted(){
        this.fetchAppointedPatients()
    }
}
</script>

<style scoped>

</style>