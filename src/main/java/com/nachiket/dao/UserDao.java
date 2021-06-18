package com.nachiket.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nachiket.model.User;



public interface UserDao extends CrudRepository<User, Integer>{

	
}
