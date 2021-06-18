package com.nachiket.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nachiket.config.ElasticConfiguration;
import com.nachiket.config.SearchQueryBuilder;
import com.nachiket.model.User;
import com.nachiket.service.UserService;
import com.nachiket.service.UserServiceImpl;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class UserController {
	
	
	  @Autowired 
	  private UserServiceImpl userServiceImpl;
	  
	  @Autowired
	  private SearchQueryBuilder searchQueryBuilder;
	  
	
	  
	  @GetMapping("/findThrElastic/{text}")
	  public List<User>  findUserByUser_Id(@PathVariable("text") final String text) throws Exception {
		  System.out.println("Search String is ::"+text);
		  List<User> userList=searchQueryBuilder.getAll(text);
		  for(User e: userList)
		  {
			  System.out.println(e.getId()+" "+e.getName()+" "+e.getCompany()+" "+e.getSalary());
		  }
		  	return userList;
		  }
	  
	  @GetMapping("/findAllUsers")
	  public ResponseEntity<List<User>> findAllUsers() {
		  System.out.println("findall called"); 
		  List<User> list= userServiceImpl.findAllUsers2(); 
		  return new ResponseEntity<List<User>>(list, new HttpHeaders(), HttpStatus.OK);
	  }
	  
	  @GetMapping("/findUserById/{id}")
	  public ResponseEntity<User>  findUserByUser_Id(@PathVariable("id") int id) throws Exception {
		  System.out.println(" going to call findUserById");
		  	  User obj=userServiceImpl.findUserById(id);
		  	  if(obj==null)
		  		  throw new Exception("Employee with id "+id+" not found");
		  	  return new ResponseEntity<User>(obj, new HttpHeaders(),HttpStatus.OK);
		  }
	  
	  @GetMapping("/findUsersByCompany/{company}") 
	  public ResponseEntity<List<User>>  findUsersByCompany(@PathVariable("company") String company) {
		  System.out.println("company is: "+company);
		  List<User> userl= userServiceImpl.findUsersByCompany(company); 
		  return	new ResponseEntity(userl,new HttpHeaders(),HttpStatus.OK ); 
		  }
	  
	  @DeleteMapping("/deleteUserById/{id}")
	  public String  deleteUserByUser_Id(@PathVariable("id") int id) {
			  System.out.println("going to delete "+id);
			  return  userServiceImpl.deleteUserById(id); 
		  }
	  
	  @PostMapping("/addUser")
	  public ResponseEntity<User>  addUser(@RequestBody User u) {
		  System.out.println("hello id "+u.getId()+" name "+u.getName()+" sal "+u.getSalary());
		  if(u!=null && !u.getName().isEmpty())
		  {
			  User new1=userServiceImpl.addUser(u);
			  return new ResponseEntity<User>(new1, new HttpHeaders(), HttpStatus.OK);
		  }
		  else
			  return null;
		  
		   
		   
		  }
	  @PutMapping("/updateUser")
	  public ResponseEntity<User>  updateUser(@RequestBody User u) {
		  System.out.println("hello id "+u.getId()+" name "+u.getName()+" sal "+u.getSalary());
		    User user=userServiceImpl.updateUser(u);
		   
		    return new ResponseEntity<User>(user, new HttpHeaders(), HttpStatus.OK);
		  }
	
	 
}
