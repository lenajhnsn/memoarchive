package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Users;
import java.util.List;

public interface UsersInterface {
//TODO: Add getUserByUsername so Authentication Controller class can work
    /**
     * Adds a new user to the database.
     *
     * @param user The user object to add.
     * @return The added user with potentially updated data (e.g., auto-generated ID).
     */
    Users addUser(Users user);

    Users getUserByUsername(String username);

    /**
     * Retrieves a user by their user ID.
     *
     * @param userId The ID of the user to retrieve.
     * @return The user object if found, otherwise null.
     */
    Users getUserByUserId(int userId);

    /**
     * Retrieves all users from the database.
     *
     * @return A list of all user objects.
     */
    List<Users> getAllUsers();

    /**
     * Updates an existing user in the database.
     *
     * @param user The user object to update.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateUser(Users user);

    /**
     * Deletes a user from the database using their user ID.
     *
     * @param userId The ID of the user to delete.
     * @return true if the deletion was successful, false otherwise.
     */
    boolean deleteUser(int userId);
}
