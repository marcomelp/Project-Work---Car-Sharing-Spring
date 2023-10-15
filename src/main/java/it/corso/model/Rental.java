package it.corso.model;

import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.Valid;

@Entity
@Table(name = "rentals")
public class Rental {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "pickup_timing")
	private LocalDateTime pickupTiming;
	
	@Column(name = "return_timing")
	private LocalDateTime returnTiming;
	
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "state")
	private String state; // P = prenotato, C = in corso, T = terminato
	
	@Valid
	@ManyToOne(cascade = CascadeType.REFRESH) //prima era one to one
	@JoinColumn(name = "user_id",referencedColumnName = "id")
	private User user;
	@ManyToOne(cascade = CascadeType.REFRESH)
	@JoinColumn(name = "vehicle_id",referencedColumnName = "id")
	private Vehicle vehicle;
	
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Vehicle getVehicle() {
		return vehicle;
	}
	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
	
	
}
