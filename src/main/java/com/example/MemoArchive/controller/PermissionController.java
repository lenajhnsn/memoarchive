package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.PermissionDao;
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
