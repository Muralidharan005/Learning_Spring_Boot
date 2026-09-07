package edu.com.User_app.Service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.com.User_app.Entity.User;
import edu.com.User_app.exception.UserNotFoundException;
import edu.com.User_app.repository.UserRepository;

@Service

public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User u) {
		return userRepository.save(u);
	}
	
	public List<User> saveAllUser(List<User> u) {
		return userRepository.saveAll(u);
	}
	
	public User fetchById(int id) {
		return  userRepository.findById(id).orElseThrow(
				()->new UserNotFoundException("User not found...!!!")
				);
	}
	
	public String UpdateById(int id,User newUser) {
		User exUser = userRepository.findById(id) .orElseThrow(
				() ->new UserNotFoundException("User not found...!!!!")
				);
		
			exUser.setName(newUser.getName());
			exUser.setAge(newUser.getAge());
			exUser.setEmail(newUser.getEmail());
			exUser.setPassword(newUser.getPassword());
			exUser.setGender(newUser.getGender());
			exUser.setMobile(newUser.getMobile());
			exUser.setProfileImg(newUser.getProfileImg());
			userRepository.save(exUser);
			
			return "Data Updated";
		}
	
	public List<User> fetchAll(){
		return userRepository.findAll();
	}
	
	public String DeleteById(int id) {
		User u = userRepository.findById(id).orElseThrow(
				() ->new UserNotFoundException("User not found...!!!!")
				);
			userRepository.deleteById(u.getId());
			return "Data deleted";
		}
		
	
	public long countData() {
		return userRepository.count();
	}
	
	public List<User>  fetchByMobile(long mobile){
		return userRepository.fetchByMobile(mobile);
	}
	
	public String updateImg(int id, MultipartFile file) throws Exception{
		User exUser = userRepository.findById(id) .orElseThrow(
				() ->new UserNotFoundException("User not found...!!!!")
				);
		String path = "images";
		String fileName = uploadImg(path, file);
		exUser.setProfileImg(fileName);
		userRepository.save(exUser);
		
		return "Img updated";
	}
	
	
	public String uploadImg(String path, MultipartFile file) throws Exception{
		String ogName = file.getOriginalFilename();
		String randomId = UUID.randomUUID().toString();
		String fileName = randomId + ogName.substring(ogName.lastIndexOf('.'));
		String filePath = path + File.separator + fileName;
		
		File f = new File(path);
		
		if(!f.exists()) {
			f.mkdir();
		}
		
		Files.copy(file.getInputStream(),Paths.get(filePath));
		
		return fileName;
	}
	
	public List<User> fetchAllUserByPage (int pageNo,int pageSize){
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		
		return userRepository.findAll(pageable).getContent();
	}
	
	public List<User> sortAllUser(String sortBy,String dir){
		Sort sort = dir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		
		return userRepository.findAll(sort);
	}
}
