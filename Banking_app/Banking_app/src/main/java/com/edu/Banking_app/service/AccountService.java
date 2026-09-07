package com.edu.Banking_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.Banking_app.Exception.NotFoundException;
import com.edu.Banking_app.entity.Account;
import com.edu.Banking_app.entity.User;
import com.edu.Banking_app.repository.AccountRepository;
import com.edu.Banking_app.repository.UserRepository;

@Service
public class AccountService {

	public final UserRepository userRepository;
	public final AccountRepository accountRepository;
	
	public AccountService(UserRepository userRepository, AccountRepository accountRepository) {
		this.userRepository = userRepository;
		this.accountRepository = accountRepository;
	}
	
	public Account addAccount (long userId, Account a) {
		User u = userRepository.findById(userId).orElseThrow(
				() -> new NotFoundException("User","User id", userId));
		
		u.addAccount(a);
		return accountRepository.save(a);
	}
	
	public String deleteAccount (long userId,long accountId) {
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User","User Id", userId));
		
		Account a = accountRepository.findById(accountId).orElseThrow(
				()-> new NotFoundException("Account","Account Id", accountId));
		
		u.removeAccount(a);
		accountRepository.delete(a);
		return "Account deleted";
	}
	
	public List<Account> fetchAllAccountByUserId (long userId){
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User","User Id", userId));
		return accountRepository.fetchAccountByUserId(u.getId());
	}
	
	public Account updateById(long accountId,Account newAc) {
//		User u = userRepository.findById(userId).orElseThrow(
//				()-> new NotFoundException("User","User id", userId));
//		List<Account> l = accountRepository.fetchAccountByUserId(userId);
		
		Account oldAc = accountRepository.findById(accountId).orElseThrow(
				()-> new NotFoundException("Account ","Account Id", accountId));
		
		oldAc.setAccountNo(newAc.getAccountNo());
		oldAc.setBalance(newAc.getBalance());
		
		return accountRepository.save(oldAc);
	}
}
