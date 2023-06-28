package com.example.management.member;

import com.example.management.member.Member;

public interface MemberRepository {
  // 생성
  Long save(Member member);

  // 조회
  Member findById(long id);
}
