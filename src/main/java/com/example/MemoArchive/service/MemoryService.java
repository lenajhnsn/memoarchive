package com.example.MemoArchive.service;


import com.example.MemoArchive.dao.MemoryDao;
import com.example.MemoArchive.dao.UsersDao;
import com.example.MemoArchive.model.Authority;
import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.model.Users;
import com.example.MemoArchive.security.SecurityUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.swing.text.html.Option;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

/**
 * MemoryService is an implementation of the MemoryService interface.
 * It provides business logic for managing memories.
 */

public class MemoryService implements MemoryServiceInterface {

    // INSTANCE VARIABLES
    private final MemoryDao memoryDao;
    private final UsersDao usersDao;

    // CONSTRUCTOR (passes MemoryDao and UsersDao as parameters)

    public MemoryService(MemoryDao memoryDao, UsersDao usersDao) {
        this.memoryDao = memoryDao;
        this.usersDao = usersDao;
    }

    // METHODS

    // CREATE
    @Override
    public Memory createMemory(Memory memory, Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated user
        Users user = usersDao.getUserByUsername(username); // Retrieve the user

        // Assuming you want to link the new memory to the current user
        if (user != null) {
            memory.setUserId(user.getUserId()); // Set the user ID of the memory to the current user's ID
        }

        return memoryDao.addMemory(memory, principal); // Create and return the new memory
    }
    // READ / GET

    @Override
    public Memory getMemoryByMemoryId(int memoryId, Principal principal) {
        String username = principal.getName();

        Memory memory = memoryDao.getMemoryByMemoryId(memoryId, principal);
        Users user = usersDao.getUserByUsername(username);

        if (memory == null || user == null || memory.getUserId() != user.getUserId()) {
            return null;
        }

        return memory;
    }

    @Override
    public List<Memory> getMemoriesByUsername(String username, Principal principal) {
        // Get the username of the currently authenticated user
        String currentUsername = principal.getName();

        // Get the user based on the provided username
        Users user = usersDao.getUserByUsername(username);

        // Check if the user exists and if the current user is authorized to access the memories
        if (user == null || !currentUsername.equals(username)) {
            return null; // If not, return null
        }

        // Retrieve and return the list of memories for the provided username
        return memoryDao.getMemoriesByUsername(username, principal);
    }

    @Override
    public List<Memory> getAllMemories() {
        return memoryDao.getAllMemories();
    }

    // UPDATE
    @Override
    public ResponseEntity<?> updateMemory(int memoryId, Memory memory, Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated user

        Memory existingMemory = memoryDao.getMemoryByMemoryId(memoryId, principal); // Retrieve the memory
        Users user = usersDao.getUserByUsername(username); // Retrieve the user

        // Check if the memory exists, the user exists, and the user is the owner of the memory
        if (existingMemory == null || user == null || existingMemory.getUserId() != user.getUserId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to update this memory."); // Return 403 Forbidden if unauthorized
        }

        memory.setMemoryId(memoryId); // Set the memory ID
        memoryDao.updateMemory(memory, principal); // Update the memory
        return ResponseEntity.ok("Memory updated successfully."); // Return 200 OK with success message
    }

    // DELETE
    @Override
    public ResponseEntity<?> deleteMemory(int memoryId, Principal principal) {
        String username = principal.getName(); // Get the username of the authenticated user

        Memory existingMemory = memoryDao.getMemoryByMemoryId(memoryId, principal); // Retrieve the memory
        Users user = usersDao.getUserByUsername(username); // Retrieve the user

        // Check if the memory exists, the user exists, and the user is the owner of the memory
        if (existingMemory == null || user == null || existingMemory.getUserId() != user.getUserId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("You are not authorized to delete this memory."); // Return 403 Forbidden if unauthorized
        }

        memoryDao.deleteMemory(memoryId, principal); // Delete the memory
        return ResponseEntity.ok("Memory deleted successfully."); // Return 200 OK with success message
    }
}
