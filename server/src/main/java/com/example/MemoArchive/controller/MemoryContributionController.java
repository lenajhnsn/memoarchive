package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.MemoryContributionDao;
import com.example.MemoArchive.model.MemoryContribution;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.security.Principal;
import java.util.List;
//TODO: Learn about how to add logging functionality for future logs/debugging

@CrossOrigin
@Component
@RestController // Includes added functionality in addition to that provided by @Controller
@RequestMapping("/memorycontribution") // Set base path for the controller
@PreAuthorize("isAuthenticated()")
public class MemoryContributionController {


    // Dependency injection for DAO
    private final MemoryContributionDao memoryContributionDao;

    public MemoryContributionController(MemoryContributionDao memoryContributionDao) {
        this.memoryContributionDao = memoryContributionDao;
    }

    // GET BY CONTRIBUTION ID (primary key)
    @GetMapping("/{id}")
    public MemoryContribution getMemoryContributionById(@PathVariable("id") int id, Principal principal) {
        try {
            return memoryContributionDao.getContributionByContributionId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory contribution not found.");
        }
    }

    // GET CONTRIBUTIONS BY MEMORY ID (foreign key)
    @GetMapping("/by-memory/{memoryId}")
    public List<MemoryContribution> getContributionsByMemoryId(@PathVariable("memoryId") int memoryId, Principal principal) {
        return memoryContributionDao.getContributionByMemoryId(memoryId);
    }

    // GET CONTRIBUTIONS BY CONTRIBUTOR ID (foreign key)
    @GetMapping("/by-contributor/{contributorId}")
    public List<MemoryContribution> getContributionsByContributorId(@PathVariable("contributorId") int contributorId, Principal principal) {
        return memoryContributionDao.getContributionByContributorId(contributorId);
    }

    // POST - Create a new memory contribution
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public MemoryContribution createMemoryContribution(@Valid @RequestBody MemoryContribution contribution, Principal principal) {
        // Log the user performing the request
        System.out.println("Request by: " + principal.getName());

        return memoryContributionDao.addMemoryContribution(contribution);
    }

    // PUT - Update an existing memory contribution
    @PutMapping("/{id}")
    public ResponseEntity<MemoryContribution> updateMemoryContribution(@Valid @PathVariable("id") int id, @RequestBody MemoryContribution contribution, Principal principal) {
        // Log the user performing the request
        System.out.println("Accessed by: " + principal);

        MemoryContribution memoryContribution = memoryContributionDao.updateContribution(id, contribution);

        if (memoryContribution == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // DELETE - Remove a memory contribution by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMemoryContribution(@PathVariable("id") int id, Principal principal) { //TODO: Create logic that checks that the principals' id matches id and refernec that method here and add service class object at top instance variables
        memoryContributionDao.deleteContributionById(id);
    }

    }

