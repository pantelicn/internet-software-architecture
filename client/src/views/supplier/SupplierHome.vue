<template>
  <div>
    <SupplierNavBar></SupplierNavBar>
    <router-view></router-view>
  </div>
</template>

<script>
import SupplierNavBar from "@/components/navbar/SupplierNavBar";
export default {
name: "SupplierHome",
  components: {SupplierNavBar}
}
</script>

<style scoped>

</style>