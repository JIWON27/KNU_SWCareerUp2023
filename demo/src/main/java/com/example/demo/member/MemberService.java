package com.example.demo.member;

public interface MemberService {
  Long join(Member member); // 생성
  Member findByMember(Long id); //id는 고유 값이어서 매개변수로 id값만 받아서 조회
  // void updateMember(long id, String name, Grade grade);
  // void deleteMember(Member member);
}
