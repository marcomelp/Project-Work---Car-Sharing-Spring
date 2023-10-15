package it.corso.dto;

import java.util.List;

import it.corso.model.Address;

public class UserDto {

	private int id;
	private String name;
	private String lastname;
	private String taxCode;
	private String licenseNumber;
	private String mail;
	private String password;
	private String licenseImage;
	private String authToken;
	private Address address;
	private UserPaymentDto payment;
	private List<RentalUserDto> rentals;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getTaxCode() {
		return taxCode;
	}
	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}
	public String getLicenseNumber() {
		return licenseNumber;
	}
	public void setLicenseNumber(String licenseNumber) {
		this.licenseNumber = licenseNumber;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getLicenseImage() {
		return licenseImage;
	}
	public void setLicenseImage(String licenseImage) {
		this.licenseImage = licenseImage;
	}
	public String getAuthToken() {
		return authToken;
	}
	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public UserPaymentDto getPayment() {
		return payment;
	}
	public void setPayment(UserPaymentDto payment) {
		this.payment = payment;
	}
	public List<RentalUserDto> getRentals() {
		return rentals;
	}
	public void setRentals(List<RentalUserDto> rentals) {
		this.rentals = rentals;
	}
	
}
