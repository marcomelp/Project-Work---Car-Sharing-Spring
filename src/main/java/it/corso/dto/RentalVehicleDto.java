package it.corso.dto;

import java.time.LocalDateTime;
import it.corso.model.StandoffArea;

public class RentalVehicleDto {

	private int id;
	private String plateNumber;
	private int registrationYear;
	private int lastKm;
	private LocalDateTime lastDetenctionDate;
	private LocalDateTime lastReviewDate;
	private char state; //  l = libero, p = prenotato
	private double rate;
	private StandoffArea standoffArea;
	
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
	public LocalDateTime getLastDetenctionDate() {
		return lastDetenctionDate;
	}
	public void setLastDetenctionDate(LocalDateTime lastDetenctionDate) {
		this.lastDetenctionDate = lastDetenctionDate;
	}
	public LocalDateTime getLastReviewDate() {
		return lastReviewDate;
	}
	public void setLastReviewDate(LocalDateTime lastReviewDate) {
		this.lastReviewDate = lastReviewDate;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
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
	
}
