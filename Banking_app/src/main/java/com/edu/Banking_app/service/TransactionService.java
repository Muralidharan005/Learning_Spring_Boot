package com.edu.Banking_app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.edu.Banking_app.Exception.NotFoundException;
import com.edu.Banking_app.entity.Account;
import com.edu.Banking_app.entity.Transaction;
import com.edu.Banking_app.entity.User;
import com.edu.Banking_app.repository.AccountRepository;
import com.edu.Banking_app.repository.TransactionRepository;
import com.edu.Banking_app.repository.UserRepository;

@Service
public class TransactionService {

	private final AccountRepository accountRepository;
	private final TransactionRepository transactionRepository;
	private final UserRepository userRepository;
	
	
	
	public TransactionService(AccountRepository accountRepository, TransactionRepository transactionRepository,
			UserRepository userRepository) {
		this.accountRepository = accountRepository;
		this.transactionRepository = transactionRepository;
		this.userRepository = userRepository;
	}

	public Transaction AddTransaction(long accountId, Transaction t) {
		Account a = accountRepository.findById(accountId).orElseThrow(
				()-> new NotFoundException("Account", "Account Id: ", accountId));
		
		a.addTransaction(t);
		
		return transactionRepository.save(t);
	}
	
	public String deleteTransaction(long accountId,long transactionId) {
		Account a = accountRepository.findById(accountId).orElseThrow(
				()-> new NotFoundException("Account ","Account Id", accountId));
		Transaction t = transactionRepository.findById(transactionId).orElseThrow(
				()-> new NotFoundException("Transaction","Transaction Id ", transactionId));
		
		a.removeTransaction(t);
		
		transactionRepository.delete(t);
		
		return "Transaction Deleted";
	}
	
	public Transaction updateTransaction(long transactionId, Transaction newTrans) {
		Transaction oldTrans = transactionRepository.findById(transactionId).orElseThrow(
				()-> new NotFoundException("Transaction","Transaction Id: ", transactionId));
		
		oldTrans.setAmount(newTrans.getAmount());
		oldTrans.setDate(newTrans.getDate());
		oldTrans.setType(newTrans.getType());
		
		return transactionRepository.save(oldTrans);
	}
	
	public List<Transaction> fetchAllTransctionByUserIdAmountBw(long userId,double st,double end){
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User ", "User Id ", userId));
		
		return transactionRepository.fetchAllTransactionsByUserIdAmountBetween(u.getId(),st,end);
		}
	
	public List<Transaction> fetchAllTransctionByUserIdDateBw(long userId,LocalDateTime st,LocalDateTime end){
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User ", "User Id ", userId));
		
		return transactionRepository.fetchAllTransactionsByUserIdDateBetween(u.getId(),st,end);
		}
	
	public List<Transaction> fetchAllTransactionByUserIdOrderByDate(long userId,int pageNumber, int pageSize){
		User u = userRepository.findById(userId).orElseThrow(()-> new NotFoundException("User","User Id " ,userId));
		
		Sort sort = Sort.by("date").ascending();
		
		Pageable pageable = PageRequest.of(pageNumber, pageSize, sort);
		
		return transactionRepository.fetchAllTransactionByUserIdOrderByDate(u.getId(), pageable);
	}
}
