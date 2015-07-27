/**
 * Test cases for the customer dao
 */
package com.vodafone.crud.dao;

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

import com.vodafone.crud.model.Customer;

/**
 * @author Geeth
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
@WebAppConfiguration
public class DataDaoImplTest {

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	DataDao dataDao;

	@Before
	public void setup() {
		Mockito.reset(dataDao);

	}

	@Test
	public void testGetCustomerByIdShouldRetunrnCustomer() {
		Customer added = new Customer();
		added.setId(1);
		added.setName("David");
		added.setAddress("Hillcrest");
		added.setPhoneNumber("111-111-111");

		// simulate Person ID = 1 in the DB
		try {
			when(dataDao.getEntityById(1)).thenReturn(added);
			Customer found = dataDao.getEntityById(1);
			assertEquals(found.getId(), 1L);
			assertEquals(found.getName(), "David");
			assertEquals(found.getAddress(), "Hillcrest");
			assertEquals(found.getPhoneNumber(), "111-111-111");
			verify(dataDao).getEntityById(1);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testAddCustomerShouldReturnAddedCustomer() {

		Customer customer = new Customer();
		customer.setName("David");
		customer.setAddress("Hillcrest");
		customer.setPhoneNumber("111-111-111");

		Customer added = new Customer();
		added.setId(1);
		added.setName("David");
		added.setAddress("Hillcrest");
		added.setPhoneNumber("111-111-111");

		try {
			when(dataDao.addEntity(customer)).thenReturn(added);
			Customer newDto = dataDao.addEntity(customer);
			assertEquals(newDto.getId(), 1L);
			assertEquals(newDto.getName(), "David");
			assertEquals(newDto.getAddress(), "Hillcrest");
			assertEquals(newDto.getPhoneNumber(), "111-111-111");
			verify(dataDao).addEntity(customer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testUpdateCustomerShouldReturnUpdateCustomer() {

		Customer customer = new Customer();
		customer.setId(1);
		customer.setName("Nick");
		customer.setAddress("Glenfield");
		customer.setPhoneNumber("22-22-22");

		Customer updated = new Customer();
		updated.setId(1);
		updated.setName("David");
		updated.setAddress("Hillcrest");
		updated.setPhoneNumber("111-111-111");

		try {
			when(dataDao.updateEntity(customer)).thenReturn(updated);
			Customer newDto = dataDao.updateEntity(customer);
			assertEquals(newDto.getId(), 1L);
			assertEquals(newDto.getName(), "David");
			assertEquals(newDto.getAddress(), "Hillcrest");
			assertEquals(newDto.getPhoneNumber(), "111-111-111");
			verify(dataDao).updateEntity(customer);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testDleteCustomerShouldReturnStatus() {

		try {
			when(dataDao.deleteEntity(1)).thenReturn(true);
			boolean status = dataDao.deleteEntity(1);
			assertTrue(status);

			verify(dataDao).deleteEntity(1);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	@Test
	public void testFindAllCustomersShouldReturnAllCustomers() {

		Customer first = new Customer();
		first.setId(1);
		first.setName("Nick");
		first.setAddress("Glenfield");
		first.setPhoneNumber("22-22-22");

		Customer second = new Customer();
		second.setId(2);
		second.setName("David");
		second.setAddress("Hillcrest");
		second.setPhoneNumber("111-111-111");
		List<Customer> list = new ArrayList<Customer>();
		list.add(first);
		list.add(second);
		try {
			when(dataDao.getEntityList()).thenReturn(list);
			List<Customer> found = dataDao.getEntityList();
			assertEquals(found.get(0).getId(), 1L);
			assertEquals(found.get(0).getName(), "Nick");
			assertEquals(found.get(0).getAddress(), "Glenfield");
			assertEquals(found.get(0).getPhoneNumber(), "22-22-22");
			assertEquals(found.get(1).getId(), 2L);
			assertEquals(found.get(1).getName(), "David");
			assertEquals(found.get(1).getAddress(), "Hillcrest");
			assertEquals(found.get(1).getPhoneNumber(), "111-111-111");
			verify(dataDao).getEntityList();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
}
