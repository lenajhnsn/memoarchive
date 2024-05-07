package com.example.MemoArchive.daos;

import com.example.MemoArchive.exceptions.DaoException;
import com.example.MemoArchive.models.Memory;
import com.example.MemoArchive.models.MemoryContribution;
import com.example.MemoArchive.models.Permission;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO: Stretch goal is add method that reduces the repetitive exception handling try/catch blocks
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
    public MemoryContribution add(MemoryContribution contribution) {
        try {
            jdbcTemplate.update("INSERT INTO MemoryContribution (memory_id, contributor_id) VALUES (?, ?)",
                    contribution.getMemoryId(), contribution.getContributorId());
            return contribution;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // GET BY CONTRIBUTION ID
    @Override
    public MemoryContribution findByContributionId(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    "SELECT * FROM MemoryContribution WHERE contribution_id = ?", this::mapRowtoMemoryContribution, id);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // GET BY MEMORY ID
    @Override
    public List<MemoryContribution> findByMemoryId(int memoryId) {
        return jdbcTemplate.query("SELECT * FROM MemoryContribution WHERE memory_id = ?",
                this::mapRowtoMemoryContribution, memoryId);
    }

    // GET BY CONTRIBUTOR ID
    @Override
    public List<MemoryContribution> findByContributorId(int contributorId) {
        return jdbcTemplate.query("SELECT * FROM MemoryContribution WHERE contributor_id = ?",
                this::mapRowtoMemoryContribution, contributorId);
    }

    // GET ALL
    @Override
    public List<MemoryContribution> findAll() {
        return jdbcTemplate.query(
                "SELECT * FROM MemoryContribution", this::mapRowtoMemoryContribution);
    }

    // UPDATE
    @Override
    public void update(MemoryContribution contribution) {
        try {
            jdbcTemplate.update("UPDATE MemoryContribution SET memory_id = ?, contributor_id = ? WHERE contribution_id = ?",
                    contribution.getMemoryId(), contribution.getContributorId(), contribution.getContributionId());
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // DELETE
    @Override
    public void deleteById(int id) {
        try {
            jdbcTemplate.update("DELETE FROM MemoryContribution WHERE contribution_id = ?", id);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
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
