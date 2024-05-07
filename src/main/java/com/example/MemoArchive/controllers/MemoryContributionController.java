package com.example.MemoArchive.controllers;

import com.example.MemoArchive.daos.MemoryContributionDao;
import com.example.MemoArchive.daos.PermissionDao;
import com.example.MemoArchive.models.MemoryContribution;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/memorycontribution") // Makes the path more direct
public class MemoryContributionController {

    // INSTANCE VARIABLES
    private MemoryContributionDao memoryContributionDao;

    public MemoryContributionController(MemoryContributionDao memoryContributionDao) {
        this.memoryContributionDao = memoryContributionDao;
    }

    // GET
    @GetMapping("/") //TODO: Which IDs do I use for the path?
    public MemoryContribution getMemoryContributionbById(@PathVariable int id) {
        return memoryContributionDao//TODO:
    }

    // POST

    // PUT

    // DELETE
}
