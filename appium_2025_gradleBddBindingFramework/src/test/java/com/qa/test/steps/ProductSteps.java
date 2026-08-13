package com.qa.test.steps;

import com.qa.test.implementation.Products;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Inject;

public class ProductSteps {

    Products products;

    @Inject
    public ProductSteps(Products products){
        this.products=products;
    }


    @Given("User I should select {int} index of product")
    public void user_i_should_select_index_of_product(int index) {
        products.addItemToCartByIndex(index);
    }
    @Given("user should select {int} index of product")
    public void user_should_select_index_of_product(int index) {
        products.addItemToCartByIndex(index);
    }
    @Then("User should click on cart button and {string} title dispalys")
    public void user_should_click_on_cart_button(String expectedTitle) {
        products.goToCartPage();
        Assert.assertEquals(products.getToastMessage(), expectedTitle);
    }
}
