package edu.com.User_app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import edu.com.User_app.Entity.User;

public interface UserRepository extends JpaRepository<User,Integer>{

	@Query(value = "select u  from User u where u.mobile = ?1")
	public List<User> fetchByMobile(long mobile);
}
