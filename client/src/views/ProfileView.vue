<template>
  <div id="profile-view">
    <h1>Your Memories</h1>
    <div class="toolbar">
      <!-- SearchBar component -->
      <SearchBar
        v-bind:memories="memories"
        v-bind:onSearch="handleSearchResults"
      />
      <!-- CreateMemory component -->
      <CreateMemory v-bind:onCreate="handleCreateMemory" />
    </div>
    <div class="image-grid">
      <!-- Loop through the memories array and create a MemoryCard for each memory -->
      <MemoryCard
        v-for="memory in filteredMemories"
        v-bind:key="memory.id"
        v-bind:memory="memory"
        v-bind:onUpdate="openEditModal"
        v-bind:onDelete="handleDeleteMemory"
      />
    </div>
    <dialog ref="editDialog" v-if="editableMemory" class="edit-modal">
      <!-- Form fields for editing memoryDate, description, and image -->
      <input v-model="editableMemory.memoryDate" placeholder="Memory Date" />
      <textarea
        v-model="editableMemory.description"
        placeholder="Description"
      ></textarea>
      <input v-model="editableMemory.content" placeholder="Image URL" />

      <!-- Button to save the changes -->
      <button v-on:click="handleUpdateMemory()">Save</button>
      <!-- Button to cancel the editing -->
      <button v-on:click="closeEditModal()">Cancel</button>
    </dialog>
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
      editableMemory: null, // Holds memory that's being updated
      showEditModal: false, // Controls visibility of the edit modal
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
          // Set the retrieved memories to the component's data
          this.memories = response.data.sort(
            (a, b) => new Date(a.memoryDate) - new Date(b.memoryDate)
          );
          this.filteredMemories = this.memories; // Initialize filtered memories
        })
        .catch((error) => {
          console.error("Error fetching memories.", error); // Log errors to the console
        });
    },

    // CREATE: Function to handle new memory creation from CreateMemory
    handleCreateMemory(newMemory) {
      this.memories.push(newMemory);
      // this.filteredMemories.push(newMemory);
    },

    // UPDATE: Function to handle updated memory from child (MemoryCard) component
    handleUpdateMemory() {
      const updatedMemory = this.editableMemory;
      // Call the update method from MemoryService, passing the updated memory details.
      MemoryService.update(updatedMemory.memoryId, updatedMemory)
        .then((response) => {
          // Extract the updated memory data from the response.
          const updatedData = response.data;
          // Update the memory in the main list (this.memories).
          this.memories = this.memories.map((memory) =>
            memory.memoryId === updatedData.memoryId ? updatedData : memory
          );
          // Re-sort memories after updating one (Array.prototype.sort method)
          this.memories.sort(
            (a, b) => new Date(a.memoryDate) - new Date(b.memoryDate)
          );
          this.filteredMemories = this.memories; // Update filtered memories

          // Close the edit modal or interface, when appropriate
          this.closeEditModal();
        })
        .catch((error) => {
          // Log any errors that occur during the update process.
          console.error("Error updating memory:", error);
        });
    },

    // DELETE: Function to handle deleted memory from child component
    handleDeleteMemory(memoryId) {
      // Make sure memoryId is defined before proceeding
      if (!memoryId) {
        console.error("No memory ID provided for deletion.");
        return;
      }
      MemoryService.delete(memoryId)
        .then(() => {
          this.getMemories(); // Refresh the list after deletion
        })
        .catch((error) => {
          console.error("Error deleting memory.", error);
        });
    },

    // SEARCH: Update the filtered memories based on the search results
    handleSearchResults(filtered) {
      this.filteredMemories = filtered;
    },

    // Open the model to edit a memory after selecting the update button
    openEditModal(memory) {
      // Initialize memory object with current details that can be edited
      this.editableMemory = { ...memory };
      // Open the dialog element
      this.$refs.editDialog.showModal();
    },

    // Close the modal
    closeEditModal() {
      // Close the dialog element
      this.$refs.editDialog.close();
    },
  },
};
</script>

<style scoped>
/* Scoped styles for the ProfileView component */

#profile-view {
  margin: 10px;
}
.toolbar {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 20px;
}
.image-grid {
  display: flex;
  flex-wrap: wrap;
  gap: 10px;
}

h1 {
  margin-top: 100px;
}
</style>
