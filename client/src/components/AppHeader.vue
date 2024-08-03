<template>
    <header>
        <div class="logo">
            <!-- Router link for logo, redirects to home page -->
            <router-link to="/"><img src="src/assets/MA.png" alt="Logo"></router-link>
        </div>
    <nav>
        <ul>
            <!-- Navigation links -->
            <li><router-link to="/about">About</router-link></li>
            <li><router-link to="/how-it-works">How It Works</router-link></li>
            <!-- //TODO: Make sign up disappear when logged in -->
            <li><router-link to="/register">Sign Up</router-link></li> 
            <li v-if="$store.state.token"><router-link v-bind:to="{ name: 'logout' }" v-on:click="$store.commit('LOGOUT')">Logout</router-link></li>
            <li v-else><router-link v-bind:to="{ name: 'login' }">Login</router-link></li>
   
        </ul>
    </nav>
</header>
</template>

<script>
    export default {

    }
</script>

<style scoped>

header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #7B7664;
  padding: 10px;
  padding-bottom: 2px;
  display: flex;
  justify-content: space-between;
  align-items: first baseline;
  z-index: 10;
}

.logo img {
  max-width: 18%;
  padding: 0;
  margin-left: 10px;
}

nav ul {
  font-family: 'Poppins', serif;
  font-size: 18px;
  list-style-type: none;
  margin: 0;
  padding: 0;
  display: flex;
  text-transform: uppercase;
}

nav ul li {
  margin-right: 20px;
}

nav ul li:last-child {
  margin-right: 20px;
}

nav ul li a {
  text-decoration: none;
  color: #EBE7D9;
  font-weight: bold;
}

a:hover {
  color: #101D24;
  text-decoration: underline;
}

</style>