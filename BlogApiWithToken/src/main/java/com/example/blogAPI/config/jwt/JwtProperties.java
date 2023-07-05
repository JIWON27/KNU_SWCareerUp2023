package com.example.blogAPI.config.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
@ConfigurationProperties("jwt") // 애플리케이션.yml의 jwt 속성을 가져와라, 뒤져라!
public class JwtProperties {

  private String issuer;
  private String secret;



}
