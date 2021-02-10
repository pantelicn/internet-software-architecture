<template>
  <b-container class="mt-4">
    <h1>Available offers</h1>
    <div>
      <table>
        <tr v-for="offer in offers" :key="offer.id">
          <td>
            {{ offer.id }}
          </td>
        </tr>
      </table>
    </div>
  </b-container>
</template>

<script>
import axios from "axios";
import {api} from "@/api";

export default {
  name: "SupplierOffers",
  data() {
    return {
      offers: [],

    }
  },
  methods: {
    getOffers() {
      axios.get(api.offer.root)
      .then(response => {
          this.offers = response.data;
      })
      .catch(() => this.$toast.error("Failed to fetch offers."));
    }
  },
  mounted() {
    this.getOffers();
  }
}
</script>

<style scoped>

</style>