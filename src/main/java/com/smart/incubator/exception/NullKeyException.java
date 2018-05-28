package com.smart.incubator.exception;

import org.apache.log4j.Logger;

/**
 * The exception class is required to handle exceptions associated with empty keys.
 *
 * @author Tsanda Andrej
 */
public class NullKeyException extends DatabaseException {
    private static final Logger LOG = Logger.getLogger(NullKeyException.class);
    private String details;


    public NullKeyException() {
        this.details = "Key is null";
    }

    public NullKeyException(String details) {
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
        return "Key null exception: [" + this.details + "]";
    }
}
