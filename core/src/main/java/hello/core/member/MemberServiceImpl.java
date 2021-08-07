package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * \ @Component 붙이면 클래스명의 앞글자만 소문자로 해서 스프링 빈 이름으로 지정이 됨
 */
@Component
//@Component("memberService2") // 스프링 빈의 이름을 직접 지정해주는 방법
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    /**
     \ @Autowired는 의존 관계 주입을 자동으로 해준다.
       생성자 위에 붙여준다.
     */
    @Autowired // ac.getBean(MemberRepository.class)
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }


    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;

    }
}
