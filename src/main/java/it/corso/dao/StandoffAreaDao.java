package it.corso.dao;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import it.corso.model.StandoffArea;

public interface StandoffAreaDao extends CrudRepository<StandoffArea, Integer>{
	
	StandoffArea findByCode(String code);
	List<StandoffArea> findByState(String state);
}