/**
 * Customer Service/Delegater
 * Communicate with dao layer
 */
package com.vodafone.crud.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.vodafone.crud.dao.DataDao;
import com.vodafone.crud.dto.CustomerDto;
import com.vodafone.crud.model.Customer;

/**
 * @author Geeth
 *
 */
public class DataServicesImpl implements DataServices {
	static final Logger logger = Logger.getLogger(DataServicesImpl.class);

	@Autowired
	DataDao dataDao;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerDto addEntity(CustomerDto dto) throws Exception {
		logger.debug("Delegating to dao to return added Customer");
		return domainToDto(dataDao.addEntity(dtoToDomain(dto)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerDto updateEntity(CustomerDto dto) throws Exception {
		logger.debug("Delegating to dao to return updated Customer");
		return domainToDto(dataDao.addEntity(dtoToDomain(dto)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public CustomerDto getEntityById(long id) throws Exception {
		logger.debug("Delegating to dao to get Customer " + id);
		return domainToDto(dataDao.getEntityById(id));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<CustomerDto> getEntityList() throws Exception {
		logger.debug("Delegating to dao to get all Customers");
		List<Customer> cusList = dataDao.getEntityList();
		List<CustomerDto> dtoList = new ArrayList<CustomerDto>();

		for (Customer cus : cusList) {
			dtoList.add(domainToDto(cus));
		}
		return dtoList;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean deleteEntity(long id) throws Exception {
		logger.debug("Delegating to dao to delete Customer" + id);
		return dataDao.deleteEntity(id);
	}

	/**
	 * Convert Customer domain object to Data Transfer Object
	 * 
	 * @param customer
	 * @return converted customer dto.
	 */
	private CustomerDto domainToDto(Customer customer) {
		logger.debug("Calling Domain object to DT Object Convertor...");
		CustomerDto dto = new CustomerDto();

		dto.setId(customer.getId());
		dto.setName(customer.getName());
		dto.setAddress(customer.getAddress());
		dto.setPhoneNumber(customer.getPhoneNumber());
		return dto;
	}

	/**
	 * Convert Customer Data Transfer Object to domain object
	 * 
	 * @param customer
	 * @return converted customer domain object.
	 */
	private Customer dtoToDomain(CustomerDto dto) {
		logger.debug("Calling DT object to Domain Object Convertor...");
		Customer customer = new Customer();

		customer.setId(dto.getId());
		customer.setName(dto.getName());
		customer.setAddress(dto.getAddress());
		customer.setPhoneNumber(dto.getPhoneNumber());
		return customer;
	}

}