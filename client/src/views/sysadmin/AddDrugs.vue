<template>
  <b-container>
    <div class="row mt-5">

      <div class="col-6 text-right">
          <div>
            <label class="mr-3">Drug name</label>
            <input type="text" v-model="drugDto.drug.name">
          </div>

          <div class="mt-4">
            <label class="mr-3">Intake type</label>
            <select v-model="drugDto.drug.intakeType">
              <option value="CAPSULE">
                Capsule
              </option>
              <option value="POWDER">
                Powder
              </option>
              <option value="OINTMENT">
                Ointment
              </option>
              <option value="TABLET">
                Tablet
              </option>
              <option value="PASTE">
                Paste
              </option>
              <option value="GEL">
                Gel
              </option>
              <option value="SYRUP">
                Syrup
              </option>
              <option value="SOLUTION">
                Solution
              </option>
            </select>
          </div>

          <div class="mt-4">
            <label class="mr-3">Manufacturer></label>
            <input type="text" v-model="drugDto.drug.manufacturer">
          </div>

          <div class="mt-4">
              <label class="mr-3">Requires prescription?</label>
              <select v-model="drugDto.drug.requiresPrescription">
                <option value="true">
                  Yes
                </option>
                <option value="false">
                  No
                </option>
              </select>
          </div>

          <div class="mt-3">
            <label>Additional notes</label>
            <div></div>
            <textarea type="text" v-model="drugDto.drug.additionalNotes" placeholder="Notes..." rows="3"> </textarea>
          </div>
      </div>

      <div class="col-6 text-left">
          <div>
            <label class="mr-3">Drug type</label>
            <select v-model="drugDto.drug.drugType">
              <option value="NERVE">
                Nerve
              </option>
              <option value="CARDIO">
                Cardiovascular
              </option>
              <option value="RESPIRATORY">
                Respiratory
              </option>
              <option value="BLOOD">
                Blood
              </option>
              <option value="ANTIINFECTANT">
                Anti-infectant
              </option>
            </select>
          </div>

          <div class="mt-3">
            <label>Ingredients (comma sep.)</label>
            <div></div>
            <textarea type="text" v-model="ingredientsSpaced" placeholder="Ingredients divided by comma" rows="5"> </textarea>
          </div>

          <div class="mt-3">
            <p>Pick alternative drugs for this drug:</p>
            <select multiple v-model="drugDto.alternativeDrugIds" style="height: 250px; width: 100%">
              <option v-for="alt in alts" :key="alt.id" :value=alt.id style="height: 25px;">
                {{alt.name}}
              </option>
            </select>
          </div>
      </div>
    </div>

    <b-button class="mt-5 pl-5 pr-5 pt-3 pb-3 btn btn-success" @click="create"> Create </b-button>

  </b-container>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
    name: "AddDrugs",
    data() {
        return {
            drugDto: {
                drug: {
                    name: '',
                    drugType: '',
                    intakeType: '',
                    ingredients: [],
                    manufacturer: '',
                    requiresPrescription: false,
                    additionalNotes: ''
                },
                alternativeDrugIds: []
            },
            ingredientsSpaced: '',
            alts: []
        }
    },
    methods: {
        create() {
            this.preprocess();
            axios.post(api.drugs.root, this.drugDto)
                .then(() => this.$toast.success(this.drugDto.drug.name + " successfully created"))
                .catch(error => this.$toast.error(error.response.data));
        },
        preprocess() {
            let ingredients = this.ingredientsSpaced.split(" ");
            ingredients.forEach(ingredient => this.drugDto.drug.ingredients.push(ingredient));
        },
        getExistingDrugs() {
            axios.get(api.drugs.simple)
                .then(response => this.alts = response.data)
                .catch(error => this.$toast.error(error.response.data));
        }
    },
    mounted() {
        this.getExistingDrugs();
    }
}
</script>

<style scoped>
  select {
    width: 55%;
  }
  input {
    width: 55%;
  }

  textarea {
    width: 71%;
  }
</style>