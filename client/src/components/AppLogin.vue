<template>
    <!-- Main container for the login view -->
<div>
  <!-- Include Header component -->
  <AppHeader />
  
  <!-- Main content area for login form -->
  <main>
    <div class="login-container">
      <!-- Title for login form -->
      <h2>Login</h2>
      
      <!-- Login form -->
      <form id="login-form" v-on:submit.prevent="login">
        <!-- Username input field -->
        <input type="text" 
        v-model="username" 
        placeholder="Username" 
        required
        autofocus>
        
        <!-- Password input field -->
        <input type="password" id="password" v-model="password" placeholder="Password" required>
        
        <!-- Login button -->
        <button type="submit">Login</button>
        
        <!-- Additional options for password reset and sign-up -->
        <p id="pass-reset">Forgot password?</p>
        <p id="sign-up">Don't have an account?</p>
        <button v-on:click="goToSignup" type="button">Sign Up</button>
      </form>
    </div>
  </main>
  
  <!-- Include Footer component -->
  <AppFooter />
</div>
</template>

<script>
import authService from "../services/AuthService";
import AppFooter from "../components/AppFooter.vue";
import AppHeader from "../components/AppHeader.vue";

export default {
  components: {
      AppHeader,
      AppFooter
  },

  data() {
    return {
      user: {
        username: "",
        password: "",
      },
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then((response) => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch((error) => {
          const response = error.response;
          if (!response) {
            alert(error);
          } else if (response.status === 401) {
            alert("Invalid username and password!");
          } else {
            alert(response.message);
          }
        });
    },
  },
};
</script>

<style scoped>
body {
font-family: 'Playfair Display', serif;
margin: 10px;
padding: 0;
background-color: #EBE7D9;
color: #101D24;
}

/* Styles for header */
header {
position: fixed;
top: 0;
left: 0;
width: 100%;
background-color: #7B7664;
padding: 10px;
display: flex;
justify-content: space-between;
align-items: center;
z-index: 10;
}

.logo img {
max-width: 18%;
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

nav ul li a {
text-decoration: none;
color: #EBE7D9;
font-weight: bold;
}

a:hover {
color: #101D24;
text-decoration: underline;
}

/* Styles for login container and form */
.login-container {
background-color: white;
margin: 80px auto; /* Centered with margin from the top for fixed header */
padding: 20px;
border: 2px solid #888;
width: 30%;
border-radius: 8px;
font-family: 'Poppins', sans-serif;
box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
}

h2 {
text-align: center;
font-size: 40px;
text-transform: uppercase;
font-family: 'Poppins', sans-serif;
margin: 0 0 20px 0;
}

form {
display: flex;
flex-direction: column;
}

input[type=text], input[type=password] {
padding: 10px;
margin-bottom: 10px;
border: 1px solid #ccc;
border-radius: 4px;
font-size: 16px;
}

button[type=submit], button[type=button] {
background-color: #101D24;
color: white;
border: none;
padding: 10px;
font-size: 16px;
border-radius: 4px;
cursor: pointer;
margin-bottom: 10px;
}

button[type=submit]:hover, button[type=button]:hover {
background-color: #234150;
}

#pass-reset, #sign-up {
font-size: 12px;
text-align: center;
}

#pass-reset:hover, #sign-up:hover {
text-decoration: underline;
cursor: pointer;
}
</style>
