package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.RentalDto;
import it.corso.model.Rental;

public interface RentalService {

	ObjectNode rentalRegistration(Rental rental, String token);
	List<RentalDto> getRentals();
	ObjectNode rentalRemoval(int id, String token);
	
}
