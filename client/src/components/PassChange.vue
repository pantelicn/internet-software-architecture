<template>
  <b-modal ref="never-logged"
           body-bg-variant="dark"
           body-text-variant="light"
           footer-bg-variant="dark"
           header-bg-variant="dark"
           header-text-variant="light"
           ok-variant="success"
           no-close-on-backdrop
           hide-header-close
           id="never-logged"
           title="Looks like you've never logged before"
           @ok="handleOk"
           @hide="handleHide"
           ok-only>
    <b-col>
      <b-row>
        <label> Please enter a new password </label>
      </b-row>
      <b-row>
        <b-form-input type="password" v-model="password"></b-form-input>
      </b-row>
      <b-row>
        <b-form-invalid-feedback :state="passwordMatching">
          Passwords must match
        </b-form-invalid-feedback>
      </b-row>
      <b-row>
        <b-form-invalid-feedback :state="passwordLengthValid">
          Your password must have at least 6 characters
        </b-form-invalid-feedback>
      </b-row>
      <b-row class="mt-2">
        <label> Validate your password </label>
      </b-row>
      <b-row>
        <b-form-input type="password" v-model="passwordValidated"></b-form-input>
      </b-row>
      <b-row>
        <b-form-invalid-feedback :state="passwordMatching">
          Passwords must match
        </b-form-invalid-feedback>
      </b-row>
      <b-row>
        <b-form-invalid-feedback :state="passwordLengthValid">
          Your password must have at least 6 characters
        </b-form-invalid-feedback>
      </b-row>
    </b-col>
  </b-modal>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
  name: "PassChange",
  computed:{
      passwordMatching(){
          return (this.password === this.passwordValidated) && (this.password && this.passwordValidated)
      },
      passwordLengthValid(){
          return (this.passwordMatching && this.password.length >= 6 && this.passwordValidated.length >=6)
      }
  },
  data() {
      return {
          password : '',
          passwordValidated : '',
      }
  },
  methods:{
      hasLoggedBefore() {
          axios.get(api.auth.hasLoggedBefore)
              .then(response => {
                 if(!response.data) {
                    this.$refs['never-logged'].show();
                 }
          })
    },
    updatePassword() {
        axios
        .post(api.auth.changePassword, { password : this.password })
        .then(() => {
            this.$nextTick(() => {
              this.$bvModal.hide('never-logged');
            })
        })
        .catch(error => {
            this.$toast.error(error.data);
        })
    },
    handleOk(bvModalEvt) {
        bvModalEvt.preventDefault();
        if(this.passwordMatching && this.passwordLengthValid) {
            this.updatePassword();
        }
    },
    handleHide(bvModalEvt) {
        if(!this.passwordMatching) {
            bvModalEvt.preventDefault();
        }
    }

  },
  mounted(){
    this.hasLoggedBefore()
  }
}
</script>

<style scoped>

</style>