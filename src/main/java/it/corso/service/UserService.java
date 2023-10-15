package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dto.UserDto;
import it.corso.model.User;

public interface UserService {

	ObjectNode userRegistration(User user);
	ObjectNode userLogin(User user);
	ObjectNode userLogout(String token);
	List<UserDto> getUsers();
	UserDto getUserData(String token);
	ObjectNode userUpdate(User user, String token);
	
}
