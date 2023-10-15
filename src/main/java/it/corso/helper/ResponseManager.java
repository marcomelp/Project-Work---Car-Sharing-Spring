package it.corso.helper;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;


@Component
public class ResponseManager {

	private ObjectMapper mapper;
	
	//costruttore
	public ResponseManager()
	{
		mapper = new ObjectMapper();
	}
	
	public ObjectNode getResponse(int code, String message)
	{
		ObjectNode response = mapper.createObjectNode(); // ci permette di creare quest'oggetto
		response.put("code", code);
		response.put("message", message);
		
		return response;
	}
}
