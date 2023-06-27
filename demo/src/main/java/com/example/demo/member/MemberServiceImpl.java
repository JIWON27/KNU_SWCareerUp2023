package com.example.demo.member;

public class MemberServiceImpl implements MemberService {

  //인터페이스 변수를 선언한거 좀 공부하기, 아래의 문장이 다형성을 나타내고 잇음.

  private final MemberRepository memberRepository = new TempMemberRepository(); //in-memory 방식

  //private final MemberRepository memberRepository = new DbMemberRepository();
  // db 종류 , 다형성을 사용안하면 어래처럼 다 선언을 해줘야함... 하지만 선언을 인터페이스로하면 선언관점에서는 코드 수정이 필요없음
  // 오직 어떤 구현체로 구현(class)할 것인지만 고민하면됨. mysql쓸껀지, orcla쓸건지 이런거를 고민하면 됨.

  // 1. in-memory
  // 2. My-SQL = new MySqlMemberRepository();
  // 3. oracle = new OracleMemberRepository();
  // 4. redis = new RedisMemberRepository();


  // 회원 생성 서비스
  @Override
  public Long join(Member member) {
    return memberRepository.save(member);
  }
  // 특정 회원 조회 서비스
  @Override
  public Member findByMember(Long id) {
    return memberRepository.findById(id);
  }
}
