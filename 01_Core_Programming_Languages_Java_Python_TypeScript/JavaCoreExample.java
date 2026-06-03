package examples.core;
import java.util.List;
public class JavaCoreExample { public long countProcessed(List<String> statuses) { return statuses.stream().filter("PROCESSED"::equals).count(); } }
