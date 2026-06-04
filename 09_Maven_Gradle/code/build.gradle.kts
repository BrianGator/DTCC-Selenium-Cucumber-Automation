plugins {
    java
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.testng:testng:7.10.2")
    testImplementation("org.seleniumhq.selenium:selenium-java:4.21.0")
    testImplementation("io.qameta.allure:allure-testng:2.13.9")
}

tasks.test {
    useTestNG {
        suites("src/test/resources/testng-suites/allure-ui-suite.xml")
    }
    systemProperty("browser", "chrome")
    systemProperty("headless", "true")
    systemProperty("allure.results.directory", layout.buildDirectory.dir("allure-results").get().asFile.absolutePath)
}
