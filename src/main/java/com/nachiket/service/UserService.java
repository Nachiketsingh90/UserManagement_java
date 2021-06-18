package com.nachiket.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nachiket.model.User;


public interface UserService {

	
	List<User> findAllUsers2();
	User findUserById(int id);
	List<User> findUsersByCompany(String company);
	User addUser(User u);
	User updateUser(User u);
	String deleteUserById(int id);
	
}
