<template>
    <div>
        <UnauthNavBar></UnauthNavBar>
        <router-view/>
    </div>
</template>

<script>
import UnauthNavBar from '../../components/navbar/UnauthNavBar'
export default {
    name:'UnauthHome',
    components:{UnauthNavBar}
}
</script>


<style>
</style>