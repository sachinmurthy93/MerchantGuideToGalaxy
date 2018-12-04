package com.virtusa.polaris.exception;

public class GalaxyTradeException extends BaseException {

	/**
	 * serialVersionUID
	 */

	private static final long serialVersionUID = -44444444493L;

	/**
	 * Creates a new GalaxyTradeException object.
	 */
	public GalaxyTradeException() {
		super();
	}

	/**
	 * Creates a new GalaxyTradeException object.
	 * 
	 * @param message
	 *            Error message
	 */
	public GalaxyTradeException(final String message) {
		super(message);
	}

	/**
	 * Creates a new GalaxyTradeException object.
	 * 
	 * @param cause
	 *            Throwable cause
	 */
	public GalaxyTradeException(final Throwable cause) {
		super(cause);
	}

	/**
	 * Creates a new GalaxyTradeException object.
	 * 
	 * @param message
	 *            Error message
	 * @param cause
	 *            Throwable cause
	 */
	public GalaxyTradeException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * Creates a new GalaxyTradeException object.
	 * 
	 * @param message
	 *            Error message
	 * @param cause
	 *            Throwable cause
	 * @param errorCode
	 *            Error code
	 */
	public GalaxyTradeException(final String message, final Throwable cause,
								final String errorCode) {
		super(message, cause, errorCode);
	}

	/**
	 * Instantiates with message and errorCode.
	 * 
	 * @param message
	 *            Error message
	 * @param errorCode
	 *            Error code
	 */
	public GalaxyTradeException(final String message, final String errorCode) {
		super(message, errorCode);
	}

}
