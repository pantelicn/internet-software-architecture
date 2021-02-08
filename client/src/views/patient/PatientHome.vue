<template>
<div>
    <UserNavbar/>
    <router-view></router-view>
</div>
</template>

<script>
import UserNavbar from '../../components/navbar/UserNavbar'

export default {
    components: {
        UserNavbar
    }
}
</script>