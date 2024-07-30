<template>
    <div id="app">
      <AppHeader />
      <router-view> </router-view>
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

  export default {
    name: 'App',
    components: {
      AppHeader,
      AppFooter
    }
  }
  </script>

  <style>

  </style>
