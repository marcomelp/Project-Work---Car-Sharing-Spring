package it.corso.controller;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.corso.dto.VehicleDto;
import it.corso.model.Rental;
import it.corso.model.Vehicle;
import it.corso.service.VehicleService;

@RestController
@RequestMapping("/carsharing/vehicle")
@CrossOrigin(origins= {"*"})
@Tag(name="Vehicle Controller", description = "Vehicle management features")
public class VehicleController 
{
	@Autowired
	private VehicleService vehicleService;

	// endpoint #1: registrazione veicolo localhost:8080/carsharing/vehicle/reg/{admin token}
	@PostMapping("/reg/{tkn}")
	public ResponseEntity<ObjectNode> vehicleRegistration(@RequestBody Vehicle vehicle, @PathVariable("tkn") String token)
	{
		ObjectNode response = vehicleService.vehicleRegistration(vehicle, token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}

	// endpoint #2: eliminazione veicolo localhost:8080/carsharing/vehicle/delete/{vehicle id}/{admin token}
	@DeleteMapping("/delete/{id}/{tkn}")
	public ResponseEntity<ObjectNode> vehicleRemoval(@PathVariable("id") int id, @PathVariable("tkn") String token)
	{
		ObjectNode response = vehicleService.vehicleRemoval(id,token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	//endpoint #4: elenco veicoli localhost:8080/carsharing/vehicle/get
	@GetMapping("/get")
	public ResponseEntity<List<VehicleDto>> getVehicles()
	{
		List<VehicleDto> response = vehicleService.getVehicles();
		return new ResponseEntity<List<VehicleDto>>(response, HttpStatus.OK);
	}
	
	//endpoint #5: elenco veicoli per fascia oraria localhost:8080/carsharing/vehicle/gethour
	@PutMapping("/gethour/{tkn}")
	public ResponseEntity<List<VehicleDto>> getAvailableVehicles(@RequestBody Rental rental, @PathVariable("tkn") String token)
	{
		List<VehicleDto> response = vehicleService.getAvailableVehicles(rental, token);
		return new ResponseEntity<List<VehicleDto>>(response, HttpStatus.OK);
	}


}
