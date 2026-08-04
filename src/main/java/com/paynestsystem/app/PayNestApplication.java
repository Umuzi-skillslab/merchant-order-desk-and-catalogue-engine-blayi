package com.paynestsystem.app;

import com.paynestsystem.domain.Customer;
import com.paynestsystem.domain.Order;
import com.paynestsystem.domain.Product;
import com.paynestsystem.service.OrderService;

public class PayNestApplication {
    public static void main(String[] args) {
        Product laptop = new Product(101, "Laptop", 12000.00);
        Product mouse = new Product(102, "Wireless Mouse", 350.00);

        Customer customer = new Customer(1, "Siya Blayi", "siyasanga.blayi@umuzi.org");

        Order order = new Order(5001, customer);
        order.addItem(laptop, 1);
        order.addItem(mouse, 2);

        OrderService orderService = new OrderService();
        orderService.printSummary(order);
    }
}