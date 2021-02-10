<template>
<b-container class="mt-4">
  <h2>Pending orders from pharmacies</h2>
  <div class="mt-4 bg-white">
    <table class="text-light bg-dark table" style="border: none; border-collapse: collapse">
      <thead style="border: none">
        <th>Deadline</th>
        <th>Drug list</th>
        <th>Create offer</th>
      </thead>
      <tr v-for="order in orders" :key="order.id">
        <td>
          <p> {{order.offerDeadline}}</p>
        </td>
        <td>
          <b-table :items="order.orderedDrugs" borderless dark>
          </b-table>
        </td>
        <td>
          <b-button class="btn btn-success w-100 pt-2 pb-2" style="margin-top: 0;"
              @click="openOfferWindow(order)" v-b-modal:offer_modal
          >Offer</b-button>
        </td>
      </tr>
    </table>
  </div>

  <div>
    <b-modal title="Create offer" id="offer_modal" @ok="sendOffer">
        <b-table :items="currentOrder.orderedDrugs">
        </b-table>
        <div class="mt-3 bg-dark text-light p-3">
         <div class="row p-4">
           <label class="col-4">Deadline</label>
           <input class="col-8" type="text" placeholder="DD/MM/YYYY" v-model="offer.deliveryDeadlineString">
         </div>
          <div class="row p-4">
            <label class="col-4">Price</label>
            <input class="col-8" type="number" placeholder="Number..." v-model="offer.price.amount">
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
  name: "SupplierOrders",
  data() {
    return {
      orders: [],
      currentOrder: {
        orderedDrugs: []
      },
      offer: {
        deliveryDeadlineString: '',
        deliveryDeadline: undefined,
        price: {
          amount: ""
        },
        purchaseOrderId: 0
      }
    }
  },
  methods: {
    getOrders() {
      axios.get(api.order.allActive)
        .then(response => {this.orders = response.data;})
        .catch(() => this.$toast.error("Failed to fetch orders"));
    },
    openOfferWindow(order) {
      this.currentOrder = order;
      this.offer.purchaseOrderId = order.id;
      axios.get(api.offer.check + "/" + order.id)
        .then(response => {
          if (!response.data) {
            this.$toast.error("You do not have enough supplies to make this offer.")
          }
        })
        .catch(() => alert("Something broke."))
    },
    sendOffer() {
      console.log(this.offer);
      if (!this.offer.price.amount || !this.offer.deliveryDeadlineString) {
        this.$toast.error("Please insert correct data.");
        return;
      }
      let tokens = this.offer.deliveryDeadlineString.split('/');
      if (tokens.length !== 3) {
        this.$toast.error("Please insert correct data.");
        return;
      }
      this.offer.deliveryDeadline = new Date(tokens[2], tokens[1], tokens[0]);
      axios.post(api.offer.root, this.offer)
        .then(() => {
          this.offer.deliveryDeadlineString = '';
          this.offer.price.amount = '';
          this.$toast.success("Offer succesfully placed");
          this.orders = this.orders.filter(order => order.id !== this.currentOrder.id);
        })
        .catch(error => this.$toast.error(error.response.data));
    }
  },
  mounted() {
    this.getOrders();
  }
}
</script>

<style scoped>

</style>