package com.example.MemoArchive.controllers;

import com.example.MemoArchive.daos.MemoryContributionDao;
import com.example.MemoArchive.daos.PermissionDao;
import com.example.MemoArchive.models.MemoryContribution;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/memorycontribution/") // Set base path for the controller
public class MemoryContributionController {

    // Dependency injection for DAO
    private final MemoryContributionDao memoryContributionDao;

    public MemoryContributionController(MemoryContributionDao memoryContributionDao) {
        this.memoryContributionDao = memoryContributionDao;
    }

    // GET BY CONTRIBUTOR ID (primary key)
    @GetMapping("/{id}")
    public MemoryContribution getMemoryContributionById(@PathVariable("id") int id) {
        return memoryContributionDao.getContributionByContributionId(id);
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
    public MemoryContribution createMemoryContribution(@RequestBody MemoryContribution contribution) {
        return memoryContributionDao.addMemoryContribution(contribution);
    }

    // PUT - Update an existing memory contribution
    @PutMapping("/{id}")
    public MemoryContribution updateMemoryContribution(@PathVariable("id") int id, @RequestBody MemoryContribution contribution) {
        return memoryContributionDao.updateContribution(id, contribution);
    }

    // DELETE - Remove a memory contribution by ID
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMemoryContribution(@PathVariable("id") int id) {
        memoryContributionDao.deleteContributionById(id);
    }
}
