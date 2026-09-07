package com.edu.Banking_app.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.edu.Banking_app.Exception.NotFoundException;
import com.edu.Banking_app.entity.User;
import com.edu.Banking_app.repository.UserRepository;

@Service
public class UserService {
	
	private final UserRepository userRepository;
	
	
	public UserService(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	public User saveData(User u) {
		return userRepository.save(u);
	}
	
	public List<User> saveAllData(List<User> u){
		return userRepository.saveAll(u);
	}
	
	public User fetchById(long id) {
		return userRepository.findById(id).orElseThrow(
				()-> new NotFoundException("User", "Userid", id));
	}
	
	public List<User> fetchAll(){
		return userRepository.findAll();
	}
	
	public String updateById(long id, User newUser) {
		User exUser = userRepository.findById(id).orElseThrow(
				()->new NotFoundException("User","User id", id));
		exUser.setName(newUser.getName());
		exUser.setEmail(newUser.getEmail());
		exUser.setPassword(newUser.getPassword());
		
		userRepository.save(exUser);
		
		return "Data Updated";
	}
	
	public String deleteById(long id) {
		User u = userRepository.findById(id).orElseThrow(
				()-> new NotFoundException("User","User id " , id));
		
		userRepository.deleteById(u.getId());
		return "Data Deleted";
		
	}
	
	public String deleteAll() {
		userRepository.deleteAll();
		return "All data was Deleted";
	}
}
