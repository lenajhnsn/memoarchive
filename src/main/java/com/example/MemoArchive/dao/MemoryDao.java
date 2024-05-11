package com.example.MemoArchive.dao;

import com.example.MemoArchive.exception.DaoException;
import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.utility.DaoExceptionUtil;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Controller // Controller annotation allows Spring to make DAO
public class MemoryDao implements MemoryInterface {

    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public MemoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public Memory addMemory(Memory memory) {
        /*
        Use the DaoExceptionUtil to handle any exceptions during the JDBC operation.
        A lambda expression is passed to the handleJdbcOperation method.
        From Research: Database operations are wrapped in lambda expressions. This simplifies passing the operation to the utility method.
         */
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            // SQL query to insert a new memory into the database.
            String sql = "INSERT INTO memory (user_id, type, content, description, memory_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";
            // Execute the update operation which returns the number of rows affected.
           jdbcTemplate.update(sql, memory.getUserId(), memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getCreationDate());
            return memory; // Return true if the update was successful
        });
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
//TODO: Should I return a boolean here or return the object?
    public boolean updateMemory(Memory memory) {
        // Run update operation using exception handling provided by DaoExceptionUtil.
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE memory SET type = ?, content = ?, description = ?, memory_date = ?, creation_date = ? WHERE memory_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getCreationDate(), memory.getMemoryId());
            return rowsAffected > 0;
        });
    }

    // DELETE
    @Override
    public boolean deleteMemory(int memoryId) {
        // Handle deletion within DaoExceptionUtil
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM memory WHERE memory_id = ?";
            return jdbcTemplate.update(sql, memoryId) > 0; // return number of rows affected greater than 0 (boolean)
        });
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