<template>
  <div id="app">
    <main class="main-content">
      <div class="memory">
        <!-- Display the title of the memory -->
        <h3>{{ memory.title }}</h3>

        <!-- Display the image associated with the memory -->
        <img v-bind:src="memory.content" v-bind:alt="memory.title" />

        <!-- Display the date of the memory -->
        <p class="memory-date">{{ memory.memoryDate }}</p>

        <!-- Display the description of the memory -->
        <p class="memory-description">{{ memory.description }}</p>

        <!-- Button to update the memory. Pressing button calls the updateMemory method -->
        <button v-on:click="triggerUpdate(memory.id, memory)">Update</button>

        <!-- Button to delete the memory. Calls the deleteMemory method -->
        <button v-on:click="triggerDelete()">Delete</button>
      </div>
    </main>
  </div>
</template>

<script>
import MemoryService from "../services/MemoryService";

export default {
  // Accept a prop named 'memory' that contains details about the memory
  props: {
    memory: {
      type: Object,
      required: true,
    },
    onUpdate: Function, // This is a function passed from the parent (ProfileView)
    onDelete: Function, // This is a function passed from the parent (ProfileView)
  },
  methods: {
    // Trigger the update prompt in the parent component
    triggerUpdate() {
      if (this.memory) {
        this.onUpdate(this.memory);
      } else {
        console.error("Memory data is missing.");
      }
    },
    // Call the onDelete function with the memory id to trigger the deletion process
    triggerDelete() {
      if (this.memory.id) {
        this.onDelete(this.memory.id);
      } else {
        console.error("Memory ID is undefined");
      }
    },
  },
};
</script>

<style scoped>
/* Scoped styles for the memory component */

.edit-memory-btn {
  background-color: #101d24; /* Light beige background */
  color: #ebe7d9; /* Dark gray/blue text color */
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  display: block;
  margin: 0 auto;
  padding: 15px 30px;
}

.edit-memory-btn:hover {
  background-color: #101d24;
  text-decoration: underline;
}

/* Style for the memory container */
.image-grid {
  display: grid;
  grid-template-columns: repeat(auto-fit, minmax(300px, 350px));
  gap: 20px;
  padding: 20px;
}

/* Style for each memory */
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
  background-color: white; /* Light beige background */
  color: #101d24; /* Dark gray/blue text color */
  border: none;
  cursor: pointer;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  margin-left: 20px;
  padding: 10px 20px;
}

#search-button {
  background-color: #101d24; /* Light beige background */
  color: #ebe7d9; /* Dark gray/blue text color */
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
#update {
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
</style>
