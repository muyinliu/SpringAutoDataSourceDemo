package com.muyinliu.springdemo.service;

import com.muyinliu.springdemo.aop.AutoDataSourceMongoTemplate;
import com.muyinliu.springdemo.entity.Token;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class QueryService {

  @Autowired
  AutoDataSourceMongoTemplate autoDataSourceMongoTemplate;

  public Token query(String chain, String tokenAddress) {

    MongoTemplate mongoTemplate = autoDataSourceMongoTemplate.getMongoTemplate();
    log.info("mongoTemplate: {}", mongoTemplate);
    return mongoTemplate.findById(tokenAddress, Token.class);

  }

}
