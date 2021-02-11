<template>
    <b-container v-if="examination">
        <b-col>
            <b-row class="mt-5" align-h="center" size="lg">
                <h2>Examination report</h2>
            </b-row>
            <b-row class="mt-1 info" align-h="center" >
                <h6>Patient: {{examination.patientFullName}}</h6>
            </b-row>
            <b-row align-h="center" class="info">
                <h6>Started at: {{examination.start}}</h6>
            </b-row>
            <b-row align-h="center" class="info">
                <h6>Ends at: {{examination.end}}</h6>
            </b-row>
            <b-row align-h="center" class="info">
                <h6>Price: {{examination.price}}</h6>
            </b-row>
            <transition
                name="fade"
                mode="out-in"
            >
            <router-view/>
            </transition>
        </b-col>
        
    </b-container>
</template>

<script>
export default {
    name: 'ExaminationReport',
    data() {
        return {
            examination:null
        }
    },
    mounted(){
        this.examination = this.$store.state.report.currentAppointment
        if(this.examination == null){
            this.$router.push({name: 'upcoming-examinations'})
        }
    },
    beforeRouteEnter (to, from, next) {
        if(!(from.name == 'upcoming-examinations' || from.name == 'my-calendar'))
            next({ name: 'upcoming-examinations' })
        else
            next()
    },
    beforeRouteLeave (to, from, next) {
        this.$store.commit('clearAppointmentReport')
        next()
    }
}
</script>

<style scoped>
.info{
    color:rgb(195, 241, 157);
}
.step-elem{
    background-color: lightgray !important;
}
.fade-enter-active,
.fade-leave-active {
  transition-duration: 0.3s;
  transition-property: opacity;
  transition-timing-function: ease;
}

.fade-enter,
.fade-leave-active {
  opacity: 0
}
</style>