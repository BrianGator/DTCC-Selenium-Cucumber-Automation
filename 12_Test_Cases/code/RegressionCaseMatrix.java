package examples.testcases;

import java.util.List;

public class RegressionCaseMatrix {
    public record CaseRow(String id, String layer, String title, String priority, String expectedResult) {}

    public static List<CaseRow> buildMatrix() {
        return List.of(
                new CaseRow("UI_LOGIN_001", "UI", "Valid user login", "P1", "Catalogue is visible"),
                new CaseRow("UI_ORDER_001", "UI", "Add item to cart", "P1", "Cart count increases"),
                new CaseRow("API_CONTRACT_001", "API", "Public status contract", "P1", "Status response is valid"),
                new CaseRow("DB_ORDER_001", "DATABASE", "Processed order saved", "P1", "Order status is processed"),
                new CaseRow("REPORT_001", "REPORTING", "Allure report generated", "P2", "Report index exists")
        );
    }

    public static void main(String[] args) {
        buildMatrix().forEach(System.out::println);
    }
}
