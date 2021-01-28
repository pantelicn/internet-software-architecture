<template>
<div>
    <div class="border border-secondary bg-dark hoverable">
        {{formatDate(appointment.start)}}<br>
        {{formatTime(appointment.start)}}<br>
        {{formatPrice(appointment.price)}}<br>
        Dr. {{appointment.dermatologistName + ' ' + appointment.dermatologistLastName}}<br>
    </div>
    <button class="btn btn-success btn-sm hidden w-100 mb-1">Schedule</button>
</div>

</template>

<script>
import { format } from 'date-fns'

export default {
    props: {
        appointment: Object
    },
    methods: {
        format,
        formatDate: function (dateString) {
            return this.format(new Date(dateString), 'd.M.yyyy.')
        },
        formatTime: function (dateString) {
            return this.format(new Date(dateString), 'hh:mm')
        },
        formatPrice: function (price) {
            return parseFloat(price.amount).toFixed(2) + price.currency
        }
    }
}
</script>

<style scoped>
.hidden {
    display: none;
}

.hidden:hover {
    display: block;
}

.hoverable:hover + .hidden {
    display: block;
}
</style>