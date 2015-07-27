/**
 * Customized Exception for Database exception.
 */
package com.vodafone.crud.Exception;

public class VodafoneDataBaseException extends VodafoneException {

	/**
	 * Generated ID.
	 */
	private static final long serialVersionUID = -5067824587267967815L;

	public VodafoneDataBaseException() {
	}

	public VodafoneDataBaseException(String massege, String code) {
		this.massege = massege;
		this.code = code;

	}
}
