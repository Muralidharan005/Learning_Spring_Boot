package edu.com.User_app.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Length(min = 3,max = 25, message = "Enter name within the range")
	private String name;
	@Length(max = 6, message = "invalid length")
	private String gender;
	@Min(value = 18, message = "Age cannot be below 18")
	@Max(value = 40, message = "Age cannot be above 40")
	private int age;
	@Email(message = "Enter valid Email")
	private String email;
	private String password;
	private long mobile;
	private String profileImg;	
	
}
