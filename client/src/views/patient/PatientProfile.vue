<template>
<div>
    <div class="modal fade" id="modal" role="dialog" aria-labelledby="modal" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content text-dark">
                <div class="modal-header">
                    <h5 class="modal-title">Update allergies</h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body text-left">
                    <div class="row">
                        <div class="col ml-1">
                            Drugs
                            <div class="border border-gray col">
                                <div v-for="drug in drugs" v-bind:key="drug.id" @click="selectedDrug = drug" style="cursor: pointer;" class="bg rounded p-1" v-bind:class="{'bg-success': selectedDrug.id == drug.id}">
                                    {{drug.name}}
                                </div>
                            </div>
                            
                        </div>
                        <div class="text-center d-flex align-items-center">
                            <div>
                                <button class="btn" @click="addAllergy">
                                    <svg width="20" height="20" fill="currentColor" class="bi bi-arrow-right" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M1 8a.5.5 0 0 1 .5-.5h11.793l-3.147-3.146a.5.5 0 0 1 .708-.708l4 4a.5.5 0 0 1 0 .708l-4 4a.5.5 0 0 1-.708-.708L13.293 8.5H1.5A.5.5 0 0 1 1 8z"/>
                                    </svg>
                                </button>
                                <br>
                                <button class="btn" @click="removeAllergy">
                                    <svg width="20" height="20" fill="currentColor" class="bi bi-arrow-left" viewBox="0 0 16 16">
                                        <path fill-rule="evenodd" d="M15 8a.5.5 0 0 0-.5-.5H2.707l3.147-3.146a.5.5 0 1 0-.708-.708l-4 4a.5.5 0 0 0 0 .708l4 4a.5.5 0 0 0 .708-.708L2.707 8.5H14.5A.5.5 0 0 0 15 8z"/>
                                    </svg>
                                </button>
                            </div>
                        </div>
                        <div class="col mr-1">
                            Your allergies
                            <div class="border border-gray col" v-if="patient" >
                                <div v-for="allergy in allergies" v-bind:key="allergy.id" @click="selectedAllergy = allergy" style="cursor: pointer;" class="bg rounded p-1" v-bind:class="{'bg-success': selectedAllergy.id == allergy.id}">
                                    {{allergy.name}}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div>
                        <button type="button" class="btn btn-success" data-dismiss="modal" @click="updateAllergies">Confirm</button>
                    </div>
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Abort</button>
                </div>
            </div>
        </div>
    </div>
    <div class="row" v-if="patient">
        <div class="col-2 mt-5">
            <div class="border border-success ml-2">
                <h5>Your penalties</h5>
                <h1 v-bind:class="{'text-danger': patient.penalties >= 3}">{{patient.penalties}}</h1>
                <div class="text-warning">
                    Having 3 or more penalties restricts access to some features.<br>
                    <b class="text-light">Penalties reset every 1st of a month.</b>
                </div>
            </div>
        </div>
        <div class="col-8">
            <Profile/>
        </div>
        <div class="col-2 mt-5">
            <div class="border border-success mr-2">
                <h5>Allergies</h5>
                <div v-if="patient.allergies.length > 0">
                    <table class="table table-striped table-dark">
                        <tr v-for="drug in patient.allergies" v-bind:key="drug.id">
                            <td>{{drug.name}}</td>
                        </tr>
                    </table>
                </div>
                <div v-else class="mt-2">No allergies recorded.</div>
                <button class="btn btn-sm btn-info mt-4 mb-1" data-toggle="modal" data-target="#modal" @click="openAllergies">Update allergies</button>
            </div>
        </div>
    </div>
</div>
</template>

<script>
import Profile from '../person/Profile.vue'
import axios from 'axios'
import { api } from '../../api.js'

export default {
    data: function () {
        return {
            patient: null,
            drugs: [],
            allergies: [],
            selectedAllergy: '',
            selectedDrug: ''
        }
    },
    components: {
        Profile
    },
    mounted: function () {
        this.fetchPatient()
    },
    methods: {
        fetchPatient: function () {
            axios.get(api.patients.root)
            .then(response => {
                this.patient = response.data
            })
            .catch()
        },
        fetchDrugs: function () {
            this.clearSelection()
            axios.get(api.drugs.root)
            .then(response => {
                this.drugs = response.data
            })
        },
        clearSelection: function () {
            this.selectedAllergy = ''
            this.selectedDrug = ''
        },
        addAllergy: function () {
            if (this.selectedDrug)
                if (!this.allergies.map(a => a.id).includes(this.selectedDrug.id))
                    this.allergies.push(
                        {
                            id: this.selectedDrug.id,
                            name: this.selectedDrug.name
                        }
                    )
        },
        removeAllergy: function () {
            if (this.selectedAllergy)
                for (let i = 0; i < this.allergies.length; i++) {
                    if (this.allergies[i].id === this.selectedAllergy.id) {
                        this.allergies.splice(i, 1);
                        return
                    }
                }
        },
        openAllergies: function () {
            this.allergies = [...this.patient.allergies]
            this.fetchDrugs()
        },
        updateAllergies: function () {
            axios.put(api.patients.allergies, this.allergies)
            .then(() => {
                this.$toast.open("Allergies successfully updated.")
                this.fetchPatient()
            })
            .catch(error => {
                this.$toast.error(error.response.data)
            })
        }
    },
}
</script>