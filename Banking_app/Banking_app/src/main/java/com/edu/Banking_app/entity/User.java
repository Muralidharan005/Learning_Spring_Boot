package com.edu.Banking_app.entity;

import java.util.Set;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "user_table")
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="user_id")
	private long id;
	@Length(min=3,max=25, message="Enter name within the range")
	private String name;
	@Email(message = "Enter valid Email")
	private String email;
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid password")
	private String password;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Loan> loans;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "user")
	private Set<Account> accounts;
	
	public void addLoan(Loan l) {
		loans.add(l);
		l.setUser(this);
	}
	
	public void removeLoan(Loan l) {
		loans.remove(l);
		l.setUser(null);
	}
	
	public void addAccount(Account a) {
		accounts.add(a);
		a.setUser(this);
	}

	public void removeAccount(Account a) {
		accounts.remove(a);
		a.setUser(null);
	}
}
