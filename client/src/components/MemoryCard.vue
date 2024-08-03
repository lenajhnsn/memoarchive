<template>
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
      <button v-on:click="updateMemory(memory.id, memory)">Update</button>
      <!-- Button to delete the memory. Calls the deleteMemory method -->
      <button v-on:click="deleteMemory(memory.id)">Delete</button>
    </div>
  </template>
  
  <script>
  import MemoryService from '../services/MemoryService';

  export default {
    // Accept a prop named 'memory' that contains details about the memory
    props: {
      memory: {
        type: Object,
        required: true,
      },
    },
    methods: {
      // Method to update an existing memory
    updateMemory(memory) {
        MemoryService.update(memory.id, memory)
        .then(response => {
          this.getMemories = response.data; // Refresh memory list after update is made
        })
        .catch(error => {
          console.error('Error updating memory:', error); // Log error to the console
        });
    },
  },
      // Method to delete an existing memory
      deleteMemory(id) {
        MemoryService.delete(id)
        .then(response => {
          this.getMemories = response.data; // Refresh memory list after memory is deleted
        })
        .catch(error => {
          console.error('Error deleting memory:', error); // Log error to the console
        });
    },
  };
  </script>
  
  <style scoped>
  /* Scoped styles for the memory component */

  .edit-memory-btn {
    background-color: #101D24; /* Light beige background */
    color: #EBE7D9; /* Dark gray/blue text color */
    border: none;
    cursor: pointer;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    display: block;
    margin:0 auto;
    padding: 15px 30px;
}

.edit-memory-btn:hover {
    background-color: #101D24;
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
    font-family: 'Poppins', sans-serif;
    color: #101D24;
    font-weight: bold;
    margin: 5px 0;
}

.memory-description {
    font-family: 'Poppins', sans-serif;
    font-size: 1em;
    color: #101D24;
    margin: 10px 0;
}

#search-input {
    background-color: white; /* Light beige background */
    color: #101D24; /* Dark gray/blue text color */
    border: none;
    cursor: pointer;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    margin-left: 20px;
    padding: 10px 20px;
}

#search-button {
    background-color: #101D24; /* Light beige background */
    color: #EBE7D9; /* Dark gray/blue text color */
    border: none;
    cursor: pointer;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    margin:0 auto;
    padding: 10px 20px;
}

#search-button:hover {
    background-color: #234150; 
}
#update {
    background-color: #101D24; 
    color: #EBE7D9; 
    border: none;
    cursor: pointer;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    margin:0 auto;
    padding: 10px 20px;
}

.delete-button {
    background-color: #101D24; 
    color: #EBE7D9; 
    border: none;
    cursor: pointer;
    border-radius: 8px;
    font-size: 16px;
    font-weight: bold;
    text-transform: uppercase;
    margin:5px;
    padding: 10px 20px;
}
  </style>
  