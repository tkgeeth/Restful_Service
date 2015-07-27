/**
 * This POJO to represent conditional Status and Error messages in form of Json, 
 * this comes handy in case of sending status response to requests if needed.
 */
package com.vodafone.crud.model;

/**
 * @author Geeth
 *
 */

public class Status {

	private int code;
	private String message;

	/**
	 * Default constructor
	 */
	public Status() {
	}

	/**
	 * @param code
	 *            the code to set
	 * @param message
	 *            the message to set
	 */
	public Status(int code, String message) {
		this.code = code;
		this.message = message;
	}

	/**
	 * @return the code
	 */
	public int getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(int code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
