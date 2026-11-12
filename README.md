# PayNest 

This project builds on Capstone 1 by adding simulated card, EFT and wallet payments.

This uses Java 21, Maven and JUnit 5.

## What the application does

- Creates products and a customer.
- Creates an order and adds products with quantities.
- Calculates each line subtotal and the grand total.
- Prints an invoice.
- Processes payment through a shared payment interface.
- Reports whether checkout succeeded or failed.

No real money moves. Payments are simulated.

## Project structure

- domain: Product, Customer, OrderItem, and Order.
- payment: PaymentMethod, CardPayment, EftPayment,
  WalletPayment, and PaymentProcessor.
- service: OrderService prints the invoice.
- app: PayNestApplication creates the objects and runs the demo.

Tests are stored under src/test/java.

## How totals work

Each line subtotal is:

unit price × quantity

The order total is the sum of all line subtotals.

The demo contains:

- One laptop at R12000.
- Two wireless mice at R350 each.
- Grand total: R12700.

## How checkout works

Order.checkout accepts a PaymentMethod.

It calculates the order total once and passes that amount to
PaymentProcessor. The processor calls the selected payment method.

If payment returns true, checkout reports order completion.
If payment returns false, checkout reports failure.

## Why use an interface?

PaymentMethod gives all payment options the same contract:
processPayment(double amount) and getPaymentType().

Checkout can use card, EFT, or wallet through that interface.
Each payment class supplies its own behaviour and label.

Adding another payment option requires a new implementation and
application wiring. The existing checkout code and order total
calculation do not need to change.

## Validation and assumptions

- Order IDs must be positive.
- An order must have a customer.
- An order item must have a product.
- Quantities must be greater than zero.
- The order item list cannot be changed directly by outside code.
- Payment amounts must be finite and greater than zero.
- The supplied payment methods simulate success for valid amounts.
- Empty orders cannot complete payment.
- A null payment method causes IllegalArgumentException.
- Prices and totals use double, matching the starter contract.
- Amounts are displayed with two decimal places.
- The decimal separator follows the computer's locale.
- Checkout does not store payment history or prevent repeat attempts.
- Refunds are outside this implementation.

## Run the tests

Open the terminal in the project folder containing pom.xml:

# powershell
mvn test

This project currently contains 18 tests covering:

- Line subtotals and order totals.
- Invalid quantities and null inputs.
- Empty orders and protected order items.
- Checkout with card, EFT, and wallet.
- Payment type labels and invalid payment amounts.
- Passing the exact order total to payment once.
- Returning failure when a payment is declined.

## HoW to Run the application

# powershell
mvn compile exec:java

This application prints the invoice followed by:

# text
Processing card payment for R12700,00
Payment successful via CARD
Amount: R12700,00
Order 5001 completed successfully.

Computers using a decimal point display R12700.00 instead.

## Try another payment method

This default demo uses CardPayment.

For EFT, replace the CardPayment import in PayNestApplication with:

-java
import com.paynestsystem.payment.EftPayment

Then change the payment construction to:

PaymentMethod paymentMethod = new EftPayment();

For wallet use:

import com.paynestsystem.payment.WalletPayment;

PaymentMethod paymentMethod = new WalletPayment();

Keep this checkout call unchanged:

order.checkout(paymentMethod);

You may run the application again to see the selected payment method.