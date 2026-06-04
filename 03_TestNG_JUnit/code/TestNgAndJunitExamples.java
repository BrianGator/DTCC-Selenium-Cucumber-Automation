package examples.testngjunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNgAndJunitExamples {
    public static int calculatePassRate(int passed, int total) {
        if (total <= 0) throw new IllegalArgumentException("Total must be greater than zero");
        return (int) Math.round((passed * 100.0) / total);
    }

    @DataProvider(name = "passRateData")
    public Object[][] passRateData() {
        return new Object[][]{{86, 100, 86}, {49, 57, 86}, {10, 10, 100}};
    }

    @Test(dataProvider = "passRateData")
    public void testNgPassRateCalculation(int passed, int total, int expected) {
        Assert.assertEquals(calculatePassRate(passed, total), expected);
    }

    @org.junit.jupiter.api.Test
    @DisplayName("JUnit validates pass rate guardrail")
    public void junitPassRateCalculation() {
        Assertions.assertEquals(86, calculatePassRate(49, 57));
    }
}
