package com.nachiket.config;

import com.nachiket.dao.UserDao;
import com.nachiket.model.User;
import com.nachiket.search.dao.UserElasticRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class Loaders {

    @Autowired
    ElasticsearchOperations operations;

    @Autowired
    UserElasticRepository userRepository;
    
    @Autowired
    UserDao userDao;

    @PostConstruct
    @Transactional
    public void loadAll(){

        operations.putMapping(User.class);
        System.out.println("Loading Data");
        userRepository.save(userDao.findAll());
        System.out.printf("Loading Completed");

    }

   
}
