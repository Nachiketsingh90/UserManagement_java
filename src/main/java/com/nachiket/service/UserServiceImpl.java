package com.nachiket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nachiket.dao.UserDao;
import com.nachiket.model.User;
import com.nachiket.search.dao.UserElasticRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserDao userDao;
	
	  @Autowired 
	  private UserElasticRepository userElasticRepository;
	 
	

	
	//@Override
	public List<User> findAllUsers2() {
		 List<User> usersList = new ArrayList<>();
	        Iterable<User> userses = userDao.findAll();
	       usersList=(List<User>) userses;
	    	userElasticRepository.save(userses);
	        return usersList;
	
		
	}
	
	
	static List<User> resultList = new ArrayList<User>();
	
	public List<User> findUsersByCompany(String company){
		//if(userDao.)
		//System.out.println("findUsersByCompany"+company);
		List<User> allPlayers=(List<User>)userDao.findAll();
		
		for (User u:allPlayers)
		{
			if(u.getCompany().equals(company))
			{
				resultList.add(u);
			}
		}
		//System.out.println("resultlist "+resultList);
		return resultList;
}
	
	public User findUserById(int id) {
		System.out.println(" findUserById call... for id: "+id);
				return userDao.findOne(id);
			
		}
	
	public User addUser(User u)
	{
		userElasticRepository.save(u);
		return userDao.save(u);
	}
	public User updateUser(User u)
	{
		String msg;
		
		if(userDao.exists(u.getId()))
		{
			userDao.save(u);
			//userElasticRepository.save(u);
			msg="User updated....";
		}
		else
			msg="User with this id not found";
		return u;
	}
	public String deleteUserById(int id) {
		
		String msg;
		
		if(userDao.exists(id))
		{
			 userDao.delete(id);
			msg="User deleted....";
		}
		else
			msg="User with this id not found";
		return msg;
		
	}
	
	
	
}
