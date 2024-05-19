package com.example.MemoArchive.service;

import com.example.MemoArchive.model.Memory;
import org.springframework.http.ResponseEntity;

import java.security.Principal;
import java.util.List;

/**
 * MemoryServiceInterface is an interface that defines the methods for managing memories.
 * It provides methods for creating, updating, retrieving, and deleting memories.
 */

public interface MemoryServiceInterface {

    // CREATE
    /**
     * Creates a new memory.
     *
     * @param memory the memory data to create
     * @return the created memory
     */
    Memory createMemory(Memory memory, Principal principal);

    // READ (get)
    /**
     * Retrieves a memory by its memory ID.
     *
     * @param memoryId the ID of the memory to retrieve
     * @return the retrieved memory
     */
    Memory getMemoryByMemoryId(int memoryId, Principal principal);

    /**
     * Retrieves all memories for a specific user.
     *
     * @param userId the ID of the user who is retrieving memories
     * @return a list of memories for the specified user
     */
    List<Memory> getMemoriesByUsername(String userId, Principal principal);

    /**
     * Retrieves all memories.
     *
     * @return a list of all memories
     */
    List<Memory> getAllMemories();

    // UPDATE
    /**
     * Updates a memory.
     *
     * @param memoryId the ID of the memory to update
     * @param memory   the updated memory data
     * @return a ResponseEntity indicating the result of the update operation
     */
    ResponseEntity<?> updateMemory(int memoryId, Memory memory, Principal principal);

    // DELETE
    /**
     * Deletes a memory by its ID.
     *
     * @param memoryId the ID of the memory to delete
     * @return a ResponseEntity indicating the result of the delete operation
     */
    ResponseEntity<?> deleteMemory(int memoryId, Principal principal);

}

