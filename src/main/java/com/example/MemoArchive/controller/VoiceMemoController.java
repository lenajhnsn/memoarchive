package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.VoiceMemoDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/voice") // Makes the path more direct
public class VoiceMemoController {

    private VoiceMemoDao voiceMemoDao;

    public VoiceMemoController(VoiceMemoDao voiceMemoDao) {
        this.voiceMemoDao = voiceMemoDao;
    }
    //TODO: Add @GetMapping, @PutMapping, @RequestMapping, etc. into controller classes. ~20 minutes into Michael's first video
    /*@GetMapping
    public

     */


}
