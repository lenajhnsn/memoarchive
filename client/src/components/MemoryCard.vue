<template>
    <div class="memory">
      <!-- Display the title of the memory -->
      <h3>{{ memory.title }}</h3>
      <!-- Display the image associated with the memory -->
      <img :src="memory.imageUrl" :alt="memory.title" />
      <!-- Display the date of the memory -->
      <p class="memory-date">{{ memory.date }}</p>
      <!-- Display the description of the memory -->
      <p class="memory-description">{{ memory.description }}</p>
      <!-- Button to update the memory. Pressing button calls the updateMemory method -->
      <button v-on:click="updateMemory(memory.id)">Update</button>
      <!-- Button to delete the memory. Calls the deleteMemory method -->
      <button v-on:click="deleteMemory(memory.id)">Delete</button>
    </div>
  </template>
  
  <script>
  export default {
    // Accept a prop named 'memory' that contains details about the memory
    props: {
      memory: {
        type: Object,
        required: true,
      },
    },
    methods: {
      /**
       * Method to handle updating the memory. Using a prop method.
       */
      updateMemory(id) {
        // Access the updateMemory function passed as a prop and call it with the memory ID
        this.$parent.handleUpdateMemory(id);
      },
      /**
       * Method to handle deleting the memory.
       */
      deleteMemory(id) {
        this.$parent.handleDeleteMemory(id);
      },
    },
  };
  </script>
  
  <style scoped>
  /* Scoped styles for the memory component */
  .memory {
    border: 1px solid #ccc;
    padding: 10px;
    margin-bottom: 10px;
  }
  .memory img {
    max-width: 100%;
  }
  .memory-description {
    margin: 10px 0;
  }
  </style>
  