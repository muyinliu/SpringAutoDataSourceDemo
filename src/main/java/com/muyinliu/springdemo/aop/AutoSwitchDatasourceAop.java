package com.muyinliu.springdemo.aop;

import java.util.Arrays;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Log4j2
public class AutoSwitchDatasourceAop {

  @Autowired
  AutoDataSourceMongoTemplate autoDataSourceMongoTemplate;

  @Autowired
  @Qualifier("bscMongoTemplate")
  MongoTemplate bscMongoTemplate;

  @Autowired
  @Qualifier("ethereumMongoTemplate")
  MongoTemplate ethereumMongoTemplate;

  public void setAutoDataSourceMongoTemplate(String chain) {

    switch (chain) {
      case "ethereum":
        autoDataSourceMongoTemplate.setMongoTemplate(ethereumMongoTemplate);
        break;
      case "bsc":
      default:
        autoDataSourceMongoTemplate.setMongoTemplate(bscMongoTemplate);
        break;
    }

  }

  @Pointcut(value = "@annotation(com.muyinliu.springdemo.annotation.AutoSwitchDataSourceByChain)")
  public void autoSwitchDataSourceByChain() {}

  @Around("autoSwitchDataSourceByChain()")
  public Object autoSwitchDataSource(ProceedingJoinPoint joinPoint) throws Throwable {

    CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
    List<String> parameterNames = Arrays.asList(codeSignature.getParameterNames());
    String chain = "bsc";
    int index = parameterNames.indexOf("chain");
    if(index != -1) {
      chain = (String) joinPoint.getArgs()[index];
    }
    log.info("chain: {}", chain);
    setAutoDataSourceMongoTemplate(chain);
    return joinPoint.proceed();

  }

}
