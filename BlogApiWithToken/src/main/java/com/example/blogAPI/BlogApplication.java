package com.example.blogAPI;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA On 시켜주는 느낌임.. 전원 스위치 켜주는 역할..
@SpringBootApplication
public class BlogApplication {

  public static void main(String[] args) {

    SpringApplication.run(BlogApplication.class, args);
  }

}
