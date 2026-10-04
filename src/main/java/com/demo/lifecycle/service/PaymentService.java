package com.demo.lifecycle.service;

/**
 * A dependency bean. It is created and fully initialized BEFORE OrderService,
 * because OrderService needs it injected in Step 5.
 */
public class PaymentService {

    public PaymentService() {
        System.out.println("[STEP 4]  INSTANTIATION          : PaymentService() constructor called");
    }

    public String processPayment(double amount) {
        return "Payment of $" + amount + " processed";
    }
}
