package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.PermissionDao;
import com.example.MemoArchive.model.Permission;
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
@RestController // Includes added functionality in addition to that provided by @Controller
@RequestMapping("/permission") // Set base path for the controller
@PreAuthorize("isAuthenticated()")
public class PermissionController {

    private PermissionDao permissionDao; //TODO: Add instance variable that references a service interface (referencing service class), replace return with memoryServices.method within method

    public PermissionController(PermissionDao permissionDao) {
        this.permissionDao = permissionDao;
    }

    // GET Permission by ID
    @GetMapping("/{permissionId}")
    public Permission getPermissionById(@PathVariable("permissionId") Integer permissionId) {
        try {
            return permissionDao.getPermissionByPermissionId(permissionId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found");
        }
    }

    // GET Permission by Owner ID
    @GetMapping("/by-owner/{ownerUserId}")
    public Permission getPermissionsByOwnerId(@PathVariable("ownerUserId") Integer ownerUserId) {
        return permissionDao.getPermissionByOwnerId(ownerUserId);
    }

    // GET Permission by Contributor ID
    @GetMapping("/by-contributor/{contributorUserId}")
    public Permission getPermissionsByContributorId(@PathVariable("contributorUserId") Integer contributorUserId) {
        return permissionDao.getPermissionByContributorId(contributorUserId);
    }

    // POST - Create a new Permission
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Permission createPermission(@Valid @RequestBody Permission permission, Principal principal) {
        // Log the user performing the request
        System.out.println("Request by: " + principal.getName());

        return permissionDao.addPermission(permission);

    }

    // PUT - Update an existing Permission
    @PutMapping("/{permissionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Permission> updatePermission(@Valid @PathVariable("permissionId") Integer permissionId, @RequestBody Permission permission) {
        boolean updated = permissionDao.updatePermission(permission);
        if (updated) {
            return ResponseEntity.ok(permission);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found.");
        }
    }

    // DELETE - Remove a Permission
    @DeleteMapping("/{permissionId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deletePermission(@PathVariable("permissionId") int permissionId) {
        boolean deleted = permissionDao.deletePermission(permissionId);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Permission not found.");
        }
    }
}
