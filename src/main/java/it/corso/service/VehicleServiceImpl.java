package it.corso.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.AdminDao;
import it.corso.dao.StandoffAreaDao;
import it.corso.dao.UserDao;
import it.corso.dao.VehicleDao;
import it.corso.dto.VehicleDto;
import it.corso.helper.ResponseManager;
import it.corso.model.Rental;
import it.corso.model.StandoffArea;
import it.corso.model.Vehicle;

@Service
public class VehicleServiceImpl implements VehicleService
{
	@Autowired
	private VehicleDao vehicleDao;
	@Autowired
	private StandoffAreaDao standoffAreaDao;
	@Autowired
	private ResponseManager responseManager;
	@Autowired
	private AdminDao adminDao;
	@Autowired
	private UserDao userDao;
	
	private ModelMapper mapper = new ModelMapper();


	@Override
	public ObjectNode vehicleRegistration(Vehicle vehicle, String token) 
	{
		if(adminDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<StandoffArea> standoffAreaOptional = standoffAreaDao.findById(vehicle.getStandoffArea().getId());
		if(!standoffAreaOptional.isPresent())
			return responseManager.getResponse(404, "Standoff Area not Found");
		vehicle.setState("L");
		StandoffArea standoffArea = vehicle.getStandoffArea();
		standoffArea.setState("O");
		vehicleDao.save(vehicle);
		standoffAreaDao.save(standoffArea);
		return responseManager.getResponse(201, "Vehicle registrated");
		
	}

	@Override
	public ObjectNode vehicleRemoval(int id, String token) 
	{
		if(adminDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<Vehicle> vehicleOptional = vehicleDao.findById(id);
		if(!vehicleOptional.isPresent())
			return responseManager.getResponse(404, "Vehicle not Found");
		Vehicle vehicle = vehicleOptional.get();
		// Verifica se il veicolo è in uno stato non consentito per la rimozione
		String status = vehicle.getState();

		if (status.equals("p") || status.equals("P")) {
			return responseManager.getResponse(400, "Cannot remove a vehicle that is reserved");
		} else if (status.equals("l") || status.equals("L")) {
			// rimozione veicolo
			
			vehicleDao.delete(vehicle);
			StandoffArea standoffArea = vehicle.getStandoffArea();
			standoffArea.setState("L");
			standoffAreaDao.save(standoffArea);
			return responseManager.getResponse(202, "Vehicle Removed");
		} else {
			// Lo stato non è valido
			return responseManager.getResponse(400, "Invalid vehicle status");
		}

	}

	@Override
	public List<VehicleDto> getVehicles() 
	{
		List<VehicleDto> vehicleDto = new ArrayList<>();
		List<Vehicle> vehicles = (List<Vehicle>) vehicleDao.findAll();
		vehicles.forEach(v ->vehicleDto.add(mapper.map(v, VehicleDto.class)));
		return vehicleDto;
		
	}
	
	@Override
	public List<VehicleDto> getAvailableVehicles(Rental rental, String token)
	{
		List<VehicleDto> availablesDto = new ArrayList<>();
		if(userDao.findByAuthToken(token) == null || 
				rental.getPickupTiming().isEqual(rental.getReturnTiming()) || 
				rental.getPickupTiming().isAfter(rental.getReturnTiming()))
			return availablesDto;
		List<Vehicle> vehicles = (List<Vehicle>) vehicleDao.findAll();
		Set<Vehicle> availables = new HashSet<>();
		vehicles.forEach(a -> {
			if(a.getRentals() == null || a.getRentals().size() == 0)
				availables.add(a);
			else
				a.getRentals().forEach(r -> {
					if(r.getState().equals("T") || 
							r.getReturnTiming().isBefore(rental.getPickupTiming()) || 
							r.getPickupTiming().isAfter(rental.getReturnTiming()))
						availables.add(a);
				});
		});
		availables.forEach(a -> availablesDto.add(mapper.map(a, VehicleDto.class)));
		return availablesDto;
	}

}
