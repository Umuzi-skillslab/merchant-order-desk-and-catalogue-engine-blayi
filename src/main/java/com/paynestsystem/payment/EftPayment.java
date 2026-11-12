package com.paynestsystem.payment;

public class EftPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("EFT payment requires a valid positive amount.");
            return false;
        }

        System.out.printf("Processing EFT payment for R%.2f%n", amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "EFT";
    }
}