package com.stv.bdd.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.stv.bdd.steps"
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeClass(alwaysRun = true)
    public void setupBaseUrl() throws IOException {
        if (System.getProperty("base.url") == null || System.getProperty("base.url").isEmpty()) {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/test/resources/config.properties");
            properties.load(fileInputStream);
            fileInputStream.close();

            String baseUrl = properties.getProperty("base.url");
            if (baseUrl != null && !baseUrl.isEmpty()) {
                System.setProperty("base.url", baseUrl);
                System.out.println("[INFO] Base URL loaded from config: " + baseUrl);
            } else {
                throw new RuntimeException("base.url is not set in config.properties");
            }
        } else {
            System.out.println("[INFO] Base URL provided via system property: " + System.getProperty("base.url"));
        }
    }
}
