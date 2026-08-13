package com.qa.test.steps;

import com.qa.test.implementation.Registration;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class FormFillSteps {

    private Registration registration;

    @Given("the user navigates to the formFill page")
    public void the_user_navigates_to_the_form_fill_page() {
        registration = new Registration();
    }
    @When("the user selects country as {string}")
    public void the_user_selects_country_as(String string) {
        registration.setCountrySelection(string);
    }
    @When("the user selects gender as {string}")
    public void the_user_selects_gender_as(String string) {
        registration.setGender(string);
    }
    @Given("the user enters {string}{string} in the Name field")
    public void the_user_enters_in_the_name_field(String string, String string2) {
        registration.setNameField(string);
        registration.setNameField(string2);
    }


    @Given("the user clicks on the button {string}")
    public void the_user_clicks_on_the_button(String string) {
        registration.submitButton();
    }
    @Then("the user should see the message \"\"Please enter your name\"\"")
    public void the_user_should_see_the_message_please_enter_your_name() {
//        String actualMessage = registration.getToastMessage();
//        System.out.println(actualMessage);
//        Assert.assertEquals(actualMessage, errorMsg);
    }

    @Then("the user should see the message \"\"Product\"\"")
    public void the_user_should_see_the_message_product() {
//        String title = registration.getTitle();
//        System.out.println(title);
//        Assert.assertEquals(title, pageName);
    }
}
