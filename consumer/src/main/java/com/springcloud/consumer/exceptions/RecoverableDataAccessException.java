package com.springcloud.consumer.exceptions;

/**
 * Exception thrown when a data access operation fails but can potentially recover.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 16.03.2003
 */
public class RecoverableDataAccessException extends DataAccessException {

    /**
     * Constructor for RecoverableDataAccessException.
     *
     * @param msg the detail message
     */
    public RecoverableDataAccessException(String msg) {
        super(msg);
    }

    /**
     * Constructor for RecoverableDataAccessException.
     *
     * @param msg the detail message
     * @param cause the root cause (usually from using a SQLException)
     */
    public RecoverableDataAccessException(String msg, Throwable cause) {
        super(msg, cause);
    }

}


