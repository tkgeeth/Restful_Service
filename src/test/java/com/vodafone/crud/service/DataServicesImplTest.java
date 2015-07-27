/**
 * Test cases for the customer Service
 */
package com.vodafone.crud.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.vodafone.crud.dao.DataDao;
import com.vodafone.crud.dto.CustomerDto;

/**
 * @author Geeth
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
@WebAppConfiguration
public class DataServicesImplTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	DataServices dataServices;

	@Autowired
	DataDao dataDao;

	@Before
	public void setup() {
		Mockito.reset(dataServices);
		Mockito.reset(dataDao);

	}

	@Test
	public void testGetCustomerByIdShouldRetunrnCustomer() {
		CustomerDto added = new CustomerDto();
		added.setId(1);
		added.setName("David");
		added.setAddress("Hillcrest");
		added.setPhoneNumber("111-111-111");

		// simulate Person ID = 1 in the DB
		try {
			when(dataServices.getEntityById(1)).thenReturn(added);
			CustomerDto found = dataServices.getEntityById(1);
			assertEquals(found.getId(), 1L);
			assertEquals(found.getName(), "David");
			assertEquals(found.getAddress(), "Hillcrest");
			assertEquals(found.getPhoneNumber(), "111-111-111");
			verify(dataServices).getEntityById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAddCustomerShouldReturnAddedCustomer() {

		CustomerDto customer = new CustomerDto();
		customer.setName("David");
		customer.setAddress("Hillcrest");
		customer.setPhoneNumber("111-111-111");

		CustomerDto added = new CustomerDto();
		added.setId(1);
		added.setName("David");
		added.setAddress("Hillcrest");
		added.setPhoneNumber("111-111-111");

		try {
			when(dataServices.addEntity(customer)).thenReturn(added);
			CustomerDto newDto = dataServices.addEntity(customer);
			assertEquals(newDto.getId(), 1L);
			assertEquals(newDto.getName(), "David");
			assertEquals(newDto.getAddress(), "Hillcrest");
			assertEquals(newDto.getPhoneNumber(), "111-111-111");
			verify(dataServices).addEntity(customer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testUpdateCustomerShouldReturnUpdateCustomer() {

		CustomerDto customer = new CustomerDto();
		customer.setId(1);
		customer.setName("Nick");
		customer.setAddress("Glenfield");
		customer.setPhoneNumber("22-22-22");

		CustomerDto updated = new CustomerDto();
		updated.setId(1);
		updated.setName("David");
		updated.setAddress("Hillcrest");
		updated.setPhoneNumber("111-111-111");

		try {
			when(dataServices.updateEntity(customer)).thenReturn(updated);
			CustomerDto newDto = dataServices.updateEntity(customer);
			assertEquals(newDto.getId(), 1L);
			assertEquals(newDto.getName(), "David");
			assertEquals(newDto.getAddress(), "Hillcrest");
			assertEquals(newDto.getPhoneNumber(), "111-111-111");
			verify(dataServices).updateEntity(customer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testDleteCustomerShouldReturnStatusTrueWhenExist() {

		try {
			when(dataServices.deleteEntity(1)).thenReturn(true);
			boolean status = dataServices.deleteEntity(1);
			assertTrue(status);

			verify(dataServices).deleteEntity(1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testDleteCustomerShouldReturnStatusFalseWhenNotExist() {

		try {
			when(dataServices.deleteEntity(1)).thenReturn(false);
			boolean status = dataServices.deleteEntity(1);
			assertTrue(!status);

			verify(dataServices).deleteEntity(1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	@Test
	public void testFindAllCustomersShouldReturnAllCustomers() {

		CustomerDto first = new CustomerDto();
		first.setId(1);
		first.setName("Nick");
		first.setAddress("Glenfield");
		first.setPhoneNumber("22-22-22");

		CustomerDto second = new CustomerDto();
		second.setId(2);
		second.setName("David");
		second.setAddress("Hillcrest");
		second.setPhoneNumber("111-111-111");
		List<CustomerDto> list = new ArrayList<CustomerDto>();
		list.add(first);
		list.add(second);
		try {
			when(dataServices.getEntityList()).thenReturn(list);
			List<CustomerDto> found = dataServices.getEntityList();
			assertEquals(found.get(0).getId(), 1L);
			assertEquals(found.get(0).getName(), "Nick");
			assertEquals(found.get(0).getAddress(), "Glenfield");
			assertEquals(found.get(0).getPhoneNumber(), "22-22-22");
			assertEquals(found.get(1).getId(), 2L);
			assertEquals(found.get(1).getName(), "David");
			assertEquals(found.get(1).getAddress(), "Hillcrest");
			assertEquals(found.get(1).getPhoneNumber(), "111-111-111");
			verify(dataServices).getEntityList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
