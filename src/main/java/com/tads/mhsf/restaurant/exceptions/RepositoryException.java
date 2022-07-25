package com.tads.mhsf.restaurant.exceptions;

import java.sql.SQLException;

public class RepositoryException extends SQLException {
    public RepositoryException() {
        super("An unexpected error occurred. Please, try again.");
    }

    public RepositoryException(String message) {
        super(message);
    }

    public RepositoryException(String message, Throwable cause) {
        super(message, cause);
    }
}
