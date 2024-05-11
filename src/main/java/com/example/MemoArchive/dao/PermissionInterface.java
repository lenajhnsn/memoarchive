package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.model.Permission;

import java.util.List;

public interface PermissionInterface {

        /**
         * Adds a new Permission to the database.
         *
         * @param permission The Permission object to add.
         * @return The Permission object after being added to the database.
         */
        Permission addPermission(Permission permission);

        /**
         * Retrieves a Permission by its primary key ID.
         *
         * @param permissionId The primary key of the Permission to find.
         * @return The found Permission, or null if not found.
         */
        Permission getPermissionByPermissionId(Integer permissionId);

        /**
         * Retrieves a Permission by the owner's user ID.
         *
         * @param ownerUserId The user ID of the owner to find Permissions for.
         * @return The found Permission, or null if not found.
         */
        Permission getPermissionByOwnerId(Integer ownerUserId);

        /**
         * Retrieves a Permission by the contributor's user ID.
         *
         * @param contributorUserId The user ID of the contributor to find Permissions for.
         * @return The found Permission, or null if not found.
         */
        Permission getPermissionByContributorId(Integer contributorUserId);

        /**
         * Retrieves all Permissions from the database.
         *
         * @return A list of all Permissions.
         */
        List<Permission> getAllPermissions();

        /**
         * Updates an existing Permission in the database.
         *
         * @param permission The Permission to update.
         * @return true if the update was successful, false otherwise.
         */
        boolean updatePermission(Permission permission);

        /**
         * Deletes a Permission from the database by its ID.
         *
         * @param permissionId The ID of the Permission to delete.
         * @return true if the deletion was successful, false otherwise.
         */
        boolean deletePermission(int permissionId);
    }