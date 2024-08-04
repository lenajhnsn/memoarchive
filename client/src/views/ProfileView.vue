<template>
  <div>
    <h1>Your Memories</h1>
    <SearchBar
        v-bind:memories="memories"
        v-bind:onSearch="handleSearchResults"
    />
    <div class="image-grid">
      <!-- Loop through the memories array and create a MemoryCard for each memory -->
      <!-- Use getter from Vuex to get memories -->
      <MemoryCard
        v-for="memory in memories"
        v-bind:key="memory.id"
        v-bind:memory="memory"
        v-on:update-memory="updateMemory(memory.id, memory)"
        v-on:delete-memory="deleteMemory(memory.id)"
      />
    </div>
  </div>
</template>

<script>
import MemoryCard from "../components/MemoryCard.vue"; // Import MemoryCard component
import SearchBar from "../components/SearchBar.vue";
import MemoryService from "../services/MemoryService";

export default {
  components: {
    MemoryCard,
    SearchBar
},
  data() {
    return {
      memories: [], // Initialize an empty array to store the memories
      searchTerm: '',
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
    getMemories() {
      MemoryService.list()
        .then((response) => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch((error) => {
          console.error("Error fetching memories:", error); // Log errors to the console
        });
    },
    // Method to get memory by its ID.
    getMemory() {
      MemoryService.get()
        .then((response) => {
          this.memories = response.data; // Set the retrieved memories to the component's data
        })
        .catch((error) => {
          console.error("Error fetching memory:", error); // Log error to the console
        });
    },
    // Method to create a new memory.
    createMemory(memory) {
      MemoryService.create(memory)
        .then((response) => {
          this.getMemories = response.data; // Refresh memory list after new memory is created
        })
        .catch((error) => {
          console.error("Error creating memory:", error); // Log error to the console
        });
    },
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
/* Scoped styles for the ProfileView component */
.image-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

h1 {
  margin-top: 100px;
}
</style>
