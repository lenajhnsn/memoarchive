<template>
  <div id="app">
    <main class="main-content">
      <div id="register-form" class="register">
        <div class="register-content">
          <h2>Sign Up</h2>
          <form v-on:submit.prevent="createAccount">
            <input
              type="text"
              v-model="firstName"
              placeholder="First Name"
              required
            />
            <input
              type="text"
              v-model="lastName"
              placeholder="Last Name"
              required
            />
            <input type="email" v-model="email" placeholder="Email" required />

            <input
              type="text"
              v-model="username"
              placeholder="Username"
              required
            />
            <input
              type="password"
              v-model="password"
              placeholder="Password"
              required
            />
            <button type="submit">Create Account</button>
            <p id="login" v-on:click="redirectToLogin">
              Already have an account? <span>Log In</span>
            </p>
          </form>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import AuthService from "../services/AuthService";

export default {
  data() {
    return {
      firstName: "",
      lastName: "",
      email: "",
      username: "",
      password: "",
    };
  },
  methods: {
    createAccount() {
      const user = {
        firstName: this.firstName,
        lastName: this.lastName,
        email: this.email,
        username: this.username,
        password: this.password,
      };

      AuthService.register(user)
        .then((response) => {
          console.log("Account created successfully.", response.data);
          this.redirectToLogin();
        })
        .catch((error) => {
          console.error("Error creating account. Please try again", error);
        });
    },
    redirectToLogin() {
      this.$router.push("/login"); // Redirect user to the login page
    },
  },
};
</script>

<style scoped>
body {
  font-family: "Playfair Display", serif;
  margin: 10px;
  padding: 0;
  background-color: #ebe7d9;
  color: #101d24;
}

h1 {
  font-family: "Playfair Display", serif;
}

h2 {
  text-align: center;
  font-size: 40px;
  text-transform: uppercase;
  font-family: "Poppins", sans-serif;
  margin: 0;
}

.register-content {
  background-color: white;
  margin: 15% auto;
  padding: 20px;
  border: 2px solid #888;
  width: 30%;
  border-radius: 8px;
  font-family: "Poppins", sans-serif;
}

form {
  display: flex;
  flex-direction: column;
}

input[type="text"],
input[type="password"],
input[type="email"] {
  padding: 10px;
  margin-bottom: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
  font-size: 16px;
}

button[type="submit"] {
  background-color: #101d24;
  color: white;
  border: none;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 10px;
  margin-top: 10px;
}

button[type="submit"]:hover {
  background-color: #234150;
}

button[type="button"] {
  background-color: #7b7664;
  color: white;
  border: none;
  padding: 10px;
  text-align: center;
  text-decoration: none;
  font-size: 16px;
  border-radius: 4px;
  cursor: pointer;
  margin-bottom: 10px;
  max-width: 50%;
}

button[type="button"]:hover {
  background-color: #a8a392;
}

span {
  font-weight: bold;
  font-size: 12.5px;
}

#login {
  font-size: 12px;
  text-align: left;
}

span:hover {
  text-decoration: underline;
  cursor: pointer;
}

/* Media query for smaller screens */
@media (max-width: 700px) {
  .register-content {
    width: 80%; /* Increase width to 80% on smaller screens */
    max-width: none; /* Remove max-width for flexibility */
    margin: 20% auto; /* Adjust margin based on screen size */
  }

  h2 {
    font-size: 32px; /* Adjust the font size for smaller screens */
  }
}
</style>
