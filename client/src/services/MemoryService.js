import axios from "axios";

export default {
  // Get all memories for authenticated user
  list() {
    return axios.get("/memory");
  },

  // Get memory by specific memory ID
  get(id) {
    return axios.get(`/memory/${id}`);
  },

  // Create a new memory
  create(memory) {
    return axios.post("/memory", memory);
  },

  // Update an existing memory
  update(id, memoryUpdateDto) {
    return axios.put(`/memory/${id}`, memoryUpdateDto);
  },

  // Delete a memory by ID
  delete(id) {
    return axios.delete(`/memory/${id}`);
  },

  // Get all memories (admin only)
  listAll() {
    return axios.get("/memory/");
  },
};
