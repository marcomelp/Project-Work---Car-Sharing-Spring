package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Vehicle;

public interface VehicleDao extends CrudRepository<Vehicle, Integer>{

	Vehicle findByState(String state);
	
}
