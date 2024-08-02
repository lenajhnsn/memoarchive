<template>
  <div>
    <h1>Your Memories</h1>
    <div class="image-grid">
      <!-- Loop through the memories array and create a MemoryCard for each memory -->
      <!-- Use getter from Vuex to get memories -->
      <MemoryCard
        v-for="memory in memories"
        :key="memory.id"
        :memory="memory"
      />
    </div>
  </div>
</template>

<script>
import MemoryCard from "../components/MemoryCard.vue"; // Import MemoryCard component
import MemoryService from "../services/MemoryService";

export default {
  components: {
    MemoryCard,
  },
  data() {
    return {
      memories: [], // Initialize an empty array to store the memories
    };
  },
  created() {
    // Get memories from the back end when the component is created
    this.getMemories();
  },
  methods: {
    /**
     * Method to get memories from the back end.
     * This will call the back end endpoint to get memories for the authenticated user.
     */
    getMemoriesByUserId() {
      MemoryService.list
        .then((response) => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch((error) => {
          console.error("Error fetching memories:", error); // Log errors to the console
        });
    },
    // Method to get memory by its ID.
    getMemoryByMemoryID() {
      MemoryService.get
        .then((response) => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch((error) => {
          console.error("Error fetching memory:", error); // Log error to the console
        });
    },
    // Method to create a new memory.
    createMemory() {
      MemoryService.create
        .then((response) => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch((error) => {
          console.error("Error creating memory:", error); // Log error to the console
        });
    },
    // Method to update an existing memory
    updateMemory() {
        MemoryService.update
        .then(response => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch(error => {
          console.error('Error updating memory:', error); // Log error to the console
        });
    },
  },
};
</script>

<style scoped>
/* Scoped styles for the ProfileView component */
.image-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}
</style>
