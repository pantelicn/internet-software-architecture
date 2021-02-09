<template>
<div>
    <b-navbar toggleable="lg" type="dark" variant="dark">
        <b-navbar-brand href="#">ISA Pharmacy App</b-navbar-brand>
    

        <b-navbar-toggle target="nav-collapse"></b-navbar-toggle>

        <b-collapse id="nav-collapse" is-nav>
            <b-navbar-nav>
                <b-nav-item class="ml-4" to="/dermatologist">Upcoming Examinations</b-nav-item>
                <b-nav-item class="ml-4" to="/dermatologist/examined-patients">Examined Patients</b-nav-item>
            </b-navbar-nav>

            <b-navbar-nav class="ml-auto">
                <b-nav-item-dropdown right>
                    <template #button-content v-if="name!=null">
                        Dermatologist {{ name.firstName + " " + name.lastName }}
                    </template>
                    <b-dropdown-item to="/dermatologist/my-profile">Profile</b-dropdown-item>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-item to="/dermatologist/time-off">Request time off</b-dropdown-item>
                    <b-dropdown-item to="/dermatologist/my-calendar">Calendar</b-dropdown-item>
                    <b-dropdown-divider></b-dropdown-divider>
                    <b-dropdown-item @click="logout()">Sign Out</b-dropdown-item>
                </b-nav-item-dropdown>
            </b-navbar-nav>     
        </b-collapse>
    </b-navbar>
</div>
</template>

<script>
import axios from 'axios'
import { api } from '../../api.js'
import { clearJwt } from '../../helpers/jwt.js'
export default {
    name:'DermatologistNavBar',
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