package com.example.MemoArchive.utility;

import com.example.MemoArchive.exception.DaoException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;

import java.sql.SQLException;

public class DaoExceptionUtil {

    public static <T> T handleJdbcOperation(JdbcExceptionOperation<T> operation) throws DaoException {
        try {
            // Execute the JDBC operation and return its result.
            return operation.execute();
        } catch (CannotGetJdbcConnectionException e) {
            // Handling for JDBC connection errors.
            throw new DaoException("Unable to connect to server or database", e);
        } catch (DataIntegrityViolationException e) {
            // Handling for database integrity constraints violations.
            throw new DaoException("Data integrity violation", e);
        } catch (DataAccessException e) {
            // General handling for DataAccessException (Spring has capabilities)
            throw new DaoException("Data access error.", e);
        }
    }
}