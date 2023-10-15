package it.corso.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.helper.PasswordValidationException;
import it.corso.model.Admin;
import it.corso.service.AdminService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/carsharing/admin")
@CrossOrigin(origins = {"*"})
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//endpoint #1 login amministratore localhost:8080/carsharing/admin/login
	@PutMapping("/login")
	public ResponseEntity<ObjectNode> adminLogin(@RequestBody Admin admin){
		ObjectNode response = adminService.adminLogin(admin);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint #2 logout amministratore localhost:8080/carsharing/admin/logout/{admin token}
	@GetMapping("/logout/{tkn}")
	public ResponseEntity<ObjectNode> adminLogout(@PathVariable("tkn") String token){
		ObjectNode response = adminService.adminLogout(token);
		return new ResponseEntity<ObjectNode>(response, HttpStatusCode.valueOf(response.get("code").asInt()));
	}
	
	//endpoint n.3: registrazione cliente localhost:8080/carsharing/admin/reg
    @PostMapping("/reg") // in metodo post
    public ResponseEntity<ObjectNode> adminRegistration(@Valid@RequestBody Admin admin)
    {
        if(Pattern.matches("(?=.\\d)(?=.[a-z])(?=.[A-Z])(?=.[@$!%?&])[A-Za-z\\d@$!%?&]{6,10}", admin.getPassword() ))
            throw new PasswordValidationException();

        ObjectNode response = adminService.adminRegistration(admin); //chi passa questa cosa all'endpoint? con @RequestBody poi metto @PostMapping("/reg")
        return new ResponseEntity<ObjectNode> (response,HttpStatusCode.valueOf(response.get("code").asInt()));
    }
	
	
}
