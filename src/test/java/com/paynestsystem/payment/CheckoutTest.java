package com.paynestsystem.payment;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CheckoutTest {

    private Order createOrder() {
        Customer customer = new Customer(
                1,
                "Test Customer",
                "customer@example.com"
        );

        Order order = new Order(5001, customer);
        order.addItem(new Product(101, "Laptop", 12000.00), 1);
        order.addItem(new Product(102, "Wireless Mouse", 350.00), 2);

        return order;
    }

    @Test
    void checkoutWorksWithCard() {
        Order order = createOrder();

        assertTrue(order.checkout(new CardPayment()));
    }

    @Test
    void checkoutWorksWithEft() {
        Order order = createOrder();

        assertTrue(order.checkout(new EftPayment()));
    }

    @Test
    void checkoutWorksWithWallet() {
        Order order = createOrder();

        assertTrue(order.checkout(new WalletPayment(15000.00)));
    }

    @Test
    void paymentTypesHaveCorrectLabels() {
        assertEquals("CARD", new CardPayment().getPaymentType());
        assertEquals("EFT", new EftPayment().getPaymentType());
        assertEquals(
                "WALLET",
                new WalletPayment(15000.00).getPaymentType()
        );
    }

    @Test
    void paymentMethodsRejectInvalidAmounts() {
        PaymentMethod[] methods = {
                new CardPayment(),
                new EftPayment(),
                new WalletPayment(15000.00)
        };

        for (PaymentMethod method : methods) {
            assertFalse(method.processPayment(0));
            assertFalse(method.processPayment(-100));
            assertFalse(method.processPayment(Double.NaN));
            assertFalse(method.processPayment(Double.POSITIVE_INFINITY));
        }
    }

    @Test
    void emptyOrderPaymentFails() {
        Customer customer = new Customer(
                1,
                "Test Customer",
                "customer@example.com"
        );

        Order order = new Order(5001, customer);

        assertFalse(order.checkout(new CardPayment()));
    }

    @Test
    void checkoutRejectsNullPaymentMethod() {
        Order order = createOrder();

        assertThrows(
                IllegalArgumentException.class,
                () -> order.checkout(null)
        );
    }

    @Test
    void checkoutPassesExactOrderTotalToPayment() {
        Order order = createOrder();
        RecordingPayment payment = new RecordingPayment();

        assertTrue(order.checkout(payment));
        assertEquals(12700.00, payment.receivedAmount, 0.001);
        assertEquals(
                order.calculateTotal(),
                payment.receivedAmount,
                0.001
        );
        assertEquals(1, payment.calls);
    }

    @Test
    void checkoutReturnsFalseWhenPaymentDeclines() {
        Order order = createOrder();

        assertFalse(order.checkout(new DeclinedPayment()));
    }

    @Test
    void walletDeclinesOrderWhenBalanceIsTooLow() {
        Customer customer = new Customer(
                1,
                "Test Customer",
                "customer@example.com"
        );

        Order order = new Order(5002, customer);
        order.addItem(new Product(103, "Keyboard", 600.00), 1);

        WalletPayment wallet = new WalletPayment(500.00);

        assertFalse(order.checkout(wallet));
        assertEquals(500.00, wallet.getBalance(), 0.001);
    }

    @Test
    void successfulWalletPaymentReducesBalance() {
        Order order = createOrder();
        WalletPayment wallet = new WalletPayment(15000.00);

        assertTrue(order.checkout(wallet));
        assertEquals(2300.00, wallet.getBalance(), 0.001);
    }

    private static class RecordingPayment implements PaymentMethod {

        private double receivedAmount;
        private int calls;

        @Override
        public boolean processPayment(double amount) {
            receivedAmount = amount;
            calls = calls + 1;
            return true;
        }

        @Override
        public String getPaymentType() {
            return "RECORDING";
        }
    }

    private static class DeclinedPayment implements PaymentMethod {

        @Override
        public boolean processPayment(double amount) {
            return false;
        }

        @Override
        public String getPaymentType() {
            return "DECLINED";
        }
    }
}