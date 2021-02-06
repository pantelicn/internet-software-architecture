<template>
  <div id="app">
    <UserNavbar/>
    <router-view/>
  </div>
</template>

<script>
import UserNavbar from './components/navbar/UserNavbar.vue'
export default {
  name: 'App',
  components: {
    UserNavbar
  }
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
</style>
