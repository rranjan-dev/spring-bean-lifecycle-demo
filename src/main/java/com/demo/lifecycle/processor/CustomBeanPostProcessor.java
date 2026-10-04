package com.demo.lifecycle.processor;

import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * Steps 7 and 9 — these two methods sandwich the initialization callbacks of Step 8.
 * They run for EVERY bean, so we filter down to the demo beans.
 */
public class CustomBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) {
        if (isDemoBean(beanName)) {
            System.out.println("[STEP 7]  BEAN POST PROC BEFORE  : postProcessBeforeInitialization('" + beanName + "')");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) {
        if (isDemoBean(beanName)) {
            System.out.println("[STEP 9]  BEAN POST PROC AFTER   : postProcessAfterInitialization('" + beanName + "') — AOP proxies would be created here");
        }
        return bean;
    }

    private boolean isDemoBean(String beanName) {
        return beanName.equals("orderService") || beanName.equals("paymentService");
    }
}
