package edu.com.User_app.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import edu.com.User_app.Entity.User;
import edu.com.User_app.Service.UserService;
import jakarta.validation.Valid;

@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/saveData")
	public User saveUser(@Valid @RequestBody User u) {
		return userService.saveUser(u);
	}
	
	@PostMapping("/saveAllData")
	public List<User> saveAllUser(@Valid @RequestBody List<User> u) {
		return userService.saveAllUser(u);
	}
	
	@GetMapping("/getById/{id}")
	public User fetchById(@PathVariable int id) {
		return userService.fetchById(id);
	}
	
	@GetMapping("/fetchAll")
	public List<User> fetchAll(){
		return userService.fetchAll();
	}
	
	@PutMapping("/updateById/{id}")
	public String UpdateById(@PathVariable int id,@Valid@RequestBody User newUser) {
		return userService.UpdateById(id,newUser);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String DeleteById(@PathVariable int id) {
		return userService.DeleteById(id);
	}
	
	@GetMapping("/count")
	public long countData() {
		return userService.countData();
	}
	
	@GetMapping("/getByMobile/{mobile}")
	public List<User> fetchByMobile(@PathVariable long mobile){
		return userService.fetchByMobile(mobile);
	}
	
	@PutMapping("/updateImg/{id}")
	public String updateImg(@PathVariable int id,@RequestBody MultipartFile file) throws Exception{
		return userService.updateImg(id, file);
	}
	
	@GetMapping("/fetchAllBySize")
	public List<User> fetchAllUserByPage(@RequestParam(required = false,defaultValue = "0") int pageNo,@RequestParam(required = false ,defaultValue = "10") int pageSize){
		return userService.fetchAllUserByPage(pageNo, pageSize);
	}
	
	@GetMapping("/SortAllUser")
	public List<User> sortAllUser(@RequestParam(required = false, defaultValue = "id") String sortBy, @RequestParam(required = false, defaultValue = "asc") String dir){
		return userService.sortAllUser(sortBy, dir);
	}
}
