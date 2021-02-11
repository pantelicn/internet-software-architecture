<template>
    <b-col class="mt-4">
        <b-row>
            <h4><b-badge variant="success"> Counseling information</b-badge></h4>
        </b-row>
        <b-row>
            <b-form-textarea
                class="mt-2"
                id="infoarea"
                v-model="text"
                placeholder="Enter info about counseling..."
                rows="3"
                max-rows="6"
                required
            ></b-form-textarea>
        </b-row>
        <b-row align-h="center" class="mt-4" >
            <b-col sm="4">
                <b-button :disabled="text==''" variant="success" @click="proceed()"> Proceed to a drug prescription step </b-button>
            </b-col>
        </b-row>

    </b-col>
</template>

<script>
export default {
    name : "CounselingReportStepTwo",
    data() {
        return {
            text: ''
        }
    },
    methods:{
        proceed(){
            this.$store.commit('setExaminationInformation', this.text)
            this.$router.push({ name: 'counseling-report-step-three' })
        }
    },
    beforeRouteLeave (to, from, next) {
        if(to.name == 'counseling-report-step-three' && this.text === '')
            next( { name:'counseling-report-step-two' } )
        else if(to.name == 'counseling-report-step-four' && this.text === '')
            next({name: 'counseling-report-step-two'})
        else
            next()
    },
    mounted() {
        this.text = this.$store.state.report.appointmentReport.examinationInformation
    },
}
</script>

<style>

</style>