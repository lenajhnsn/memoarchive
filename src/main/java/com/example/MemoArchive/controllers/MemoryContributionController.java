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
    public ResponseEntity<MemoryContribution> getMemoryContributionById(@PathVariable("id") int id) {
        MemoryContribution contribution = memoryContributionDao.findByContributionId(id);
        if (contribution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contribution);
    }

    // GET BY MEMORY ID (foreign key)
    @GetMapping("/by-memory/{memoryId}")
    public ResponseEntity<List<MemoryContribution>> getContributionsByMemoryId(@PathVariable("memoryId") int memoryId) {
        List<MemoryContribution> contributions = memoryContributionDao.findByMemoryId(memoryId);
        if (contributions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contributions);
    }

    // GET BY CONTRIBUTOR ID (foreign key)
    @GetMapping("/by-contributor/{contributorId}")
    public ResponseEntity<List<MemoryContribution>> getContributionsByContributorId(@PathVariable("contributorId") int contributorId) {
        List<MemoryContribution> contributions = memoryContributionDao.findByContributorId(contributorId);
        if (contributions.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(contributions);
    }


    // POST
    @PostMapping("/")
    public ResponseEntity<MemoryContribution> createMemoryContribution(@RequestBody MemoryContribution contribution) {
        MemoryContribution savedContribution = memoryContributionDao.add(contribution);
        return new ResponseEntity<>(savedContribution, HttpStatus.CREATED);
    }

    // PUT
    @PutMapping("/{id}")
    public ResponseEntity<MemoryContribution> updateMemoryContribution(@PathVariable("id") int id, @RequestBody MemoryContribution contribution) {
        MemoryContribution existingContribution = memoryContributionDao.findByContributionId(id);
        if (existingContribution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        existingContribution.setMemoryId(contribution.getMemoryId());
        existingContribution.setContributorId(contribution.getContributorId());
        memoryContributionDao.update(existingContribution);
        return ResponseEntity.ok(existingContribution);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMemoryContribution(@PathVariable("id") int id) {
        MemoryContribution contribution = memoryContributionDao.findByContributionId(id);
        if (contribution == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        memoryContributionDao.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
