package it.corso.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.corso.dto.RentalDto;
import it.corso.model.Rental;

public interface RentalDao extends CrudRepository<Rental, Integer>{
	
	List<Rental> findByState(String state);
	
}
