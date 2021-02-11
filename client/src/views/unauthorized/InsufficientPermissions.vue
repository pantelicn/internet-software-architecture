<template>
<div id="jumbotron">
  <b-jumbotron header="Insufficient Permissions" bg-variant="dark">
    <p>You dont have permission to access this page :(</p>
    <b-button variant="success" @click="$router.go(-2)">Go back</b-button>
  </b-jumbotron>
</div>
</template>

<script>
export default {
    name: 'InsufficientPermissions',
}
</script>

<style scoped>
.bg-dark{
    background-color: #393f45 !important;
}
</style>