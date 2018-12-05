
package com.virtusa.polaris.exception;

/**
 * Base Exception.
 * 
 * @author Sachin Murthy
 */
public class BaseException extends Exception {
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -666666666L;

    /** Error code. */
    protected String errorCode;

    /**
     * No Argument constructor.
     */
    public BaseException() {
	super();
    }

    /**
     * Instantiates with message.
     * 
     * @param message
     *            Error message
     */
    public BaseException(final String message) {
	super(message);
    }

    /**
     * Instantiates with cause.
     * 
     * @param cause
     *            Throwable cause
     */
    public BaseException(final Throwable cause) {
	super(cause);
    }

    /**
     * Instantiates with message and cause.
     * 
     * @param message
     *            Error message
     * @param cause
     *            Throwable cause
     */
    public BaseException(final String message, final Throwable cause) {
	super(message, cause);
    }

    /**
     * Instantiates with message, cause and errorCode.
     * 
     * @param message
     *            Error message
     * @param cause
     *            Throwable cause
     * @param errorCode
     *            Error code
     */
    public BaseException(final String message, final Throwable cause,
	    final String errorCode) {
	super(message, cause);
	this.errorCode = errorCode;
    }

    /**
     * Instantiates with message and errorCode.
     * 
     * @param message
     *            Error message
     * @param errorCode
     *            Error code
     */
    public BaseException(final String message, final String errorCode) {
	super(message);
	this.errorCode = errorCode;
    }

    /**
     * Returns errorCode.
     * 
     * @return errorCode
     */
    public String getErrorCode() {
	return errorCode;
    }
}
