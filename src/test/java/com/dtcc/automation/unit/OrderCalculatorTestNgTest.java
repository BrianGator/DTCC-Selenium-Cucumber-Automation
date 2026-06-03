package com.dtcc.automation.unit;

import com.dtcc.automation.app.OrderCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class OrderCalculatorTestNgTest {
    @Test(description = "TestNG unit test validates fee calculation business logic")
    public void calculatesNetAmountUsingTestNg() {
        OrderCalculator calculator = new OrderCalculator();
        Assert.assertEquals(calculator.calculateNetAmount(new BigDecimal("1000.00"), new BigDecimal("0.010")), new BigDecimal("990.00"));
    }
}
