<template>
<div class="container" v-if="dermatologists!=null">
    <div class="d-flex flex-column" id="scheduling-opt" v-if="showOptions" >
        <div class="p-2">
            <button type="button" class="btn btn-primary" v-on:click="showPredefined">
                Choose a predefined examination
            </button>
        </div>
        <div class="p-2"> OR </div>
        <div class="p-2">
            <button type="button" class="btn btn-primary" v-on:click="showExamCreation">
                Schedule a new examination by yourself
            </button>
        </div>
    </div>
    <div class="d-flex flex-column justify-content-center" id="predefined-exams" v-if="predefined">
        <div class="d-flex row justify-content-center">
            <label for="doctorSelect" id="doctor-select-label" class="mt-2">Dermatologist:</label>
            <select id="doctorSelect" class="form-control" @change="doctorSelected($event)">
                <option selected> Choose a dermatologist... </option>
                <option v-for="dermatologist in dermatologists" 
                        :key="dermatologist.id"
                        :value="dermatologist.id"> 
                    {{dermatologist.firstName + " " + dermatologist.lastName}} 
                </option>
            </select>
        </div>
    
        <div class="d-flex row mt-4 justify-content-center">
            <label class="mt-2 mr-4" id="exam-select-label" for="examSelect">Examination:</label>
            <select class="d-flex flex-row form-control " id="doctorSelect" >
                <option selected> Choose an examination... </option>
                <option> 14.12.2021. [10:30 - 11:00] </option>
                <option> 15.12.2021. [11:30 - 12:00]</option>
            </select>
        </div>
        <div class="d-flex row pt-4 justify-content-center">
            <button type="button" class="btn btn-primary" v-on:click="back">
                Back
            </button>
            <button type="button" class="btn btn-primary ml-4">
                Schedule
            </button>
            
        </div>
    </div>
    <div class="d-flex flex-column justify-content-center" id="exam-creation" v-if="examCreation">
        <div class="d-flex mr-4 row justify-content-center">
            <label for="doctorSelect2" id="doctor-select-label2" class="mt-2">Dermatologist:</label>
            <select id="doctorSelect2" class="form-control ml-2" @change="doctorSelected($event)" > 
                <option selected> Choose a dermatologist... </option>
                <option :value="dermatologist.id" 
                        v-for="dermatologist in dermatologists"
                        :key="dermatologist.id"> 
                    {{dermatologist.firstName + " " + dermatologist.lastName}} 
                </option>
            </select>
        </div>
        <div class="d-flex row pt-4 justify-content-center">
             <label id="date-time-select-label" class="mt-2">Date and time: </label>
            <DatePicker
                id="date-picker"
                :min-date="date"
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
        <div class="d-flex row pt-4 justify-content-center">
            <label for="duration-select" id="duration" class="mt-2">Duration:</label>
            <select id="duration" class="form-control ml-2">
                <option selected> Choose a duration... </option>
                <option> 15 minutes </option>
                <option> 30 minutes </option>
                <option> 45 minutes </option>
                <option> 1 hour </option>
            </select>
        </div>
        <div class="d-flex row pt-4 justify-content-center">
            <button type="button" class="btn btn-primary" v-on:click="back">
                Back
            </button>
            <button type="button" class="btn btn-primary ml-4">
                Schedule
            </button>
        </div>
       
    </div>
</div>

    
</template>

<script>
export function getTomorrowsDate(){
    let date = new Date()
    date.setDate(date.getDate() + 1)
    return date
}

import DatePicker from 'v-calendar/lib/components/date-picker.umd'
import axios from 'axios'
export default {
    name:"ExaminationReport",
    data:function(){
        return{
            showOptions:true,
            predefined:false,
            examCreation:false,
            date:getTomorrowsDate(),
            dermatologists:null,
            selectedDoctor:null
            
        }
    },
    components:{DatePicker},
    methods:{
        showPredefined:function(){
            this.showOptions = false
            this.predefined = true
        },
        showExamCreation:function(){
            this.showOptions = false
            this.examCreation = true
        },
        back:function(){
            this.showOptions = true
            this.examCreation = false
            this.predefined = false
        },
        getDermaBasicInfo:function(){
            axios.get('http://localhost:5000/api/employees/dermatologists/basic-info/1')
            .then(response=>{
                this.dermatologists = response.data
            })
        },
        doctorSelected:function(event){
            this.selectedDoctor = event.target.value
        }
    },
    mounted(){
        this.getDermaBasicInfo()
        
    }
}
</script>

<style scoped>

#scheduling-opt{
    margin-top: 10%;
}

#scheduling-opt .btn,.p-2{
    font-size: 20px
}
#predefined-exams,#exam-creation{
    margin-top: 10%;
}
#date-time-select-label{
    margin-left:-21.5%;
}
#date-picker{
    margin-left: 1%;
}
#predefined-exams select,#exam-creation select {
    width: 40%;
}
#doctor-select-label{
    margin-right: 1.5%;
}
#duration{
    margin-right: 2.7%
}
.btn-primary{
    background-color: #42b983 !important;
    border-color:#164731 !important;
}

.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to {
  opacity: 0;
}

</style>