package com.edu.Banking_app.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.Banking_app.entity.Transaction;
import com.edu.Banking_app.service.TransactionService;

@RestController
public class TransactionController {

	@Autowired
	private TransactionService transactionService;
	
	@PostMapping("account/{accountId}/addTransaction")
	public ResponseEntity<Transaction> addTransaction(@PathVariable long accountId, @RequestBody Transaction t) {
		return new ResponseEntity<Transaction>(transactionService.AddTransaction(accountId, t),HttpStatus.CREATED);
	}
	
	@DeleteMapping("account/{accountId}/deleteTransaction/{transactionId}")
	public ResponseEntity<String> deleteTransaction(@PathVariable long accountId ,@PathVariable long tranactionId) {
		return new ResponseEntity<String>(transactionService.deleteTransaction(accountId,tranactionId),HttpStatus.OK);
	}
	
	@PutMapping("account/{transactionId}/updateTransaction")
	public ResponseEntity<Transaction> updateTransaction(@PathVariable long transactionId, Transaction t) {
		return new ResponseEntity<Transaction>(transactionService.updateTransaction(transactionId, t),HttpStatus.ACCEPTED);
	}
	
	@GetMapping("user/{userId}/amount")
	public ResponseEntity<List<Transaction>> fetchAllTransctionByUserIdAmountBw(@PathVariable long userId,@RequestParam(required = false,defaultValue = "500") double st, @RequestParam(required = false, defaultValue = "10000") double end){
		return new ResponseEntity<List<Transaction>>(transactionService.fetchAllTransctionByUserIdAmountBw(userId, st, end),HttpStatus.FOUND);
	}
	
	@GetMapping("user/{userId}/date")
	public ResponseEntity<List<Transaction>> fetchAllTransctionByUserIdDateBw(@PathVariable long userId,@RequestParam(required = false,defaultValue = "2000-01-01T00:00:00") LocalDateTime st, @RequestParam(required = false, defaultValue = "2026-12-31T23:59:59") LocalDateTime end){
		return new ResponseEntity<List<Transaction>>(transactionService.fetchAllTransctionByUserIdDateBw(userId, st, end),HttpStatus.FOUND);
	}
	
	@GetMapping("user/{userId}/transaction/orderByDate/")
	public ResponseEntity<List<Transaction>> fetchAllTransactionByUserIdOrderByDate(@PathVariable long userId,@RequestParam(required = false,defaultValue = "0") int pageNumber, @RequestParam(required = false, defaultValue = "5") int pageSize){
		return new ResponseEntity<List<Transaction>>(transactionService.fetchAllTransactionByUserIdOrderByDate(userId, pageNumber, pageSize),HttpStatus.FOUND);
	}
}
