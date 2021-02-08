<template>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="/">ISA Pharmacy App</a>

        <div class="collapse navbar-collapse" id="navbar-content">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item">
                    <router-link class="nav-link" to="/pharmacies"> Pharmacies </router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/drugs"> Drugs </router-link>
                </li>
            </ul>
            <ul class="navbar-nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/register">Register</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/">Login </router-link>
                </li>
            </ul>
        </div>
    </nav>
</template>

<script>
export default {
    name: 'UnauthNavBar'
}
</script>

<style>

</style>