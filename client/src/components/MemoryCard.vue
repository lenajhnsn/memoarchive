<template>
  <div id="app">
    <main class="main-content">
      <div class="memory">
        <!-- Display the image associated with the memory -->
        <img v-bind:src="memory.content" />

        <!-- Display the date of the memory -->
        <p class="memory-date">{{ memory.memoryDate }}</p>

        <!-- Display the description of the memory -->
        <p class="memory-description">{{ memory.description }}</p>
        <div id="icons">
          <!-- Icon to update the memory. Pressing button calls the updateMemory method -->
          <font-awesome-icon
            icon="pen-to-square"
            v-on:click="triggerUpdate()"
            id="update-icon"
          ></font-awesome-icon>

          <!-- Icon to delete the memory. Calls the deleteMemory method -->
          <font-awesome-icon
            icon="trash-can"
            v-on:click="triggerDelete()"
            id="delete-icon"
          ></font-awesome-icon>
        </div>
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
  // created() {
  //   // What data is being passed in
  //   console.log(this.memory);
  // },
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
      if (this.memory.memoryId) {
        this.onDelete(this.memory.memoryId);
      } else {
        console.error("Memory ID is undefined");
      }
    },
  },
};
</script>

<style scoped>
/* Scoped styles for the memory component */


/* Styling for the update and delete icons */
#update-icon,
#delete-icon {
  font-size: 24px; /* Set the size of the icon */
  color: #101d24; /* Set the color of the icon */
  cursor: pointer; /* Make the icon clickable */
  margin: 10px; /* Provide some space around the icon */
  display: block;
  text-align: center;
}

#update-icon:hover,
#delete-icon:hover {
  color: #234150;
}

/* Styles for icons container */
#icons {
  display: flex;
  justify-content: space-between;
  align-items: center; /* Align icons vertically in the center */
  width: 100%; /* Container takes the full width */
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
  height: 400px;
  display: flex; /* Flexbox for vertical alignment */
  flex-direction: column; /* Align items vertically */
  justify-content: space-between; /* Even space between elements */
  box-sizing: border-box; /* Includes padding in width and height */
}

.memory h3 {
  font-size: 1.5em;
  margin: 10px 0;
}

.memory img {
  max-width: 100%; /* Make sure the image does not exceed the card width */
  max-height: 200px; /* Limit the max height of the image */
  object-fit: cover; /* Crop the image to fit within the tile */
  border-radius: 8px;
  margin-bottom: 10px;
  flex-shrink: 0; /* Prevent image from shrinking */
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
