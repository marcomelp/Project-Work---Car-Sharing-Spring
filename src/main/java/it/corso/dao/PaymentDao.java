package it.corso.dao;

import org.springframework.data.repository.CrudRepository;

import it.corso.model.Payment;

public interface PaymentDao extends CrudRepository<Payment, Integer>{

}
