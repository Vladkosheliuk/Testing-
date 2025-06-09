package com.stv.bdd.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features/defect",
        glue = {"com.stv.bdd.steps.defect"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class DefectTestRunner {
}
