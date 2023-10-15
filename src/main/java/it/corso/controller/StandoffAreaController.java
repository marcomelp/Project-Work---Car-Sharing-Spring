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
import it.corso.model.StandoffArea;
import it.corso.service.StandoffAreaService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carsharing/standoffarea")
@CrossOrigin(origins = {"*"})
public class StandoffAreaController {

	@Autowired
	private StandoffAreaService standoffAreaService;
	
	//endpoint n.1 registrazione area di sosta
	//localhost:8080/carsharing/standoffarea/reg/{tkn}
	@PostMapping("/reg/{tkn}")
	public ResponseEntity<ObjectNode> standoffAreaRegistration(@Valid@RequestBody StandoffArea standoffArea, @PathVariable("tkn") String token) {
		
		ObjectNode response = standoffAreaService.standoffAreaRegistration(standoffArea, token);
		return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint n.2 lista zone di sosta
	//localhost:8080/carsharing/standoffarea/get
	@GetMapping("/get")
	public ResponseEntity<List<StandoffArea>> getStandoffAreas(){
		List<StandoffArea> response = standoffAreaService.getStandoffArea();
		return new ResponseEntity<List<StandoffArea>>(response, HttpStatus.OK);
	}
	
	//endpoint n.3 rimozione zona di sosta
	//localhost:8080/carsharing/standoffarea/delete/{standoffArea id}/{admin token}
	@DeleteMapping("/delete/{id}/{tkn}")
	public ResponseEntity<ObjectNode> standoffAreaRemoval(@PathVariable("id") int id, @PathVariable("tkn")String token){
		ObjectNode response = standoffAreaService.standoffAreaRemoval(id, token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint n.4
	//localhost:8080/carsharing/standoffarea/update/{admin token}
	@PutMapping("/update/{tkn}")
	public ResponseEntity<ObjectNode> standoffAreaUpdate(@RequestBody StandoffArea standoffArea, @PathVariable("tkn") String token) {
		ObjectNode response = standoffAreaService.standoffAreaUpdate(standoffArea, token);
		return new ResponseEntity<ObjectNode>(response,HttpStatusCode.valueOf(response.get("code").asInt()));
	}
}
