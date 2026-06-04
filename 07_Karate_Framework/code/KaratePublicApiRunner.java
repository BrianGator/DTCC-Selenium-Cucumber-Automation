package examples.karate;

import com.intuit.karate.junit5.Karate;

public class KaratePublicApiRunner {
    @Karate.Test
    Karate runPublicApiContractTests() {
        return Karate.run("features/PublicStatusContract.feature").relativeTo(getClass());
    }
}
