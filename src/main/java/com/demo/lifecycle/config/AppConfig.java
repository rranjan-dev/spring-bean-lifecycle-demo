package com.demo.lifecycle.config;

import com.demo.lifecycle.processor.CustomBeanFactoryPostProcessor;
import com.demo.lifecycle.processor.CustomBeanPostProcessor;
import com.demo.lifecycle.service.OrderService;
import com.demo.lifecycle.service.PaymentService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Step 2 — Spring reads this class and turns the @Bean methods into bean
 * DEFINITIONS. No objects are created yet.
 *
 * The @Bean methods only call the constructor; all wiring is left to Spring so
 * steps 5 to 9 are genuinely done by the container.
 */
@Configuration
public class AppConfig {

    // 'static' is required for post-processors — they must be created before
    // the @Configuration class itself is fully processed.

    @Bean
    public static CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor() {
        return new CustomBeanFactoryPostProcessor();
    }

    @Bean
    public static CustomBeanPostProcessor customBeanPostProcessor() {
        return new CustomBeanPostProcessor();
    }

    @Bean
    public PaymentService paymentService() {
        return new PaymentService();
    }

    @Bean(initMethod = "customInit", destroyMethod = "customDestroy")
    public OrderService orderService() {
        return new OrderService();
    }
}
