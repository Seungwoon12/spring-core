package hello.core.lifecycle;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class BeanLifeCycleTest {

    @Test
    public void lifeCycleTest() {
        // ConfigurableApplicationContext가 AnnotationConfigApplicationContext 보다 상위 인터페이스임
        ConfigurableApplicationContext ac = new AnnotationConfigApplicationContext(LifeCycleConfig.class);
        NetworkClient client = ac.getBean(NetworkClient.class);
        ac.close(); // close를 위해 ConfigurableApplicationContext 인터페이스가 필요함
    }

    @Configuration
    static class LifeCycleConfig {
        /**
         * 라이브러리는 대부분 'close', 'shutdown' 이라는 이름의 종료 메서드를 사용한다.
         * destroyMethod 는 기본값이 '(inferred)'로 등록되어 있다.
         * 이 추론 기능은 'close', 'shutdown' 라는 이름의 메서드를 자동으로 호출해준다. 이름 그대로
         * 종료 메서드를 추론해서 호출해주는 것이다.
         *
         * 따라서, 직접 스프링 빈으로 등록하면 종료 메서드는 따로 적어주지 않아도 잘 동작한다.
         * 추론 기능을 사용하기 싫으면 destroyMethod='' 처럼 빈 공백을 지정하면 된다.
         *
         * 하지만 이 방법보다는 애노테이션을 활용한 방법(@PostConstruct, @PreDestroy)을 쓰는 것이 나음. 그러나 애노테이션을 활용한 방법은
         * 외부 라이브러리에 적용하지 못한다. 따라서, 외부 라이브러리를 초기화, 종료 해야하는 경우에만 @Bean(initMethod = "init", destroyMethod = "close") 이 방법을 쓰자
         *
         * */
//        @Bean(initMethod = "init", destroyMethod = "close")
        @Bean
        public NetworkClient networkClient() {
            NetworkClient networkClient = new NetworkClient();
            networkClient.setUrl("http://hello-spring.dev");
            return networkClient;
        }
    }
}
