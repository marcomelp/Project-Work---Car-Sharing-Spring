package it.corso.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.AdminDao;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.Admin;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ResponseManager responseManager;
	
	@Override
	public ObjectNode adminLogin(Admin admin) {
		Admin existing = adminDao.findByUsernameAndPassword(admin.getUsername(), SecurityManager.encode(admin.getPassword()));
		if(existing == null)
			return responseManager.getResponse(401, "not authorized");
		existing.setAuthToken(SecurityManager.generateToken(existing.getUsername(), true));
		adminDao.save(existing);
		return responseManager.getResponse(202, existing.getAuthToken());
	}

	@Override
	public ObjectNode adminLogout(String token) {
		Admin existing  = adminDao.findByAuthToken(token);
		if(existing == null)
			return responseManager.getResponse(401, "not authorized");
		existing.setAuthToken(null);
		adminDao.save(existing);
		return responseManager.getResponse(202, "logout accepted");
	}

	@Override
	public ObjectNode adminRegistration(Admin admin) {
		if(adminDao.findByUsername(admin.getUsername()) != null)
			return responseManager.getResponse(406, "existing mail");
		//se l'username non esiste passo alla registrazione dell'utente
		admin.setPassword(SecurityManager.encode(admin.getPassword()));
		adminDao.save(admin);
		return responseManager.getResponse(201, "user registrated");
	}

}
