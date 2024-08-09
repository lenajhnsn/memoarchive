<template>
  <div id="app">
    <main class="main-content">
      <div class="create-memory">
        <button v-on:click="openCreateForm" class="create-button">
          Add Memory
        </button>
        <dialog ref="createDialog" class="create-dialog">
          <form method="dialog" class="create-form">
            <!-- <div v-if="showCreateForm" class="create-form"> -->
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
          </form>
        </dialog>
      </div>
    </main>
  </div>
</template>

<script>
import MemoryService from "../services/MemoryService";

export default {
  data() {
    return {
      // showCreateForm: false, // Create memory form is not visible by default
      newMemory: { content: "", memoryDate: "", description: "" }, // New memory data
    };
  },
  props: {
    onCreate: Function, // Callback function to update memory list in parent (ProfileView) component
  },
  methods: {
    // // Toggle the visibility of the create form
    // toggleCreateForm() {
    //   this.showCreateForm = !this.showCreateForm;
    // },
    openCreateForm() {
      this.$refs.createDialog.showModal(); // Open the create form modal
    },

    closeCreateForm() {
      this.$refs.createDialog.close(); // Close the create form modal
      this.resetForm(); // Reset form fields
    },

    // Function to save new memory using the back end
    createMemory() {
      const memoryDate = new Date(this.newMemory.memoryDate);
      const memoryData = {
        // spread properties of newMemory object into memoryData object
        ...this.newMemory,
        memoryDate: `${(memoryDate.getUTCMonth() + 1)
          .toString()
          .padStart(2, "0")}/${memoryDate
          .getUTCDate()
          .toString()
          .padStart(2, "0")}/${memoryDate.getUTCFullYear()}`,

      };
      MemoryService.create(memoryData)
        .then((response) => {
          this.onCreate(response.data); // Call parent component's create handler with new memory
          this.resetForm(); // Reset form fields
          this.closeCreateForm = false; // Hide the form
        })
        .catch((error) => {
          console.error("Error creating memory.", error);
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
/* Styling for the main container of the create memory form */
.create-memory {
  margin-top: 20px;
}

/* Styling for the button to open the modal */
.create-button {
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

/* Styling for the dialog modal itself */
.create-dialog {
  background-color: white; /* Set background to white for consistency */
  border: 2px solid #888; /* Add border to match the login container */
  padding: 20px;
  width: 80%;
  max-width: 500px;
  border-radius: 8px; /* Ensure corners are rounded */
  margin: auto; /* Center the modal on the screen */
  font-family: "Poppins", sans-serif; /* Use the same font as the login form */
}

/* Styling for the form inside the modal */
.create-form {
  display: flex;
  flex-direction: column;
  margin-top: 20px;
}

/* Styling for the input fields */
.create-input,
.create-description {
  display: block;
  padding: 10px; 
  margin-bottom: 10px; 
  border: 1px solid #ccc;
  border-radius: 4px; 
  font-size: 16px; 
}

/* Styling for the description textarea */
.create-description {
  resize: vertical;
  font-family: "Poppins", sans-serif; 
}

/* Styling for the save and cancel buttons */
.save-button,
.cancel-button {
  background-color: #101d24; /* Base button color */
  color: white; /* Text color */
  border: none;
  padding: 10px; /* Match the padding of the login buttons */
  font-size: 16px;
  border-radius: 4px; /* Consistent rounded corners */
  cursor: pointer;
  margin-bottom: 10px; /* Consistent margin at the bottom */
}

/* Hover effects for buttons */
.save-button:hover,
.cancel-button:hover {
  background-color: #234150; /* Darken on hover for both buttons */
}

/* Distinct background colors for save and cancel buttons */
.save-button {
  background-color: #101d24; 
}

.cancel-button {
  background-color: #86847b; /* Distinctive color for cancel button */
}
</style>
