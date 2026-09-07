package com.edu.Banking_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.edu.Banking_app.entity.Account;
import com.edu.Banking_app.service.AccountService;

@RestController
public class AccountController {
	public final AccountService accountService;

	public AccountController(AccountService accountService) {
		this.accountService = accountService;
	}
	
	@PostMapping("/user/{userId}/addAccount")
	public ResponseEntity<Account> addAccount(@PathVariable long userId, @RequestBody Account a) {
		return new ResponseEntity<Account>(accountService.addAccount(userId, a),HttpStatus.CREATED);
	}
	
	@DeleteMapping("/user/{userId}/account/{accountId}")
	public ResponseEntity<String> deleteAccount(@PathVariable long userId, @PathVariable long accountId) {
		return new ResponseEntity<String>(accountService.deleteAccount(userId, accountId),HttpStatus.OK);
	}
	
	@GetMapping("/user/{userId}/accounts")
	public ResponseEntity<List<Account>> fetchAllAccountByUserId (@PathVariable long userId){
		return new ResponseEntity<List<Account>>(accountService.fetchAllAccountByUserId(userId),HttpStatus.FOUND);
	}
	
	@PutMapping("/updateAccount/{accountId}")
	public ResponseEntity<Account> updateById(@PathVariable long accountId,@RequestBody Account newAc) {
		return new ResponseEntity<Account>(accountService.updateById(accountId, newAc),HttpStatus.OK);
	}
}
