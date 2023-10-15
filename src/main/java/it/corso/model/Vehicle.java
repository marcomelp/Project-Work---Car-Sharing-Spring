package it.corso.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "vehicles")
public class Vehicle {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Pattern(regexp = "^[A-Z]{2}\\d{3}[A-Z]{2}$", message = "Error on plate_number field")
	@Column(name = "plate_number")
	private String plateNumber;
	
	@Column(name = "registration_year")
	private int registrationYear;
	
	@Column(name = "last_km")
	private int lastKm;
	
	
	@Column(name = "last_detenction_date")
	private LocalDate lastDetenctionDate;
	
	
	@Column(name = "last_review_date")
	private LocalDate lastReviewDate;
	
	@Column(name = "state")
	private String state; //  l = libero, p = prenotato
	
	@Column(name = "rate")
	private double rate;
	
	
	@Valid
	@OneToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "standoff_id",referencedColumnName = "id")
	private StandoffArea standoffArea;
	@OneToMany(
			mappedBy = "vehicle", //fa riferimento all'attributo vehicle in Rental.java
			cascade = CascadeType.REFRESH,
			fetch = FetchType.EAGER,  //se si fa casino, controllare questi
			orphanRemoval = true
			)
	private List<Rental> rentals = new ArrayList<>();
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPlateNumber() {
		return plateNumber;
	}
	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}
	public int getRegistrationYear() {
		return registrationYear;
	}
	public void setRegistrationYear(int registrationYear) {
		this.registrationYear = registrationYear;
	}
	public int getLastKm() {
		return lastKm;
	}
	public void setLastKm(int lastKm) {
		this.lastKm = lastKm;
	}
	public LocalDate getLastDetenctionDate() {
		return lastDetenctionDate;
	}
	public void setLastDetenctionDate(LocalDate lastDetenctionDate) {
		this.lastDetenctionDate = lastDetenctionDate;
	}
	public LocalDate getLastReviewDate() {
		return lastReviewDate;
	}
	public void setLastReviewDate(LocalDate lastReviewDate) {
		this.lastReviewDate = lastReviewDate;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getRate() {
		return rate;
	}
	public void setRate(double rate) {
		this.rate = rate;
	}
	public StandoffArea getStandoffArea() {
		return standoffArea;
	}
	public void setStandoffArea(StandoffArea standoffArea) {
		this.standoffArea = standoffArea;
	}
	public List<Rental> getRentals() {
		return rentals;
	}
	public void setRentals(List<Rental> rentals) {
		this.rentals = rentals;
	}
}
