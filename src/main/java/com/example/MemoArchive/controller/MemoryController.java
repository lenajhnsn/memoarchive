package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.MemoryDao;
import com.example.MemoArchive.model.Memory;
import jakarta.validation.Valid;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
//TODO: Add Principal principal functionality and logging

@RestController // Includes @Controller functions
@RequestMapping("/memory") // Set base path for controller
@PreAuthorize("isAuthenticated()")
public class MemoryController {

    // Dependency injection for DAO
    private final MemoryDao memoryDao;

    public MemoryController(MemoryDao memoryDao) { //Constructor
        this.memoryDao = memoryDao;
    }

    // GET BY MEMORY ID (primary key)
    @GetMapping("/{memoryId}")
    public Memory getMemoryByMemoryId(@PathVariable("memoryId") int memoryId, Principal principal) {
        try {
            return memoryDao.getMemoryByMemoryId(memoryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory not found.");
        }
    }

    // GET BY USER ID (foreign key)
    @GetMapping("/by-user/{userId}")
    public List<Memory> getMemoriesByUserId(@PathVariable("userId") int userId, Principal principal) {
        try {
            return memoryDao.getMemoriesByUsername(userId); //
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory not found.");
        }
    }

    // GET ALL MEMORIES
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')") // Only administrators can access all memories for all users
    public List<Memory> getAllMemories() {
        return memoryDao.getAllMemories();
    }

    // POST - Create a new memory
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Memory createMemory(@Valid @RequestBody Memory memory) {
        return memoryDao.addMemory(memory);
    }

    // PUT - Update an existing memory
    @PutMapping("/{memoryId}")
    public boolean updateMemory(@Valid @PathVariable("memoryId") int memoryId, @RequestBody Memory memory, Principal principal) {
        memory.setMemoryId(memoryId); // Set memory ID just in case it's not set in the request body
        return memoryDao.updateMemory(memory);
    }

    // DELETE - Remove a memory by ID
    @DeleteMapping("/{memoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMemory(@PathVariable("memoryId") int memoryId, Principal principal) {
        return memoryDao.deleteMemory(memoryId);
    }
}



