package com.paynestsystem.domain;

import org.junit.jupiter.api.Test;

import com.paynestsystem.domain.OrderItem;
import com.paynestsystem.domain.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OrderItemTest {
    @Test
    void calculateTotalMultipliesProductPriceByQuantity() {
        Product product = new Product(1, "Keyboard", 450.00);
        OrderItem item = new OrderItem(product, 3);

        assertEquals(1350.00, item.calculateTotal());
    }

    @Test
    void constructorRejectsInvalidQuantity() {
        Product product = new Product(1, "Keyboard", 450.00);

        assertThrows(IllegalArgumentException.class, () -> new OrderItem(product, 0));
    }
}