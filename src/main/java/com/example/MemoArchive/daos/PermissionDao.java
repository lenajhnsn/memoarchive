package com.example.MemoArchive.daos;

import com.example.MemoArchive.exceptions.DaoException;
import com.example.MemoArchive.models.Memory;
import com.example.MemoArchive.models.Permission;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

//TODO: Finish CRUD methods, implement interface, add DOA exceptions
@Controller // Controller annotation allows Spring to create DAO
public class PermissionDao {
    // INSTANCE VARIABLES
    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public PermissionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE (POST)

    // READ -- GET BY ID

    // READ -- GET MEMORY BY PERMISSION ID

    public Permission getPermissionByPermissionId(Integer permissionId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM permission WHERE permission_id = ?", this::mapRowtoPermission);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Unable to get data.", e);
        }
    }

    // READ -- GET MEMORY BY OWNER ID

    public Permission getPermissionByOwnerId(Integer ownerUserId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM permission WHERE owner_user_id = ?", this::mapRowtoPermission);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Unable to get data.", e);
        }
    }
    // READ -- GET MEMORY BY Co ID

    public Permission getPermissionByContributorId(Integer contributorUserId) {
        try { // Start try block to catch any exceptions
            return jdbcTemplate.queryForObject("SELECT * FROM permission WHERE contributor_user_id = ?", this::mapRowtoPermission);
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Unable to get data.", e);
        }
    }

    // READ -- GET ALL PERMISSIONS
    public List<Permission> getAll() {
        return jdbcTemplate.query("select * from permission", this::mapRowtoPermission);
    }

    // UPDATE (PUT)

    public boolean updatePermission(Permission permission) {
        try {
            String sql = "UPDATE permission SET access_type = ?, owner_user_id = ?, contributorUserId = ?, WHERE permission_id = ?"; //TODO: Should owner/contributor IDs be included in the set properties?
            return jdbcTemplate.update(sql, permission.getPermissionId(), permission.getOwnerUserId(), permission.getContributorUserId()) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }
    // DELETE

    public boolean deletePermission(int permissionId) {
        try {
            String sql = "DELETE FROM permission WHERE permission_id = ?";
            return jdbcTemplate.update(sql, permissionId) > 0;
        } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            throw new DaoException("Data access error.", e);
        }
    }

    // MAP ROW SET
    private Permission mapRowtoPermission(ResultSet row, int rowNumber) throws SQLException {
        Permission permission = new Permission();
        permission.setPermissionId(row.getInt("permission_id"));
        permission.setOwnerUserId(row.getInt("owner_user_id"));
        permission.setContributorUserId(row.getInt("contributor_user_id"));
        permission.setAccessType(row.getString("access_type"));
        return permission;

    }
}
