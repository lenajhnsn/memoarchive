package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.VoiceMemo;
import java.util.List;

public interface VoiceMemoInterface {

    /**
     * Adds a new VoiceMemo to the database.
     *
     * @param voiceMemo The VoiceMemo object to add, containing details like memory ID and transcript text.
     * @return The VoiceMemo object added to the database, ideally with any database-generated values like an ID.
     */
    VoiceMemo addVoiceMemo(VoiceMemo voiceMemo);

    /**
     * Retrieves a VoiceMemo by its unique ID.
     *
     * @param voiceMemoId The unique identifier of the VoiceMemo to retrieve.
     * @return The VoiceMemo object if found, or null if no VoiceMemo with the specified ID exists.
     */
    VoiceMemo getVoiceMemoById(int voiceMemoId);

    /**
     * Retrieves all VoiceMemos from the database.
     *
     * @return A list of all VoiceMemos currently stored in the database.
     */
    List<VoiceMemo> getAllVoiceMemos();

    /**
     * Updates an existing VoiceMemo in the database.
     *
     * @param voiceMemo The VoiceMemo object to update, which must include the ID to specify which VoiceMemo to update.
     * @return true if the update was successful (i.e., at least one row was affected), false otherwise.
     */
    boolean updateVoiceMemo(VoiceMemo voiceMemo);

    /**
     * Deletes a VoiceMemo from the database based on its ID.
     *
     * @param voiceMemoId The unique identifier of the VoiceMemo to delete.
     * @return true if the deletion was successful (i.e., the VoiceMemo was found and deleted), false otherwise.
     */
    boolean deleteVoiceMemo(int voiceMemoId);
}
