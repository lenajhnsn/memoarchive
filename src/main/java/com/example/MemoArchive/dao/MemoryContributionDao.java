package com.example.MemoArchive.dao;

import com.example.MemoArchive.exception.DaoException;
import com.example.MemoArchive.model.MemoryContribution;
import com.example.MemoArchive.utility.DaoExceptionUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


@Controller // Controller annotation allows Spring to create DAO
public class MemoryContributionDao implements MemoryContributionInterface {
    private JdbcTemplate jdbcTemplate;

    // Spring creates a constructor that injects the datasource object required by JdbcTemplate
    public MemoryContributionDao(DataSource dataSource) { // Provides connection config to the database
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public MemoryContribution addMemoryContribution(MemoryContribution contribution) {
        /*
        Use the DaoExceptionUtil to handle any exceptions during the JDBC operation.
        A lambda expression is passed to the handleJdbcOperation method.
        */
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            jdbcTemplate.update("INSERT INTO MemoryContribution (memory_id, contributor_id) VALUES (?, ?)",
                    contribution.getMemoryId(), contribution.getContributorId());
            return contribution;
        });
    }

    // GET BY CONTRIBUTION ID
    @Override
    public MemoryContribution getContributionByContributionId(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM MemoryContribution WHERE contribution_id = ?", this::mapRowtoMemoryContribution, id);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // GET BY MEMORY ID
    @Override
    public List<MemoryContribution> getContributionByMemoryId(int memoryId) {
        return jdbcTemplate.query("SELECT * FROM MemoryContribution WHERE memory_id = ?",
                this::mapRowtoMemoryContribution, memoryId);
    }

    // GET BY CONTRIBUTOR ID
    @Override
    public List<MemoryContribution> getContributionByContributorId(int contributorId) {
        return jdbcTemplate.query("SELECT * FROM MemoryContribution WHERE contributor_id = ?",
                this::mapRowtoMemoryContribution, contributorId);
    }

    // GET ALL
    @Override
    public List<MemoryContribution> getAllMemoryContributions() {
        return jdbcTemplate.query(
                "SELECT * FROM MemoryContribution", this::mapRowtoMemoryContribution);
    }

    // UPDATE
    @Override
    public MemoryContribution updateContribution(int id, MemoryContribution contribution) {
        // Update a specific contribution using provided details.
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            jdbcTemplate.update("UPDATE MemoryContribution SET memory_id = ?, contributor_id = ? WHERE contribution_id = ?",
                    contribution.getMemoryId(), contribution.getContributorId(), contribution.getContributionId());
            return getContributionByContributionId(id);
        });
    }

    // DELETE
    @Override
    public boolean deleteContributionById(int id) {
        // Delete a memory contribution by ID. Handle exceptions with DaoExceptionUtil
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM MemoryContribution WHERE contribution_id = ?";
            return jdbcTemplate.update(sql, id) > 0;
        });
    }

    // MAP ROW SET
    private MemoryContribution mapRowtoMemoryContribution(ResultSet rowSet, int rowNumber) throws SQLException {
        MemoryContribution memoryContribution = new MemoryContribution();
        memoryContribution.setContributorId(rowSet.getInt("contributor_id"));
        memoryContribution.setContributionId(rowSet.getInt("contribution_id"));
        memoryContribution.setMemoryId(rowSet.getInt("memory_id"));
        return memoryContribution;

    }
}
