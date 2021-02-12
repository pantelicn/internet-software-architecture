<template>
  <b-container>
    <b-row align-h="center" class="mt-4">
      <h2>
        Register in order to use the platform
      </h2>
    </b-row>

    <div style="display: grid;
          grid-template-columns: repeat(2, 1fr);
          grid-template-rows: 1fr;
          grid-column-gap: 0;
          grid-row-gap: 0;">

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Email address:</label>
          </b-row>
          <b-row>
            <b-input placeholder="Email" v-model="email"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Password:</label>
          </b-row>
          <b-row>
            <b-input placeholder="Password" type="password" v-model="password"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Username:</label>
          </b-row>
          <b-row>
            <b-input placeholder="Username" v-model="username"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>First name:</label>
          </b-row>
          <b-row>
            <b-input placeholder="First name" v-model="firstName"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Last name:</label>
          </b-row>
          <b-row>
            <b-input placeholder="Last name" v-model="lastName"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>PID: </label>
          </b-row>
          <b-row>
            <b-input placeholder="Personal identification number" v-model="pid"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Phone number:</label>
          </b-row>
          <b-row>
            <b-input placeholder="Phone number" v-model="phoneNumber"></b-input>
          </b-row>
        </b-col>

        <b-col cols="10" class="mt-4">
          <b-row>
            <label>Gender:</label>
          </b-row>
          <b-row>
            <b-select v-model="gender">
              <b-select-option value="MALE">
                MALE
              </b-select-option>
              <b-select-option value="FEMALE">
                FEMALE
              </b-select-option>
            </b-select>
          </b-row>
        </b-col>

      <b-col cols="10" class="mt-4">
        <b-row>
          <label>Country:</label>
        </b-row>
        <b-row>
          <select class="form-control" v-model="selectedCountry" @change="fetchCities"
          >
            <option
                v-for="country in countries"
                v-bind:key="country.id"
                v-bind:value="country"
            >
              {{country.name}}
            </option>
          </select>
        </b-row>
      </b-col>

      <b-col cols="10" class="mt-4">
        <b-row>
          <label>City:</label>
        </b-row>
        <b-row>
          <select class="form-control" v-model="selectedCity">
            <option
                v-for="city in cities"
                v-bind:key="city.id"
                v-bind:value="city"
            >
              {{city.name}}
            </option>
          </select>
        </b-row>
      </b-col>

      <b-col cols="10" class="mt-4">
        <b-row>
          <label>Address:</label>
        </b-row>
        <b-row>
          <b-input placeholder="Address" v-model="address.streetName"></b-input>
        </b-row>
      </b-col>

      <b-col cols="10" class="mt-4">
        <b-row>
          <label>Date of birth:</label>
        </b-row>
        <b-row>
          <b-input placeholder="YYYY.MM.DD" v-model="dobString"></b-input>
        </b-row>
      </b-col>
    </div>

    <div class="mt-5" style="width: 100%; margin: 0 auto; text-align: center;">
      <b-button class="btn-success pl-5 pr-5 pt-2 pb-2" @click="register">Register</b-button>
    </div>
    <div class="mt-5" style="width: 100%; margin: 0 auto; text-align: center;">
      <p class="text-light"> {{ statusMessage }}</p>
    </div>
  </b-container>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
  name : "Registration",
  data() {
      return {
          email : '',
          password : '',
          username : '',
          firstName : '',
          lastName : '',
          phoneNumber : '',
          dobString : '',
          dateOfBirth : undefined,
          gender : 'MALE',
          address : {
              longitude : 0, latitude : 0,
              streetName : '',
              city : undefined
          },
          pid : '',
          cities : [],
          countries : [],
          selectedCity : undefined,
          selectedCountry : undefined,
          statusMessage : ''
      }
  },
  methods : {
      register() {
          if (!this.validateInput()) return;

          this.address.city = {
              id : this.selectedCity.id
          }

          let tokens = this.dobString.split(".");
          if (tokens.length !== 3) {
              this.statusMessage = "Invalid date";
              return;
          }
          this.dateOfBirth = new Date(tokens[0], tokens[1], tokens[2]);

          axios.post(api.auth.register, {
              email : this.email,
              password : this.password,
              firstName : this.firstName,
              lastName : this.lastName,
              phoneNumber : this.phoneNumber,
              dateOfBirth : this.dateOfBirth,
              gender : this.gender,
              address : this.address,
              username : this.username,
              pid : this.pid
          })
          .then(() => {
              this.statusMessage = "Registration successful. Please check your email to activate your account.";
          })
          .catch(error => console.log("error "))
      },
      validateInput() {
          if (!this.email) {this.statusMessage = "Incorrect mail"; return false;}
          if (!this.password)  {this.statusMessage = "Incorrect password"; return false;}
          if (!this.firstName) {this.statusMessage = "Incorrect first name"; return false;}
          if (!this.pid) {this.statusMessage = "Incorrect PID"; return false;}
          if (!this.lastName) {this.statusMessage = "Incorrect last name"; return false;}
          if (!this.phoneNumber) {this.statusMessage = "Incorrect phone"; return false;}
          if (!this.dobString) {this.statusMessage = "Incorrect date of birth"; return false;}
          if (!this.address) {this.statusMessage = "Incorrect address"; return false;}
          if (!this.address.streetName) {this.statusMessage = "Incorrect address"; return false;}
          if (!this.selectedCity) {this.statusMessage = "Incorrect city"; return false;}
          if (!this.username) {this.statusMessage = "Incorrect username"; return false;}
          return true;
      },
      fetchCities() {
          axios.get(api.locale.cities + "/" + this.selectedCountry.id)
            .then(response => {
                this.cities = response.data;
            })
            .catch(() => this.statusMessage = "Failed to fetch cities for " + this.selectedCountry.id);
      },
      fetchCountries() {
          axios.get(api.locale.countries)
            .then(response => {
                this.countries = response.data;
            })
            .catch(() => this.statusMessage = "Failed to fetch countries");
      }
  },
  mounted() {
      this.fetchCountries();
  }
}
</script>

<style scoped>

</style>