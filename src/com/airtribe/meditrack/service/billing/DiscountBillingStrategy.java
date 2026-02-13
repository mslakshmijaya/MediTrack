package com.airtribe.meditrack.service.billing;

import com.airtribe.meditrack.constants.Constants;

public class DiscountBillingStrategy implements BillingStrategy {

    private final double discountPercentage;

    public DiscountBillingStrategy(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double calculate(double baseAmount) {
        double discounted = baseAmount - (baseAmount * discountPercentage);
        return discounted + (discounted * Constants.TAX_RATE);
    }
}