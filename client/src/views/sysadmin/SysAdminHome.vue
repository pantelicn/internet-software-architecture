<template>
  <div>
    <SysAdminNavbar></SysAdminNavbar>
    <router-view></router-view>
  </div>
</template>

<script>
import SysAdminNavbar from "@/components/navbar/SysAdminNavBar";

export default {
name: "SysAdminHome",
components: {SysAdminNavbar}
}
</script>

<style scoped>

</style>