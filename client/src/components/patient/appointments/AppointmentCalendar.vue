<template>
<div class="container">
    <div class="row">
        <div class="col">
            <div class="border border-success">Monday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(0)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Tuesday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(1)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Wednesday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(2)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Thursday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(3)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Friday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(4)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Saturday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(5)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
        <div class="col">
            <div class="border border-success">Sunday</div>
            <FreeAppointment v-for="appointment in appointmentsForDay(6)" v-bind:key="appointment.id" v-bind:appointment="appointment"/>
        </div>
    </div>
</div>
</template>

<script>
import FreeAppointment from './FreeAppointment.vue'

export default {
    props: {
        appointments: Array
    },
    components: {
        FreeAppointment
    },
    methods: {
        appointmentsForDay: function (dayOfWeek) {
            let appointmentsForDay = []
            this.appointments.forEach(a => {
                if (new Date(a.start).getDay() == dayOfWeek) {
                    appointmentsForDay.push(a);
                }
            })
            return appointmentsForDay
        }
    }
}
</script>