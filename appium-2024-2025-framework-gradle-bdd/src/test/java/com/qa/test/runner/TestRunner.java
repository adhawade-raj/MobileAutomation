package com.qa.test.runner;

import com.qa.test.hooks.Hooks;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.qa.test.steps", "com.qa.test.hooks"},
        plugin = {"pretty","json:target/MyReports/report.json",
                "junit:target/MyReports/report.xml"
                },
        tags = "@Products",
        dryRun = false
)
public class TestRunner extends Hooks {

}
