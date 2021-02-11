<template>
<div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#">ISA Pharmacy App</b-navbar-brand>
    

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item class="ml-4" to="/pharmacist">Upcoming Counselings</b-nav-item>
                <b-nav-item class="ml-4" to="/pharmacist/counseled-patients">Counseled Patients</b-nav-item>
                <b-nav-item class="ml-4" to="/pharmacist/drug-dispensing">Drug dispensing</b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <b-nav-item-dropdown right>
                    <template #button-content v-if="name!=null">
                        Pharmacist {{name.firstName + " " + name.lastName}}
                    </template>
                    <b-dropdown-item to="/pharmacist/my-profile">Profile</b-dropdown-item>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-item to="/pharmacist/time-off">Request time off</b-dropdown-item>
                    <b-dropdown-item to="/pharmacist/my-calendar">Calendar</b-dropdown-item>
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
import axios from 'axios'
import { api } from '../../api.js'
export default {
    name:'PharmacistNavBar',
    data() {
        return {
            name:null
        }
    },
    methods: {
        logout(){
            clearJwt()
            this.$router.push({ name: 'login' })
        },
        getName(){
            axios.get(api.person.name)
            .then(res => {
                this.name = res.data
            })
        }
    },
    mounted(){
        this.getName()
    }
}
</script>