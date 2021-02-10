<template>
  <b-container>
    <div class="row mt-5">
      <table class="w-100">
        <tr v-for="complaint in complaints" :key="complaint.id">
          <td class="row mt-2 mb-2 pb-3 pt-3" style="border: 1px whitesmoke solid;">
            <div class="col-2">
              <span>
                [{{complaint.type === "EMPLOYEE_COMPLAINT" ? "Employee" : "Pharmacy"}}]
              </span>
            </div>
            <div class="col-8 text-left">
              <span class="font-weight-bold" style="font-size: 18px;">
                {{complaint.text}}
              </span>
            </div>
            <div class="col-2">
              <b-button class="btn btn-warning" @click="openResponseDialog(complaint)" v-b-modal:modal_respond> Respond</b-button>
            </div>
          </td>
        </tr>
      </table>
    </div>
    <b-modal id="modal_respond" title="Create a new response" @ok="respond" ok-title="Respond" cancel-title="Close" ok>
      <h5>{{ currentComplaint.text }}</h5>
      <textarea class="mt-4 w-100 p-2" style="height: 150px;" placeholder="Respond to this complaint...">

      </textarea>
    </b-modal>
  </b-container>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
  name: "ComplaintResponse",
  data() {
      return {
          complaints: [],
          currentResponse: {
              text: ""
          },
          currentComplaint: {
              text: '',
              id: undefined
          }
      }
  },
  methods: {
      openResponseDialog(complaint) {
          this.currentComplaint.id = complaint.id;
          this.currentResponse.text = "";
          this.currentComplaint.text = complaint.text;
      },
      respond() {
          axios.post(api.complaint.respond, {
              complaintId: this.currentComplaint.id,
              text: this.currentResponse.text
          })
          .then(() => {
              this.$toast.success("Response successfully submitted.");
              this.complaints = this.complaints.filter(value => value.id !== this.currentComplaint.id)
          })
          .catch(() => this.$toast.error("Failed to submit response."));
      }
  },
  mounted() {
      axios.get(api.complaint.root)
          .then(response => {
              this.complaints = response.data;
          })
          .catch(error => this.$toast.error("Failed to fetch complaints."));
  }
}
</script>

<style scoped>

</style>