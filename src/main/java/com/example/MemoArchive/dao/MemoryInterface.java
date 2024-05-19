package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Memory;

import java.security.Principal;
import java.util.List;

public interface MemoryInterface {
    /**
     * Finds a Memory by its primary key ID.
     *
     * @param memoryId the primary key of the Memory to find
     * @return the found Memory, or null if not found
     */
    Memory getMemoryByMemoryId(int memoryId, Principal principal);

    /**
     * Finds a Memories by its primary key ID.
     *
     * @param username the primary key of the Memory to find
     * @return the found Memories, or null if not found
     */
    List<Memory> getMemoriesByUsername(String username, Principal principal);


    /**
     * Saves a new Memory to the database.
     *
     * @param memory the Memory to save
     * @return the saved Memory, potentially updated with new data (e.g., auto-generated ID)
     */

    Memory addMemory(Memory memory, Principal principal);

    /**
     * Updates an existing Memory in the database.
     *
     * @param memory the Memory to update
     */

    boolean updateMemory(Memory memory, Principal principal);

    /**
     * Deletes a Memory from the database by ID.
     *
     * @param memoryId the ID of the Memory to delete
     */
    boolean deleteMemory(int memoryId, Principal principal); //TODO: figure out whether to keep this method with a void or boolean return.

    /**
     * Retrieves all Memories from the database.
     *
     * @return a list of all Memories
     */
    List<Memory> getAllMemories();
}
