package com.muyinliu.springdemo.aop;

import lombok.Data;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

@Component
@RequestScope
@Data
public class AutoDataSourceMongoTemplate {

  private MongoTemplate mongoTemplate;

}
