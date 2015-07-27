/**
 * 
 */
package com.vodafone.crud.dto;

/**
 * @author Geeth
 *
 */
public class CustomerDto {

	private long id;
	private String name;
	private String address;
	private String phoneNumber;

	/**
	 * Default constructor
	 */
	public CustomerDto() {

	}

	/**
	 * Constructor with properties
	 * 
	 * @param id
	 * @param name
	 * @param address
	 * @param phoneNumber
	 */
	public CustomerDto(long id, String name, String address, String phoneNumber) {
		this.id = id;
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * Constructor with properties
	 * 
	 * @param name
	 * @param address
	 * @param phoneNumber
	 */
	public CustomerDto(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber
	 *            the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CustomerDto [id=" + id + ", name=" + name + ", address=" + address + ", phoneNumber=" + phoneNumber
				+ "]";
	}
}
