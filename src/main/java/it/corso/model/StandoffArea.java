package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "standoff_areas")
public class StandoffArea {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "^\\d{3}[A-Za-z]$", message = "Invalid code")
	@Column(name = "code")
	private String code;
	
	@Pattern(regexp = "[a-zA-Z0-9\\sàèìòù'.]{1,50}", message = "Error on street field")
	@Column(name = "street")
	private String street;
	
	@Pattern(regexp = "[a-zA-Z0-9/_-]{1,20}", message = "Error on civic field")
	@Column(name = "civic")
	private String civic;
	
	@Column(name = "state")
	private String state; // O occupato, L libero
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCivic() {
		return civic;
	}
	public void setCivic(String civic) {
		this.civic = civic;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	
}
