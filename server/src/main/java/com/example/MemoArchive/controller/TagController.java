package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.TagDao;
import com.example.MemoArchive.model.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.security.Principal;
import java.util.List;

@Component
@RestController //TODO: Add principal to controller classes
@RequestMapping("/tag")
@PreAuthorize("isAuthenticated()")
public class TagController {

    private final TagDao tagDao;

    public TagController(TagDao tagDao) {
        this.tagDao = tagDao;
    }

    // GET Tag by ID
    @GetMapping("/{tagId}")
    public Tag getTagById(@PathVariable("tagId") Integer tagId) {
        try {
            return tagDao.getTagByTagId(tagId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found.");
        }
    }

    // GET Tag by User ID
    @GetMapping("/by-user/{userId}")
    public Tag getTagByUserId(@PathVariable("userId") Integer userId) {
        try {
            return tagDao.getTagByUserId(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found for user.");
        }
    }

    // GET Tag by Memory ID
    @GetMapping("/by-memory/{memoryId}")
    public Tag getTagByMemoryId(@PathVariable("memoryId") Integer memoryId) {
        try {
            return tagDao.getTagByMemoryId(memoryId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found for memory.");
        }
    }

    // GET All Tags
    @GetMapping("/")
    public List<Tag> getAllTags() {
        return tagDao.getAllTags();
    }

    // POST - Create a new Tag
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Tag createTag(@Valid @RequestBody Tag tag, Principal principal) {
        System.out.println("Request by: " + principal.getName());
        return tagDao.addTag(tag);
    }

    // PUT - Update an existing Tag
    @PutMapping("/{tagId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Tag> updateTag(@Valid @PathVariable("tagId") Integer tagId, @RequestBody Tag tag) {
        boolean updated = tagDao.updateTag(tag);
        if (updated) {
            return ResponseEntity.ok(tag);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found.");
        }
    }

    // DELETE - Remove a Tag
    @DeleteMapping("/{tagId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteTag(@PathVariable("tagId") int tagId) {
        boolean deleted = tagDao.deleteTag(tagId);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found.");
        }
    }
}

