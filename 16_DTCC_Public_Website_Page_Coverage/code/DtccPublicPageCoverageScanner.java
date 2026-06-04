package examples.publicsite;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;
import java.util.List;

public class DtccPublicPageCoverageScanner {
    private final HttpClient client = HttpClient.newBuilder()
            .connectTimeout(Duration.ofSeconds(10))
            .followRedirects(HttpClient.Redirect.NORMAL)
            .build();

    public record PageCheck(String name, String url, int statusCode, boolean passed) {}

    public List<String> priorityUrls() {
        return List.of(
                "https://www.dtcc.com/",
                "https://www.dtcc.com/client-center",
                "https://www.dtcc.com/legal",
                "https://www.dtcc.com/news",
                "https://www.dtcc.com/products"
        );
    }

    public PageCheck check(String name, String url) throws Exception {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url))
                .timeout(Duration.ofSeconds(15))
                .GET()
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        boolean ok = response.statusCode() >= 200 && response.statusCode() < 400 && response.body().length() > 100;
        return new PageCheck(name, url, response.statusCode(), ok);
    }

    public static void main(String[] args) throws Exception {
        DtccPublicPageCoverageScanner scanner = new DtccPublicPageCoverageScanner();
        for (String url : scanner.priorityUrls()) {
            System.out.println(scanner.check(url, url));
        }
    }
}
