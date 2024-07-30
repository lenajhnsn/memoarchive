package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Tag;
import java.util.List;

public interface TagInterface {

    /**
     * Adds a new tag to the database.
     * @param tag The tag object to add.
     * @return true if the tag was successfully added, false otherwise.
     */
    Tag addTag(Tag tag);

    /**
     * Retrieves a tag by its ID.
     * @param tagId The ID of the tag to retrieve.
     * @return The retrieved tag, or null if not found.
     */
    Tag getTagByTagId(int tagId);

    /**
     * Retrieves a tag by its ID.
     * @param userId The ID of the tag to retrieve.
     * @return The retrieved tag, or null if not found.
     */
    Tag getTagByUserId(int userId);

    /**
     * Retrieves a tag by its ID.
     * @param memoryId The ID of the tag to retrieve.
     * @return The retrieved tag, or null if not found.
     */
    Tag getTagByMemoryId(int memoryId);

    /**
     * Retrieves all tags from the database.
     * @return A list of all tags.
     */
    List<Tag> getAllTags();

    /**
     * Updates an existing tag in the database.
     * @param tag The tag object to update.
     * @return true if the update was successful, false otherwise.
     */
    boolean updateTag(Tag tag);

    /**
     * Deletes a tag from the database by its ID.
     * @param tagId The ID of the tag to delete.
     * @return true if the tag was successfully deleted, false otherwise.
     */
    boolean deleteTag(int tagId);
}
