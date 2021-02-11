<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">ISA Pharmacy App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-content" aria-controls="navbar-content" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbar-content">

      <!-- Drop down HISTORY -->
      <ul class="navbar-nav mr-auto">
        <router-link to="/supplier/orders" class="nav-link">Pending orders</router-link>
      </ul>


      <!-- Regular hyperlink -->
      <div class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          Supplier
        </a>
        <div class="dropdown-menu dropdown-menu-right bg-dark" aria-labelledby="navbar-dropdown">
          <router-link class="dropdown-item" to="/supplier/profile">Profile</router-link>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#" @click="logout">Log out</a>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import {clearJwt} from "@/helpers/jwt";

export default {
  name: "SupplierNavBar",
  methods: {
    logout: function () {
      clearJwt()
      this.$router.push({ name: 'login' })
    },
  }
}
</script>

<style scoped>

</style>