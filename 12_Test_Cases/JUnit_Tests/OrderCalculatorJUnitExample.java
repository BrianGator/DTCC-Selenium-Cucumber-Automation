package examples.testcases.junit;

// Source implementation: src/test/java/com/dtcc/automation/unit/OrderCalculatorTest.java
// Purpose: JUnit 5 validates small business-logic units without browser/API dependencies.
public class OrderCalculatorJUnitExample {
    public String command() { return "mvn -Dtest=OrderCalculatorTest test"; }
}
