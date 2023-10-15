package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "street")
	@Pattern(regexp = "[a-zA-Z\\sàèìòù'0-9.]{1,50}", message = "error on street field")
	private String street;
	
	@Column(name = "civic")
	@Pattern(regexp = "[a-zA-Z\\s0-9/_-]{1,20}", message = "error on civic field")
	private String civic;

	@Column(name = "cap")
	@Pattern(regexp = "[0-9]{5}", message = "error on cap field")
	private String cap;
	
	@Column(name = "town")
	@Pattern(regexp = "[a-zA-Z\\sàèìòù'.]{1,50}", message = "error on town field")
	private String town;
	
	@Column(name = "province")
	@Pattern(regexp = "[a-zA-Z]{2}", message = "error on province field")
	private String province;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCap() {
		return cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public String getTown() {
		return town;
	}

	public void setTown(String town) {
		this.town = town;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}
	
}
