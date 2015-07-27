/**
 * Customer service interface
 */
package com.vodafone.crud.service;

import java.util.List;

import com.vodafone.crud.dto.CustomerDto;

/**
 * @author Geeth
 */
public interface DataServices {
	public CustomerDto addEntity(CustomerDto dto) throws Exception;

	public CustomerDto updateEntity(CustomerDto dto) throws Exception;

	public CustomerDto getEntityById(long id) throws Exception;

	public List<CustomerDto> getEntityList() throws Exception;

	public boolean deleteEntity(long id) throws Exception;
}
