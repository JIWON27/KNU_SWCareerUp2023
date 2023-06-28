package com.example.management.member;

import com.example.management.member.Member;

public interface MemberService {

  //생성
  Long join(Member member);

  //단건 조회
  Member findByMember(long id);

}
