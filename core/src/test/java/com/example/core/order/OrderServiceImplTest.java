package com.example.core.order;

import com.example.core.AutoAppConfig;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderServiceImplTest {

    @Test
    void orderTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class);
    }
}
