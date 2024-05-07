package com.example.MemoArchive.daos;

import com.example.MemoArchive.exceptions.DaoException;
import com.example.MemoArchive.models.Memory;
import com.example.MemoArchive.models.MemoryContribution;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller // Controller annotation allows Spring to make DAO
public class MemoryDao {

    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public MemoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE (POST)

    public boolean addMemory(Memory memory) {
        try {
            String sql = "INSERT INTO memory (user_id, type, content, description, memory_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, memory.getUserId(), memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getCreationDate()) > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }

    }

    // READ -- GET MEMORY BY MEMORY ID

    public Memory getMemoryByMemoryId(Integer memoryId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM memory WHERE memory_id = ?", this::mapRowtoMemory);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Unable to get data.", e);
        }
    }

    // READ -- GET MEMORY BY USER ID

    public Memory getMemoryByUserId(Integer userId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM memory WHERE user_id = ?", this::mapRowtoMemory);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Unable to get data.", e);
        }
    }

    // READ -- GET ALL

    public List<Memory> getAll() {
        return jdbcTemplate.query("SELECT * FROM memory", this::mapRowtoMemory);
        // Query function takes care of looping and adding results to the list
    }

    // UPDATE (PUT)

    public boolean updateMemory(Memory memory) {
        try {
            String sql = "UPDATE memory SET type = ?, content = ?, description = ?, memory_date = ?, creation_date = ? WHERE id = ?";
            return jdbcTemplate.update(sql, memory.getUserId(), memory.getMemoryId()) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }

    }

    // DELETE

    public boolean deleteMemory(Integer memoryId) {
        try {
            String sql = "DELETE FROM memory WHERE id = ?";
            return jdbcTemplate.update(sql, memoryId) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // MAP ROW SET
    private Memory mapRowtoMemory(ResultSet row, int rowNumber) throws SQLException { // Added ResultSet to simplify DAO
        Memory memory = new Memory();
        memory.setMemoryId(row.getInt("memory_id"));
        memory.setUserId(row.getInt("user_id"));
        memory.setType(row.getString("type"));
        memory.setContent(row.getString("content"));
        memory.setDescription(row.getString("description"));
        memory.setCreationDate(row.getDate("creation_date").toLocalDate());
        memory.setMemoryDate(row.getDate("memory_date").toLocalDate());

        return memory;

    }
}
