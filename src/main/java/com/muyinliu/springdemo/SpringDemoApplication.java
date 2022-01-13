package com.muyinliu.springdemo;

import com.muyinliu.springdemo.app.Initializer;
import javax.annotation.PostConstruct;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties
@Log4j2
public class SpringDemoApplication {

  @Autowired
  Initializer initializer;

  public static void main(String[] args) {
    SpringApplication.run(SpringDemoApplication.class, args);
  }

  @PostConstruct
  public void postConstruct() {
    log.info("initializing app");
    initializer.init();
    log.info("App initialized");
  }

}
