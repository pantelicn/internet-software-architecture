<template>
 <b-col>
    <b-row>
        <h4><b-badge variant="success">Examination scheduling</b-badge></h4>
    </b-row>
    <b-row align-h="center" class="mt-4" >
        <b-col sm="4">
            <b-button variant="success" @click="back()"> Go back to a drug prescription step </b-button>
        </b-col>
        <b-col sm="4">
            <b-button variant="success" @click="proceed()"> Finish an examination </b-button>
        </b-col>
        
    </b-row>
</b-col>
</template>

<script>
export default {
    name: 'ExamReportStepThree',
    methods:{
        proceed(){
            alert('finished')
        },
        back(){
            this.$router.push({ name: 'exam-report-step-three' })
        }
    }
}
</script>

<style>

</style>