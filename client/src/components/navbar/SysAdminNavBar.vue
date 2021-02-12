<template>
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/">ISA Pharmacy App</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-content" aria-controls="navbar-content" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbar-content">

     <!-- Drop down HISTORY -->
      <ul class="navbar-nav mr-auto">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            Register new
          </a>
          <div class="dropdown-menu bg-dark" aria-labelledby="navbar-dropdown">
            <router-link class="dropdown-item" to="/sys/register-supplier">Supplier</router-link>
            <router-link class="dropdown-item" to="/sys/register-pharmacy">Pharmacy</router-link>
            <router-link class="dropdown-item" to="/sys/register-ph-admin">Pharmacy admin</router-link>
            <router-link class="dropdown-item" to="/sys/register-sys-admin">Sys admin</router-link>
            <router-link class="dropdown-item" to="/sys/register-dermatologist">Dermatologist</router-link>
          </div>
        </li>
        <router-link to="/sys/complaints" class="nav-link">Complaints</router-link>
        <router-link to="/sys/drugs" class="nav-link">Add drugs</router-link>
      </ul>


      <!-- Regular hyperlink -->
      <div class="nav-item dropdown">
        <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
          SysAdmin
        </a>
        <div class="dropdown-menu dropdown-menu-right bg-dark" aria-labelledby="navbar-dropdown">
          <a class="dropdown-item" href="#" @click="logout">Log out</a>
        </div>
      </div>
    </div>
  </nav>
</template>

<script>
import {clearJwt} from "@/helpers/jwt";

export default {
  name: "SysAdminNavbar",
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