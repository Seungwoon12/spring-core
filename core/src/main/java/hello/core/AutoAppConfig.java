package hello.core;

import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(
        /**
         * basePackages나 basePackageClasses를 지정하지 않으면 @ComponentScan을 붙인 클래스가 속한 패키지를
         * 포함해서 그 하위패키지까지 다 뒤진다. 여기서는 hello.core를 시작위치로 해서 그 하위패키지까지 범위가 된다.
         *
         * 일반적으로 설정 정보 클래스의 위치를 프로젝트 최상단에 두고 @ComponentScan 애노테이션을 붙이고
         * basePackages 지정은 생략한다.
        */

        // 컴포넌트 스캔의 범위를 지정하는 것(어디서 부터 찾는지 지정). 지정 안하면 라이브러리 이런 것까지 다
        // 뒤지기 때문에 유연함이 필요할 때 지정해주면 좋음
//        basePackages = "hello.core.member",

        // 지정 해준 클래스가 속한 패키지를 포함해서 그 하위 패키지까지가 컴포넌트 스캔의 범위가 됨
//        basePackageClasses = AutoAppConfig.class,

        //  기존 예제 코드를 최대한 남기고 유지하기 위해  @Configuration 붙은 애들을 제외시킴
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {

        /**
         * 수동 빈 등록이 우선권을 가진다. 즉, 수동 빈이 자동 빈을 오버라이딩 해버린다.
         * 하지만 스프링 부트를 사용할 경우(스프링 부트 테스트 포함)는 충돌이 나도록 디폴트로 설정이 되어있다. 스프링 부트는 친절함
         * application.properties에 'spring.main.allow-bean-definition-overriding=true'라고 해주면 수동 빈으로 오버라이딩 됨
         * 디폴트는 false임
         */
//        @Bean(name = "memoryMemberRepository")
//        MemberRepository memberRepository() {
//                return new MemoryMemberRepository();
//        }
}

