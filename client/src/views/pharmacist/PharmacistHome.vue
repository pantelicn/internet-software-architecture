<template>
    <div>
        <PharmacistNavBar></PharmacistNavBar>
        <router-view/>
    </div>
</template>

<script>
import PharmacistNavBar from '../../components/navbar/PharmacistNavBar'
export default {
    name: 'PharmacistHome',
    components: {
        PharmacistNavBar
    }
}
</script>

<style>

</style>