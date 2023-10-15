package it.corso.service;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.fasterxml.jackson.databind.node.ObjectNode;
import it.corso.dao.AdminDao;
import it.corso.dao.StandoffAreaDao;
import it.corso.helper.ResponseManager;
import it.corso.model.StandoffArea;

@Service
public class StandoffAreaServiceImpl implements StandoffAreaService {

	@Autowired
	private StandoffAreaDao standoffAreaDao;
	
	@Autowired
	private ResponseManager responseManager;
	
	@Autowired
	private AdminDao adminDao;
	
	public ObjectNode standoffAreaRegistration(StandoffArea standoffArea, String token)
	{
		if (adminDao.findByAuthToken(token) == null) {
            return responseManager.getResponse (401, "Not Authorized");
		}
	    if(standoffAreaDao.findByCode(standoffArea.getCode()) != null)
	    	return responseManager.getResponse(406, "Existing Standoff Area");
	    //se l'area di sosota non esiste
	    //standoffArea.setCode(token);
	    standoffArea.setState("L");
	    standoffAreaDao.save(standoffArea);
		return responseManager.getResponse(201, "Standoff area Added");
	    
	}

	@Override
	public List<StandoffArea> getStandoffArea() {

		List<StandoffArea> standoffAreas = (List<StandoffArea>)standoffAreaDao.findAll();
		return standoffAreas;
	}

	@Override
	public ObjectNode standoffAreaRemoval(int id, String token) {
		if(adminDao.findByAuthToken(token) == null)
			return responseManager.getResponse(401, "Not Authorized");
		
		Optional<StandoffArea> standoffAreaOptional = standoffAreaDao.findById(id);
		if(!standoffAreaOptional.isPresent()) {
            return responseManager.getResponse(404, "Standoff area Not Found");
        }
		
		StandoffArea existing = standoffAreaOptional.get();
		if(existing.getState().equals("L")) {
			standoffAreaDao.delete(standoffAreaOptional.get());
			return responseManager.getResponse(202, "Standoff area Removed");
		}
		return responseManager.getResponse(406, "Impossible to remove");
	}

	@Override
	public ObjectNode standoffAreaUpdate(StandoffArea standoffArea, String token) {
		if (adminDao.findByAuthToken(token) == null) 
            return responseManager.getResponse (401, "Not Authorized");
		
		Optional<StandoffArea> standoffAreaOptional = standoffAreaDao.findById(standoffArea.getId());
        if(!standoffAreaOptional.isPresent()) 
            return responseManager.getResponse(404, "Standoff area Not Found");
        
        
        StandoffArea existing = standoffAreaOptional.get();
        if(existing.getState().equals("L")) { //libero
        	
        	existing.setCode(standoffArea.getCode());
        	existing.setStreet(standoffArea.getStreet());
        	existing.setCivic(standoffArea.getCivic());
        	existing.setState(standoffArea.getState());
        		standoffAreaDao.save(existing);
        		return responseManager.getResponse(200, "Standoff area Updated");
        }
        return responseManager.getResponse(406, "Impossible to update");
        
	}

}
