/**
 * Test cases for the customer dao
 */
package com.vodafone.crud.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.vodafone.crud.dto.CustomerDto;
import com.vodafone.crud.service.DataServices;
import com.vodafone.crud.util.TestUtil;

/**
 * @author Geeth
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:testContext.xml" })
@WebAppConfiguration
public class RestControllerTest {
	private MockMvc mockMvc;

	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	DataServices dataServices;

	@Before
	public void setup() {
		Mockito.reset(dataServices);
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void add_NewCustomer_ShouldAddCustomerAndReturnAddedCustomer() throws Exception {

		CustomerDto dto = new CustomerDto("David", "Hillcrest", "111-111-111");
		CustomerDto added = new CustomerDto(1, "David", "Hillcrest", "111-111-111");

		when(dataServices.addEntity(dto)).thenReturn(added);

		mockMvc.perform(
				post("/customer/create").contentType(TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(dto))).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

		ArgumentCaptor<CustomerDto> dtoCaptor = ArgumentCaptor.forClass(CustomerDto.class);
		verify(dataServices, times(1)).addEntity((dtoCaptor.capture()));
		verifyNoMoreInteractions(dataServices);

		CustomerDto dtoArgument = dtoCaptor.getValue();
		assertTrue(dtoArgument.getId() == 0);
		assertThat(dtoArgument.getName(), is("David"));
		assertThat(dtoArgument.getAddress(), is("Hillcrest"));
		assertThat(dtoArgument.getPhoneNumber(), is("111-111-111"));

	}

	@Test
	public void update_ExistingCustomer_ShouldUpdateCustomerAndReturnUpdatedCustomer() throws Exception {

		CustomerDto updated = new CustomerDto(1, "Nick", "Glenfield", "22-22-22");

		when(dataServices.updateEntity(updated)).thenReturn(updated);

		mockMvc.perform(
				post("/customer/update").contentType(TestUtil.APPLICATION_JSON_UTF8).content(
						TestUtil.convertObjectToJsonBytes(updated))).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8));

		ArgumentCaptor<CustomerDto> dtoCaptor = ArgumentCaptor.forClass(CustomerDto.class);
		verify(dataServices, times(1)).updateEntity((dtoCaptor.capture()));
		verifyNoMoreInteractions(dataServices);

		CustomerDto dtoArgument = dtoCaptor.getValue();
		assertTrue(dtoArgument.getId() == 1);
		assertThat(dtoArgument.getName(), is("Nick"));
		assertThat(dtoArgument.getAddress(), is("Glenfield"));
		assertThat(dtoArgument.getPhoneNumber(), is("22-22-22"));

	}

	@Test
	public void delete_Customer_ShouldDeleteCustomerAndReturnStaus() throws Exception {

		when(dataServices.deleteEntity(1)).thenReturn(true);
		mockMvc.perform(get("/customer/{id}", 1)).andExpect(status().isOk());

	}

	@Test
	public void findById_CustomerFound_ShouldReturnFoundCustomer() throws Exception {
		CustomerDto first = new CustomerDto(1, "Nick", "Glenfield", "222-222-222");

		when(dataServices.getEntityById(1)).thenReturn(first);
		mockMvc.perform(get("/customer/{id}", 1)).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$id", is(1)))
				.andExpect(jsonPath("$name", is("Nick"))).andExpect(jsonPath("$address", is("Glenfield")))
				.andExpect(jsonPath("$phoneNumber", is("222-222-222")));
	}

	@Test
	public void findAll_CustomersFound_ShouldReturnFoundCustomers() throws Exception {
		CustomerDto first = new CustomerDto(1, "David", "Hillcrest", "111-111-111");

		CustomerDto second = new CustomerDto(2, "Nick", "Glenfield", "222-222-222");

		when(dataServices.getEntityList()).thenReturn(Arrays.asList(first, second));

		mockMvc.perform(get("/customer/list")).andExpect(status().isOk())
				.andExpect(content().contentType(TestUtil.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1))).andExpect(jsonPath("$[0].name", is("David")))
				.andExpect(jsonPath("$[0].address", is("Hillcrest")))
				.andExpect(jsonPath("$[0].phoneNumber", is("111-111-111"))).andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("Nick"))).andExpect(jsonPath("$[1].address", is("Glenfield")))
				.andExpect(jsonPath("$[1].phoneNumber", is("222-222-222")));

		verify(dataServices, times(1)).getEntityList();
		verifyNoMoreInteractions(dataServices);
	}
}
