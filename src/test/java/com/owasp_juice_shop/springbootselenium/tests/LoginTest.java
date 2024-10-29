package com.owasp_juice_shop.springbootselenium.tests;

import com.owasp_juice_shop.springbootselenium.annotations.LazyAutowired;
import com.owasp_juice_shop.springbootselenium.steps.LoginSteps;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.openqa.selenium.WebDriver;

@Execution(ExecutionMode.CONCURRENT)
public class LoginTest extends BaseTest {

    @LazyAutowired
    LoginSteps loginSteps;

    @Test
    @Disabled("Todo: This test needs to be fixed.")
    public void invalidUserNameInvalidPassword() {
        System.out.println("Driver of invalidUserNameInvalidPassword test: " + applicationContext
                .getBean(WebDriver.class));

        loginSteps
                .givenIAmAtLoginPage()
                .whenILogin("dsazdovski@test.com", "11223344")
                .thenIVerifyInvalidLoginMessage(); // Verifying the unified invalid login message
    }

    @Test
    public void emptyUserEmptyPassword() {
        logger.info("Driver of emptyUserEmptyPassword test: " + applicationContext
                .getBean(WebDriver.class));

        loginSteps
                .givenIAmAtLoginPage()
                .whenILogin("", "")  // Empty username and password
                .thenIVerifyInvalidLoginMessage(); // The same error message is expected for both empty fields
    }
}
