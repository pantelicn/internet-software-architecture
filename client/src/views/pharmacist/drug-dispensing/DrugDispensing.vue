<template>
    <b-container>
        <b-col>
            <b-row align-h="center" class="mt-5 mb-3">
                <h2> Drug dispensing</h2>
            </b-row>
            <b-row align-h="center" class="mt-5">
                <b-col cols="2">
                    <label for="reservation-identifier" class="mt-1"> Reservation identifier:</label>
                </b-col>
                <b-col cols="5">
                    <b-input-group size="sm">
                        <b-form-input id="reservation-identifier"
                            v-model="reservationId"
                            type="search"
                             placeholder="Type an reservation identifier..."
                        >
                        </b-form-input>
                        <b-input-group-append>
                            <b-button  @click="reservationId = ''">Clear</b-button>
                        </b-input-group-append>
                        <b-input-group-append>
                            <b-button variant="success" @click="search" :disabled="reservationId ==''">Search</b-button>
                        </b-input-group-append>
                    </b-input-group>
                </b-col>
            </b-row>
            <b-row align-h="center" class="mt-4" v-if="foundDrugReservation != null">
                <b-card title="Reservation found!" sub-title="Reservation info:" style="width: 50%">
                    <b-card-text class="mt-2">
                        <b-col align-self="start">
                            <b-row class="mb-2 ml-1" >
                                Drug:
                            </b-row>
                            <b-row class="ml-3" >
                                Manufacturer : {{foundDrugReservation.manufacturer}}
                            </b-row>
                            <b-row class="ml-3" > 
                                Name: {{foundDrugReservation.name}}
                            </b-row>
                            <b-row class="ml-3" > 
                                Quantity: {{foundDrugReservation.quantity}} units
                            </b-row>
                            <b-row class="ml-3" >
                                Price per unit: {{foundDrugReservation.price.amount.amount + " " +
                                foundDrugReservation.price.amount.currency}}
                            </b-row>

                            <b-row class="mt-3 ml-1" >
                                <b>
                                    Total price : {{foundDrugReservation.quantity*foundDrugReservation.price.amount.amount
                                     + ' ' + foundDrugReservation.price.amount.currency}} 
                                </b>
                            </b-row>
                            <b-row class="mt-3" align-h="center" >
                                <b-button variant="success" @click="dispense">Dispense</b-button>
                            </b-row>

                        </b-col>
                    </b-card-text>
                    
                </b-card>
            </b-row>
        </b-col>
    </b-container>
</template>

<script>
import axios from 'axios'
import { api } from '../../../api.js'
export default {
    name:'DrugDispensing',
    data() {
        return {
            reservationId:'',
            foundDrugReservation:null
        }
    },
    methods:{
        search(){
            axios.get(api.drugs.reservations + this.reservationId )
            .then(res=>{
                this.foundDrugReservation = res.data
            })
            .catch(err=>{
                if(err.response.status == 400)
                    this.$toast.error('Reservation identifier isn\'t valid')
            })
        },
        dispense(){
            axios.delete(api.drugs.dispense + this.reservationId).
            then(res=>{
                this.$toast.open('Drug successfully dispensed')
                this.foundDrugReservation = null
                this.reservationId = ""
            })
        }
    },
}
</script>

<style scoped>
.card{
    background-color : #777575 !important;
}
.text-muted{
    color: #c2c2c2 !important;
}

</style>