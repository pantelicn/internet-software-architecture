<template>
  <div id="app">
    <router-view/>
  </div>
</template>

<script>
export default {
  name: 'App',
  mounted() {
        let placesImport = document.createElement('script');
            placesImport.setAttribute('src', "https://maps.googleapis.com/maps/api/js?key=AIzaSyAT-T7ngofAB9KXFewCksAfTWiuqx8-WCk&libraries=places")
            document.head.appendChild(placesImport)
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
#app .dropdown-menu-right{
    background-color: #393f45 !important;
}
#app hr {
    border-color : rgb(58, 92, 75) !important;
}
#app .modal-footer{
  border-top:1px solid #4f5357 !important;
}
</style>
