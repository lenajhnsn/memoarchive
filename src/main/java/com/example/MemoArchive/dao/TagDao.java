package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Tag;
import com.example.MemoArchive.utility.DaoExceptionUtil;
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

@Controller // Controller annotation allows Spring to create DAO
public class TagDao implements TagInterface {

    private JdbcTemplate jdbcTemplate;

    // Spring creates the datasource
    public TagDao(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // ----METHODS----

    // CREATE
    @Override
    public Tag addTag(Tag tag) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "INSERT INTO tag (memory_id, user_id) VALUES (?, ?)";
            jdbcTemplate.update(sql, tag.getMemoryId(), tag.getUserId());
            return tag;
        });
    }

    // READ -- GET BY TAG ID
    @Override
    public Tag getTagByTagId(int tagId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM tag where tag_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToTag, tagId);

        });
    }

    // READ -- GET BY USER ID
    @Override
    public Tag getTagByUserId(int userId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM tag where usery_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToTag, userId);

        });
    }

    // READ -- GET BY MEMORY ID
    @Override
    public Tag getTagByMemoryId(int memoryId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "SELECT * FROM tag where usery_id = ?";
            return jdbcTemplate.queryForObject(sql, this::mapRowToTag, memoryId);

        });
    }

    // READ -- GET ALL TAGS
    @Override
    public List<Tag> getAllTags() {
        // Query function takes care of looping and adding results to the list
        return jdbcTemplate.query("select * from tag", this::mapRowToTag);
    }

    // UPDATE TAG
    @Override
    public boolean updateTag(Tag tag) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "UPDATE tag SET memory_id = ?, user_id = ? WHERE tag_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, tag.getMemoryId(), tag.getUserId(), tag.getTagId());
            return rowsAffected > 0; // Return true if the update was successful
        });
    }

    // DELETE TAG
    @Override
    public boolean deleteTag(int tagId) {
        return DaoExceptionUtil.handleJdbcOperation(() -> {
            String sql = "DELETE FROM tag WHERE tag_id = ?";
            int rowsAffected = jdbcTemplate.update(sql, tagId);
            return rowsAffected > 0; // Return true if the deletion was successful
        });
    }

    // MAP ROW SET
    public Tag mapRowToTag(ResultSet rowSet, int rowNumber) throws SQLException {
        Tag tag = new Tag();
        tag.setTagId(rowSet.getInt("tagId"));
        tag.setMemoryId(rowSet.getInt("memoryId"));
        tag.setUserId(rowSet.getInt("userId"));
        return tag;
    }
}


