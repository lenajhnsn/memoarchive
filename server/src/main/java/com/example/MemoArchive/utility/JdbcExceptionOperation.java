package com.example.MemoArchive.utility;

import org.springframework.dao.DataAccessException;

import java.sql.SQLException;

public interface JdbcExceptionOperation <T> {
    /*
    - This is a functional interface because it contains exactly one abstract method
    - <T> symbol used as a generic for multiple classes
    - This method is expected to contain JDBC exception operations and throws DataAccessException if any errors occur.
    - Simplifying using Spring: Spring has database error abstraction that provides detailed exceptions appropriate for various JDBC issues.
     */
    T execute() throws DataAccessException;
}
