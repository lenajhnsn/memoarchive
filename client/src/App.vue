<template>
    <div id="app">
      <AppHeader />
      <main class="main-content">
        <RouterView />
      </main>
      <AppFooter />
    </div>
  </template>

  <script>
  import AppHeader from './components/AppHeader.vue';
  import AppFooter from './components/AppFooter.vue';


  export default {
    name: 'App',
    components: {
    AppHeader,
    AppFooter,


}
  }
  </script>

  <style>

  </style>
