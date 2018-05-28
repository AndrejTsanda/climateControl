package com.smart.incubator.exception;

import org.apache.log4j.Logger;

/**
 * The exception class is required to handle exceptions associated with empty entities.
 *
 * @author Tsanda Andrej
 */
public class NullDomainException extends DatabaseException {
    private static final Logger LOG = Logger.getLogger(NullDomainException.class);
    private String details;


    public NullDomainException() {
        this.details = "Domain is null.";
    }

    public NullDomainException(String details) {
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
        return "Domain null exception: [" + this.details + "]";
    }
}
