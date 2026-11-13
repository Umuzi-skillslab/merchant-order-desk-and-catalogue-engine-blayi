package com.paynestsystem.payment;

public class PaymentProcessor {

    public boolean processPayment(PaymentMethod method, double amount) {
        if (method == null) {
            throw new IllegalArgumentException("Payment method cannot be null.");
        }

        boolean success = method.processPayment(amount);

        if (success) {
            System.out.println("Payment successful via "
                    + method.getPaymentType());
            System.out.printf("Amount: R%.2f%n", amount);
        } else {
            System.out.println("Payment failed via "
                    + method.getPaymentType());
        }

        return success;
    }
}