<template>
  <div>
    <SysAdminNavbar></SysAdminNavbar>
    <PassChange/>
    <router-view></router-view>
  </div>
</template>

<script>
import SysAdminNavbar from "@/components/navbar/SysAdminNavBar";
import PassChange from "@/components/PassChange";

export default {
name: "SysAdminHome",
components: {PassChange, SysAdminNavbar}
}
</script>

<style scoped>

</style>