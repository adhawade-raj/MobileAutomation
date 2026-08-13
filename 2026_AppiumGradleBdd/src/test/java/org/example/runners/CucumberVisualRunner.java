package org.example.runners;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"org.example.steps", "org.example.hooks", "org.example.support", "org.example.percys"},
        plugin = {"pretty"},
        // Default tag to run; users can override at runtime with -Dcucumber.filter.tags
        tags = "@visual"
)
public class CucumberVisualRunner {
    // Runner for JUnit4 + Cucumber; filtered to @visual scenarios by default
}

