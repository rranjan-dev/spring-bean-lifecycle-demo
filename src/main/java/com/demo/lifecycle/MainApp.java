package com.demo.lifecycle;

import com.demo.lifecycle.config.AppConfig;
import com.demo.lifecycle.service.OrderService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * All 11 steps of the Spring bean lifecycle.
 *
 * The container is built in three calls — create, register, refresh — instead of
 * the usual one-liner, so steps 1, 2 and 3 can be seen in the right order.
 * Steps 3 to 9 all happen inside refresh().
 */
public class MainApp {

    public static void main(String[] args) {

        // ─── Step 1: IoC container created ────────────────────────────────────
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        System.out.println("[STEP 1]  CONTAINER CREATED      : new AnnotationConfigApplicationContext()");

        // ─── Step 2: Bean definitions loaded ──────────────────────────────────
        context.register(AppConfig.class);
        System.out.println("[STEP 2]  DEFINITIONS LOADED     : register(AppConfig.class), "
                + context.getBeanFactory().getSingletonCount() + " beans created so far");

        // ─── Steps 3 to 9 run inside refresh() ────────────────────────────────
        context.refresh();

        // ─── Step 10: Bean is ready ───────────────────────────────────────────
        OrderService orderService = context.getBean(OrderService.class);
        System.out.println("[STEP 10] BEAN READY             : " + orderService.placeOrder("Laptop", 999.99));

        // ─── Step 11: Destruction ─────────────────────────────────────────────
        context.close();
    }
}
