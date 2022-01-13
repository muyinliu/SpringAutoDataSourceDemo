package com.muyinliu.springdemo.config;

import com.mongodb.client.MongoClients;
import org.springframework.boot.autoconfigure.mongo.MongoProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;

@Configuration
public class MultipleDatabaseConfig {

  @Bean
  @Primary
  @ConfigurationProperties(prefix = "spring.data.mongodb.dbs.bsc")
  public MongoProperties bscMongoProperties() {
    return new MongoProperties();
  }

  @Bean(name = "bscMongoTemplate")
  @Primary
  public MongoTemplate bscMongoTemplate() {
    MongoProperties mongoProperties = bscMongoProperties();
    return new MongoTemplate(new SimpleMongoClientDatabaseFactory(
        MongoClients.create(mongoProperties.getUri()),
        mongoProperties.getDatabase()));
  }


  @Bean
  @ConfigurationProperties(prefix = "spring.data.mongodb.dbs.ethereum")
  public MongoProperties ethereumMongoProperties() {
    return new MongoProperties();
  }

  @Bean(name = "ethereumMongoTemplate")
  public MongoTemplate ethereumMongoTemplate() {
    MongoProperties mongoProperties = ethereumMongoProperties();
    return new MongoTemplate(new SimpleMongoClientDatabaseFactory(
        MongoClients.create(mongoProperties.getUri()),
        mongoProperties.getDatabase()));
  }

}
