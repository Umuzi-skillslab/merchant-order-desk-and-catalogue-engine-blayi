package com.paynestsystem.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Order {
    private final int id;
    private final Customer customer;
    private final List<OrderItem> items;

    public Order(int id, Customer customer) {
    if (id <= 0) {
        throw new IllegalArgumentException("Order ID must be positive.");
    }

    if (customer == null) {
        throw new IllegalArgumentException("Customer cannot be null.");
    }

    this.id = id;
    this.customer = customer;
    this.items = new ArrayList<>();
}

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double calculateTotal() {
        double total = 0.0;

        for (OrderItem item : items) {
            total = total + item.calculateTotal();
        }

        return total;
    }
}