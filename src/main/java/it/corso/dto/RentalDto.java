package it.corso.dto;

import java.time.LocalDateTime;


public class RentalDto {

	private int id;
	private LocalDateTime pickupTiming;
	private LocalDateTime returnTiming;
	private double amount;
	private char state; // P = prenotato, C = in corso, T = terminato
	private UserDto user;
	private RentalVehicleDto vehicle;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public LocalDateTime getPickupTiming() {
		return pickupTiming;
	}
	public void setPickupTiming(LocalDateTime pickupTiming) {
		this.pickupTiming = pickupTiming;
	}
	public LocalDateTime getReturnTiming() {
		return returnTiming;
	}
	public void setReturnTiming(LocalDateTime returnTiming) {
		this.returnTiming = returnTiming;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	public RentalVehicleDto getVehicle() {
		return vehicle;
	}
	public void setVehicle(RentalVehicleDto vehicle) {
		this.vehicle = vehicle;
	}
	
}
