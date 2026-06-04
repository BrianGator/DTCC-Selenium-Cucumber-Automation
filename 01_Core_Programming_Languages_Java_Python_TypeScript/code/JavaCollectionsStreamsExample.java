package examples.core;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class JavaCollectionsStreamsExample {
    public record TestCase(String id, String layer, String priority, boolean automated) {}

    public static List<TestCase> highPriorityAutomatedTests(List<TestCase> tests) {
        return tests.stream()
                .filter(TestCase::automated)
                .filter(test -> "P1".equalsIgnoreCase(test.priority()))
                .sorted(Comparator.comparing(TestCase::id))
                .toList();
    }

    public static Map<String, Long> countByLayer(List<TestCase> tests) {
        return tests.stream().collect(Collectors.groupingBy(TestCase::layer, Collectors.counting()));
    }

    public static void main(String[] args) {
        List<TestCase> tests = List.of(
                new TestCase("UI-001", "ui", "P1", true),
                new TestCase("API-001", "api", "P1", true),
                new TestCase("DB-001", "database", "P2", true)
        );
        System.out.println(highPriorityAutomatedTests(tests));
        System.out.println(countByLayer(tests));
    }
}
