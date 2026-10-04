package com.demo.lifecycle.service;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * The demo bean — implements every lifecycle hook so steps 4 to 11 can be seen.
 */
public class OrderService implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private PaymentService paymentService;

    // ─── Step 4: Instantiation ────────────────────────────────────────────────
    public OrderService() {
        System.out.println("[STEP 4]  INSTANTIATION          : OrderService() constructor called, paymentService = " + paymentService);
    }

    // ─── Step 5: Dependency Injection ─────────────────────────────────────────
    @Autowired
    public void setPaymentService(PaymentService paymentService) {
        this.paymentService = paymentService;
        System.out.println("[STEP 5]  DEPENDENCY INJECTION   : setPaymentService() called by Spring, paymentService = " + paymentService);
    }

    // ─── Step 6: Aware interfaces ─────────────────────────────────────────────
    @Override
    public void setBeanName(String name) {
        System.out.println("[STEP 6]  AWARE                  : setBeanName('" + name + "')");
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) {
        System.out.println("[STEP 6]  AWARE                  : setBeanFactory()");
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) {
        System.out.println("[STEP 6]  AWARE                  : setApplicationContext()");
    }

    // ─── Step 8: Initialization callbacks, in this order ──────────────────────
    @PostConstruct
    public void postConstruct() {
        System.out.println("[STEP 8]  INIT                   : @PostConstruct (runs 1st)");
    }

    @Override
    public void afterPropertiesSet() {
        System.out.println("[STEP 8]  INIT                   : InitializingBean.afterPropertiesSet() (runs 2nd)");
    }

    /** Configured by @Bean(initMethod = "customInit") in AppConfig. */
    public void customInit() {
        System.out.println("[STEP 8]  INIT                   : custom init-method customInit() (runs 3rd)");
    }

    // ─── Step 11: Destruction callbacks, in this order ────────────────────────
    @PreDestroy
    public void preDestroy() {
        System.out.println("[STEP 11] DESTROY                : @PreDestroy (runs 1st)");
    }

    @Override
    public void destroy() {
        System.out.println("[STEP 11] DESTROY                : DisposableBean.destroy() (runs 2nd)");
    }

    /** Configured by @Bean(destroyMethod = "customDestroy") in AppConfig. */
    public void customDestroy() {
        System.out.println("[STEP 11] DESTROY                : custom destroy-method customDestroy() (runs 3rd)");
    }

    public String placeOrder(String item, double price) {
        return "Order placed for '" + item + "' — " + paymentService.processPayment(price);
    }
}
