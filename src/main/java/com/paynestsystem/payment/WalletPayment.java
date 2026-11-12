package com.paynestsystem.payment;

public class WalletPayment implements PaymentMethod {

    @Override
    public boolean processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println("Wallet payment requires a valid positive amount.");
            return false;
        }

        System.out.printf("Processing wallet payment for R%.2f%n", amount);
        return true;
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }
}