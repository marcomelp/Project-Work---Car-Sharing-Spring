package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.VehicleDto;
import it.corso.model.Rental;
import it.corso.model.Vehicle;

public interface VehicleService {

	ObjectNode vehicleRegistration(Vehicle vehicle, String token);
	ObjectNode vehicleRemoval(int id, String token);
	List<VehicleDto> getVehicles();
	public List<VehicleDto> getAvailableVehicles(Rental rental, String token);
	
}
