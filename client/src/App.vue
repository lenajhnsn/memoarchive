<template>
    <div id="app">
      <AppHeader />
      <router-view> </router-view>
      <HomePage />
      <AppAbout />
      <HowItWorksVue />
      <SignUp />
      <EditMemories />

      <AppFooter />
    </div>
    <nav>
        <router-link v-bind:to="{ name: 'logout' }" v-if="$store.state.token">
          Logout
        </router-link>
        <router-link v-bind:to="{ name: 'login' }" v-else>Login</router-link>
    </nav>
  </template>

  <script>
  import AppHeader from './components/AppHeader.vue';
  import AppFooter from './components/AppFooter.vue';
  import AppAbout from './components/AppAbout.vue';
  import AppLogin from './components/AppLogin.vue';
  import Dashboard from './components/Dashboard.vue';
  import EditMemories from './components/EditMemories.vue';
  import HomePage from './components/HomePage.vue';
  import HowItWorks from './components/HowItWorks.vue';
  import SignUp from './components/SignUp.vue';

  export default {
    name: 'App',
    components: {
    AppHeader,
    AppFooter,
    HomePage,
    AppAbout,
    EditMemories,
    AppLogin,
    SignUp,
    Dashboard,
    HowItWorks

}
  }
  </script>

  <style>

  </style>
