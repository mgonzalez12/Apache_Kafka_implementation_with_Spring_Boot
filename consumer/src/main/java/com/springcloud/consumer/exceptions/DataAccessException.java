package com.springcloud.consumer.exceptions;


import org.hibernate.metamodel.mapping.NonTransientException;

/**
 * Root of the hierarchy of data access exceptions that are considered
 * non-transient - where a retry of the same operation would fail
 * unless the cause of the Exception is corrected.
 *
 * @author Rod Johnson
 * @author Juergen Hoeller
 * @since 16.03.2003
 */
public abstract class DataAccessException implements NonTransientException {

    /**
     * Constructor for DataAccessException.
     * @param msg the detail message
     */
    public DataAccessException(String msg) {
        super();
    }

    /**
     * Constructor for DataAccessException.
     * @param msg the detail message
     * @param cause the root cause (usually from using a underlying
     * data access API such as JDBC)
     */
    public DataAccessException(String msg, Throwable cause) {
        super();
    }

}
