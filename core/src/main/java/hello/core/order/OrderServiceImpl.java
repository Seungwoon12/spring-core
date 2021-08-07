package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor // lombok을 통해 생성자 만들기(의존관계 주입 할 빈 필드에 'final' 꼭 붙여줘야함)
public class OrderServiceImpl implements OrderService {


    // cf) 'final'을 붙이면 초기화를 통해 값을 셋팅하거나 생성자를 통해 값을 셋팅하는 것만 가능하다는 장점이 있음
    // 또한 생성자에서 값을 셋팅하는 것을 깜빡했다면 컴파일 오류를 발생시키기 때문에 어디에 문제가 있는지 알 수 있어서 좋음
    /**
     * DIP를 지킨 경우
     */
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;

    /**
     * 수정자 주입하는 경우(setter 주입)
     */
//    @Autowired
//    public void setMemberRepository(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }
//    @Autowired
//    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
//        this.discountPolicy = discountPolicy;
//    }

    /**
     * DIP를 지키기 않은 경우
     */
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    /**
     * 생성자가 딱 1개만 있으면 @Autowired를 생략해도 자동 주입 된다.
     *
     * 항상 생성자 주입을 선택하고,가끔 옵션이 필요한 경우에만 수정자 주입을 선택해라. 필드 주입은 사용하지 않는게 좋다.
     */
//    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy  = discountPolicy;
    }


    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
