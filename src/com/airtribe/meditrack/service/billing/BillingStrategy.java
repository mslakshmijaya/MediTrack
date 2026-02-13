package com.airtribe.meditrack.service.billing;

public interface BillingStrategy {
	double calculate(double baseAmount);
}
