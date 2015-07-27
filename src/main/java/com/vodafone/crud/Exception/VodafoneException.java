/**
 * Customized Exception
 */
package com.vodafone.crud.Exception;

/**
 * @author Geeth
 *
 */
public class VodafoneException extends Exception {

	/**
	 * Generated Id.
	 */
	private static final long serialVersionUID = 3010032126792162336L;

	protected String massege;
	protected String code;

	public VodafoneException() {
	}

	public VodafoneException(String massege, String code) {
		this.massege = massege;
		this.code = code;

	}

	/**
	 * @return the massege
	 */
	public String getMassege() {
		return massege;
	}

	/**
	 * @param massege
	 *            the massege to set
	 */
	public void setMassege(String massege) {
		this.massege = massege;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
