package com.example.MemoArchive.dao;

import com.example.MemoArchive.exception.DaoException;
import com.example.MemoArchive.model.Memory;
import com.example.MemoArchive.model.Users;
import com.example.MemoArchive.utility.DaoExceptionUtil;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.Date;
import java.security.Principal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.List;

@Controller // Controller annotation allows Spring to make DAO
public class MemoryDao implements MemoryInterface {

    private JdbcTemplate jdbcTemplate;
    private DataSource dataSource;

    // Spring creates the datasource
    public MemoryDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.dataSource = dataSource;
    }

    // ----METHODS----

    // CREATE
    @Override
    public Memory addMemory(Memory memory, Principal principal) {
        /*
        Use the DaoExceptionUtil to handle any exceptions during the JDBC operation.
        A lambda expression is passed to the handleJdbcOperation method.
        From Research: Database operations are wrapped in lambda expressions. This simplifies passing the operation to the utility method.
         */
        UsersDao usersDao = new UsersDao(dataSource);
        Users user = usersDao.getUserByUsername(principal.getName());
        memory.setUserId(user.getUserId());
        memory.setType("photo");
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            // Get a key holder to hold the new id from the memory table
            KeyHolder keyHolder = new GeneratedKeyHolder();

            // SQL query to insert a new memory into the database.
            String sql = "INSERT INTO memory (user_id, type, content, description, memory_date, creation_date) VALUES (?, ?, ?, ?, ?, ?)";

            // Execute the update operation which returns the number of rows affected.
           jdbcTemplate.update(connection -> {
               // Create  a "prepared statement" which is just SQL and all its parameters
               PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
               ps.setInt(1, memory.getUserId());
               ps.setString(2, memory.getType());
               ps.setString(3, memory.getContent());
               ps.setString(4, memory.getDescription());
               ps.setDate(5, Date.valueOf(memory.getMemoryDate()));
               ps.setDate(6, Date.valueOf(LocalDate.now()));
               System.out.println(ps.toString());
               return ps;
           }, keyHolder);

           // Use the returned database id for the memory's memoryId
           memory.setMemoryId((int)keyHolder.getKeys().get("memory_id"));

           return memory; // Return true if the update was successful
        });
    }

    // READ -- GET MEMORY BY MEMORY ID
    @Override
    public Memory getMemoryByMemoryId(int memoryId, Principal principal) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM memory WHERE memory_id = ?", this::mapRowtoMemory, memoryId);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        }
    }

    // READ -- GET MEMORIES BY USER ID
    @Override
    public List<Memory> getMemoriesByUserId(int userId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.query("SELECT * FROM memory WHERE user_id = ?", this::mapRowtoMemory, userId);
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
    public boolean updateMemory(Memory memory, Principal principal) {
        // Run update operation using exception handling provided by DaoExceptionUtil.
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE memory SET type = ?, content = ?, description = ?, memory_date = ? WHERE memory_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, memory.getType(), memory.getContent(), memory.getDescription(), memory.getMemoryDate(), memory.getMemoryId());
            return rowsAffected > 0;
        });
    }

    // DELETE
    @Override
    public boolean deleteMemory(int memoryId, Principal principal) {
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