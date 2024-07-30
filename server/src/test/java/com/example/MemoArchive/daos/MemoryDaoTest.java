package com.example.MemoArchive.daos;

import com.example.MemoArchive.dao.MemoryDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class MemoryDaoTest extends BaseDaoTests {
//TODO: add a testing DB to the test-data.sql file

    private MemoryDao dao;

    @BeforeEach
    public void setUp() {
        this.dao = new MemoryDao(BasicDataSource);
    }

    @Test
    void addMemory() {
    }

    @Test
    void getById() {
    }

    @Test
    void getAll() {
    }

    @Test
    void update() {
    }

    @Test
    void delete() {
    }

    @Test
    void mapRowToMemory() {
    }
}