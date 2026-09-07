package edu.adv.Product_app.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import edu.adv.Product_app.Entity.Product;

public interface ProductRepository extends JpaRepository<Product,Integer>{
	
	public List<Product> findByName(String name);
	public List<Product> findByPriceBetween(double fprice,double lprice);
	
}
