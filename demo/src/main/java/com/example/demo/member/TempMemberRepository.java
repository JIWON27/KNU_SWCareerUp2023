package com.example.demo.member;

import java.util.ArrayList;
import java.util.List;

public class TempMemberRepository implements MemberRepository {
  // 필드에 보통 final을 넣는 걸 추천. final이 붙으면 초기화를 무조간 해줘야 함.
  private final List<Member> list = new ArrayList<>();

  // 회원 생성하고 데이터에 입력하는 기능
  @Override
  public Long save(Member member) {
    this.list.add(member);
    return member.getId();
  }
  // 회원 단건 조회하는 기능인데, 저장소 기준으로 보면 특정 데이터를 출력
  @Override
  public Member findById(Long id) {
    // 1번 id만 조회하겠다고 가정
    return list.stream().filter(member -> 1L == member.getId()).findAny().orElse(null);
  }
}
