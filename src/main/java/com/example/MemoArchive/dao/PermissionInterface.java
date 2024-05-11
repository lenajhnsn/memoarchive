package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.model.Permission;

import java.util.List;

public interface PermissionInterface {
    /**
     * Finds a Permission level by its primary key ID.
     *
     * @param permissionId the primary key of the Memory to find
     * @return the found Memory, or null if not found
     */
    Permission getPermissionByPermissionId(int permissionId);

    /**
     * Finds a Memory by its primary key ID.
     *
     * @param userId the primary key of the Memory to find
     * @return the found Memory, or null if not found
     */
    Memory getMemoryByUserId(int userId);


    /**
     * Saves a new Memory to the database.
     *
     * @param memory the Memory to save
     * @return the saved Memory, potentially updated with new data (e.g., auto-generated ID)
     */

    boolean addMemory(Memory memory);

    /**
     * Updates an existing Memory in the database.
     *
     * @param memory the Memory to update
     */

    boolean updateMemory(Memory memory);

    /**
     * Deletes a Memory from the database by ID.
     *
     * @param memoryId the ID of the Memory to delete
     */
    boolean deleteMemory(int memoryId); //TODO: figure out whether to keep this method with a void or boolean return.

    /**
     * Retrieves all Memories from the database.
     *
     * @return a list of all Memories
     */
    List<Memory> getAllMemories();
}
