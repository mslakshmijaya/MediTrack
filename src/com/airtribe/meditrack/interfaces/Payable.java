package com.airtribe.meditrack.interfaces;

public interface Payable {
    double calculatePayment();

    // ✅ Default method for printing payment info
    default void printPaymentInfo() {
        System.out.println("Payment due: " + calculatePayment());
    }

}
