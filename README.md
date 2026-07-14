# PayNest Capstone 1 


It demonstrates:
- Creating products
- Creating a customer
- Creating an order
- Adding order items with quantities
- Calculating line subtotals
- Calculating the grand total
- Printing an invoice summary

## How to run the tests

In the terminal, run:


mvn clean test


## How to run the demo

In the terminal, run:

mvn exec:java

### Expected Output

============================
PAYNEST INVOICE
================================
Order ID: 5001
Customer: Siya Blayi
Email :siyasanga.blayi@umuzi.org
----------------------------------
Product   Qty       Subtotal
----------------------------------
Laptop    1          R12000
Wireless Mouse     2  R700
---------------------------------
Total: R12700
================================



The program prints a PayNest invoice showing:
- Order ID
- Customer name and email
- Each product
- Quantity
- Subtotal
- Total amount

## Notes

Quantities must be greater than zero. Invalid quantities are rejected using `IllegalArgumentException`.

Order totals are calculated from the order items, where each line subtotal is:

product price * quantity
