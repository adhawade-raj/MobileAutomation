package org.example.steps;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.percys.PercyHelper;
import org.example.support.DriverHolder;

public class VisualSteps {

    private final DriverHolder holder;

    public VisualSteps(DriverHolder holder) {
        this.holder = holder;
    }

    @Given("the app is launched")
    public void the_app_is_launched() {
        // Hook has already launched the app in TestHooks
        System.out.println("[VisualSteps] App is launched and ready for visual testing");

        if (holder.driver == null) {
            throw new IllegalStateException("Driver not initialized - app failed to launch");
        }

        System.out.println("[VisualSteps] Driver successfully initialized");
    }

    @When("I take a Percy snapshot {string}")
    public void i_take_a_percy_snapshot(String name) {
        System.out.println("[VisualSteps] Starting Percy snapshot: " + name);

        AndroidDriver driver = holder.driver;
        if (driver == null) {
            throw new IllegalStateException("Driver not initialized - cannot take screenshot");
        }

        try {
            PercyHelper.snapshot(driver, name);
            System.out.println("[VisualSteps] ✓ Percy snapshot completed for: " + name);
        } catch (Exception e) {
            System.err.println("[VisualSteps] ✗ Error taking Percy snapshot: " + e.getMessage());
            throw new RuntimeException("Failed to take Percy snapshot: " + name, e);
        }
    }

    @Then("the test completes")
    public void the_test_completes() {
        System.out.println("[VisualSteps] Test completed successfully");
        // Success criteria handled by Percy or visual diffing in CI
    }
}

