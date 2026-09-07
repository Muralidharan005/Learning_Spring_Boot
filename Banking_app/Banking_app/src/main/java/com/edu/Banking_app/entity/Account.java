package com.edu.Banking_app.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private long accountNo;
	private double balance;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "account")
	private Set<Transaction> transactions;
	
	public void addTransaction(Transaction t) {
		transactions.add(t);
		t.setAccount(this);
	}
	
	public void removeTransaction(Transaction t) {
		transactions.remove(t);
		t.setAccount(null);
	}
}
