package edu.adv.Product_app.Entity;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Length(min = 3, max = 25 , message = "Enter the name within the range")
	private String name;
	@Min(value = 15, message = "Enter valid quantity")
	private int quantity;
	private double price;
	private double rating;
	private String type;
	private String img;
	private String brand;

}
