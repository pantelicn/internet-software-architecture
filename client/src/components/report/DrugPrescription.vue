<template>
    <b-container >
        <div v-if="checkFinished">
            <b-row v-if="errText == ''">
                <b-col>
                    <label>Specify duration in days:</label>
                </b-col>
                <b-col>
                    <b-form-input type="number" v-model="duration" ></b-form-input>
                </b-col>
                <b-col>
                    <b-button size="sm" @click="prescribe()">
                            Prescribe
                    </b-button>
                </b-col>
            </b-row>
            <b-col v-else>
                <b-row>
                    <label class="err-text">{{this.errText}}</label>
                </b-row>
                <b-row>
                    Consider prescribing its alternatives: 
                </b-row>
                <b-row v-for="alternative in alternatives" :key="alternative.id">
                    &#183; {{alternative.name}}
                </b-row>
            </b-col>
            
        </div>
        <div v-else>
            <b-row align-h="center">
                <b-spinner label="Spinning"></b-spinner>
            </b-row>
        </div>
    </b-container>
</template>

<script>
import { api } from '../../api.js'
import axios from 'axios'
export default {
    
    name: 'DrugPrescripton',
    props: {
        drug: Object
    },
    data() {
        return {
            appointment:null,
            alternatives:null,
            checkFinished: false,
            errText:'',
            duration:0
        }
    },
    mounted(){
        this.appointment = this.$store.state.report.currentAppointment
        this.checkAvailability()
    },
    methods:{
        prescribe(){
            if(this.duration == 0){
                this.$toast.error('Please provide a duration')
                return;
            }

            this.$store.commit('addPrescribedDrug',
                    {   drug: this.drug, 
                        duration: this.duration }
            )
            this.$root.$emit('bv::hide::modal','drug-prescription-modal')
            this.$root.$emit('update::prescribed')

        },
        checkAvailability(){
            axios.get(api.storedDrugs.isAvailable + '?pharmacyId=' + this.appointment.pharmacyId
                        + '&drugId=' + this.drug.id)
            .then(res=>{
                this.checkIfPatientIsAllergic()
            })
            .catch(err=>{
                if(err.response.status === 417){
                    this.alternatives = err.response.data
                    this.errText = this.drug.name + " isn't currently available at the pharmacy."
                    this.checkFinished=true
                }
            })
        },
        checkIfPatientIsAllergic(){
            axios.get(api.patients.isAllergic + '?patientId=' + this.appointment.patientId
                        + '&drugId=' + this.drug.id)
            .then(res=>{
                this.checkFinished=true
            })
            .catch(err=>{
                 if(err.response.status === 417){
                    this.alternatives = err.response.data
                    this.errText = this.appointment.patientFullName
                     + " is allergic to " + this.drug.name
                     this.checkFinished = true
                }
            })
        }
    }
}
</script>

<style scoped>
.err-text{
    color: rgb(241, 113, 113);
}

</style>