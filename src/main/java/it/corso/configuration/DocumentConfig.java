package it.corso.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

//localhost:8080/swagger-ui.html
@Configuration
public class DocumentConfig {
	
	@Bean // capisce che non è un annotation normale ma che deve occuparsi dell'oggetto, istanziarlo e gestirlo
	public OpenAPI getDocumentAPI() {
		
		OpenAPI api = new OpenAPI();
		Info info = new Info();
		
		info.title("Car Sharing Web Service");
		info.description("RESTful Web Service for Car Sharing Project");
		info.version("1.0.0");
		api.info(info);
		
		return api;
	}
}
