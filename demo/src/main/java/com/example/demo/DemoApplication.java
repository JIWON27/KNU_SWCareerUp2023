package com.example.demo;

import com.example.demo.member.Grade;
import com.example.demo.member.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;

@SpringBootApplication
public class DemoApplication {
	// main 메서드는 자바에서 실행 시작점을 의미한다.
	// 스프링 부트는 자바 기반으로 만들어 졌으므로 main 메서드에서 시작된다.
	public static void main(String[] args) {
		// 자바 기반으로 실행된 상태에서 처음 시작되는 구문
		// 이 구문은 DemoApplication 이라는 클래스를 시작으로 스프링 부트의 기둥을 담당한다.
		SpringApplication.run(DemoApplication.class, args);


	}
}
