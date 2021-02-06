<template>
    <div>
        <DermatologistNavBar></DermatologistNavBar>
        <router-view/>
    </div>
</template>

<script>
import DermatologistNavBar from '../../components/navbar/DermatologistNavBar'
export default {
    name: 'DermatologistHome',
    components: {
        DermatologistNavBar
    }
}
</script>

<style>

</style>