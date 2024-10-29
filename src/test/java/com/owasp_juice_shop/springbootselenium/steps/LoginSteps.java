package com.owasp_juice_shop.springbootselenium.steps;

import com.owasp_juice_shop.springbootselenium.annotations.ElapsedTime;
import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import com.owasp_juice_shop.springbootselenium.annotations.LazyComponent;
import com.owasp_juice_shop.springbootselenium.annotations.TakeScreenshot;
import com.owasp_juice_shop.springbootselenium.pages.HomePage;
import com.owasp_juice_shop.springbootselenium.pages.LoginPage;
import org.springframework.beans.factory.annotation.Value;

@LazyComponent
public class LoginSteps {

    @Value("${browser}")
    private String browser;

    @LazyAutowired
    HomePage homePage;

    @LazyAutowired
    LoginPage loginPage;

    // Navigate to the login page
    public LoginSteps givenIAmAtLoginPage() {
        homePage.goToHomePage().goToLoginPageViaAccount();
        return this;
    }

    // Perform login action
    @ElapsedTime
    public LoginSteps whenILogin(String userName, String password) {
        loginPage.login(userName, password);
        return this;
    }

    // Take a screenshot and verify the universal error message for invalid login
    @TakeScreenshot
    public LoginSteps thenIVerifyInvalidLoginMessage() {
        // Using the single universal error message for both username and password issues
        loginPage.verifyLoginErrorMessage("Invalid username or password.");
        return this;
    }

    // Take a screenshot and verify successful login

    public LoginSteps thenIVerifySuccessfulLogin() {
        loginPage.verifySuccessfulLogin();
        return this;
    }
}
