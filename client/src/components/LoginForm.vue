<template>
<div>
  <!-- Main content area for login form -->
  <main>
    <div class="login-container">
      <!-- Title for login form -->
      <h2>Login</h2>
      
      <!-- Login form -->
      <form id="login-form" v-on:submit.prevent="login">
        
        <!-- Username input field -->
        <input type="text" 
        v-model="user.username" 
        placeholder="Username" 
        required
        autofocus>
        
        <!-- Password input field -->
        <input type="password" 
        id="password" 
        v-model="user.password" 
        placeholder="Password" 
        required>

        <!-- Login button -->
        <button type="submit">Login</button> 
        
        <!-- Additional options for password reset and sign-up -->
        <p id="pass-reset">Forgot password?</p>
        <p id="sign-up" v-on:click="goToSignUp">Don't have an account? <span>Sign Up</span></p>
      </form>
    </div>
  </main>
</div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  components: {
  
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
            this.$router.push("/memories");
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
    goToSignUp() {
      this.$router.push("/register"); // Redirect user to the login page
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

/* Styles for login container and form */
.login-container {
  background-color: white;
  margin: 15% auto;
  padding: 20px;
  border: 2px solid #888;
  width: 30%;
  border-radius: 8px;
  font-family: "Poppins", sans-serif;
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

span {
  font-weight: bold;
  font-size: 12.5px;
}

#pass-reset, #sign-up {
font-size: 12px;
text-align: left;
}

span:hover {
text-decoration: underline;
cursor: pointer;
}
</style>
