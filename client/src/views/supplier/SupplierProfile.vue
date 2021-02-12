<template>
  <div>
    <Profile/>
  </div>
</template>

<script>
import Profile from "@/views/person/Profile";

export default {
  name: "SupplierProfile",
  data() {
    return {
      loaded: false,
      person: undefined
    }
  },
  components: {
    Profile
  },
  mounted() {
    this.getPerson();
  }
}
</script>

<style scoped>

</style>