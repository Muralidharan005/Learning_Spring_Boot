package com.edu.Banking_app.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import com.edu.Banking_app.Exception.NotFoundException;
import com.edu.Banking_app.entity.Loan;
import com.edu.Banking_app.entity.User;
import com.edu.Banking_app.repository.LoanRepository;
import com.edu.Banking_app.repository.UserRepository;

@Service
public class LoanService {
	
	private final LoanRepository loanRepository;
	private final UserRepository userRepository;
	
	public LoanService(LoanRepository loanRepository,UserRepository userRepository) {
		this.loanRepository = loanRepository;
		this.userRepository = userRepository;
	}
	
	public Loan applyLoan(long userId, Loan loan) {
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User","User Id", userId));
		u.addLoan(loan);
		loan.setApplyDate(LocalDateTime.now());
		
		return loanRepository.save(loan);
	}
	
	public List<Loan> fetchAllLoan(){
		return loanRepository.findAll();
	}
	
	public String updateLoan(long loanId,Loan newLoan) {
		
		Loan oldLoan = loanRepository.findById(loanId).orElseThrow(
				()-> new NotFoundException("Loan","Loan Id", loanId));
		
		oldLoan.setAmount(newLoan.getAmount());
		oldLoan.setBalance(newLoan.getBalance());
		oldLoan.setApprovalDate(newLoan.getApprovalDate());
		oldLoan.setDisbursementDate(newLoan.getDisbursementDate());
		oldLoan.setStatus(newLoan.getStatus());
		
		loanRepository.save(oldLoan);
		return "Loan data updated ";
	}
	
	public String deleteLoan(long userId,long loanId ) {
		User u = userRepository.findById(userId).orElseThrow(
				()-> new NotFoundException("User","User Id", userId));
		
		Loan l = loanRepository.findById(loanId).orElseThrow(
				()-> new NotFoundException("Loan","Loan Id", loanId));
		
		u.removeLoan(l);
		
		loanRepository.deleteById(loanId);
		
		return "Loan Data Deleted";
	}
	
	public List<Loan> fetchAllLoanByUserId(long userId){
		return loanRepository.fetchAllLoanByUserId(userId);
	}
}
