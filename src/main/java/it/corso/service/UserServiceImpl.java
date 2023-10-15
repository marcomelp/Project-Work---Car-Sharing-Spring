package it.corso.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.dao.AddressDao;
import it.corso.dao.AdminDao;
import it.corso.dao.PaymentDao;
import it.corso.dao.UserDao;
import it.corso.dto.UserDto;
import it.corso.helper.ResponseManager;
import it.corso.helper.SecurityManager;
import it.corso.model.Address;
import it.corso.model.Payment;
import it.corso.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private ResponseManager responseManager;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private PaymentDao paymentDao;
	
	private ModelMapper mapper = new ModelMapper();
	
	@Override
	public ObjectNode userRegistration(User user) {
		if(userDao.findByMail(user.getMail()) != null)
			return responseManager.getResponse(406, "existing mail");
		//se l'username non esiste passo alla registrazione dell'utente
		user.setPassword(SecurityManager.encode(user.getPassword()));
		userDao.save(user);
		return responseManager.getResponse(201, "user registrated");
	}

	@Override
	public ObjectNode userLogin(User user) {
		User existing = userDao.findByMailAndPassword(user.getMail(), SecurityManager.encode(user.getPassword()));
		if(existing == null)
			return responseManager.getResponse(401, "Not Authorized");
		
		existing.setAuthToken(SecurityManager.generateToken(existing.getMail(), false));
		userDao.save(existing);
		return responseManager.getResponse(existing.getId(), existing.getAuthToken());
	}

	@Override
	public ObjectNode userLogout(String token) {
		User existing = userDao.findByAuthToken(token);
		if(existing==null)
			return responseManager.getResponse(401, "non authorized");
		existing.setAuthToken(null);
		userDao.save(existing);
		return responseManager.getResponse(202, "logout accepted");
	}

	//da verificare getUsers
	
	@Override
	public List<UserDto> getUsers() {
		List<UserDto> userDto = new ArrayList<>();
		List<User> users = (List<User>) userDao.findAll();
		users.forEach(c ->userDto.add(mapper.map(c, UserDto.class)));
		return userDto;
	}

	@Override
	public UserDto getUserData(String token) {
		User user = userDao.findByAuthToken(token);
		if(user == null)
			return new UserDto();
		UserDto userDto = mapper.map(user, UserDto.class);
		userDto.setPassword(SecurityManager.decode(userDto.getPassword()));
		return userDto;
	}

	@Override
	public ObjectNode userUpdate(User user, String token) {
		
		if(userDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		Optional<User> userOptional = userDao.findById(user.getId());
		if(!userOptional.isPresent())
			return responseManager.getResponse(404, "User Not Found");
		Optional<Address> addressOptional = addressDao.findById(user.getAddress().getId());
		if(!addressOptional.isPresent())
			return responseManager.getResponse(404, "Address Info Not Found");
		Optional<Payment> paymentOptional = paymentDao.findById(user.getPayment().getId());
		if(!paymentOptional.isPresent())
			return responseManager.getResponse(404, "Payment Info Not Found");
		User existing = userOptional.get();
		Address indirizzo = addressOptional.get();
		Payment pagamento = paymentOptional.get();
		indirizzo.setStreet(user.getAddress().getStreet());
		indirizzo.setCivic(user.getAddress().getCivic());
		indirizzo.setCap(user.getAddress().getCap());
		indirizzo.setTown(user.getAddress().getTown());
		indirizzo.setProvince(user.getAddress().getProvince());
		pagamento.setType(user.getPayment().getType());
		pagamento.setCardNumber(user.getPayment().getCardNumber());
		pagamento.setHolder(user.getPayment().getHolder());
		pagamento.setExpiration(user.getPayment().getExpiration());
		existing.setName(user.getName());
		existing.setLastname(user.getLastname());
		existing.setTaxCode(user.getTaxCode());
		existing.setLicenseNumber(user.getLicenseNumber());
		existing.setMail(user.getMail());
		existing.setPassword(SecurityManager.encode(user.getPassword()));
		existing.setLicenseImage(user.getLicenseImage());
		existing.setAddress(indirizzo);
		existing.setPayment(pagamento);
		userDao.save(existing);
		return responseManager.getResponse(202, "User Data Updated");
	}

}
