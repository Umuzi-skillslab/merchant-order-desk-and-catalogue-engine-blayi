package com.paynestsystem.payment;

public class CardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Card payment requires a valid positive amount.");
            return false;
        }

        System.out.printf("Processing card payment for R%.2f%n", amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}