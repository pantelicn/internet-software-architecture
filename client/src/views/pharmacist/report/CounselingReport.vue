<template>
    <b-container v-if="counseling">
        <b-col>
            <b-row class="mt-5" align-h="center" size="lg">
                <h2>Counseling report</h2>
            </b-row>
            <b-row class="mt-1 info" align-h="center" >
                <h6>Patient: {{counseling.patientFullName}}</h6>
            </b-row>
            <b-row align-h="center" class="info">
                <h6>Started at: {{counseling.start}}</h6>
            </b-row>
            <b-row align-h="center" class="info">
                <h6>Ends at: {{counseling.end}}</h6>
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
    name: 'CounselingReport',
    data() {
        return {
            counseling:null
        }
    },
    mounted(){
        this.counseling=this.$store.state.report.currentAppointment

        if(this.counseling == null){
            this.$router.push({name: 'upcoming-counselings'})
        }
    },
    beforeRouteEnter (to, from, next) {
        if(!(from.name == 'upcoming-counselings' || from.name == 'my-calendar'))
            next({ name: 'upcoming-counselings' })
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