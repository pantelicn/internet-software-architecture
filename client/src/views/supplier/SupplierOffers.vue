<template>
  <b-container class="mt-5">
    <div class="row">
      <h1 class="col-9">Available offers</h1>
      <select class="col-3" v-model="filter" style="font-size: 24px;" @change="doFilter">
        <option value="All">
          All
        </option>
        <option value="ACCEPTED">
          Accepted
        </option>
        <option value="REJECTED">
          Rejected
        </option>
        <option value="PENDING">
          Pending
        </option>
      </select>
    </div>
    <div class="mt-5">
      <table class="text-light bg-dark table" style="border: none; border-collapse: collapse">
        <thead style="border: none">
          <th>Id</th>
          <th>Price</th>
          <th>Delivery deadline</th>
          <th>Status</th>
          <th>Ordered drugs</th>
          <th>Edit</th>
        </thead>
        <tr v-for="offer in displayedOffers" :key="offer.id">
          <td>{{offer.id}}</td>
          <td>{{offer.price.amount}} RSD</td>
          <td>{{new Date(offer.deliveryDeadline).toDateString()}}</td>
          <td>{{offer.status}}</td>
          <td>
                <b-table :items="offer.purchaseOrder.orderedDrugs" borderless dark></b-table>
          </td>
          <td>
                <b-button class="btn btn-success w-100 pt-2 pb-2" style="margin-top: 0;"
                    @click="openUpdateWindow(offer)" v-b-modal:update_modal>Edit</b-button>
          </td>
        </tr>
      </table>
    </div>
    <div>
      <b-modal id="update_modal" title="Update your offer" @ok="update">
        <b-table :items="currentOffer.purchaseOrder.orderedDrugs">
        </b-table>
        <div class="mt-3 bg-dark text-light p-3">
          <div class="row p-4">
            <label class="col-4">Deadline</label>
            <input class="col-8" type="text" placeholder="DD/MM/YYYY" v-model="currentOffer.deliveryDeadlineString">
          </div>
          <div class="row p-4">
            <label class="col-4">Price</label>
            <input class="col-8" type="number" placeholder="Number..." v-model="currentOffer.price.amount">
          </div>
        </div>
      </b-modal>
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
      allOffers: [],
      displayedOffers: [],
      currentOffer: {
        purchaseOrder: {
          orderedDrugs: []
        },
        price: {
          amount: ''
        },
        deliveryDeadlineString: '',
        deliveryDeadline: undefined
      },
      filter: "All"
    }
  },
  methods: {
    getOffers() {
      axios.get(api.offer.root)
      .then(response => {
          this.allOffers = response.data;
          this.displayedOffers = this.allOffers;
      })
      .catch(() => this.$toast.error("Failed to fetch offers."));
    },
    openUpdateWindow(offer) {
      this.currentOffer = offer;
      this.currentOffer.deliveryDeadlineString = new Date(this.currentOffer.deliveryDeadline).toDateString();
    },
    update() {
      let offer = this.currentOffer;
      if (!offer.price.amount || !offer.deliveryDeadlineString) {
        this.$toast.error("Please insert correct data.")
        return;
      }
      offer.deliveryDeadline = new Date(offer.deliveryDeadlineString);

      axios.put(api.offer.root, {
            deliveryDeadline: offer.deliveryDeadline,
            price: offer.price,
            purchaseOrderId: offer.purchaseOrder.id
      })
        .then(() => this.$toast.success("Successfully updated your offer."))
        .catch(error => this.$toast.error(error.response.data));
    },
    doFilter() {
      if (this.filter === "All") {
        this.displayedOffers = this.allOffers;
        return;
      }
      this.displayedOffers = [];
      this.allOffers.forEach(offer => {
          if (offer.status === this.filter) {
            this.displayedOffers.push(offer);
          }
      })
      if (this.displayedOffers.length === 0) {
        this.$toast.info("No offers with status [" + this.filter + "]");
      }
    }
  },
  mounted() {
    this.getOffers();
  }
}
</script>

<style scoped>

</style>