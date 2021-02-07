<template>
    <b-container>
         <b-row class="mt-5" align-h="center" size="lg">
            <h2>Upcoming Examinations</h2>
        </b-row>

        <b-col lg="4" class="my-3 mt-4">
            <b-form-group label="Patient:" label-for="filter-input" label-cols-sm="2" label-align-sm="right"
                label-size="sm" class="mb-0">
                <b-input-group size="sm">
                    <b-form-input id="filter-input"
                    v-model="filter" type="search" placeholder="Patients name or examination id..."
                    >
                    </b-form-input>

                    <b-input-group-append>
                        <b-button :disabled="!filter" @click="filter = ''">Clear</b-button>
                    </b-input-group-append>
                </b-input-group>
            </b-form-group>
        </b-col>

         <b-table
            class="mt-4"
            striped hover
            :dark="true"
            :items="examinations"
            :fields="fields"
            show-empty
            empty-text="You have no upcoming examinations."
            :filter="filter"
            empty-filtered-text="There are no patients to show."
        >
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

            <template #cell(startExam)="row">
                <b-button size="sm" @click="commitExamination(row.item,$event.target)">
                    Start
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
            appointmentType="examination"></appointment-history>
        </b-modal>

    </b-container>
</template>

<script>
import axios from 'axios'
import moment from 'moment'
import { api } from '../../../api.js'
import PatientProfileInfo from '../../../components/patient/PatientProfileInfo.vue'
import AppointmentHistory from '../../../components/patient/appointments/AppointmentHistory.vue'

export default {
    name: 'UpcomingExaminations',
    components: { PatientProfileInfo, AppointmentHistory},
    data() {
        return {
            examinations:[],
            fields:[
                { key: 'appointmentId', label: 'Examination Identifier', sortable: true },
                { key: 'price', label: 'Price', sortable:true },
                { key: 'patientFullName', label: 'Patients full name', sortable: true},
                { key: 'start', label: 'Examination begins at', sortable: true },
                { key: 'end', label: 'Examination ends at', sortable: true },
                { key: 'pharmacy', label: 'Pharmacy', sortable: true },
                { key: 'profile', label: 'Patients profile' },
                { key: 'examHistory', label: 'Patients examination history' },
                { key: 'startExam', label: 'Start an examination'}
                
            ],
            filter :null,
            filterOn: ['patientFullName','appointmentId'],
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
        fetchUpcomingExaminations(){
            axios.get(api.appointments.upcoming).then(res=>{
                res.data.forEach(element => {
                    this.examinations.push({
                        appointmentId : element.appointmentId,
                        price : element.price,
                        patientId : element.patientId,
                        pharmacyId : element.pharmacyId,
                        patientFullName : element.patientFirstName + " " + element.patientLastName,
                        start : moment(new Date(element.start)).format("MM/DD/YYYY HH:mm"),
                        end : moment(new Date(element.end)).format("MM/DD/YYYY HH:mm"),
                        pharmacy : element.pharmacyName
                    })
                });
            })
        },
        patientProfile:function(item,button){
            this.patientProfileModal.patientId = item.patientId
            this.patientProfileModal.title='Patient ' + item.patientFullName
            this.$root.$emit('bv::show::modal',this.patientProfileModal.id,button)
        },
        examinationHistory:function(item,button){
            this.patientExaminationsModal.patientId = item.patientId
            this.patientExaminationsModal.title='Examination history for a patient ' + item.patientFullName
            this.$root.$emit('bv::show::modal',this.patientExaminationsModal.id,button)
        },
        commitExamination:function(item,button){
            this.$store.commit('setCurrentAppointment',item)
            this.$router.push('/dermatologist/examination-report/')
        }
    },
    mounted(){
        this.fetchUpcomingExaminations()
    }
}
</script>

<style>
</style>