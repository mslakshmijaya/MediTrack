package com.airtribe.meditrack.entity;

import java.time.LocalDateTime;

import com.airtribe.meditrack.interfaces.Payable;
import com.airtribe.meditrack.service.billing.BillingStrategy;
import com.airtribe.meditrack.util.IdGenerator;

public class Bill implements Payable {

    private final String billId;
    private final Patient patient;
    private final Doctor doctor;
    private final Appointment appointment;
    private final double baseAmount;
    private final LocalDateTime generatedAt;

    private BillingStrategy billingStrategy;

    public Bill(Patient patient,
                Doctor doctor,
                Appointment appointment,
                double baseAmount,
                BillingStrategy billingStrategy) {

        this.billId = IdGenerator.generateId("BILL");
        this.patient = patient;
        this.doctor = doctor;
        this.appointment = appointment;
        this.baseAmount = baseAmount;
        this.generatedAt = LocalDateTime.now();
        this.billingStrategy = billingStrategy;
    }

    public String getBillId() {
        return billId;
    }

    public double getBaseAmount() {
        return baseAmount;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

   

    public BillSummary generateSummary() {
        return new BillSummary(
                billId,
                patient.getName(),
                doctor.getName(),
                baseAmount,
                calculatePayment(),
                generatedAt
        );
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId='" + billId + '\'' +
                ", patient=" + patient.getName() +
                ", doctor=" + doctor.getName() +
                ", baseAmount=" + baseAmount +
                ", totalAmount=" + calculatePayment() +
                ", generatedAt=" + generatedAt +
                '}';
    }

	@Override
	public double calculatePayment() {
		 return billingStrategy.calculate(baseAmount);
	}
	

	
}