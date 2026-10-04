package com.demo.lifecycle.processor;

import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

/**
 * Step 3 — runs once, after all bean definitions are loaded but before any bean
 * is instantiated. It sees metadata only, never objects.
 */
public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        System.out.println("[STEP 3]  BEAN FACTORY POST PROC : postProcessBeanFactory(), "
                + beanFactory.getBeanDefinitionCount() + " definitions, 0 beans created so far");

        // Definitions can still be modified here.
        beanFactory.getBeanDefinition("orderService").setLazyInit(false);
        System.out.println("[STEP 3]  BEAN FACTORY POST PROC : modified orderService definition (lazyInit = false)");
    }
}
