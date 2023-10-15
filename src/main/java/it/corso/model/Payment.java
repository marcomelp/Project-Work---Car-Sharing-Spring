package it.corso.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "payments")
public class Payment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "[a-zA-Z\\s]{1,100}", message = "Error on type field")
	@Column(name = "type")
	private String type;
	
	@Pattern(regexp = "[0-9]{16}", message = "Error on card_number field")
	@Column(name = "card_number")
	private String cardNumber;
	
	@Pattern(regexp = "[a-zA-Z\\s]{1,200}", message = "Error on holder field")
	@Column(name = "holder")
	private String holder;
	
	@Pattern(regexp = "^(0[1-9]|1[0-2])/\\d{2}$", message = "Error on expiration field")
	@Column(name = "expiration")
	private String expiration;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public String getHolder() {
		return holder;
	}
	public void setHolder(String holder) {
		this.holder = holder;
	}
	public String getExpiration() {
		return expiration;
	}
	public void setExpiration(String expiration) {
		this.expiration = expiration;
	}
	
}
