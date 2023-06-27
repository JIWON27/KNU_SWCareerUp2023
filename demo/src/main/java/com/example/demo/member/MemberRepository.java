package com.example.demo.member;

import java.util.ArrayList;

public interface MemberRepository {
  // 서비스에서 생성과 단견 조회에 대한 기능만 구현할 것이므로
  // repository에서도 생성과 단건 조회에 대한 기능만 구현하면 된다.

  // 회원 생성을 담당하는 repository
  Long save(Member member); // 생성된 id값을 반환할려고 long을 반환타입으로 지정함

  // 회원 단건 조회를 담당하는 repository
  Member findById(Long id);
}
