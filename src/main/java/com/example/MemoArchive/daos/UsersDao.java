package com.example.MemoArchive.daos;

import com.example.MemoArchive.models.Users;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UsersDao implements GeneralDaoInterface<Users> {
    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public UsersDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public boolean add(Users user) {
        try {
            String sql = "INSERT INTO users (first_name, last_name, email, password, account_creation_date) VALUES (?, ?, ?, ?, ?)";
            return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getAccountCreationDate()) > 0;
        } catch (CannotGetJdbcConnectionException e) {
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // READ USERS ID
    @Override
    public Users getById(Integer userId) {
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from users where user_id = ?", userId);
            if (rowSet.next()) {
                return mapRowToUser(rowSet);
            }
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
        return null;
    }

    // GET LIST OF ALL TAGS
    @Override
    public List<Users> getAll() {
        List<Users> user = new ArrayList<>();
        try {
            SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from users");
            while (rowSet.next()) {
                user.add(mapRowToUser(rowSet));
            }
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
        return user;
    }

    // UPDATE USER
    @Override
    public boolean update(Users user) {
        try {
            String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ?, password = ?, account_creation_date = ? WHERE user_id = ?";
            return jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getPassword(), user.getUserId()) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // DELETE USER
    @Override
    public boolean delete(Integer userId) {
        try {
            String sql = "DELETE FROM users WHERE user_id = ?";
            return jdbcTemplate.update(sql, userId) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // MAP ROW TO USER
    public Users mapRowToUser(SqlRowSet rowSet){
        Users user = new Users();
        user.setUserId(rowSet.getInt("user_id"));
        user.setFirstName(rowSet.getString("first_name"));
        user.setLastName(rowSet.getNString("last_name"));
        user.setEmail(rowSet.getString("email"));
        user.setPassword(rowSet.getString("password"));
        user.setUsername(rowSet.getString("username"));
        user.setAccountCreationDate(rowSet.getDate("account_creation_date").toLocalDate());
        return user;
    }
}
