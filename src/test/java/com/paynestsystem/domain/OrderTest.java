package com.paynestsystem.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderTest {
    @Test
    void calculateTotalAddsAllLineSubtotals() {
        Customer customer = new Customer(1, "Mia Kaluya", "mia@example.co.za");
        Product laptop = new Product(101, "Laptop", 12000.00);
        Product mouse = new Product(102, "Wireless Mouse", 350.00);
        Order order = new Order(5001, customer);

        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        assertEquals(12700.00, order.calculateTotal());
    }

    @Test
    void addItemRejectsInvalidQuantity() {
        Customer customer = new Customer(1, "Mia Kaluya", "mia@example.co.za");
        Product laptop = new Product(101, "Laptop", 12000.00);
        Order order = new Order(5001, customer);

        assertThrows(IllegalArgumentException.class, () -> order.addItem(laptop, 0));
    }
}