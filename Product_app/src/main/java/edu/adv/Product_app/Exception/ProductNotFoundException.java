package edu.adv.Product_app.Exception;

public class ProductNotFoundException extends RuntimeException {
	public ProductNotFoundException (String msg) {
		super(msg);
	}
}
