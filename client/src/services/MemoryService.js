import axios from "axios";

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
});

export default {
  // Get all memories for authenticated user
  list() {
    return http.get("/memory");
  },

  // Get memory by specific memory ID
  get(id) {
    return http.get(`/memory/${id}`);
  },

  // Create a new memory
  create(memory) {
    return http.post("/memory", memory);
  },

  // Update an existing memory
  update(id, memoryUpdateDto) {
    return http.put(`/memory/${id}`, memoryUpdateDto);
  },

  // Delete a memory by ID
  delete(id) {
    return http.delete(`/memory/${id}`);
  },

  // Get all memories (admin only)
  listAll() {
    return http.get("/memory/");
  },
};
