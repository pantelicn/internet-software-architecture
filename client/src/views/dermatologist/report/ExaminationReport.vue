<template>
    <b-container>
        <b-col>
            <b-row class="mt-5" align-h="center" size="lg">
                <h2>Examination report</h2>
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
            examinationId : null
        }
    },
}
</script>

<style scoped>
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