package com.edu.Banking_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.edu.Banking_app.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(value = "select a from Account a where a.user.id =?1")
	public List<Account> fetchAccountByUserId(long userId);
}
