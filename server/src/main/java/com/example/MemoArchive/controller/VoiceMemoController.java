package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.VoiceMemoDao;
import com.example.MemoArchive.model.VoiceMemo;
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
@RestController
@RequestMapping("/voice-memo")
@PreAuthorize("isAuthenticated()")
public class VoiceMemoController {

    private VoiceMemoDao voiceMemoDao;

    public VoiceMemoController(VoiceMemoDao voiceMemoDao) {
        this.voiceMemoDao = voiceMemoDao;
    }

    // GET Voice Memo by ID
    @GetMapping("/{voiceMemoId}")
    public VoiceMemo getVoiceMemoById(@PathVariable("voiceMemoId") Integer voiceMemoId) {
        try {
            return voiceMemoDao.getVoiceMemoById(voiceMemoId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voice Memo not found");
        }
    }

    // GET All Voice Memos
    @GetMapping("/")
    public List<VoiceMemo> getAllVoiceMemos() {
        return voiceMemoDao.getAllVoiceMemos();
    }

    // POST - Create a new Voice Memo
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public VoiceMemo createVoiceMemo(@Valid @RequestBody VoiceMemo voiceMemo, Principal principal) {
        System.out.println("Request by: " + principal.getName());
        return voiceMemoDao.addVoiceMemo(voiceMemo);
    }

    // PUT - Update an existing Voice Memo
    @PutMapping("/{voiceMemoId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<VoiceMemo> updateVoiceMemo(@Valid @PathVariable("voiceMemoId") Integer voiceMemoId, @RequestBody VoiceMemo voiceMemo) {
        voiceMemo.setVoiceMemoId(voiceMemoId); // Ensure the voice memo ID is set correctly in the body
        boolean updated = voiceMemoDao.updateVoiceMemo(voiceMemo);
        if (updated) {
            return ResponseEntity.ok(voiceMemo);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voice Memo to update not found.");
        }
    }

    // DELETE - Remove a Voice Memo
    @DeleteMapping("/{voiceMemoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteVoiceMemo(@PathVariable("voiceMemoId") int voiceMemoId) {
        boolean deleted = voiceMemoDao.deleteVoiceMemo(voiceMemoId);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Voice Memo to delete not found.");
        }
    }
}
