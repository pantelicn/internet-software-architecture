<template>
    <b-col v-if="myPharmacies">
        <b-row align-h="center" class="mt-5 mb-3">
            <h2> Request time off</h2>
        </b-row>
        <b-row align-h="center" class="mt-5 mb-2">
            <label> Pharmacy:</label> 
        </b-row>
        <b-row cols="5" align-h="center">
            <b-form-select v-model="pharmacyId" :options="options"
             ></b-form-select>
        </b-row>
        <b-row align-h="center" class="mt-5 mb-2">
            <label> Select a time period: </label>
        </b-row>
        <b-row  cols="7" align-h="center">
            <DatePicker v-model="range" is-range :min-date="minDate" >
                <template v-slot="{ inputValue, inputEvents }">
                    <div class="flex justify-center items-center">
                    <input
                        :value="inputValue.start"
                        v-on="inputEvents.start"
                        class="border px-2 py-1 w-32 mr-3 rounded focus:outline-none focus:border-indigo-300"
                    />
                    <svg
                        class="w-4 h-4 mx-6 mr-3"
                        fill="none"
                        viewBox="0 0 25 25"
                        stroke="currentColor"
                    >
                        <path
                        stroke-linecap="round"
                        stroke-linejoin="round"
                        stroke-width="2"
                        d="M14 5l7 7m0 0l-7 7m7-7H3"
                        />
                    </svg>
                    <input
                        :value="inputValue.end"
                        v-on="inputEvents.end"
                        class="border px-2 py-1 w-32 rounded focus:outline-none focus:border-indigo-300"
                    />
                    </div>
                </template>
            </DatePicker>
        </b-row>
        <b-row align-h="center" class="mt-5 mb-2" cols="3">
            <label> Why are you requesting time off? </label>
        </b-row>
        <b-row cols="3" align-h="center">
            <b-form-textarea
                id="textarea"
                v-model="info"
                placeholder="Enter a reason..."
                rows="3"
                max-rows="6"
            ></b-form-textarea>
        </b-row>
        <b-row align-h="center" class="mt-5">
            <b-button variant="success" size="lg" @click="submit" :disabled="disabled">Submit</b-button>
        </b-row>

    </b-col>
</template>

<script>
export function getTomorrowsDate() {
    let date = new Date()
    date.setDate(date.getDate() + 1)
    return date
}
import axios from 'axios'
import { api } from '../../api.js'
import { getRoleId, getRole } from '../../helpers/jwt.js'

import DatePicker from 'v-calendar/lib/components/date-picker.umd'
export default {
    name:'TimeOffRequest',
    components:{ DatePicker },
    computed:{
        disabled(){
            if(this.pharmacyId === '' || this.pharmacyId == null)
                return true
            return false
        },
        select(){
            if(this.myPharmacies.length==1)
                return 1;
            else
                return this.selected
        }
    },
    data() {
        return {
            minDate: getTomorrowsDate(),
            range:{
                start: getTomorrowsDate(),
                end: getTomorrowsDate()
            },
            info:'',
            pharmacyId:'',
            myPharmacies:null,
            options:[
                { value:null, text: 'Select a pharmacy'}
            ],
            selected:null
        }
    },
    methods:{
        fetchMyPharmacies(){
            axios.get(api.employees.myPharmacies)
            .then(res => {
                this.myPharmacies = res.data
                this.myPharmacies.forEach(element => {
                    this.options.push({
                        value: element.pharmacyId,
                        text: element.pharmacyName
                    })
                });
            })
        },
        reroute(){
            if(getRole() == "ROLE_PHARMACIST")
                this.$router.push({ name: 'upcoming-counselings' })
            else if(getRole() == "ROLE_DERMATOLOGIST")
                this.$router.push({ name: "upcoming-examinations"})
        },
        submit(){
            let timeOffRequestDto = {
                employeeId: getRoleId(),
                pharmacyId: this.pharmacyId,
                requestInfo: this.info,
                start: this.range.start,
                end: this.range.end
            }
            
            axios.post(api.timeOff.root,timeOffRequestDto).then(res => {
                this.$toast.open('Time off request successfully submitted!')
                this.reroute()
            })
        }
    },
    mounted(){
        this.fetchMyPharmacies()
    }
}
</script>

<style scoped>
svg {
    width: 2%;
    height: 2%;
}
</style>