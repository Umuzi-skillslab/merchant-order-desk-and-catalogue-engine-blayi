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

## Design notes

The project is split into small domain classes so each class has one clear responsibility.

`Product` stores catalogue information such as the product id, name, and price.

`Customer` stores the customer identity used on the order receipt.

`OrderItem` exists because an order does not just contain products. It contains products with quantities. This class links one product to one quantity and calculates the line subtotal.

`Order` owns the list of order items. Other classes can read the list, but they cannot directly change it because `getItems()` returns an unmodifiable list. This protects the order total from being changed accidentally.

`OrderService` prints the order summary. Keeping printing separate from the domain objects makes it easier to change the output later without changing the order calculation logic.

The catalogue can be extended later by adding more fields to `Product`, such as category, description, or SKU. The checkout flow would still work because `OrderItem` and `Order` only depend on the product price and name.
