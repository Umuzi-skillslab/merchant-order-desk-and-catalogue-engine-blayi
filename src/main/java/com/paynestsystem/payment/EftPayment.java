package com.paynestsystem.payment;

public class EftPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        System.out.println("Processing EFT payment for R"
                + String.format("%.0f", amount));

        return true;
    }

    @Override
    public String getPaymentType() {
        return "EFT";
    }
}