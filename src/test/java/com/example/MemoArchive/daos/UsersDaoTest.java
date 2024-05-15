package com.example.MemoArchive.daos;

import com.example.MemoArchive.dao.UsersDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UsersDaoTest extends BaseDaoTests {
//TODO: Do I need the method names to match those in the DAOs exactly?
    private UsersDao dao;

    @BeforeEach
    public void setUp() {
        this.dao = new UsersDao(dataSource);
    }

    @Test
    public void add() {
    }

    @Test
    public void getById() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void mapRowToUser() {
    }
}
