package examples.reports;

import java.nio.file.Files;
import java.nio.file.Path;

public class AllureReportArtifactChecker {
    public static boolean reportPortalExists(Path projectRoot) {
        return Files.exists(projectRoot.resolve("target/report-portal/index.html"));
    }

    public static boolean allureReportExists(Path projectRoot) {
        return Files.exists(projectRoot.resolve("target/site/allure-report/index.html"));
    }

    public static boolean surefireOutputExists(Path projectRoot) {
        return Files.isDirectory(projectRoot.resolve("target/surefire-reports"));
    }

    public static void main(String[] args) {
        Path root = Path.of(args.length == 0 ? "." : args[0]);
        System.out.println("Allure: " + allureReportExists(root));
        System.out.println("Portal: " + reportPortalExists(root));
        System.out.println("Surefire: " + surefireOutputExists(root));
    }
}
