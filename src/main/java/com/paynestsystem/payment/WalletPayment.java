package com.paynestsystem.payment;

public class WalletPayment implements PaymentMethod {

    private double balance;

    public WalletPayment(double balance) {
        if (!Double.isFinite(balance) || balance < 0) {
            throw new IllegalArgumentException(
                    "Wallet balance must be finite and non-negative."
            );
        }

        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!Double.isFinite(amount) || amount <= 0) {
            System.out.println(
                    "Wallet payment requires a valid positive amount."
            );
            return false;
        }

        if (amount > balance) {
            System.out.println(
                    "Wallet payment declined: insufficient balance."
            );
            return false;
        }

        balance = balance - amount;

        System.out.printf(
                "Processing wallet payment for R%.2f%n",
                amount
        );

        return true;
    }

    @Override
    public String getPaymentType() {
        return "WALLET";
    }
}