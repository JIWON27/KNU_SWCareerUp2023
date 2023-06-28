package com.example.management.member;

import com.example.management.member.Member;
import com.example.management.member.MemberRepository;

import java.util.ArrayList;
import java.util.List;

public class TempMemberRepository implements MemberRepository {

  public final List<Member> list = new ArrayList<>();

  // 저장
  @Override
  public Long save(Member member) {
    this.list.add(member);
    return member.getId();
  }

  // 조회
  @Override
  public Member findById(long id) {
    // 람다식
    return list.stream().filter(member -> 1L == member.getId()).findAny().orElse(null);
  }
}
