package com.nachiket.config;


import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Component;

import com.nachiket.model.User;

import java.util.List;

@Component
public class SearchQueryBuilder {

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;


    public List<User> getAll(String text) {
    	System.out.println("searching with elastic "+text);
        QueryBuilder query = QueryBuilders.boolQuery()
                .should(
                        QueryBuilders.queryStringQuery(text)
                                .lenient(true)
                                .field("name")
                                .field("company")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("name")
                        .field("company"));
        System.out.println("query is  "+query);
        
        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<User> userses = elasticsearchTemplate.queryForList(build, User.class);
        System.out.println(" Users no is : "+userses.size());
        return userses;
    }
}
