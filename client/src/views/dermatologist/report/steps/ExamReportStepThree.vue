<template>
    <b-col>
        <b-row>
            <h4><b-badge variant="success">Drug prescription</b-badge></h4>
        </b-row>
        <b-row align-h="center" class="mt-4" >
            <b-col sm="5">
                <b-button variant="success" @click="back()"> Go back to an examination information step </b-button>
            </b-col>
            <b-col sm="5">
                <b-button variant="success" @click="proceed()"> Proceed to an examination scheduling step </b-button>
            </b-col>
            
        </b-row>
    </b-col>
</template>

<script>
export default {
    name: 'ExamReportStepThree',
    methods:{
        proceed(){
            this.$router.push({ name: 'exam-report-step-four' })
        },
        back(){
            this.$router.push({ name: 'exam-report-step-two' })
        }
    }

}
</script>

<style>

</style>