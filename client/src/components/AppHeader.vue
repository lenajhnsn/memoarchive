<template>
  <div id="app">
    <header>
      <div class="logo">
        <!-- Router link for logo, redirects to home page -->
        <router-link to="/"
          ><img src="src/assets/MA.png" alt="Logo"
        /></router-link>
      </div>
      <nav>
        <ul>
          <!-- Navigation links -->
          <li><router-link to="/about">About</router-link></li>
          <li><router-link to="/how-it-works">How It Works</router-link></li>

          <!-- Show Sign Up only if not logged in -->
          <li v-if="!isLoggedIn">
            <router-link to="/register">Sign Up</router-link>
          </li>

          <!-- Show Login/Logout based on the state of user authentication -->
          <li v-if="isLoggedIn">
            <router-link v-on:click.prevent="logout" to="/logout">Logout</router-link>
          </li>
          <li v-if="!isLoggedIn"><router-link to="/login">Login</router-link></li>

          <!-- Profile link/icon only shown when logged in -->
          <li v-if="isLoggedIn">
            <router-link to="/memories">
              <img
                src="src/assets/profile-icon.png"
                alt="Profile"
                class="profile-icon"
              />
            </router-link>
          </li>
        </ul>
      </nav>
    </header>
  </div>
</template>

<script>
export default {
  computed: {
    isLoggedIn() {
      return this.$store.state.token;
    },
  },
  methods: {
    logout() {
      this.$store.commit("LOGOUT");
      this.$router.push("/login"); // Redirect to home after logout
    },
  },
};
</script>

<style scoped>
header {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  background-color: #7b7664;
  padding: 10px 20px; /* Added right padding for spacing */
  padding-bottom: 2px;
  display: flex;
  justify-content: space-between;
  align-items: first baseline;
  z-index: 10;
}

.logo img {
  max-width: 18%;
  padding: 0;
  margin-left: 0;
}

nav {
  display: flex;
  justify-content: flex-end;
  align-items: center;
  width: 100%;
}

nav ul {
  font-family: "Poppins", serif;
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
  margin-right: 0;
}

nav ul li a {
  text-decoration: none;
  color: #ebe7d9;
  font-weight: bold;
}

a:hover {
  color: #101d24;
  text-decoration: underline;
}
.profile-link {
  margin-left: auto; /* Pushes the profile icon to the right */
}

.profile-icon {
  width: 24px;
  height: 24px;
  border-radius: 50%;
}

/* Media query for mobile devices */
@media (max-width: 768px) {
  nav ul {
    font-size: 14px; /* Reduce font size for mobile */
  }

  nav ul li {
    margin-right: 10px; /* Reduce margin for mobile */
  }
}
</style>
