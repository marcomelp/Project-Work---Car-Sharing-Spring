package it.corso.service;

import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;

import it.corso.model.StandoffArea;

public interface StandoffAreaService {

	ObjectNode standoffAreaRegistration(StandoffArea standoffArea, String token);
	List<StandoffArea> getStandoffArea();
	ObjectNode standoffAreaRemoval(int id, String token);
	ObjectNode standoffAreaUpdate(StandoffArea standoffArea, String token);
	
}
