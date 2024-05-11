package com.example.MemoArchive.dao;

import com.example.MemoArchive.exception.DaoException;
import com.example.MemoArchive.model.Permission;
import com.example.MemoArchive.utility.DaoExceptionUtil;
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
//TODO: Stretch goal is add method that reduces the repetitive exception handling try/catch blocks
@Controller // Controller annotation allows Spring to create DAO
public class PermissionDao implements PermissionInterface {
    private JdbcTemplate jdbcTemplate;

    // Constructor
    public PermissionDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public Permission addPermission(Permission permission) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "INSERT INTO permission (access_type, owner_user_id, contributor_user_id) VALUES (?, ?, ?)";
            jdbcTemplate.update(sql, permission.getAccessType(), permission.getOwnerUserId(), permission.getContributorUserId());
            return permission; // Return the original permission object
        });
    }

    // READ -- GET BY PERMISSION ID (primary key)
    @Override
    public Permission getPermissionByPermissionId(Integer permissionId) {
        return DaoExceptionUtil.handleJdbcOperation(() ->
                jdbcTemplate.queryForObject("SELECT * FROM permission WHERE permission_id = ?", this::mapRowtoPermission, permissionId)
        );
    }

    // READ -- GET BY OWNER ID
    @Override
    public Permission getPermissionByOwnerId(Integer ownerUserId) {
        return DaoExceptionUtil.handleJdbcOperation(() ->
                jdbcTemplate.queryForObject("SELECT * FROM permission WHERE owner_user_id = ?", this::mapRowtoPermission, ownerUserId)
        );
    }

    // READ -- GET PERMISSION BY CONTRIBUTOR ID
    @Override
    public Permission getPermissionByContributorId(Integer contributorUserId) {
        return DaoExceptionUtil.handleJdbcOperation(() ->
                jdbcTemplate.queryForObject("SELECT * FROM permission WHERE contributor_user_id = ?", this::mapRowtoPermission, contributorUserId)
        );
    }

    // READ -- GET ALL PERMISSIONS
    @Override
    public List<Permission> getAllPermissions() {
        return jdbcTemplate.query("select * from permission", this::mapRowtoPermission);
    }

    // UPDATE
    @Override
    public boolean updatePermission(Permission permission) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE permission SET access_type = ?, owner_user_id = ?, contributor_user_id = ? WHERE permission_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, permission.getAccessType(), permission.getOwnerUserId(), permission.getContributorUserId(), permission.getPermissionId());
            return rowsAffected > 0;
        });
    }

    // DELETE
    @Override
    public boolean deletePermission(int permissionId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM permission WHERE permission_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, permissionId);
            return rowsAffected > 0;
        });
    }

    // MAP ROW TO PERMISSION
    private Permission mapRowtoPermission(ResultSet rs, int rowNum) throws SQLException {
        Permission permission = new Permission();
        permission.setPermissionId(rs.getInt("permission_id"));
        permission.setOwnerUserId(rs.getInt("owner_user_id"));
        permission.setContributorUserId(rs.getInt("contributor_user_id"));
        permission.setAccessType(rs.getString("access_type"));
        return permission;
    }
}
