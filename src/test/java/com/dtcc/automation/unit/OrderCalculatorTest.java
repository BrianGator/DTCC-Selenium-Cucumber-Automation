package com.dtcc.automation.unit;

import com.dtcc.automation.app.OrderCalculator;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class OrderCalculatorTest {
    private final OrderCalculator calculator = new OrderCalculator();

    @Test
    void calculatesNetAmountAfterFee() {
        BigDecimal net = calculator.calculateNetAmount(new BigDecimal("1000.00"), new BigDecimal("0.025"));
        assertEquals(new BigDecimal("975.00"), net);
    }

    @Test
    void rejectsNegativeGrossAmount() {
        assertThrows(IllegalArgumentException.class, () -> calculator.calculateNetAmount(new BigDecimal("-1.00"), new BigDecimal("0.025")));
    }

    @Test
    void routesHighValueOrdersToReview() {
        assertEquals("PENDING_HIGH_VALUE_REVIEW", calculator.routeByAmount(new BigDecimal("10000000")));
    }
}
