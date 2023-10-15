package it.corso.service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.model.Admin;

public interface AdminService {

	ObjectNode adminLogin(Admin admin);
	ObjectNode adminLogout(String token);
	ObjectNode adminRegistration(Admin admin);
	
}
