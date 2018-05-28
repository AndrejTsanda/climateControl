package com.smart.incubator.exception;

import org.apache.log4j.Logger;

/**
 * The exception class is required to handle exceptions
 * that are associated with errors in querying the database.
 *
 * @author Tsanda Andrej
 */
public class ErrorQueryException extends DatabaseException {
    private static final Logger LOG = Logger.getLogger(ErrorQueryException.class);
    private String details;


    public ErrorQueryException() {
        this.details = "Undefined SQL query error.";
    }

    public ErrorQueryException(String details) {
        this.details = details;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }


    @Override
    public String toString() {
        return "SQL query error: [" + this.details + "]";
    }
}
