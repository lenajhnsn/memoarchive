package com.example.MemoArchive.dao;

import com.example.MemoArchive.model.Tag;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Controller;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Controller // Controller annotation allows Spring to create DAO
public class TagDao implements GeneralDaoInterface<Tag>{

        // INSTANCE VARIABLES
        private JdbcTemplate jdbcTemplate;

        // Spring creates the datasource
        public TagDao(DataSource dataSource) {
            this.jdbcTemplate = new JdbcTemplate(dataSource);
        }

        // ----METHODS----

        // CREATE (POST)
        @Override
        public boolean add(Tag tag) {
            try {
                String sql = "INSERT INTO tag (memory_id, user_id) VALUES (?, ?)";
                return jdbcTemplate.update(sql, tag.getMemoryId(), tag.getUserId()) > 0;
            } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
                throw new DaoException("Data integrity violation", e);
            } catch (DataAccessException e) {
                throw new DaoException("Data access error", e);
            }
        }

        // READ -- GET BY ID //TODO: Decide whether or not to have methods to get by memory/user IDs too
        @Override
        public Tag getById(Integer tagId) {
            try {
                SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from tag where tag_id = ?", tagId);
                if (rowSet.next()) {
                    return mapRowToTag(rowSet);
                }
            } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
                throw new DaoException("Data integrity violation", e);
            }  catch (DataAccessException e) {
                throw new DaoException("Data access error", e);
            }
            return null;
        }
        // READ -- GET ALL
        @Override
        public List<Tag> getAll() {
            List<Tag> tag = new ArrayList<>();
            try {
                SqlRowSet rowSet = jdbcTemplate.queryForRowSet("select * from tag");
                while (rowSet.next()) {
                    tag.add(mapRowToTag(rowSet));
                }
            } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
                throw new DaoException("Data integrity violation", e);
            } catch (DataAccessException e) {
                throw new DaoException("Data access error", e);
            }
            return tag;
        }

        // UPDATE (PUT)
        @Override
        public boolean update(Tag tag) {
            try {
                String sql = "UPDATE tag SET memory_id = ?, user_id = ? WHERE tag_id = ?";
                return jdbcTemplate.update(sql, tag.getMemoryId(), tag.getUserId(), tag.getTagId()) > 0;
            } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
                throw new DaoException("Data integrity violation", e);
            }  catch (DataAccessException e) {
                throw new DaoException("Data access error", e);
            }
        }
        // DELETE
        @Override
        public boolean delete(Integer tagId) {
            try {
                String sql = "DELETE FROM tag WHERE tag_id = ?";
                return jdbcTemplate.update(sql, tagId) > 0;
            } catch (CannotGetJdbcConnectionException e) { // JDBC connection failed
                throw new DaoException("Unable to connect to server or database", e);
            } catch (DataIntegrityViolationException e) { // Something violated data integrity constraints defined in schema
                throw new DaoException("Data integrity violation", e);
            }
        }
        // MAP ROW SET
        public Tag mapRowToTag(SqlRowSet rowSet) {
            Tag tag = new Tag();
            tag.setTagId(rowSet.getInt("tagId"));
            tag.setMemoryId(rowSet.getInt("memoryId"));
            tag.setUserId(rowSet.getInt("userId"));
            return tag;
        }
    }

