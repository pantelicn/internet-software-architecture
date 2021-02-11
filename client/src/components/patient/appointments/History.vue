<template>
<div>
    <table class="table table-striped table-dark">
        <thead>
            <tr>
                <th>
                    Doctor
                </th>
                <th @click="$emit('sort-duration')" style="cursor: pointer;">
                    Duration
                </th>
                <th>
                    Time
                </th>
                <th @click="$emit('sort-date')" style="cursor: pointer;">
                    Date
                </th>
                <th @click="$emit('sort-price')" style="cursor: pointer;">
                    Price
                </th>
            </tr>
        </thead>
        <tbody>
            <tr v-for="appointment in appointments" v-bind:key="appointment.id">
                <td>
                    {{appointment.employeeName + ' ' + appointment.employeeLastName}}
                </td>
                <td>
                    {{appointment.durationMinutes + 'min.'}}
                </td>
                <td>
                    {{formatTime(appointment.start)}}
                </td>
                <td>
                    {{formatDate(appointment.start)}}
                </td>
                <td>
                    {{appointment.price.amount + appointment.price.currency}}
                </td>
            </tr>
        </tbody>
    </table>
</div>
</template>

<script>
import { format } from 'date-fns'
export default {
    props: {
        appointments: Array
    },
    methods: {
        formatTime: function (date) {
            return format(new Date(date), "hh:mm")
        },
        formatDate: function (date) {
            return format(new Date(date), "dd.MM.yyyy.")
        }
    }
}
</script>