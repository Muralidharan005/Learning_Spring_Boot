package com.edu.Banking_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.Banking_app.entity.Loan;
import com.edu.Banking_app.service.LoanService;

@RestController
public class LoanController {
	
	private final LoanService loanService;
	
	public LoanController(LoanService loanService) {
		this.loanService = loanService;
	}
	
	@PostMapping("users/{userId}/loans")
	public ResponseEntity<Loan> applyLoan(@PathVariable long userId,@RequestBody Loan loan) {
		return new ResponseEntity<Loan> (loanService.applyLoan(userId, loan),HttpStatus.CREATED);
	}
	
	@GetMapping("/getAllLoans")
	public ResponseEntity<List<Loan>> fetchAllLoan(){
		return new ResponseEntity<List<Loan>>(loanService.fetchAllLoan(),HttpStatus.FOUND);
	}
	
	@DeleteMapping("/user/{userId}/loan/{loanId}")
	public ResponseEntity<String> deleteLoan(@PathVariable long userId,@PathVariable long loanId) {
		return new ResponseEntity<String>(loanService.deleteLoan(userId, loanId),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/loans")
	public ResponseEntity<List<Loan>> fetchAllLoanByUserId(@PathVariable long userId){
		return new ResponseEntity<List<Loan>>(loanService.fetchAllLoanByUserId(userId),HttpStatus.FOUND);
	}
	
}
