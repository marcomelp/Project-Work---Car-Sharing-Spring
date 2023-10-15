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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dto.RentalDto;
import it.corso.model.Rental;
import it.corso.service.RentalService;

@RestController
@RequestMapping("/carsharing/rental")
@CrossOrigin(origins = {"*"})
public class RentalController {
	
	@Autowired
	private RentalService rentalService;
	
	//endpoint numero 1: registrazione rental localhost:8080/carsharing/rental/reg/(user token)
		@PostMapping("/reg/{tkn}")
		public ResponseEntity<ObjectNode> rentalRegistration(@RequestBody Rental rental, @PathVariable("tkn") String token){
			ObjectNode response = rentalService.rentalRegistration(rental, token);
			return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
		}
		
		//endpoint numero 2: eliminazione rental localhost:8080/carsharing/rental/delete/(rental id)/(user token)
		@DeleteMapping("/delete/{id}/{tkn}")
		public ResponseEntity<ObjectNode>rentalRemoval(@PathVariable("id") int id, @PathVariable("tkn") String token){
			ObjectNode response = rentalService.rentalRemoval(id, token);
			return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
		}
		
		//endpoint numero 3: elenco rental localhost:8080/carsharing/rental/get
		@GetMapping("/get")
		public ResponseEntity<List<RentalDto>> getRentals(){
			List<RentalDto> response = rentalService.getRentals();
			return new ResponseEntity<List<RentalDto>>(response, HttpStatus.OK);
		}
		
}
