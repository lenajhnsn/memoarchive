<template>
  <div id="app">
    <main class="main-content">
      <div class="search-bar">
        <input
          id="search-input"
          v-model="searchTerm"
          placeholder="Search Memories"
          :style="inputStyle"
        />
        <button
          id="search-button"
          v-on:click="searchMemories"
          class="search-button"
        >
          Search
        </button>
      </div>
    </main>
  </div>
</template>

<script>
export default {
  data() {
    return {
      searchTerm: "", // Holds the search input
    };
  },
  props: {
    memories: Array, // Array of memories passed from parent (ProfileView) component
    onSearch: Function, // Callback function to update filtered memories in parent component
  },
  methods: {
    // Function to filter memories based on the search term
    searchMemories() {
      const term = this.searchTerm.toLowerCase();
      // Filter memories to include only those matching the search term in the description
      const filtered = this.memories.filter((memory) =>
        memory.description.toLowerCase().includes(term)
      );
      this.onSearch(filtered); // Call the parent (ProfileView) component's search handler with filtered results
    },
  },
};
</script>

<style scoped>
.search-bar {
  display: flex;
  align-items: center;
}

.search-input {
  background-color: white;
  color: #101d24;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  margin-left: 20px;
  padding: 10px 20px;
  cursor: pointer;
}

.search-button {
  background-color: #101d24;
  color: #ebe7d9;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  padding: 10px 20px;
  cursor: pointer;
  margin: 0 auto;
}
</style>
