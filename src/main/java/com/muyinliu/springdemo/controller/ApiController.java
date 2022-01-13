package com.muyinliu.springdemo.controller;

import com.muyinliu.springdemo.annotation.AutoSwitchDataSourceByChain;
import com.muyinliu.springdemo.service.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApiController {

  @Autowired
  QueryService queryService;

  @GetMapping("/token")
  @AutoSwitchDataSourceByChain
  public String token(
      @RequestParam(value = "chain", required = false, defaultValue = "bsc")
          String chain,
      @RequestParam(value = "address", required = false, defaultValue = "world")
          String address) {

    return queryService.query(chain, address).getSymbol();

  }

}
