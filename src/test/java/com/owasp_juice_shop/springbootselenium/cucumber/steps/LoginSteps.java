package com.owasp_juice_shop.springbootselenium.cucumber.steps;

import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import com.owasp_juice_shop.springbootselenium.pages.HomePage;
import com.owasp_juice_shop.springbootselenium.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginSteps {

    @LazyAutowired
    private HomePage homePage;

    @LazyAutowired
    private LoginPage loginPage;

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        homePage.goToHomePage().goToLoginPageViaAccount();
    }

    @When("I try to login with {string} and {string}")
    public void iTryToLoginWithAnd(String userName, String password) {
        loginPage.login(userName, password);
    }

    @Then("I verify invalid login message")
    public void iVerifyInvalidLoginMessage() {
        loginPage.verifyLoginErrorMessage("Invalid email or password.");
    }

    @Then("I verify successful login message")
    public void iVerifySuccessfulLoginMessage() {
        loginPage.verifySuccessfulLogin();
    }

    @Then("I verify that the login button is unclickable")
    public void iVerifyUnclickableLoginButton() {
        loginPage.verifyUnclickableLoginButton();
    }


}
