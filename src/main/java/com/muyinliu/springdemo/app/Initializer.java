package com.muyinliu.springdemo.app;

import com.muyinliu.springdemo.entity.Token;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class Initializer {

  @Autowired
  @Qualifier("bscMongoTemplate")
  MongoTemplate bscMongoTemplate;

  @Autowired
  @Qualifier("ethereumMongoTemplate")
  MongoTemplate ethereumMongoTemplate;

  public void init() {
    log.info("insert token records to DBs");
    try {
      /* insert same address token records to different DBs */
      Token bscToken = Token.builder().address("0x1234").symbol("BNB").decimals(18).build();
      bscMongoTemplate.insert(bscToken);

      Token ethToken = Token.builder().address("0x1234").symbol("ETH").decimals(18).build();
      ethereumMongoTemplate.insert(ethToken);
    } catch (Exception e) {
      log.info("{}", e.getMessage());
    }
  }

}
