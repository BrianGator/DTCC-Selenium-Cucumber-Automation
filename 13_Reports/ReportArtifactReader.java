package examples.reports;

import java.nio.file.Files;
import java.nio.file.Path;

public class ReportArtifactReader {
    public static void main(String[] args) throws Exception {
        Path report = Path.of("13_Reports/Sample-Test-Execution-Summary.md");
        System.out.println(Files.readString(report));
    }
}
