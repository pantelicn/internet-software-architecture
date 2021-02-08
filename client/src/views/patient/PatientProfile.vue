<template>
    <Profile/>
</template>

<script>
import Profile from '../person/Profile.vue'

export default {
    data: function () {
        return {
            penalties: 0
        }
    },
    components: {
        Profile
    }
}
</script>