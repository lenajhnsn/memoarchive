package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Users;
import com.example.MemoArchive.utility.DaoExceptionUtil;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Component
public class UsersDao implements UsersInterface {
    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public UsersDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public Users addUser(Users user) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "INSERT INTO users (first_name, last_name, email, password, username, account_creation_date) VALUES (?, ?, ?, ?, ?, ?)";
            String passwordHash = new BCryptPasswordEncoder().encode(user.getPassword());
            jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), passwordHash, user.getUsername(), user.getAccountCreationDate());
            return user;
        });
    }

    // READ -- GET BY USER ID
    @Override
    public Users getUserByUserId(int userId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM users WHERE user_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToUser, userId);
        });
    }

    // READ -- GET USER BY USERNAME
    @Override
    public Users getUserByUsername(String username) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM users WHERE username = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToUser, username);
        });
        //TODO: iLike/Wildcard to match username fully

    }

    // GET LIST OF ALL TAGS
    @Override
    public List<Users> getAllUsers() {
        // Query function takes care of looping and adding results to the list
        return jdbcTemplate.query("select * from users", this::mapRowToUser);

    }

    // UPDATE USER
    @Override
    public boolean updateUser(Users user) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, account_creation_date = ? WHERE user_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAccountCreationDate(), user.getUserId());
            return rowsAffected > 0; // Return true if the update was successful
        });
    }

    // DELETE USER
    @Override
    public boolean deleteUser(int userId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM users WHERE user_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, userId);
            return rowsAffected > 0; // Return true if the deletion was successful
        });
    }

    // MAP ROW TO USER
    public Users mapRowToUser(ResultSet rowSet, int rowNumber) throws SQLException {
        Users user = new Users();
        user.setUserId(rowSet.getInt("user_id"));
        user.setFirstName(rowSet.getString("first_name"));
        user.setLastName(rowSet.getString("last_name"));
        user.setEmail(rowSet.getString("email"));
        user.setPassword(rowSet.getString("password"));
        user.setUsername(rowSet.getString("username"));
        user.setAccountCreationDate(rowSet.getDate("account_creation_date").toLocalDate());
        return user;
    }
}
