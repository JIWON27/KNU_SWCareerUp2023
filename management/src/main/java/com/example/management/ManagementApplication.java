package com.example.management;
import com.example.management.config.AppConfig;
import com.example.management.member.Grade;
import com.example.management.member.MemberService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.example.management.member.Member;

@SpringBootApplication
public class ManagementApplication {

  public static void main(String[] args) {
    SpringApplication.run(ManagementApplication.class, args);

    ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    //스프링이 관리하고있는 곳으로부터 객체를 "주입"시키는 것, DI, IoC
    //DI : 의존성 주입
    //IoC : 제어의 역전
    MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
    Member member = new Member(1L, "A", Grade.VIP);
    memberService.join(member);
  }
}
