package com.example.MemoArchive.controller;

import com.example.MemoArchive.dao.UsersDao;
import com.example.MemoArchive.model.Users;
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
@RequestMapping("/users")
@PreAuthorize("isAuthenticated()")
public class UsersController {

    private UsersDao usersDao;

    public UsersController(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    // GET User by ID
    @GetMapping("/{userId}")
    public Users getUserById(@PathVariable("userId") Integer userId) {
        try {
            return usersDao.getUserByUserId(userId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
    }

    // GET All Users
    @GetMapping("/")
    public List<Users> getAllUsers() {
        return usersDao.getAllUsers();
    }

    // POST - Create a new User
    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN')")
    public Users createUser(@Valid @RequestBody Users user, Principal principal) {
        System.out.println("Request by: " + principal.getName());
        return usersDao.addUser(user);
    }

    // PUT - Update an existing User
    @PutMapping("/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Users> updateUser(@Valid @PathVariable("userId") Integer userId, @RequestBody Users user) {
        user.setUserId(userId); // Ensure the user ID is set correctly in the body
        boolean updated = usersDao.updateUser(user);
        if (updated) {
            return ResponseEntity.ok(user);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User to update not found.");
        }
    }

    // DELETE - Remove a User
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN')")
    public void deleteUser(@PathVariable("userId") int userId) {
        boolean deleted = usersDao.deleteUser(userId);
        if (!deleted) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User to delete not found.");
        }
    }
}
