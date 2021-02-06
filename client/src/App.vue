<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  height: 100vh;
  text-align: center;
  color: #ffffff;
  background-color: #393f45;
}

#app a {
  color: #42b983;
}
#app .dropdown-menu{
    background-color: #393f45 !important;
}
#app .dropdown-divider {
    border-color : rgb(58, 92, 75) !important;
}
</style>
