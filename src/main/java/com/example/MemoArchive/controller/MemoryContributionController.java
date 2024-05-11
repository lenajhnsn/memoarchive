package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.MemoryContributionDao;
import com.example.MemoArchive.model.MemoryContribution;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;
//TODO: Learn about how to add logging functionality for future logs/debugging

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
    public MemoryContribution getMemoryContributionById(@PathVariable("id") int id) {
        try {
            return memoryContributionDao.getContributionByContributionId(id);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Memory contribution not found.");
        }
    }

    // GET BY MEMORY ID (foreign key)
    @GetMapping("/by-memory/{memoryId}")
    public List<MemoryContribution> getContributionsByMemoryId(@PathVariable("memoryId") int memoryId) {
        return memoryContributionDao.getContributionByMemoryId(memoryId);
    }

    // GET BY CONTRIBUTOR ID (foreign key)
    @GetMapping("/by-contributor/{contributorId}")
    public List<MemoryContribution> getContributionsByContributorId(@PathVariable("contributorId") int contributorId) {
        return memoryContributionDao.getContributionByContributorId(contributorId);
    }

    // POST - Create a new memory contribution
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public MemoryContribution createMemoryContribution(@Valid @RequestBody MemoryContribution contribution) {
        return memoryContributionDao.addMemoryContribution(contribution);
    }

    // PUT - Update an existing memory contribution
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public MemoryContribution updateMemoryContribution(@Valid @PathVariable("id") int id, @RequestBody MemoryContribution contribution) {
        return memoryContributionDao.updateContribution(id, contribution);
    }

    // DELETE - Remove a memory contribution by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteMemoryContribution(@PathVariable("id") int id) {
        memoryContributionDao.deleteContributionById(id);
    }
}
