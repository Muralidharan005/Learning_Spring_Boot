package edu.jsp.demo_app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

	@RequestMapping("/get")
	public String m1() {
		return "Hello World";
	}
	
	@RequestMapping("/getId")
	public String getId(@RequestParam int id) {
		return "Id = "+id;
	}
	
	@PutMapping("/getName/{name}")
	public String getName(@PathVariable String name) {
		return "Name = "+name;
	}
	
	@RequestMapping(value = "/getStd",method = RequestMethod.POST )
	public Student getStd() {
		Student s = new Student();
		s.setId(1);
		s.setName("Mani");
		s.setMark(58.5);
		
		return s;
	}
	
	@RequestMapping("/getList")
	public List<Student> getList(){
		
		Student s1 = new Student();
		s1.setId(1);
		s1.setName("Mani");
		s1.setMark(58.5);
		
		Student s2 = new Student();
		s2.setId(2);
		s2.setName("Robert");
		s2.setMark(77.5);
		
		Student s3 = new Student();
		s3.setId(3);
		s3.setName("Logesh");
		s3.setMark(85.7);
		
		Student s4 = new Student();
		s4.setId(4);
		s4.setName("Sridhar");
		s4.setMark(75.1);
		
		List<Student> l = new ArrayList<Student>();
		l.add(s1);
		l.add(s2);
		l.add(s3);
		l.add(s4);
		
		return l;
	}
	
	@PostMapping("/getDetails")
	public Student getDetails(@RequestBody Student s) {
		
		return s;
	}

}
