<template>
<div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#">ISA Pharmacy App</b-navbar-brand>
    

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item class="ml-4" to="/pharmacist">Upcoming Counselings</b-nav-item>
                <b-nav-item class="ml-4" to="/pharmacist/counseled-patients">Counseled Patients</b-nav-item>
                <b-nav-item class="ml-4" to="#">Drug dispensing</b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <b-nav-item-dropdown right>
                    <template #button-content>
                        Pharmacist name
                    </template>
                    <b-dropdown-item href="#">Profile</b-dropdown-item>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-item href="#">Request time off</b-dropdown-item>
                    <b-dropdown-item href="#">Calendar</b-dropdown-item>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-item @click="logout()">Sign Out</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>     
        </b-collapse>
    </b-navbar>
</div>
</template>

<script>
import { clearJwt } from '../../helpers/jwt.js'
export default {
    name:'PharmacistNavBar',
    methods: {
        logout(){
            clearJwt()
            this.$router.push({ name: 'login' })

        }
    },
}
</script>