package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Admin;

public interface AdminDao extends CrudRepository<Admin, Integer>{

	Admin findByUsernameAndPassword(String username, String password);
	Admin findByAuthToken(String authToken);
	Admin findByUsername(String username);
	
}
