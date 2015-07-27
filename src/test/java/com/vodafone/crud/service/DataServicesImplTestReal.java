/**
 * 
 */
package com.vodafone.crud.service;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.context.WebApplicationContext;

import com.vodafone.crud.dto.CustomerDto;

/**
 * @author Geeth
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring-config.xml"})  
@WebAppConfiguration
public class DataServicesImplTestReal {
	@Autowired
	protected WebApplicationContext webApplicationContext;

	@Autowired
	private DataServices dataServices;
	


	@Test
	public void addEntityTest()  {
		CustomerDto dto = new CustomerDto();
		dto.setName("ABC11");
		dto.setAddress("Address11");
		dto.setPhoneNumber("324324343411");
		try {
			
			dto=dataServices.addEntity(dto);
			System.out.println(dto.toString());
			assertNotNull(dto);
		} catch (Exception e) {
			e.printStackTrace();
			assert(false);
		}

	}
	
	@Test
	public void updateEntityTest()  {
		CustomerDto dto = new CustomerDto();
		dto.setId(1);
		dto.setName("ABC");
		dto.setAddress("Address");
		dto.setPhoneNumber("3243243434");
		try {
			dataServices.updateEntity(dto);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	@Test
	public void deleteEntityTest()  {

		try {
			dataServices.deleteEntity(3);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	@Test
	public void getEntityListTest()  {

		try {
			List<CustomerDto> list =dataServices.getEntityList();
			for(CustomerDto dto:list){
				System.out.println(dto.getId());
				System.out.println(dto.getName());
				System.out.println(dto.getAddress());
				System.out.println(dto.getPhoneNumber());
				System.out.println("**************************");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}
}
