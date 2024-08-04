<template>
  <div>
    <h1>Your Memories</h1>
    <!-- SearchBar component -->
    <SearchBar
      v-bind:memories="memories"
      v-bind:onSearch="handleSearchResults"
    />
    <!-- CreateMemory component -->
    <CreateMemory v-bind:onCreate="handleCreateMemory" />

    <div class="image-grid">
      <!-- Loop through the memories array and create a MemoryCard for each memory -->
      <MemoryCard
        v-for="memory in filteredMemories"
        v-bind:key="memory.id"
        v-bind:memory="memory"
        v-bind:onUpdate="handleUpdateMemory"
        v-bind:onDelete="handleDeleteMemory"
      />
    </div>
  </div>
</template>

<script>
import MemoryCard from "../components/MemoryCard.vue";
import SearchBar from "../components/SearchBar.vue";
import MemoryService from "../services/MemoryService";
import CreateMemory from "../components/CreateMemory.vue";

export default {
  components: {
    MemoryCard,
    SearchBar,
    CreateMemory,
  },
  data() {
    return {
      memories: [], // Initialize an empty array to store the memories
      filteredMemories: [], // Array to  store filtered memories based on search
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
          this.filteredMemories = response.data; // Initialize filtered memories
        })
        .catch((error) => {
          console.error("Error fetching memories.", error); // Log errors to the console
        });
    },

    // CREATE: Function to handle new memory creation from CreateMemory
    handleCreateMemory(newMemory) {
      this.memories.push(newMemory);
      this.filteredMemories.push(newMemory);
    },

    // UPDATE: Function to handle updated memory from child (MemoryCard) component
    handleUpdateMemory(updatedMemory) {
      // Update memory in the main list
      this.memories = this.memories.map((memory) =>
        memory.id === updatedMemory.id ? updatedMemory : memory
      );
      // Update memory in the filtered list
      this.filteredMemories = this.filteredMemories.map((memory) =>
        memory.id === updatedMemory.id ? updatedMemory : memory
      );
    },

    // DELETE: Function to handle deleted memory from child component
    handleDeleteMemory(memoryId) {
      // Make sure memoryId is defined before proceeding
      if (!memoryId) {
        console.error("No memory ID provided for deletion.");
        return;
      }
      // Remove the memory from the main list
      this.memories = this.memories.filter((memory) => memory.id !== memoryId);
      // Remove the memory from the filtered list
      this.filteredMemories = this.filteredMemories.filter(
        (memory) => memory.id !== memoryId
      );
    },

    // SEARCH: Update the filtered memories based on the search results
    handleSearchResults(filtered) {
      this.filteredMemories = filtered;
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

h1 {
  margin-top: 100px;
}
</style>
