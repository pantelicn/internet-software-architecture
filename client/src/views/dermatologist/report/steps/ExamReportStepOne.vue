<template>
    <b-col>
        <b-row align-h="center" class="mt-5"> 
            <h3>Patient shown up?</h3>
        </b-row>
        <b-row align-h="center" class="mt-4" >
            <b-col sm="1">
                <b-button variant="success" @click="proceed()"> Yes </b-button>
            </b-col>
            <b-col sm="1">
                <b-button variant="danger"> No </b-button>
            </b-col>
        </b-row>
    </b-col>
</template>

<script>
export default {
    name:"ExamReportStepOne",
    methods: {
        proceed(){
            this.$router.push({ name: 'exam-report-step-two' })
        }
    },
}
</script>

<style>

</style>