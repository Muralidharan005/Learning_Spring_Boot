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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.Banking_app.entity.User;
import com.edu.Banking_app.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {

	private final UserService userService;
	
	
	public UserController(UserService userService) {
		this.userService = userService;
	}

	@PostMapping("/saveData")
	public ResponseEntity<User> saveData(@Valid @RequestBody User u) {
		return new ResponseEntity<User>(userService.saveData(u),HttpStatus.CREATED);
	}
	
	@PostMapping("/saveAllData")
	public ResponseEntity<List<User>> saveAllData(@Valid @RequestBody List<User> u){
		return new ResponseEntity<List<User>>( userService.saveAllData(u),HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<User> fetchById(@PathVariable int id) {
		return new ResponseEntity<User>(userService.fetchById(id),HttpStatus.FOUND);
	}
	
	@GetMapping("/fetchAll")
	public ResponseEntity<List<User>> fetchAll(){
		return new ResponseEntity<List<User>>(userService.fetchAll(),HttpStatus.FOUND);
	}
	
	@PutMapping("/updateById")
	public ResponseEntity<String> updateById(@RequestParam int id,@Valid @RequestBody User u) {
		return new ResponseEntity<String>(userService.updateById(id, u),HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<String> deleteById(@PathVariable int id) {
		return new ResponseEntity<String>(userService.deleteById(id),HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAll")
	public ResponseEntity<String> deleteAll() {
		return new ResponseEntity<String>(userService.deleteAll(),HttpStatus.NO_CONTENT);
	}
}
