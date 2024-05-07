package com.example.MemoArchive.controllers;

import com.example.MemoArchive.daos.PermissionDao;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission") // Makes the path more direct
public class PermissionController {
    private PermissionDao permissionDao;

    public PermissionController(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }
}
