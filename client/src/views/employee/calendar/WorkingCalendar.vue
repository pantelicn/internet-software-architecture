<template>
  <b-container v-if="events.length != 0">
    <b-col>
        <b-row align-h="center" class="mt-5 mb-3">
            <h2> Working Calendar </h2>
        </b-row>

        <b-row>
          <vue-cal :selected-date="currentDate"
            style="height:800px"
            :time-from="8 * 60"
            :time-to="22 * 60"
            :time-step="5"
            :disable-views="['years']"
            :events="events"
            :on-event-click="onEventClick">
          </vue-cal>
        </b-row>
    </b-col>

    <b-modal id="report-modal" :title=" 'Start ' + getAppointmentType() + '?'"
    v-if="selectedAppointment!=null" @ok="ok()">
          <b-col>
            <b-row>
              Pharmacy: {{selectedAppointment.pharmacy}}
            </b-row>
            <b-row>
              Patient: {{selectedAppointment.patientFullName}}
            </b-row>
            <b-row>
              Starts at: {{selectedAppointment.start}}
            </b-row>
            <b-row>
              Ends at: {{selectedAppointment.end}}
            </b-row>
            <b-row v-if="role == 'ROLE_DERMATOLOGIST'">
              Price: {{selectedAppointment.price}}
            </b-row>
          </b-col>
    </b-modal>

  </b-container>
</template>

<script>
import VueCal from 'vue-cal'
import 'vue-cal/dist/vuecal.css'
import axios from 'axios'
import moment from 'moment'
import { api } from '../../../api.js'
import { getRole } from '../../../helpers/jwt.js'

export default {
  name: 'WorkingCalendar',
  components: { VueCal },
  computed:{
    currentDate(){
      return new Date()
    } 
  },
  data() {
    return {
      role:'',
      events: [],
      selectedAppointment:null
    }
  },
  methods:{
    getAppointmentType(){
      let role = getRole()
      let type = role == "ROLE_DERMATOLOGIST" ? "Examination" : "Counseling"
      return type
    },
    onEventClick(event, e){
      if(!this.isInPast(event.appointment) && event.appointment.patientFullName !== "null null"){
        this.role = getRole()
        this.selectedAppointment = event.appointment
        this.$root.$emit('bv::show::modal','report-modal')
      }
    }
    ,
    formAppointment(element){
      return {
        appointmentId : element.appointmentId,
        price : element.price,
        patientId : element.patientId,
        pharmacyId : element.pharmacyId,
        patientFullName : element.patientFirstName + " " + element.patientLastName,
        start : moment(new Date(element.start)).format("MM/DD/YYYY HH:mm"),
        end : moment(new Date(element.end)).format("MM/DD/YYYY HH:mm"),
        pharmacy : element.pharmacyName
      }
    },
    ok(){
      this.$store.commit('setCurrentAppointment',this.selectedAppointment)
      if(getRole() == "ROLE_DERMATOLOGIST")
        this.$router.push('/dermatologist/examination-report/')
      else if(getRole() == "ROLE_PHARMACIST")
        this.$router.push('/pharmacist/counseling-report/')

    },
    isInPast(appointment){
      let date = new Date(appointment.start)
      let now = new Date()
      return date < now
    },

    addEvents(appointment){
      let content = "Pharmacy: <br>" + appointment.pharmacyName + "<br>"
      
      if( appointment.patientFirstName === null)
        content += "<b><em> Unreserved <br>" + this.getAppointmentType() + "</b></em>"
      else
        content += "<br>" + "Patient:<br>" 
        + appointment.patientFirstName + " " + appointment.patientLastName

      this.events.push({
        start : moment(new Date(appointment.start)).format("YYYY-MM-DD HH:mm"),
        end : moment(new Date(appointment.end)).format("YYYY-MM-DD HH:mm"),
        title : this.getAppointmentType(),
        content: content,
        appointment: this.formAppointment(appointment),
        class: appointment.patientFirstName != null && !this.isInPast(appointment) ? 'appointment' : '',
      })
    },

    fetchAppointments(){
            axios.get(api.appointments.all).then(res=>{
                res.data.forEach(element => {
                    this.addEvents(element)
                });
            })
        }
  },

  mounted() {
    this.fetchAppointments()
  },
}
</script>

<style>

.vuecal__menu{
  background-color: rgba(219, 236, 181, 0.42) !important;
}
.vuecal__title-bar{
  background-color: rgba(192, 236, 180, 0.144) !important
}
.vuecal__title button{
  color: white !important;
}
.vuecal__weekdays-headings{
  background-color: rgba(192, 236, 180, 0.144) !important
}
.vuecal__body{
  background-color: rgba(192, 236, 180, 0.144) !important;
  height: 150%;
}
.appointment {cursor: pointer;}

.vuecal__event-title {
  font-size: 1.2em;
  font-weight: bold;
  margin: 4px 0 8px;
}

.vuecal__event-time {
  display: inline-block;
  margin-bottom: 12px;
  padding-bottom: 12px;
  border-bottom: 1px solid rgba(0, 0, 0, 0.2);
}
.vuecal__cell-events-count{
  background-color: rgba(91, 245, 49, 0.575) !important;
}
.vuecal__event-content {
  font-style: italic;
}
</style>