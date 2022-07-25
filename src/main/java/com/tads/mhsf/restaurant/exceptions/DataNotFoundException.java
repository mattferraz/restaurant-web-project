package com.tads.mhsf.restaurant.exceptions;

public class DataNotFoundException extends Exception {

    public DataNotFoundException() {
        super("User not found!");
    }

    public DataNotFoundException(String message) {
        super(message);
    }

    public DataNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
