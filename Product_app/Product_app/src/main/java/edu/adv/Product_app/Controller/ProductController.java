package edu.adv.Product_app.Controller;

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

import edu.adv.Product_app.Entity.Product;
import edu.adv.Product_app.Serive.ProductService;
import jakarta.validation.Valid;

@RestController
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@PostMapping("/saveData")
	public Product saveData(@Valid @RequestBody Product p) {
		return productService.saveData(p);
	}
	
	@PostMapping("/saveAllData")
	public List<Product> saveAllData(@Valid @RequestBody List<Product> p){
		return productService.SaveAllData(p);
	}
	
	@GetMapping("/getById")
	public Product fetchById(@RequestParam int id) {
		return productService.fetchById(id);
	}

	@GetMapping("/getByName/{name}")
	public List<Product> fetchByName(@PathVariable String name){
		return productService.fetchByName(name);
	}
	
	@PutMapping("/updateById/{id}")
	public Product updateById(@PathVariable int id,@Valid @RequestBody  Product p) {
		return productService.updateById(id, p);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public String deleteById(@PathVariable int id) {
		return productService.deleteById(id);
	}
	
	@DeleteMapping("/deleteAll")
	public String deleteAll() {
		return productService.deleteAll();
	}
	
	@PutMapping("/updateImg/{id}")
	public String updateImg(@PathVariable int id, @RequestBody MultipartFile file) throws Exception {
		return productService.updateImg(id, file);
	}
	
	@GetMapping("/fetchByPage")
	public List<Product> fetchByPage(@RequestParam(required = false, defaultValue = "0") int pageNo, @RequestParam(required = false, defaultValue = "10") int PageSize){
		return productService.fetchByPage(pageNo, PageSize);
	}
	
	@GetMapping("/getBwPrice")
	public List<Product> fetchBwPrice(@RequestParam(required = false, defaultValue = "0") double fprice,@RequestParam double lprice){
		return productService.fetchBwPrice(fprice, lprice);
	}
	
	@GetMapping("/sortBy")
	public List<Product> sortAllData(@RequestParam(required = false, defaultValue = "id") String sortBy, @RequestParam(required = false, defaultValue = "asc") String dir){
		return productService.sortAllData(sortBy, dir);
	}
}
