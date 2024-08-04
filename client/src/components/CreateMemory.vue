<template>
  <div id="app">
    <main class="main-content">
      <div class="create-memory">
        <button v-on:click="toggleCreateForm" class="create-button">
          Add Memory
        </button>

        <div v-if="showCreateForm" class="create-form">
          <input
            v-model="newMemory.content"
            placeholder="Image URL"
            class="create-input"
          />
          <input
            v-model="newMemory.memoryDate"
            type="date"
            class="create-input"
          />

          <!-- text area allows for multi-line text -->
          <textarea
            v-model="newMemory.description"
            placeholder="Description"
            class="create-description"
          ></textarea>

          <button v-on:click="createMemory" class="save-button">Save</button>
          <button v-on:click="cancelCreate" class="cancel-button">
            Cancel
          </button>
        </div>
      </div>
    </main>
  </div>
</template>

<script>
import MemoryService from "../services/MemoryService";

export default {
  data() {
    return {
      showCreateForm: false, // Create memory form is not visible by default
      newMemory: { content: "", memoryDate: "", description: "" }, // New memory data
    };
  },
  props: {
    onCreate: Function, // Callback function to update memory list in parent (ProfileView) component
  },
  methods: {
    // Toggle the visibility of the create form
    toggleCreateForm() {
      this.showCreateForm = !this.showCreateForm;
    },
    // Function to save new memory using the back end
    createMemory() {
      const memoryData = {
        // spread properties of newMemory object into memoryData object
        ...this.newMemory,
        // TODO: Automatically set the creation date
      };
      MemoryService.create(memoryData)
        .then((response) => {
          this.onCreate(response.data); // Call parent component's create handler with new memory
          this.resetForm(); // Reset form fields
          this.showCreateForm = false; // Hide the form
        })
        .catch((error) => {
          console.error("Error creating memory:", error);
        });
    },
    // Function to reset form fields to default values
    resetForm() {
      this.newMemory = {
        content: "",
        memoryDate: "",
        description: "",
      };
    },
    // Function to cancel the memory creation process
    cancelCreate() {
      this.resetForm();
      this.showCreateForm = false;
    },
  },
};
</script>

<style scoped>
.create-memory {
  margin-top: 20px;
}

.create-button,
.save-button,
.cancel-button {
  background-color: #101d24;
  color: #ebe7d9;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: bold;
  text-transform: uppercase;
  cursor: pointer;
  padding: 10px 20px;
  margin-top: 10px;
}

.create-button {
  margin-right: 10px;
}

.save-button {
  background-color: #284657; /* Green for save */
}

.cancel-button {
  background-color: #86847b; /* Red for cancel */
}

.create-form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

.create-input,
.create-description {
  display: block;
  margin: 10px 0;
  width: 100%;
  padding: 8px;
  border-radius: 4px;
  border: 1px solid #ccc;
}

.create-description {
  resize: vertical;
}
</style>
