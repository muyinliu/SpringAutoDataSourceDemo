package com.muyinliu.springdemo.entity;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document("Token")
public class Token implements Serializable {

  @Id
  private String address;
  private String symbol;
  private Integer decimals;

}
