package com.edu.Banking_app.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.Banking_app.entity.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {

	@Query(value = "select t from Transaction t where t.account.user.id = ?1 and t.amount between ?2 and ?3")
	public List<Transaction> fetchAllTransactionsByUserIdAmountBetween(long userId,double st,double end);
	
	@Query(value = "select t from Transaction t where t.account.user.id = ?1 and t.date between ?2 and ?3")
	public List<Transaction> fetchAllTransactionsByUserIdDateBetween(long userId,LocalDateTime st,LocalDateTime end);

	@Query(value = "select t from Transaction t where t.account.user.id = ?1")
	public List<Transaction> fetchAllTransactionByUserIdOrderByDate(long userId,Pageable pageable);
	
}
