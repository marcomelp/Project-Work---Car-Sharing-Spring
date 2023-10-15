package it.corso.service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dao.RentalDao;
import it.corso.dao.UserDao;
import it.corso.dao.VehicleDao;
import it.corso.dto.RentalDto;
import it.corso.helper.ResponseManager;
import it.corso.model.Rental;
import it.corso.model.User;
import it.corso.model.Vehicle;

@Service
public class RentalServiceImpl implements RentalService {

	@Autowired
	private ResponseManager responseManager;
	
	@Autowired
	private RentalDao rentalDao;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private VehicleDao vehicleDao;
	
	private ModelMapper mapper = new ModelMapper();
	

	
	@Override
	public ObjectNode rentalRegistration(Rental rental, String token) {
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return responseManager.getResponse(406, "User Unavailable");
		if(rental.getPickupTiming().isBefore(LocalDateTime.now()) || rental.getPickupTiming().isEqual(LocalDateTime.now()) ||
				rental.getPickupTiming().isEqual(rental.getReturnTiming()) || rental.getPickupTiming().isAfter(rental.getReturnTiming()))
			return responseManager.getResponse(400, "Unacceptable Period");
		Optional<Vehicle> vehicleOptional = vehicleDao.findById(rental.getVehicle().getId());
		if(!vehicleOptional.isPresent())
			return responseManager.getResponse(404, "Not Found");
		Vehicle vehicle = vehicleOptional.get();
		for(Rental r:vehicle.getRentals())
			if(!r.getReturnTiming().isBefore(rental.getPickupTiming()) && 
                    !r.getPickupTiming().isAfter(rental.getReturnTiming()))
				return responseManager.getResponse(400, "Unacceptable Period");
		rental.setUser(user);
        rental.setVehicle(vehicle);
        rental.setState("P");
        rentalDao.save(rental);
        return responseManager.getResponse(201, "Rental Added");
	}

	@Override
	public List<RentalDto> getRentals() {
		
		List<RentalDto> rentalDto = new ArrayList<>();
		List<Rental> rentals = (List<Rental>) rentalDao.findAll();
		rentals.forEach(r ->rentalDto.add(mapper.map(r, RentalDto.class)));
		return rentalDto;
	}

	@Override
	public ObjectNode rentalRemoval(int id, String token) {
		//controlla se l'utente sta svolgendo l'operazione cercandone il token
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<Rental> rentalOptional = rentalDao.findById(id);
		if(!rentalOptional.isPresent())
			return responseManager.getResponse(404, "Rental Not Found");
		if(rentalOptional.isPresent()) {
			Rental rental = rentalOptional.get();
			if(rental.getPickupTiming().isAfter(LocalDateTime.now())) {
				rentalDao.delete(rental);
				return responseManager.getResponse(202, "Rental removed");
			}else if(rental.getPickupTiming().isBefore(LocalDateTime.now()) && rental.getReturnTiming().isAfter(LocalDateTime.now()))
				return responseManager.getResponse(401, "Ongoing Rental");
			}
			rentalDao.delete(rentalOptional.get());
			return responseManager.getResponse(202, "Rental removed");
			
	}


}
