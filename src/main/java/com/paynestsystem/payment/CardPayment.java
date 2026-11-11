package com.paynestsystem.payment;

public class CardPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing card payment for R"
                + String.format("%.0f", amount));

        return true;
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}