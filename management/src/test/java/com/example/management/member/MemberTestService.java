package com.example.management.member;

//import org.junit.jupiter.api.Assertions; 이거하니까 asserThat 오류뜸
import com.example.management.config.AppConfig;
import com.example.management.member.Grade;
import com.example.management.member.Member;
import com.example.management.member.MemberService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

  //  MemberService memberService = new MemberServiceImpl();
  MemberService memberService;

  // @BeforeEach - 테스트를 돌리기위해서 미리 실행되어야할것들 기술
  @BeforeEach
  public void beforeEach() {
    AppConfig appConfig = new AppConfig();
    memberService = appConfig.memberService();
  }

  @Test
  @DisplayName("멤버 가입 테스트")
    //TDD
  void join(){
    //given -> 테스트를 위한 주닙 작업 (테스트 데이터 등을 미리 만든다)
    Member member1 = new Member(1L, "A", Grade.VIP);

    //when -> 테스트 시점. 내가 원하는 테스트를 수행하는 단계
    memberService.join(member1); // 가입을 시키는 단계

    Member findmember1 = memberService.findByMember(1L);

    //then -> 테스트된 결과를 확인하는 단계
    Assertions.assertThat(member1).isEqualTo(findmember1); // JUnit 사용하기 위한 문법 어설션
  }


}