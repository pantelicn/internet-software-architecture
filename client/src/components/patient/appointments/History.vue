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
                <th>
                  Complaint
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
                <td>
                  <b-button class="btn btn-warning" v-b-modal.modal_complaint @click="loadComplaintWindow(appointment)"
                        >New complaint</b-button>
                </td>
            </tr>
        </tbody>
    </table>

    <b-modal id="modal_complaint" title="File a new complaint" @ok="sendComplaint">
      <div class="bg-info w-100 p-1">
        <b-textarea v-model="complaint.text">
        </b-textarea>
      </div>
      <div class="mt-3 ml-1">
        <span> File a complaint against: </span>

        <select v-model="complaint.type">
          <option value="EMPLOYEE_COMPLAINT">
            Employee
          </option>
          <option value="PHARMACY_COMPLAINT">
            Pharmacy
          </option>
        </select>
      </div>
    </b-modal>
</div>
</template>

<script>
import { format } from 'date-fns'
import axios from "axios";
import {api} from "@/api";
export default {
    props: {
        appointments: Array
    },
    data() {
        return {
            complaint: {
              text : '',
              employeeId: undefined,
              pharmacyId: undefined,
              pharmacyName: undefined,
              employeeName: undefined,
              type: "EMPLOYEE_COMPLAINT"
            }
        }
    },
    methods: {
        formatTime: function (date) {
            return format(new Date(date), "hh:mm")
        },
        formatDate: function (date) {
            return format(new Date(date), "dd.MM.yyyy.")
        },
        sendComplaint() {
            let complaint = {
                text: this.complaint.text,
                type: this.complaint.type,
            }
            if (this.complaint.type === "EMPLOYEE_COMPLAINT") {
                complaint.employeeId = this.complaint.employeeId;
            }
            else {
                complaint.pharmacyId = this.complaint.pharmacyId;
            }

            axios.post(api.complaint.root, complaint)
                .then(() => {
                    this.$toast.success("Succesfully sent a complaint.")
                })
                .catch(() => this.$toast.error("Failed to send complaint."))
        },
        loadComplaintWindow(appointment) {
            this.complaint.employeeId = appointment.employeeId;
            this.complaint.pharmacyId = appointment.pharmacyId;
            this.complaint.text = '';
            console.table(appointment);
            console.log("Employee id: " + appointment.employeeId + "\nPharmacy id: " + appointment.pharmacyId);
        }
    }
}
</script>