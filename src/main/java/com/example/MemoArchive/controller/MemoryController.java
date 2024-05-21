package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.MemoryDao;
import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.service.MemoryService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;
/**
 * MemoryController handles HTTP requests related to memory operations.
 */

@Component
@RestController // Includes @Controller functions
@RequestMapping("/memory") // Set base path for controller
@PreAuthorize("isAuthenticated()")
public class MemoryController {

    // Dependency injection for MemoryService
    private final MemoryService memoryService;

    public MemoryController(MemoryService memoryService) { //Constructor
        this.memoryService = memoryService;
    }

    // GET BY MEMORY ID (primary key)
    @GetMapping("/{memoryId}")
    public Memory getMemoryByMemoryId(@PathVariable("memoryId") int memoryId, Principal principal) {
        try {
            return memoryService.getMemoryByMemoryId(memoryId, principal);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory not found.");
        }
    }

    // GET BY USER ID (foreign key)
    @GetMapping("/by-user/{userId}")
    public List<Memory> getMemoriesByUsername(@PathVariable("username") String username, Principal principal) {
        try {
            return memoryService.getMemoriesByUsername(username, principal); //
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory not found.");
        }
    }

    // GET ALL MEMORIES
    @GetMapping("/")
    @PreAuthorize("hasRole('ADMIN')") // Only administrators can access all memories for all users
    public List<Memory> getAllMemories() {
        return memoryService.getAllMemories();
    }

    // POST - Create a new memory
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public Memory createMemory(@Valid @RequestBody Memory memory, Principal principal) {
        return memoryService.createMemory(memory, principal);
    }

    // PUT - Update an existing memory
    @PutMapping("/{memoryId}")
    public ResponseEntity<?> updateMemory(@Valid @PathVariable("memoryId") int memoryId, @RequestBody Memory memory, Principal principal) {
        memory.setMemoryId(memoryId); // Set memory ID just in case it's not set in the request body
        return memoryService.updateMemory(memoryId, memory, principal);
    }

    // DELETE - Remove a memory by ID
    @DeleteMapping("/{memoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> deleteMemory(@PathVariable("memoryId") int memoryId, Principal principal) {
        return memoryService.deleteMemory(memoryId, principal );
    }
}



