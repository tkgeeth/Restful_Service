/**
 * Customer Rest Controller class.  Use to perform CRUD operations.
 */
package com.vodafone.crud.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vodafone.crud.dto.CustomerDto;
import com.vodafone.crud.model.Status;
import com.vodafone.crud.service.DataServices;

/**
 * @author Geeth
 *
 */
@Controller
@RequestMapping("/customer")
public class RestController {

	@Autowired
	DataServices dataServices;

	static final Logger logger = Logger.getLogger(RestController.class);

	/**
	 * Use to add a new customer to database.
	 * 
	 * @param dto
	 *            Customer data transfer object
	 * @return Whether success of the operation
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status addCustomer(@RequestBody CustomerDto dto) {
		logger.debug("Delegating to service to return Added Customer");
		try {
			dataServices.addEntity(dto);
			logger.debug("Customer added Successfully !");
			return new Status(1, "Customer added Successfully !");
		} catch (Exception e) {
			logger.warn("Customer wasn't added !");
			return new Status(0, e.toString());
		}

	}

	/**
	 * Use to update an existing customer to database.
	 * 
	 * @param dto
	 *            Customer data transfer object
	 * @return Whether success of the operation
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody Status updateCustomer(@RequestBody CustomerDto dto) {
		logger.debug("Delegating to service to return updated Customer");
		try {
			dataServices.updateEntity(dto);
			return new Status(1, "Customer updated Successfully !");
		} catch (Exception e) {
			logger.warn("Customer wasn't updated !");
			return new Status(0, e.toString());
		}

	}

	/**
	 * Use to retrieve an exiting customer
	 * 
	 * @param id
	 *            Unique identifier of the particular customer.
	 * @return Fetched customer
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public @ResponseBody CustomerDto getCustomer(@PathVariable("id") long id) {
		logger.debug("Delegating to service to return Customer " + id);
		CustomerDto dto = null;
		try {
			dto = dataServices.getEntityById(id);

		} catch (Exception exe) {
			logger.debug("Customer wasn't fetched " + id);
			logger.error(exe.getMessage());
		}
		return dto;
	}

	/**
	 * Use to retrieve all existing customers
	 * 
	 * @return List of fetched customers
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public @ResponseBody List<CustomerDto> getCustomers() {
		logger.debug("Delegating to service to return all Customers");
		List<CustomerDto> customerList = null;
		try {
			customerList = dataServices.getEntityList();

		} catch (Exception exe) {
			logger.debug("Customers fetching was failed");
			logger.error(exe.getMessage());
		}

		return customerList;
	}

	/**
	 * Use to delete an existing customer
	 * 
	 * @param id
	 *            Unique identifier of the particular customer.
	 * @return Whether success of the operation
	 */
	@RequestMapping(value = "delete/{id}", method = RequestMethod.GET)
	public @ResponseBody Status deleteCustomer(@PathVariable("id") long id) {
		logger.debug("Delegating to service to delete Customer" + id);
		try {
			dataServices.deleteEntity(id);
			return new Status(1, "Customer deleted Successfully !");
		} catch (Exception exe) {
			logger.debug("Customer wasn't deleted " + id);
			return new Status(0, exe.toString());
		}

	}
}
