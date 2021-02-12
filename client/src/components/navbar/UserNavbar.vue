<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/">ISA Pharmacy App</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar-content" aria-controls="navbar-content" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" id="navbar-content">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        Schedule
                    </a>
                    <div class="dropdown-menu bg-dark" aria-labelledby="navbar-dropdown">
                        <router-link to="/patient" class="dropdown-item">Dermatologist examination</router-link>
                        <router-link class="dropdown-item" to="/patient/schedule/counseling">Pharmacist counseling</router-link>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                        History
                    </a>
                    <div class="dropdown-menu bg-dark" aria-labelledby="navbar-dropdown">
                        <router-link class="dropdown-item" to="/patient/history/examinations">Dermatologist visits</router-link>
                        <router-link class="dropdown-item" to="/patient/history/counselings">Pharmacist counseling</router-link>
                    </div>
                </li>
                <router-link to="/patient/drugs" class="nav-link">Drug reservation</router-link>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/patient/feedback">Feedback</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/patient/appointments">Appointments</router-link>
                </li>
            </ul>
            <div class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" id="navbar-dropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    User
                </a>
                <div class="dropdown-menu dropdown-menu-right bg-dark" aria-labelledby="navbar-dropdown">
                    <router-link class="dropdown-item" to="/patient/profile">Profile</router-link>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#" @click="logout">Log out</a>
                </div>
            </div>
        </div>
    </nav>
</template>

<script>
import { clearJwt } from '../../helpers/jwt.js'
export default {
    methods: {
        logout: function () {
            clearJwt()
            this.$router.push({ name: 'login' })
        },
    }
}
</script>