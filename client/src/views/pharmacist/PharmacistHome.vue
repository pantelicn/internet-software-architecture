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

<style scoped>
.modal-footer{
    border-top:1px solid #4f5357 !important;
}
.modal-header{
    border-bottom: 1px solid #4f5357 !important;
}
</style>