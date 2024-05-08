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
public class MemoryDao implements MemoryInterface {

    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public MemoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public boolean addMemory(Memory memory) {
        try {
            String sql = "INSERT INTO memory (user_id, type, content, description, memory_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
            int rowsAffected = jdbcTemplate.update(sql, memory.getUserId(), memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getCreationDate());
            return rowsAffected > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }

    }

    // READ -- GET MEMORY BY MEMORY ID
    @Override
    public Memory getMemoryByMemoryId(int memoryId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM memory WHERE memory_id = ?", this::mapRowtoMemory, memoryId);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    // READ -- GET MEMORY BY USER ID
    @Override
    public Memory getMemoryByUserId(int userId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM memory WHERE user_id = ?", this::mapRowtoMemory, userId);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    // READ -- GET ALL

    public List<Memory> getAllMemories() {
        return jdbcTemplate.query("SELECT * FROM memory", this::mapRowtoMemory);
        // Query function takes care of looping and adding results to the list
    }

    // UPDATE (PUT)

    public boolean updateMemory(Memory memory) {
        try {
            String sql = "UPDATE memory SET type = ?, content = ?, description = ?, memory_date = ?, creation_date = ? WHERE memory_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getCreationDate(), memory.getMemoryId());
            return rowsAffected > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }

    }

    // DELETE
    @Override
    public boolean deleteMemory(int memoryId) {
        try {
            String sql = "DELETE FROM memory WHERE memory_id = ?";
            return jdbcTemplate.update(sql, memoryId) > 0; // return number of rows affected greater than 0 (boolean)
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (
                DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
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

//TODO: For post/put/delete: Create method that can be extended/used; pass