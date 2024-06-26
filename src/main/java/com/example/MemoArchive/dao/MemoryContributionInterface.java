package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.MemoryContribution;

import java.util.List;

// Interface defines data access operations for this class' entities
public interface MemoryContributionInterface {

    /**
     * Finds a MemoryContribution by its primary key ID.
     *
     * @param id the primary key of the MemoryContribution to find
     * @return the found MemoryContribution, or null if not found
     */
    MemoryContribution getContributionByContributionId(int id);

    /**
     * Finds all MemoryContribution records associated with a specific memory ID.
     *
     * @param memoryId the ID of the memory to fetch contributions for
     * @return a list of MemoryContribution entities
     */
    List<MemoryContribution> getContributionByMemoryId(int memoryId);

    /**
     * Finds all MemoryContribution records associated with a specific contributor ID.
     *
     * @param contributorId the ID of the contributor to fetch contributions for
     * @return a list of MemoryContribution entities
     */

    List<MemoryContribution> getContributionByContributorId(int contributorId);

    /**
     * Saves a new MemoryContribution to the database.
     *
     * @param contribution the MemoryContribution to save
     * @return the saved MemoryContribution, potentially updated with new data (e.g., auto-generated ID)
     */

    MemoryContribution addMemoryContribution(MemoryContribution contribution);

    /**
     * Updates an existing MemoryContribution in the database.
     *
     * @param contribution the MemoryContribution to update
     */

    MemoryContribution updateContribution(int id, MemoryContribution contribution);

    /**
     * Deletes a MemoryContribution from the database by ID.
     *
     * @param id the ID of the MemoryContribution to delete
     */
    boolean deleteContributionById(int id);

    /**
     * Retrieves all MemoryContributions from the database.
     *
     * @return a list of all MemoryContributions
     */
    List<MemoryContribution> getAllMemoryContributions();
}
