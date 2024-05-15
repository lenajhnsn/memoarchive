package com.example.MemoArchive.daos;

import com.example.MemoArchive.dao.TagDao;
import com.example.MemoArchive.model.Tag;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.dao.DataAccessException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

    class TagDaoTest extends BaseDaoTests {
        private TagDao dao;

        @BeforeEach
        public void setUp() {
            this.dao = new TagDao(dataSource);
        }

        @Test
        void add_ValidTag_ShouldAdd() {
            // Memory_id 2 and user_id 3 are valid and exist.
            Tag tag = new Tag();
            tag.setMemoryId(2);
            tag.setUserId(3);
            Tag added = dao.addTag(tag);
            assertNotNull(added);
            assertNotNull(dao.getTagByTagId(tag.getTagId()));
        }

        @Test
        void add_InvalidTag_ShouldThrowException() {
            // Memory_id 999 and user_id 999 are invalid and don't exist.
            Tag tag = new Tag();
            tag.setMemoryId(999);
            tag.setUserId(999);
            assertThrows(DataAccessException.class, () -> dao.addTag(tag));
        }

        @Test
        void getById_ExistingTag_ReturnsTag() {
            // Testing using tagId 1 which is valid and exists
            Tag result = dao.getTagByTagId(1);
            assertNotNull(result);
            assertEquals(1, result.getTagId());
        }

        @Test
        void getById_NonExistingTag_ReturnsNull() {
            // Testing using tagId 999 which is invalid and doesn't exist
            Tag result = dao.getTagByTagId(999);
            assertNull(result);
        }

        @Test
        void getAll_ReturnsFullListOfTags() {
            // There are two tags on the list
            List<Tag> tags = dao.getAllTags();
            assertNotNull(tags); // The list exists and shouldn't be null
            assertFalse(tags.isEmpty()); // The list should not be empty
            assertTrue(tags.size() == 2); // The list should be two
        }

        @Test
        void update_ValidTag_UpdatesSuccessfully() {
            // Testing for tag_id 1 which is valid and exists in the db
            Tag tag = dao.getTagByTagId(1);
            tag.setMemoryId(2); // Change to another valid memory_id.
            assertTrue(dao.updateTag(tag)); // Successful update

            Tag updatedTag = dao.getTagByTagId(1);
            assertEquals(2, updatedTag.getMemoryId());
        }

        @Test
        void update_InvalidTag_FailsUpdate() {
            // Testing using tagId 999 which is invalid and doesn't exist, memory and user IDs are valid
            Tag tag = new Tag(999, 1, 2);
            assertFalse(dao.updateTag(tag));
        }

        @Test
        void delete_ValidTag_DeletesSuccessfully() {
            assertTrue(dao.deleteTag(1)); // existing, valid tagId
            assertNull(dao.getTagByTagId(1)); // After being deleted above it should be a null result when passed through on this line
        }
        @Test
        void delete_InvalidTag_FailsDelete() {
            assertFalse(dao.deleteTag(999)); // Non-existing, invalid tagId
        }
    }
