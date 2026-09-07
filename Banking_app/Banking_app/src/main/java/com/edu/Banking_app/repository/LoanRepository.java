package com.edu.Banking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.Banking_app.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long> {

	@Query(value = "select l from Loan l where l.user.id=?1")
	public List<Loan> fetchAllLoanByUserId(long userId);
}
