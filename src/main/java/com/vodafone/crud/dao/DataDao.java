/**
 * Interface of the DAO 
 */
package com.vodafone.crud.dao;

import java.util.List;

import com.vodafone.crud.model.Customer;

/**
 * @author Geeth
 *
 */
public interface DataDao {

	public Customer addEntity(Customer customer) throws Exception;

	public Customer updateEntity(Customer customer) throws Exception;

	public Customer getEntityById(long id) throws Exception;

	public List<Customer> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}
