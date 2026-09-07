package edu.adv.Product_app.Serive;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.adv.Product_app.Entity.Product;
import edu.adv.Product_app.Exception.ProductNotFoundException;
import edu.adv.Product_app.Repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	public Product saveData(Product p) {
		return productRepository.save(p);
	}
	
	public Product fetchById(int id) {
		Optional<Product> o = productRepository.findById(id);
		
		if(o.isPresent()) {
			return o.get();
		}
		return null;
	}
	
	public List<Product> fetchByName(String name){
		return productRepository.findByName(name);
	}
	
	public Product updateById(int id,Product newProduct) {
		Product oldProduct = productRepository.findById(id).orElseThrow(
				()-> new ProductNotFoundException("Product not found...!!"));
		
		oldProduct.setName(newProduct.getName());
		oldProduct.setPrice(newProduct.getPrice());
		oldProduct.setQuantity(newProduct.getQuantity());
		oldProduct.setRating(newProduct.getRating());
		oldProduct.setBrand(newProduct.getBrand());
		oldProduct.setType(newProduct.getType());
		
		return productRepository.save(oldProduct);
		
	}
	
	public String deleteById(int id) {
		Product p = productRepository.findById(id).orElseThrow(
				()-> new ProductNotFoundException("Product not found..!!"));
		
		productRepository.delete(p);
		return "Product deleted";
	}
	
	public String deleteAll() {
		productRepository.deleteAll();
		return "Data deleted";
	}
	
	public String updateImg(int id, MultipartFile file) throws Exception{
		Product exPro = productRepository.findById(id) .orElseThrow(
				() ->new ProductNotFoundException("Product not found...!!!!")
				);
		String path = "images";
		String fileName = uploadImg(path, file);
		exPro.setImg(fileName);
		productRepository.save(exPro);
		
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
	
	public List<Product> fetchBwPrice(double fprice,double lprice){
		return productRepository.findByPriceBetween(fprice, lprice);
	}
	
	public List<Product> SaveAllData(List<Product> p){
		return productRepository.saveAll(p);
	}
	
	public List<Product> fetchByPage(int pageNo,int pageSize){
		Pageable page = PageRequest.of(pageNo, pageSize);
		
		return productRepository.findAll(page).getContent();
	}
	
	public List<Product> sortAllData(String sortBy, String dir){
		Sort sort = dir.equalsIgnoreCase("DESC") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
		return productRepository.findAll(sort);
	}
}
