<template>
  <div>
    <SupplierNavBar></SupplierNavBar>
    <PassChange/>

    <router-view></router-view>
  </div>
</template>

<script>
import SupplierNavBar from "@/components/navbar/SupplierNavBar";
import PassChange from "@/components/PassChange";
export default {
name: "SupplierHome",
  components: {PassChange, SupplierNavBar},
}
</script>

<style scoped>

</style>