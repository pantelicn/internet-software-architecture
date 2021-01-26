<template>
<div>
    <FreeAppointment v-for="appointment in freeAppointments" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
</div>
</template>

<script>
import FreeAppointment from '../components/FreeAppointment.vue'
export default {
    data: function () {
        return {
            freeAppointments: []
        }
    },
    components: {
        FreeAppointment
    }
}
</script>