package it.corso.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import it.corso.model.StandoffArea;

public class VehicleDto {

	private int id;
	private String plateNumber;
	private int registrationYear;
	private int lastKm;
	private LocalDate lastDetenctionDate;
	private LocalDate lastReviewDate;
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
	
}
