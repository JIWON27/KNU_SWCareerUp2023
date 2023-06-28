package com.example.management.member;

// Member 데이터를 repository에 넘기기전에 어떠한 서비스 처리 로직이 실행되는 클래스
public class MemberServiceImpl implements MemberService {
  // 변경 전 코드 : private final MemberRepository memberRepository = new TempMemberRepository();
  // 선언, final이 붙어있어서 초기화를 해줘야함.
  private final MemberRepository memberRepository;

  // MemberServiceImpl 객체가 생성됨과 동시에 초기화 하기 위해 생성자 안에서 초기화 작업이 이루어지도록 함.
  public MemberServiceImpl(MemberRepository memberRepository) {
    this.memberRepository = memberRepository;
    // 수정 후 : this.memberRepository = 신규 관심사로 만들어진 객체
  }
  @Override
  public Long join(Member member) {
    return memberRepository.save(member);

  }
  @Override
  public Member findByMember(long id) {
    return memberRepository.findById(id);
  }
}
