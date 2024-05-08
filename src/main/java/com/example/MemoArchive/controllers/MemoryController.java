package com.example.MemoArchive.controllers;

import com.example.MemoArchive.daos.MemoryDao;
import com.example.MemoArchive.models.Memory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memory/") // Set base path for controller
public class MemoryController {

    // Dependency injection for DAO
    private final MemoryDao memoryDao;

    public MemoryController(MemoryDao memoryDao){ //Constructor
        this.memoryDao = memoryDao;
    }

    // GET BY MEMORY ID (primary key)
    @GetMapping("/{memoryId}")
    public Memory getMemoryByMemoryId(@PathVariable("memoryId") int memoryId) {
        return memoryDao.getMemoryByMemoryId(memoryId);
    }

    // GET BY USER ID (foreign key)
    @GetMapping("/by-user/{userId}")
    public List<Memory> getMemoriesByUserId(@PathVariable("userId") int userId) {
        return memoryDao.getAllMemories(); //
    }

    // GET ALL MEMORIES
    @GetMapping("/")
    public List<Memory> getAllMemories() {
        return memoryDao.getAllMemories();
    }

    // POST - Create a new memory
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public boolean createMemory(@RequestBody Memory memory) {
        return memoryDao.addMemory(memory);
    }

    // PUT - Update an existing memory
    @PutMapping("/{memoryId}")
    public boolean updateMemory(@PathVariable("memoryId") int memoryId, @RequestBody Memory memory) {
        memory.setMemoryId(memoryId); // Set memory ID just in case it's not set in the request body
        return memoryDao.updateMemory(memory);
    }

    // DELETE - Remove a memory by ID
    @DeleteMapping("/{memoryId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public boolean deleteMemory(@PathVariable("memoryId") int memoryId) {
        return memoryDao.deleteMemory(memoryId);
    }
}



