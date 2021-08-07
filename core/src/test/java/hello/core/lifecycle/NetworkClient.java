package hello.core.lifecycle;

// javax로 시작하는 것은 자바 진영에서 공식적으로 지원하는 것이기 때문에 꼭 스프링이 아니라 다른 컨테이너를 써도 적용됨
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * InitializingBean은 초기화 빈임
 * DisposableBean은 종료하는 빈임
 * 나온지 오래되었고 더 나은 방법들이 있어서 지금은 잘 사용하지 않는다.
 */
//public class NetworkClient implements InitializingBean, DisposableBean {
public class NetworkClient {

    private String url;

    public NetworkClient() {
        System.out.println("생성자 호출 url = " + url);
    }

    public void setUrl(String url) {
        this.url = url;
    }

    //서비스 시작 시 호출
    public void connect() {
        System.out.println("connect: " + url);
    }

    public void call(String message) {
        System.out.println("call: " + url + " message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect() {
        System.out.println("close: " + url);
    }

    // afterPropertiesSet은 '의존관계 주입이 끝나면 호출하겠다' 라는 뜻
//    @Override
//    public void afterPropertiesSet() throws Exception {
//        System.out.println("NetworkClient.afterPropertiesSet");
//        connect();
//        call("초기화 연결 메시지");
//    }
//    @Override
//    public void destroy() throws Exception {
//        System.out.println("NetworkClient.destroy");
//        disconnect();
//    }

    @PostConstruct
    public void init() throws Exception {
        System.out.println("NetworkClient.init");
        connect();
        call("초기화 연결 메시지");
    }

    @PreDestroy
    public void close() throws Exception {
        System.out.println("NetworkClient.close");
        disconnect();
    }
}
