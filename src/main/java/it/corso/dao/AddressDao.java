package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Address;

public interface AddressDao extends CrudRepository<Address, Integer>{
	
}
