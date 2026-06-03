package com.dtcc.automation.app;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class OrderCalculator {
    public BigDecimal calculateNetAmount(BigDecimal grossAmount, BigDecimal feeRate) {
        if (grossAmount == null || feeRate == null) throw new IllegalArgumentException("Amounts cannot be null.");
        if (grossAmount.signum() < 0) throw new IllegalArgumentException("Gross amount cannot be negative.");
        BigDecimal fee = grossAmount.multiply(feeRate).setScale(2, RoundingMode.HALF_UP);
        return grossAmount.subtract(fee).setScale(2, RoundingMode.HALF_UP);
    }

    public String routeByAmount(BigDecimal amount) {
        if (amount.compareTo(new BigDecimal("10000000")) >= 0) {
            return "PENDING_HIGH_VALUE_REVIEW";
        }
        return "PROCESSED";
    }
}
