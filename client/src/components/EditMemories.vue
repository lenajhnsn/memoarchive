<template>
  <section id="edit-memories">
    <h2>Edit Your Memories</h2>
    <!-- Search input for filtering memories based on the search term. Two-way data binding for search input -->
    <input type="text" v-model="searchTerm" placeholder="Search memories" />

    <!-- Button to trigger searchMemories method. Event binding for search button click -->
    <button v-on:click="searchMemories">Search</button>

    <!-- Grid to display all the filtered memories -->
    <div class="image-grid">
      <!-- v-for to loop through each memory in the filteredMemories array -->
      <div
        v-for="memory in filteredMemories"
        v-bind:key="memory.id"
        class="memory"
      >
        <h3>{{ memory.title }}</h3>

        <!-- Display memory image. V-bind for image src and alt attributes -->
        <img v-bind:src="memory.imageUrl" v-bind:alt="memory.title" />
        <p class="memory-date">{{ memory.date }}</p>
        <p class="memory-description">{{ memory.description }}</p>

        <!-- Button to trigger updateMemory method for the current memory. Event binding for update button -->
        <button v-on:click="updateMemory(memory.id)" class="update-button">
          Update
        </button>

        <!-- Button to trigger deleteMemory method for the current memory. Event binding for delete button -->
        <button v-on:click="deleteMemory(memory.id)" class="delete-button">
          Delete
        </button>
      </div>
    </div>
  </section>
</template>

<script>
// import axios from "axios"; // TODO: get this import to work

export default {
  data() {
    return {
      searchTerm: "", // Search term for filtering memories
      memories: [], // Array to store memories fetched from the API
    };
  },
  computed: {

     // Compute the filtered memories based on the search term
    filteredMemories() {
      return this.memories.filter((memory) => {
        return (
          memory.title.toLowerCase().includes(this.searchTerm.toLowerCase()) ||
          memory.description
            .toLowerCase()
            .includes(this.searchTerm.toLowerCase())
        );
      });
    },
  },
  //   TODO: Add methods here for CRUD functionality
  // TODO: Get help with endpoints. Ex: '/api/memories/${id}'
};
</script>

<style scoped>
/* @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;600&display=swap");
@import url("https://fonts.googleapis.com/css2?family=Playfair+Display&display=swap"); */

body {
  font-family: "Playfair Display", serif;
  margin: 0;
  padding: 0;
  background-color: #ebe7d9;
  color: #101d24;
}

h2 {
  text-align: center;
  font-size: 40px;
}

.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 350px));
  gap: 20px;
  padding: 20px;
}

.memory {
  background-color: #fff;
  border: 1px solid #ccc;
  border-radius: 8px;
  padding: 15px;
  text-align: center;
  box-shadow: 0 2px 4px rgba(16, 29, 36, 0.1);
  width: 300px;
}

.memory h3 {
  font-size: 1.5em;
  margin: 10px 0;
}

.memory img {
  width: 100%;
  height: auto;
  border-radius: 8px;
  margin-bottom: 10px;
}

.memory-date {
  font-size: 1em;
  font-family: "Poppins", sans-serif;
  color: #101d24;
  font-weight: bold;
  margin: 5px 0;
}

.memory-description {
  font-family: "Poppins", sans-serif;
  font-size: 1em;
  color: #101d24;
  margin: 10px 0;
}

#search-input {
  background-color: white;
  color: #101d24;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  margin-left: 20px;
  padding: 10px 20px;
}

#search-button {
  background-color: #101d24;
  color: #ebe7d9;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin: 0 auto;
  padding: 10px 20px;
}

#search-button:hover {
  background-color: #234150;
}

.update-button {
  background-color: #101d24;
  color: #ebe7d9;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin: 0 auto;
  padding: 10px 20px;
}

.update-button:hover {
  background-color: #234150;
}

.delete-button {
  background-color: #101d24;
  color: #ebe7d9;
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  margin: 5px;
  padding: 10px 20px;
}

.delete-button:hover {
  background-color: #234150;
}
</style>
