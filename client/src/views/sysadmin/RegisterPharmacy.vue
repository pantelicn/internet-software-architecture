<template>
  <b-container>
    <h2>Register a new pharmacy</h2>

    <div class="mt-5 text-center">
      <div class="row mb-3">
        <label class="col-2">Name
        </label>
        <input class="col7" type="text" placeholder="Name.." v-model="pharmacyDto.name"/>
      </div>

      <div class="row mb-3">
        <label class="col-2">
          Address
        </label>
        <input class="col7" type="text" placeholder="Address.." v-model="pharmacyDto.address.streetName"/>
        <label class="ml-5 mr-2">City</label>
        <select v-model="selectedCity">
          <option v-for="city in cities" :key="city.id" :value="city">
            {{city.name}}
          </option>
        </select>
        <label class="ml-5 mr-2">Country</label>
        <select v-model="selectedCountry" @change="getCities"
        >
          <option
              v-for="country in countries"
              v-bind:key="country.id"
              v-bind:value="country"
          >
            {{country.name}}
          </option>
        </select>
      </div>
      <div class="row mb-3">
        <label class="col-2">
          Description
        </label>
        <input class="col7" type="text" placeholder="Description.." v-model="pharmacyDto.description"/>
      </div>
      <div class="row mb-3">
        <label class="col-2">
          Counseling price
        </label>
        <input class="col7" type="number" placeholder="Amount of RSD" v-model="pharmacyDto.counselingPrice.amount"/>
      </div>
    </div>

    <b-button class="btn btn-success" @click="create">Create</b-button>

  </b-container>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
    name: "RegisterPharmacy",
    data() {
        return {
            cities: [],
            countries: [],
            selectedCountry: undefined,
            selectedCity: undefined,
            pharmacyDto: {
                name: '',
                description: '',
                counselingPrice: {
                    amount: 0
                },
                address: {
                    streetName: '',
                    longitude: 0, latitude: 0,
                    city: {
                        id: undefined
                    }
                }
            }
        }
    },
    methods: {
        getCities() {
            axios.get(api.locale.cities + '/' + this.selectedCountry.id)
                .then(response => {
                    this.cities = [];
                    this.cities.push({id: -1, name: "Choose a city"})
                    response.data.forEach(city => this.cities.push(city))
                })
                .catch(() => this.$toast.error("Failed to fetch cities."));
        },
        getCountries() {
            axios.get(api.locale.countries)
                .then(response => this.countries = response.data)
                .catch(() => this.$toast.error("Failed to fetch countries."));
        },
        create() {
            console.log(this.pharmacyDto)
            this.pharmacyDto.address.city.id = this.selectedCity.id;
            axios.post(api.pharmacies.root, this.pharmacyDto)
                .then(response => this.$toast.success("Successfully created pharmacy."))
                .catch(error => this.$toast.error(error.response.data));
        }
    },
    mounted() {
        this.getCountries();
    }
}
</script>

<style scoped>

</style>