package com.example.demo2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Demo2Application {

  public static void main(String[] args) {
    SpringApplication.run(Demo2Application.class, args);
  }
  //Web은 Presentation 영역
  //Service는 비지니스 여역
  //Domain은 멤버 클래스, 리포지토리 이런것들..
}
