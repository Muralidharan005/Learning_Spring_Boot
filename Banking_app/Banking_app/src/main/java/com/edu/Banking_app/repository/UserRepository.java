package com.edu.Banking_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.edu.Banking_app.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
