package com.nachiket.search.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.nachiket.model.User;


public interface UserElasticRepository extends ElasticsearchRepository<User, Integer>{

	 List<User> findByName(String text);
}
