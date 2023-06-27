package com.example.demo.member;

//import org.junit.jupiter.api.Assertions; 이거하니까 asserThat 오류뜸
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {
  // 커멘드 + n -> test클릭

  //선언은 인터페이스, 초기화는 클래스
  MemberService memberService = new MemberServiceImpl();

  @Test
  @DisplayName("멤버 가입 테스트")
  //TDD
  void join(){
    //given -> 테스트를 위한 주닙 작업 (테스트 데이터 등을 미리 만든다)
    Member member = new Member(1L, "A", Grade.VIP);

    //when -> 테스트 시점. 내가 원하는 테스트를 수행하는 단계
    memberService.join(member); // 가입을 시키는 단계

    Member findmember = memberService.findByMember(1L);

    //then -> 테스트된 결과를 확인하는 단계
    Assertions.assertThat(member).isEqualTo(findmember); // JUnit 사용하기 위한 문법 어설션
  }
}