package com.dtcc.automation.regression;

import com.dtcc.automation.app.OrderCalculator;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.math.BigDecimal;

public class RegressionSmokeTest {
    @Test(description = "Smoke regression confirms normal orders still route to processed state")
    public void normalAmountStillRoutesToProcessed() {
        OrderCalculator calculator = new OrderCalculator();
        Assert.assertEquals(calculator.routeByAmount(new BigDecimal("9999999.99")), "PROCESSED");
    }

    @Test(description = "Smoke regression confirms high-value orders still route to review")
    public void highValueAmountRoutesToReview() {
        OrderCalculator calculator = new OrderCalculator();
        Assert.assertEquals(calculator.routeByAmount(new BigDecimal("10000000.00")), "PENDING_HIGH_VALUE_REVIEW");
    }
}
